package shaman.rescue;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import shaman.rescue.RescueCommand.CommandEx;

import java.util.HashSet;

public final class Rescue extends JavaPlugin implements Listener {
    FileConfiguration config = this.getConfig();
    public Location s;
    public HashSet<String> flourmastercaban = new HashSet<String>();

    @Override
    public void onEnable() {
        this.getCommand("rescue").setExecutor(new CommandEx());
        PluginManager plugin = getServer().getPluginManager();
        plugin.registerEvents(this,this);
        config.addDefault("CommandOnlyC", false);
        config.addDefault("CommandX", 0.5);
        config.addDefault("CommandY", 0.5);
        config.addDefault("CommandZ", 0.5);
        config.addDefault("CommandYAW", 0);
        config.addDefault("CommandPITCH", 0);
        config.addDefault("CommandWorldName", "world");
        s = new Location(getServer().getWorld(config.getString("CommandWorldName")), config.getInt("CommandX"),config.getInt("CommandY"),config.getInt("CommandZ"),config.getInt("CommandYAW"),config.getInt("CommandPITCH"));
        this.saveDefaultConfig();

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if(flourmastercaban.contains(e.getPlayer().getName())) {
            flourmastercaban.remove(e.getPlayer().getName());
            Player p = e.getPlayer();
            p.teleport(s);
            p.setLastDamage(0);
            p.sendTitle("Вы были телепортированы на спавн!".replace("&", "§"),"&cУдачной игры!".replace("&", "§"));
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
