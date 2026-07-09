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

public class SetSpectate implements CommandExecutor {

    private final FakeDeathBan plugin;

    public SetSpectate(FakeDeathBan plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String[] args) {

        if (args.length < 1) {
            sender.sendMessage(FakeDeathBan.prefix + ChatColor.RED + "/setspectate <player>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            sender.sendMessage(FakeDeathBan.prefix + ChatColor.RED + Messages.getMessage("player-not-found", args[0]));
            return true;
        }

        plugin.getConfig().set("default-spectator", target.getName());
        plugin.saveConfig();

        sender.sendMessage(FakeDeathBan.prefix + ChatColor.GREEN + Messages.getMessage("sets-s", target.getName()));

        return true;
    }
}