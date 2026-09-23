package org.dpdns.sazenice.fakeDeathBan.other;

import org.dpdns.sazenice.fakeDeathBan.FakeDeathBan;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class Messages {

    private static FileConfiguration configuration;

    public static void setup(Plugin pl) {

        File file = new File(pl.getDataFolder(), "lang/" + pl.getConfig().getString("language") + ".yml");

        if (!file.exists()) {
            pl.saveResource("lang/" + pl.getConfig().get("language"), true);
            FakeDeathBan.sendDebug("Language file " + pl.getConfig().get("language") + " doesn't exist.");
        }

        configuration = YamlConfiguration.loadConfiguration(file);
        FakeDeathBan.sendDebug(FakeDeathBan.prefix + ChatColor.YELLOW + "Successfully loaded language " +  pl.getConfig().get("language"));
    }

    public static String getMessage(String path, Object... args) {
        String msg = configuration.getString(path);

        if (msg == null) {
            FakeDeathBan.sendMessage(ChatColor.DARK_RED + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            FakeDeathBan.sendMessage(ChatColor.RED + "If translations in the language file don't exist,");
            FakeDeathBan.sendMessage(ChatColor.RED + "remove the FakeDeathBan folder and restart the server");
            FakeDeathBan.sendMessage(ChatColor.DARK_RED + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            return "Error!";
        }

        msg = String.format(msg, args);
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}