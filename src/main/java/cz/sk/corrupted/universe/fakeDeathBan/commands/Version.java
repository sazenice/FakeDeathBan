package cz.sk.corrupted.universe.fakeDeathBan.commands;

import cz.sk.corrupted.universe.fakeDeathBan.FakeDeathBan;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.jspecify.annotations.NonNull;

import java.util.Objects;


public class Version implements CommandExecutor {
    private final PluginDescriptionFile description = Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("FakeDeathBan")).getDescription();
    @Override
    public boolean onCommand(CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {

        sender.sendMessage(FakeDeathBan.prefix + ChatColor.GREEN +
                "\nNázev: " + description.getName() +
                "\nVerze: " + description.getVersion() +
                "\nAPI verze: " + description.getAPIVersion() +
                "\nPlný název: " + description.getFullName() +
                "\nAutor: " + description.getAuthors());
        return true;
    }
}
