package cz.sk.corrupted.universe.fakeDeathBan.commands;

import cz.sk.corrupted.universe.fakeDeathBan.FakeDeathBan;
import cz.sk.corrupted.universe.fakeDeathBan.files.Messages;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SetSound implements CommandExecutor {
    private final FakeDeathBan plugin;

    public SetSound(FakeDeathBan plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(FakeDeathBan.prefix + ChatColor.RED + Messages.getMessage("setsound-1-f"));
            return true;
        }
        String type = args[0].toLowerCase();
        if (!type.equals("death") && !type.equals("revive")) {
            sender.sendMessage(FakeDeathBan.prefix + ChatColor.RED + Messages.getMessage("setsound-1-f"));
            return true;
        }
        if (args.length == 1) {
            plugin.getConfig().set(type + "-sound", null);
            sender.sendMessage(FakeDeathBan.prefix + ChatColor.GREEN + Messages.getMessage("setsound-3-s", type));
            plugin.saveConfig();
            return true;
        }

        String soundName = args[1];
        NamespacedKey key = NamespacedKey.fromString(soundName);
        if (key == null || Registry.SOUNDS.get(key) == null) {
            sender.sendMessage(FakeDeathBan.prefix + ChatColor.RED + Messages.getMessage("setsound-3-f", soundName));
            return true;
        }

        plugin.getConfig().set(type + "-sound", soundName);

        if (type.equals("death")) {
            sender.sendMessage(FakeDeathBan.prefix + ChatColor.GREEN + Messages.getMessage("setsound-1-s", soundName));
        } else {
            sender.sendMessage(FakeDeathBan.prefix + ChatColor.GREEN + Messages.getMessage("setsound-2-s", soundName));
        }

        plugin.saveConfig();
        return true;
    }
}
