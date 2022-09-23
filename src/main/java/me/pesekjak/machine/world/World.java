package me.pesekjak.machine.world;

import lombok.Builder;
import lombok.Getter;
import me.pesekjak.machine.entities.Entity;
import me.pesekjak.machine.entities.Player;
import me.pesekjak.machine.network.packets.PacketOut;
import me.pesekjak.machine.network.packets.out.PacketPlayOutChangeDifficulty;
import me.pesekjak.machine.network.packets.out.PacketPlayOutWorldSpawnPosition;
import me.pesekjak.machine.utils.NamespacedKey;
import me.pesekjak.machine.world.dimensions.DimensionType;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Builder
public class World {

    public static final World MAIN = World.builder()
            .name(NamespacedKey.machine("main"))
            .dimensionType(DimensionType.OVERWORLD)
            .seed(1)
            .difficulty(Difficulty.DEFAULT_DIFFICULTY)
            .build();

    @Getter
    private final NamespacedKey name;
    @Getter
    private final DimensionType dimensionType;
    @Getter
    private final List<Entity> entityList = new CopyOnWriteArrayList<>();
    @Getter
    private final long seed;
    @Getter
    private Difficulty difficulty;
    @Getter
    private Location worldSpawn;

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        PacketOut packet = new PacketPlayOutChangeDifficulty(difficulty);
        for (Entity entity : entityList) {
            if (!(entity instanceof Player player))
                continue;
            try {
                player.getConnection().sendPacket(packet);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setWorldSpawn(Location location) {
        this.worldSpawn = location;
        PacketOut packet = new PacketPlayOutWorldSpawnPosition(location);
        for (Entity entity : entityList) {
            if (!(entity instanceof Player player))
                continue;
            try {
                player.getConnection().sendPacket(packet);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
