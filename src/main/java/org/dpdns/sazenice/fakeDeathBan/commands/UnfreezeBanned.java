package org.dpdns.sazenice.fakeDeathBan.commands;

import org.dpdns.sazenice.fakeDeathBan.FakeDeathBan;
import org.dpdns.sazenice.fakeDeathBan.other.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NonNull;

public class UnfreezeBanned implements CommandExecutor {

    private final FakeDeathBan plugin;

    public UnfreezeBanned(FakeDeathBan plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {

        if (args.length == 0) {

            for (Player player : Bukkit.getOnlinePlayers()) {
                FakeDeathBan.frozen.remove(player.getUniqueId().toString());
            }
            sender.sendMessage(FakeDeathBan.prefix + ChatColor.GREEN + Messages.getMessage("unfreeze-1-s"));
        } else {

            for (String arg : args) {
                Player target = Bukkit.getPlayer(arg);

                if (target == null) {
                    sender.sendMessage(FakeDeathBan.prefix + ChatColor.RED + Messages.getMessage("player-not-found", arg));
                    continue;
                }
                String uuid = target.getUniqueId().toString();

                if (FakeDeathBan.frozen.remove(uuid)) {
                    sender.sendMessage(FakeDeathBan.prefix + ChatColor.GREEN + Messages.getMessage("unfreeze-2-s", target.getName()));
                } else {
                    sender.sendMessage(FakeDeathBan.prefix + ChatColor.YELLOW + Messages.getMessage("unfreeze-1-f", target.getName()));
                }
            }
        }

        plugin.saveFrozen();
        return true;
    }
}
