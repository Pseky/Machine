package me.pesekjak.machine.network.packets.out;

import lombok.Getter;
import lombok.Setter;
import me.pesekjak.machine.network.packets.PacketOut;
import me.pesekjak.machine.utils.FriendlyByteBuf;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;

public class PacketLoginDisconnect extends PacketOut {

    public static final int ID = 0x00;

    static {
        PacketOut.register(PacketLoginDisconnect.class, ID, PacketState.LOGIN_OUT,
                PacketLoginDisconnect::new
        );
    }

    @Getter @Setter @NotNull
    private Component message;

    public PacketLoginDisconnect(FriendlyByteBuf buf) {
        message = GsonComponentSerializer.gson().deserialize(buf.readString(StandardCharsets.UTF_8));
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public byte[] serialize() {
        return new FriendlyByteBuf()
                .writeString(GsonComponentSerializer.gson().serialize(message), StandardCharsets.UTF_8)
                .bytes();
    }

    @Override
    public PacketOut clone() {
        return new PacketLoginDisconnect(new FriendlyByteBuf(serialize()));
    }

}
