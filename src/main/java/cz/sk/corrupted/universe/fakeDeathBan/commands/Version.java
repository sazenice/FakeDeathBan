package cz.sk.corrupted.universe.fakeDeathBan.commands;

import cz.sk.corrupted.universe.fakeDeathBan.FakeDeathBan;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;


public class Version implements CommandExecutor {
    private final PluginDescriptionFile description = Bukkit.getPluginManager().getPlugin("FakeDeathBan").getDescription();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        sender.sendMessage(FakeDeathBan.prefix + ChatColor.GREEN +
                "\nNázev: " + description.getName() +
                "\nVerze: " + description.getVersion() +
                "\nAPI verze: " + description.getAPIVersion() +
                "\nPlný název: " + description.getFullName() +
                "\nAutor: " + description.getAuthors());
        return true;
    }
}
