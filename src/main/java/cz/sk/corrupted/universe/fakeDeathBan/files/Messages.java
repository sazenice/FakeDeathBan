package cz.sk.corrupted.universe.fakeDeathBan.files;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class Messages {

    private static File file;
    private static FileConfiguration configuration;
    private static Plugin plugin;

    public static void setup(Plugin pl){
        plugin = pl;

        file = new File(plugin.getDataFolder(), "messages.yml");

        if (!file.exists()) {
            plugin.saveResource("messages.yml", false); // copies from jar if exists
        }

        configuration = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get(){
        if (configuration == null) {
            throw new IllegalStateException("Messages not initialized! Call setup() first.");
        }
        return configuration;
    }

    public static void save(){
        try{
            configuration.save(file);
        }catch (IOException e){
            plugin.getLogger().severe("Nepodařilo se uložit messages.yml!");
        }
    }

    public static String getMessage(String path, Object... args) {
        String msg = configuration.getString(path);

        if (msg == null) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Pokud zprávy chybí a v messages.yml nic není,");
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "vymaž messages.yml a restartuj server!!");
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            return ChatColor.RED + "Chybí zpráva: " + path;
        }

        msg = String.format(msg, args);
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}