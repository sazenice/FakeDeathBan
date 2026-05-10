package cz.sk.corrupted.universe.fakeDeathBan.commands;

import cz.sk.corrupted.universe.fakeDeathBan.FakeDeathBan;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class Check implements CommandExecutor {
    private final FakeDeathBan plugin;

    public Check(FakeDeathBan plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        final FileConfiguration config = this.plugin.getConfig();

        List<String> paths = FakeDeathBan.paths;

        for (String path : paths){
            if (!plugin.getConfig().contains(path)){
                sender.sendMessage(ChatColor.DARK_RED + "CRITICAL ERROR!" +
                        "\nPath " + path + " is missing in config.yml!!!!!!" +
                        "\nDelete config.yml and restart the server" +
                        "\n\n===    Plugin will now turn off  ===\n");
                Bukkit.getPluginManager().disablePlugin(plugin);
                return false;
            }
        }

        sender.sendMessage(FakeDeathBan.prefix + ChatColor.AQUA +
                "config.yml contents:" +
                "\ndeathbaned: " + config.getMapList("deathbanned") +
                "\nfrozen: " + config.getMapList("frozen") +
                "\ndefault-spectator: " + config.getString("default-spectator") +
                "\ndefault-gamemode: " + config.getString("default-gamemode") +
                "\ndeath-sound: " + config.getString("death-sound") +
                "\nrevive-sound: " + config.getString("revive-sound") +
                "\nconfig-version: " + config.getString("config-version") +
                "\nlanguage: " + config.getString("language"));
        return true;
    }
}
