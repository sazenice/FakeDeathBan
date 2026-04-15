package cz.sk.corrupted.universe.fakeDeathBan.commands;

import cz.sk.corrupted.universe.fakeDeathBan.FakeDeathBan;
import cz.sk.corrupted.universe.fakeDeathBan.files.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Objects;

public class DefaultGamemode implements CommandExecutor {
    private final FakeDeathBan plugin;

    public DefaultGamemode(FakeDeathBan plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1){
            sender.sendMessage(FakeDeathBan.prefix + ChatColor.RED + Messages.getMessage("defg-f-1"));
            return true;
        }
        if (!Objects.equals(args[0], "survival") && !Objects.equals(args[0], "creative") && !Objects.equals(args[0], "spectator") && !Objects.equals(args[0], "adventure")){
            sender.sendMessage(FakeDeathBan.prefix + ChatColor.RED + Messages.getMessage("defg-f-2"));
            return true;
        }

        sender.sendMessage(FakeDeathBan.prefix + ChatColor.GREEN + Messages.getMessage("defg-s", args[0]));

        plugin.getConfig().set("default-gamemode", args[0].toUpperCase());
        plugin.saveConfig();
        return true;
    }
}
