package me.pesekjak.machine.network.packets.in.status;

import lombok.AllArgsConstructor;
import lombok.ToString;
import me.pesekjak.machine.network.packets.PacketIn;
import me.pesekjak.machine.utils.FriendlyByteBuf;

@AllArgsConstructor
@ToString
public class PacketStatusInRequest extends PacketIn {

    private static final int ID = 0x00;

    static {
        register(PacketStatusInRequest.class, ID, PacketState.STATUS_IN,
                PacketStatusInRequest::new
        );
    }

    public PacketStatusInRequest(FriendlyByteBuf buf) {

    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public byte[] serialize() {
        return new byte[1];
    }

    @Override
    public PacketIn clone() {
        return new PacketStatusInRequest(new FriendlyByteBuf(serialize()));
    }

}