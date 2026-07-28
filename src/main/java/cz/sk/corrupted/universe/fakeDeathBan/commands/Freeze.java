package cz.sk.corrupted.universe.fakeDeathBan.commands;

import cz.sk.corrupted.universe.fakeDeathBan.FakeDeathBan;
import cz.sk.corrupted.universe.fakeDeathBan.other.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NonNull;

public class Freeze implements CommandExecutor {

    private final FakeDeathBan plugin;

    public Freeze(FakeDeathBan plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {

        if (args.length == 0) {

            for (Player player : Bukkit.getOnlinePlayers()) {
                String uuid = player.getUniqueId().toString();

                if (FakeDeathBan.deathbanned.contains(uuid) && !FakeDeathBan.frozen.contains(uuid) && !player.hasPermission("fakedeathban.bypass.freeze")) {
                    FakeDeathBan.frozen.add(uuid);
                }
            }

            plugin.saveFrozen();

            sender.sendMessage(FakeDeathBan.prefix + ChatColor.GREEN + Messages.getMessage("freeze-1-s"));
            return true;
        }

        for (String arg : args) {
            Player target = Bukkit.getPlayer(arg);

            if (target == null) {
                sender.sendMessage(FakeDeathBan.prefix + ChatColor.RED + Messages.getMessage("player-not-found", arg));
                continue;
            }

            if (target.hasPermission("fakedeathban.bypass.freeze")){
                sender.sendMessage(FakeDeathBan.prefix + ChatColor.RED + Messages.getMessage("p-immune", arg));
                continue;
            }
            String uuid = target.getUniqueId().toString();

            if (!FakeDeathBan.deathbanned.contains(uuid)) {
                sender.sendMessage(FakeDeathBan.prefix + ChatColor.RED + Messages.getMessage("p-db-f", target.getName()));
                continue;
            }

            if (!FakeDeathBan.frozen.contains(uuid)) {
                FakeDeathBan.frozen.add(uuid);
                sender.sendMessage(FakeDeathBan.prefix + ChatColor.GREEN + Messages.getMessage("freeze-2-s", target.getName()));
            } else {
                sender.sendMessage(FakeDeathBan.prefix + ChatColor.YELLOW + Messages.getMessage("freeze-1-f", target.getName()));
            }
        }

        plugin.saveFrozen();
        return true;
    }
}
