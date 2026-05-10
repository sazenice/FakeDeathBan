package cz.sk.corrupted.universe.fakeDeathBan.other;

import cz.sk.corrupted.universe.fakeDeathBan.FakeDeathBan;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class Messages {

    private static FileConfiguration configuration;

    public static void setup(Plugin pl) {

        File file = new File(pl.getDataFolder(), "lang/" + pl.getConfig().get("language") + ".yml");

        if (!file.exists()) {
            throw new RuntimeException("Language file " + pl.getConfig().get("language") + " doesn't exist. Please remove the FakeDeathBan folder!");
        }

        configuration = YamlConfiguration.loadConfiguration(file);
        Bukkit.getConsoleSender().sendMessage(FakeDeathBan.prefix + ChatColor.YELLOW + "Successfully loaded language " +  pl.getConfig().get("language"));
    }

    public static String getMessage(String path, Object... args) {
        String msg = configuration.getString(path);

        if (msg == null) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "If translations in the language file don't exist,");
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "remove the FakeDeathBan folder and restart the server");
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            return "Error!";
        }

        msg = String.format(msg, args);
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}