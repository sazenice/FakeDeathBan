package cz.sk.corrupted.universe.fakeDeathBan.commands;

import cz.sk.corrupted.universe.fakeDeathBan.FakeDeathBan;
import cz.sk.corrupted.universe.fakeDeathBan.files.Messages;
import org.bukkit.*;
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
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        List<String> deathbanned = plugin.getConfig().getStringList("deathbanned");
        List<String> frozen = plugin.getConfig().getStringList("frozen");

        String soundString = plugin.getConfig().getString("death-sound");
        NamespacedKey key = NamespacedKey.fromString(soundString);
        Sound sound = null;

        if (key != null) {
            sound = Registry.SOUNDS.get(key);
        }

        if (args.length == 0) {

            for (Player player : Bukkit.getOnlinePlayers()) {
                String uuidString = player.getUniqueId().toString();

                if (deathbanned.remove(uuidString)) {
                    frozen.remove(uuidString);
                    player.setGameMode(GameMode.valueOf(plugin.getConfig().getString("default-gamemode")));
                    if (sender instanceof Player executor) {
                        if (sound != null){
                            executor.getWorld().playSound(executor, sound, 1, 1);
                        }
                        player.teleport(executor.getLocation());
                    }
                }
            }

            plugin.getConfig().set("deathbanned", deathbanned);
            plugin.getConfig().set("frozen", deathbanned);
            plugin.saveConfig();

            sender.sendMessage(FakeDeathBan.prefix + ChatColor.GREEN + Messages.getMessage("udb-1-s"));

            return true;
        }

        for (String arg : args) {
            Player target = Bukkit.getPlayer(arg);

            if (target == null) {
                sender.sendMessage(FakeDeathBan.prefix + ChatColor.RED +
                        Messages.getMessage("p-f", arg));
                continue;
            }

            String uuidString = target.getUniqueId().toString();

            if (deathbanned.remove(uuidString)) {
                frozen.remove(uuidString);
                target.setGameMode(GameMode.valueOf(plugin.getConfig().getString("default-gamemode")));
                if (sender instanceof Player executor) {
                    if (sound != null){
                        executor.getWorld().playSound(executor, sound, 1, 1);
                    }
                    target.teleport(executor.getLocation());
                }
                sender.sendMessage(FakeDeathBan.prefix + ChatColor.GREEN + Messages.getMessage("udb-2-s", target.getName()));
            } else {
                sender.sendMessage(FakeDeathBan.prefix + ChatColor.YELLOW + Messages.getMessage("p-db-f", target.getName()));
            }
        }

        plugin.getConfig().set("deathbanned", deathbanned);
        plugin.getConfig().set("frozen", frozen);
        plugin.saveConfig();

        return true;
    }
}