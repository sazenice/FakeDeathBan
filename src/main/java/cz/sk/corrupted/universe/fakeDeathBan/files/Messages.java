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

        file = new File(plugin.getDataFolder(), "lang/cs_cz.yml");

        if (!file.exists()) {
            plugin.saveResource("lang/cs_cz.yml", false);
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
            plugin.getLogger().severe("Nepodařilo se uložit cs_cz.yml!");
        }
    }

    public static String getMessage(String path, Object... args) {
        String msg = configuration.getString(path);

        if (msg == null) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Pokud překlady chybí a v lang/cs_cz.yml nic není,");
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "vymaž cs_cz.yml a restartuj server!!");
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            return ChatColor.RED + "Chybí překlad: " + path + "\nAktualizoval jsi na novou verzi? vymaž lang/cs_cz.yml";
        }

        msg = String.format(msg, args);
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}