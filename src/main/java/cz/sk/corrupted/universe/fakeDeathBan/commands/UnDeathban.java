package cz.sk.corrupted.universe.fakeDeathBan.commands;

import cz.sk.corrupted.universe.fakeDeathBan.FakeDeathBan;
import cz.sk.corrupted.universe.fakeDeathBan.files.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class UnDeathban implements CommandExecutor {

    private final FakeDeathBan plugin;

    public UnDeathban(FakeDeathBan plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NonNull CommandSender commandSender,@NonNull Command command,@NonNull String s, String @NonNull [] strings) {

        if (!(commandSender instanceof Player p)) {
            commandSender.sendMessage(FakeDeathBan.prefix + ChatColor.RED + Messages.getMessage("con-com-not-allowed"));
            return true;
        }

        List<String> deathbanned = plugin.getConfig().getStringList("deathbanned");

        if (strings.length == 0) {

            for (Player player : Bukkit.getOnlinePlayers()) {
                if (deathbanned.remove(player.getUniqueId().toString())) {
                    player.teleport(p.getLocation());
                    player.setGameMode(GameMode.ADVENTURE);
                }
            }

            plugin.getConfig().set("deathbanned", deathbanned);
            plugin.saveConfig();

            p.sendMessage(FakeDeathBan.prefix + ChatColor.GREEN + Messages.getMessage("all-undeathbanned"));
            return true;
        }

        for (String arg : strings) {

            Player target = Bukkit.getPlayer(arg);

            if (target == null) {
                p.sendMessage(FakeDeathBan.prefix + ChatColor.RED + Messages.getMessage("player-not-found", arg));
                continue;
            }

            if (deathbanned.remove(target.getUniqueId().toString())) {
                target.teleport(p.getLocation());
                target.setGameMode(GameMode.ADVENTURE);

                p.sendMessage(FakeDeathBan.prefix + ChatColor.GREEN + Messages.getMessage("specific-undeathbanned"), target.getName());
            } else {
                p.sendMessage(FakeDeathBan.prefix + ChatColor.YELLOW + Messages.getMessage("already-alive", target.getName()));
            }
        }

        plugin.getConfig().set("deathbanned", deathbanned);
        plugin.saveConfig();

        return true;
    }
}