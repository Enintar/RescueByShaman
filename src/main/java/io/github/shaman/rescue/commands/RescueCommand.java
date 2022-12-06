package io.github.shaman.rescue.commands;

import io.github.shaman.rescue.Rescue;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getServer;

public class RescueCommand implements CommandExecutor {
    public static final String COMMAND_CONSOLE = ChatColor.translateAlternateColorCodes('&', "&cRescue &7| &fДанную команду можно выполнять только с &cконсоли&f!");
    public static final String COMMAND_USAGE = ChatColor.translateAlternateColorCodes('&', "&cRescue &7| &fИспользуйте: &c/rescue [ник]");
    public static final String COMMAND_SUCCESS_ONLINE = ChatColor.translateAlternateColorCodes('&', "&cRescue &7| &fВы успешно телепортировали &c%s &fна спавн!");
    public static final String COMMAND_NO_PLAYER = ChatColor.translateAlternateColorCodes('&', "&cRescue &7| &fИгрок с ником &c%s &fне существует!");
    public static final String COMMAND_ALREADY = ChatColor.translateAlternateColorCodes('&', "&cRescue &7| &fИгрок с ником &c%s &fуже есть в очереди!");
    public static final String COMMAND_SUCCESS_OFFLINE = ChatColor.translateAlternateColorCodes('&', "&cRescue &7| &fВы успешно добавили &c%s &fв очередь!");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Rescue rescue = Rescue.getInstance();
        if (sender instanceof Player && rescue.isConsoleOnly()) {
            sender.sendMessage(COMMAND_CONSOLE);
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(COMMAND_USAGE);
            return true;
        }
        OfflinePlayer a = getServer().getOfflinePlayer(args[0]);
        if (a.isOnline()) {
            rescue.spawnTeleport(a.getPlayer());
            sender.sendMessage(String.format(COMMAND_SUCCESS_ONLINE, a.getName()));
            return true;
        }
        if (!a.hasPlayedBefore()) {
            sender.sendMessage(String.format(COMMAND_NO_PLAYER, args[0]));
            return true;
        }
        if (rescue.getTeleportNames().add(a.getName())) {
            sender.sendMessage(String.format(COMMAND_SUCCESS_OFFLINE, a.getName()));
        } else {
            sender.sendMessage(String.format(COMMAND_ALREADY, a.getName()));
        }
        return true;
    }
}