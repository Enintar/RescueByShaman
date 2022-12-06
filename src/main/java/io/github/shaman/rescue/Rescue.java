package io.github.shaman.rescue;

import io.github.shaman.rescue.commands.RescueCommand;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public final class Rescue extends JavaPlugin implements Listener {
    // Constants
    public static final String TELEPORT_TITLE = ChatColor.translateAlternateColorCodes('&', "&fВы были телепортированы на спавн!");
    public static final String TELEPORT_SUBTITLE = ChatColor.translateAlternateColorCodes('&', "&cУдачной игры!");

    // Plugin variables (Runtime variables)
    private static Rescue instance;
    private final Set<String> teleportNames = new HashSet<>();

    // Config variables
    private boolean consoleOnly;
    private Location spawnLocation;

    @Override
    public void onEnable() {
        instance = this;

        getCommand("rescue").setExecutor(new RescueCommand());
        getServer().getPluginManager().registerEvents(this, this);

        Configuration config = getConfig();
        saveDefaultConfig();
        consoleOnly = getConfig().getBoolean("consoleOnly", false);
        spawnLocation = new Location(getServer().getWorld(config.getString("spawnWorld", "world")),
                config.getDouble("spawnX", 0D), config.getDouble("spawnY", 0D),
                config.getDouble("spawnZ", 0D), (float) config.getDouble("spawnYaw", 0D),
                (float) config.getDouble("spawnPitch", 0D));
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (teleportNames.remove(p.getName())) {
            spawnTeleport(p);
        }
    }

    public void spawnTeleport(Player player) {
        player.teleport(spawnLocation);
        player.setLastDamage(0);
        player.sendTitle(TELEPORT_TITLE, TELEPORT_SUBTITLE, 20, 50, 20);
    }

    public static Rescue getInstance() {
        return instance;
    }

    public Set<String> getTeleportNames() {
        return teleportNames;
    }

    public boolean isConsoleOnly() {
        return consoleOnly;
    }
}
