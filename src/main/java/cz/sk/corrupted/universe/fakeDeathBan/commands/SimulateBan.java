package cz.sk.corrupted.universe.fakeDeathBan.commands;

import cz.sk.corrupted.universe.fakeDeathBan.FakeDeathBan;
import cz.sk.corrupted.universe.fakeDeathBan.other.Messages;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NonNull;

public class SimulateBan implements CommandExecutor {
    private final FakeDeathBan plugin;

    public SimulateBan(FakeDeathBan plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        if (args.length < 2){ sender.sendMessage(ChatColor.RED + Messages.getMessage("simulateban-1-f")); return true; }
        Sound sound = null;
        String soundString = plugin.getConfig().getString("death-sound");
        if (soundString != null) {
            NamespacedKey key = NamespacedKey.fromString(soundString);
            if (key != null) {
                sound = Registry.SOUNDS.get(key);
            }
        }
        final Sound deathSound = sound;

        Bukkit.broadcastMessage(String.join(" ", args));
        Bukkit.broadcastMessage(ChatColor.YELLOW + args[0] + " left the game");

        for (Player player : Bukkit.getOnlinePlayers()){
            if (deathSound != null){
                player.getWorld().playSound(player.getLocation(), deathSound, 5f, 1);
            }
        }

        return true;
    }
}
