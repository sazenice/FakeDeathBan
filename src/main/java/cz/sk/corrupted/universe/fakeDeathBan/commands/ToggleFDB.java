package cz.sk.corrupted.universe.fakeDeathBan.commands;

import cz.sk.corrupted.universe.fakeDeathBan.FakeDeathBan;
import cz.sk.corrupted.universe.fakeDeathBan.files.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ToggleFDB implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FakeDeathBan.isEnabled = !FakeDeathBan.isEnabled;
        String toSend;
        if (FakeDeathBan.isEnabled){toSend = "vypli";}else{toSend = "zapli";}
        sender.sendMessage(FakeDeathBan.prefix + ChatColor.GREEN + Messages.getMessage("toggle", toSend));
        return true;
    }
}
