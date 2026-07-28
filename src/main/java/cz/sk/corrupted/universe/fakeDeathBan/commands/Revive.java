package cz.sk.corrupted.universe.fakeDeathBan.commands;

import cz.sk.corrupted.universe.fakeDeathBan.FakeDeathBan;
import cz.sk.corrupted.universe.fakeDeathBan.other.Messages;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NonNull;

public class Revive implements CommandExecutor {

    private final FakeDeathBan plugin;

    public Revive(FakeDeathBan plugin) {
        this.plugin = plugin;
    }

    private GameMode getDefaultGameMode() {
        String gamemodeString = plugin.getConfig().getString("default-gamemode");
        if (gamemodeString == null) {
            return GameMode.ADVENTURE;
        }
        try {
            return GameMode.valueOf(gamemodeString);
        } catch (IllegalArgumentException e) {
            return GameMode.ADVENTURE;
        }
    }

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        if (label.equals("udb") || label.equals("undeathban")){
            sender.sendMessage(FakeDeathBan.prefix + ChatColor.YELLOW + "Command has been renamed to 'revive'");
        }

        Sound sound = null;
        String soundString = plugin.getConfig().getString("revive-sound");
        if (soundString != null) {
            NamespacedKey key = NamespacedKey.fromString(soundString);
            if (key != null) {
                sound = Registry.SOUNDS.get(key);
            }
        }

        GameMode defaultGamemode = getDefaultGameMode();

        if (args.length == 0) {

            for (Player player : Bukkit.getOnlinePlayers()) {
                String uuidString = player.getUniqueId().toString();

                if (FakeDeathBan.deathbanned.remove(uuidString)) {
                    FakeDeathBan.frozen.remove(uuidString);
                    player.setGameMode(defaultGamemode);
                    if (sender instanceof Player executor) {
                        if (sound != null){
                            executor.getWorld().playSound(executor, sound, 1, 1);
                        }
                        player.teleport(executor.getLocation());
                        for (Player onlinePlayer : Bukkit.getOnlinePlayers()){
                            onlinePlayer.showPlayer(plugin, player);
                        }
                    }
                }
            }

            plugin.saveDeathbanned();
            plugin.saveFrozen();

            sender.sendMessage(FakeDeathBan.prefix + ChatColor.GREEN + Messages.getMessage("revive-1-s"));

            return true;
        }

        for (String arg : args) {
            Player target = Bukkit.getPlayer(arg);

            if (target == null) {
                sender.sendMessage(FakeDeathBan.prefix + ChatColor.RED +
                        Messages.getMessage("player-not-found", arg));
                continue;
            }

            String uuidString = target.getUniqueId().toString();

            if (FakeDeathBan.deathbanned.remove(uuidString)) {
                FakeDeathBan.frozen.remove(uuidString);
                target.setGameMode(defaultGamemode);
                if (sender instanceof Player executor) {
                    if (sound != null){
                        executor.getWorld().playSound(executor, sound, 1, 1);
                    }
                    target.teleport(executor.getLocation());
                    for (Player onlinePlayer : Bukkit.getOnlinePlayers()){
                        onlinePlayer.showPlayer(plugin, target);
                    }
                }
                sender.sendMessage(FakeDeathBan.prefix + ChatColor.GREEN + Messages.getMessage("revive-2-s", target.getName()));
            } else {
                sender.sendMessage(FakeDeathBan.prefix + ChatColor.YELLOW + Messages.getMessage("p-db-f", target.getName()));
            }
        }

        plugin.saveDeathbanned();
        plugin.saveFrozen();

        return true;
    }
}
