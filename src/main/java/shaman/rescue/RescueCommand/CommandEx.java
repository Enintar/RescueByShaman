package shaman.rescue.RescueCommand;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import shaman.rescue.Rescue;

import static org.bukkit.Bukkit.getServer;

public class CommandEx implements org.bukkit.command.CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (JavaPlugin.getPlugin(Rescue.class).getConfig().getBoolean("CommandOnlyC")) {
            if ((sender instanceof Player)) {
                sender.sendMessage("&cRescue &7| &fДанную команду можно выполнять только с &cконсоли".replace("&", "§"));
            } else {
                if (args.length == 0) {
                    sender.sendMessage("&cRescue &7| &fИспользуйте: &c/rescue [ник]".replace("&", "§"));
                } else {
                    OfflinePlayer a = getServer().getOfflinePlayer(args[0]);
                    if (a.isOnline()) {
                        a.getPlayer().teleport(JavaPlugin.getPlugin(Rescue.class).s);
                        a.getPlayer().sendTitle("Вы были телепортированы на спавн!".replace("&", "§"), "&cУдачной игры!".replace("&", "§"));
                        sender.sendMessage("&cRescue &7| &fВы успешно телепортировали &c".replace("&", "§") + a.getPlayer().getDisplayName() + "&f на спавн!".replace("&", "§"));
                    } else {
                        if (JavaPlugin.getPlugin(Rescue.class).flourmastercaban.contains(a.getName())) {
                            sender.sendMessage("&cRescue &7| &fИгрок с ником&c ".replace("&", "§") + a.getName() + " &fуже есть в очереди!".replace("&", "§"));
                        } else {
                            if (a.hasPlayedBefore()) {
                                JavaPlugin.getPlugin(Rescue.class).flourmastercaban.add(a.getName());
                                sender.sendMessage("&cRescue &7| &fВы успешно добавили &c".replace("&", "§") + a.getName() + "&f в очередь!".replace("&", "§"));
                            } else {
                                sender.sendMessage("&cRescue &7| &fИгрок с ником&c ".replace("&", "§") + args[0] + " &fне существует!".replace("&", "§"));
                            }
                        }
                    }
                }
            }
        }
        if (!JavaPlugin.getPlugin(Rescue.class).getConfig().getBoolean("CommandOnlyC")) {
            if (args.length == 0) {
                sender.sendMessage("&cRescue &7| &fИспользуйте: &c/rescue [ник]".replace("&", "§"));
            } else {
                OfflinePlayer a = getServer().getOfflinePlayer(args[0]);
                if (a.isOnline()) {
                    a.getPlayer().teleport(JavaPlugin.getPlugin(Rescue.class).s);
                    a.getPlayer().sendTitle("Вы были телепортированы на спавн!".replace("&", "§"), "&cУдачной игры!".replace("&", "§"));
                    sender.sendMessage("&cRescue &7| &fВы успешно телепортировали &c".replace("&", "§") + a.getPlayer().getDisplayName() + "&f на спавн!".replace("&", "§"));
                } else {
                    if (JavaPlugin.getPlugin(Rescue.class).flourmastercaban.contains(a.getName())) {
                        sender.sendMessage("&cRescue &7| &fИгрок с ником&c ".replace("&", "§") + a.getName() + " &fуже есть в очереди!".replace("&", "§"));
                    } else {
                        if (a.hasPlayedBefore()) {
                            JavaPlugin.getPlugin(Rescue.class).flourmastercaban.add(a.getName());
                            sender.sendMessage("&cRescue &7| &fВы успешно добавили &c".replace("&", "§") + a.getName() + "&f в очередь!".replace("&", "§"));
                        } else {
                            sender.sendMessage("&cRescue &7| &fИгрок с ником&c ".replace("&", "§") + args[0] + " &fне существует!".replace("&", "§"));
                        }
                    }
                }
            }
        }
        return false;
    }
}