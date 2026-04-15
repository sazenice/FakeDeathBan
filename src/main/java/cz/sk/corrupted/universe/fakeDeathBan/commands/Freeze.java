package cz.sk.corrupted.universe.fakeDeathBan.commands;

import cz.sk.corrupted.universe.fakeDeathBan.FakeDeathBan;
import cz.sk.corrupted.universe.fakeDeathBan.files.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class Freeze implements CommandExecutor {

    private final FakeDeathBan plugin;

    public Freeze(FakeDeathBan plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {

        List<String> deathbanned = plugin.getConfig().getStringList("deathbanned");
        List<String> frozen = plugin.getConfig().getStringList("frozen");

        if (args.length == 0) {
            int count = 0;
            for (Player player : Bukkit.getOnlinePlayers()) {
                String uuid = player.getUniqueId().toString();
                if (deathbanned.contains(uuid) && !frozen.contains(uuid)) {
                    frozen.add(uuid);
                    count++;
                }
            }
            plugin.getConfig().set("frozen", frozen);
            plugin.saveConfig();

            sender.sendMessage(FakeDeathBan.prefix + ChatColor.GREEN + Messages.getMessage("freeze-1-s"));
            return true;
        }

        for (String arg : args) {
            Player target = Bukkit.getPlayer(arg);
            if (target == null) {
                sender.sendMessage(FakeDeathBan.prefix + ChatColor.RED +
                        Messages.getMessage("p-f", arg));
                continue;
            }

            String uuid = target.getUniqueId().toString();

            if (!deathbanned.contains(uuid)) {
                sender.sendMessage(FakeDeathBan.prefix + ChatColor.RED +
                        Messages.getMessage("p-db-f", target.getName()));
                continue;
            }

            if (!frozen.contains(uuid)) {
                frozen.add(uuid);
                sender.sendMessage(FakeDeathBan.prefix + ChatColor.GREEN +
                        Messages.getMessage("freeze-2-s", target.getName()));
            } else {
                sender.sendMessage(FakeDeathBan.prefix + ChatColor.YELLOW + Messages.getMessage("freeze-1-f", target.getName()));
            }
        }

        plugin.getConfig().set("frozen", frozen);
        plugin.saveConfig();
        return true;
    }
}