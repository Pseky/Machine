package me.pesekjak.machine.network.packets.out.play;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.pesekjak.machine.network.packets.PacketOut;
import me.pesekjak.machine.utils.FriendlyByteBuf;

@AllArgsConstructor
@ToString
public class PacketPlayOutCenterChunk extends PacketOut {

    private static final int ID = 0x4B;

    @Getter @Setter
    private int chunkX, chunkZ;

    static {
        register(PacketPlayOutCenterChunk.class, ID, PacketState.PLAY_OUT,
                PacketPlayOutCenterChunk::new);
    }

    public PacketPlayOutCenterChunk(FriendlyByteBuf buf) {
        chunkX = buf.readVarInt();
        chunkZ = buf.readVarInt();
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public byte[] serialize() {
        return new FriendlyByteBuf()
                .writeVarInt(chunkX)
                .writeVarInt(chunkZ)
                .bytes();
    }

    @Override
    public PacketOut clone() {
        return new PacketPlayOutCenterChunk(new FriendlyByteBuf(serialize()));
    }

}