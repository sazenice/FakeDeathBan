package cz.sk.corrupted.universe.fakeDeathBan.commands;

import cz.sk.corrupted.universe.fakeDeathBan.FakeDeathBan;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class Check implements CommandExecutor {
    private final FakeDeathBan plugin;

    public Check(FakeDeathBan plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        final FileConfiguration config = this.plugin.getConfig();

        List<String> paths = FakeDeathBan.paths;

        for (String path : paths){
            if (!plugin.getConfig().contains(path)){
                sender.sendMessage(ChatColor.DARK_RED + "ZÁVAŽNÁ CHYBA!" +
                        "\nChybí cesta " + path + " v config.yml!!!!!!" +
                        "\nOdstraň config.yml a restartuj server" +
                        "\nnebo přidej tuto cestu do config.yml a restartuj server!" +
                        "\n\n===    Plugin se teď vypne.  ===\n");
                Bukkit.getPluginManager().disablePlugin(plugin);
                return false;
            }
        }

        sender.sendMessage(FakeDeathBan.prefix + ChatColor.AQUA +
                "Obsah config.yml:" +
                "\ndeathbaned: " + config.getMapList("deathbanned") +
                "\nfrozen: " + config.getMapList("frozen") +
                "\ndefault-spectator: " + config.getString("default-spectator") +
                "\ndefault-gamemode: " + config.getString("default-gamemode"));

        return true;
    }
}
