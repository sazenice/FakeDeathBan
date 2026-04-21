package cz.sk.corrupted.universe.fakeDeathBan.commands;

import cz.sk.corrupted.universe.fakeDeathBan.FakeDeathBan;
import cz.sk.corrupted.universe.fakeDeathBan.other.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jspecify.annotations.NonNull;

public class ToggleFDB implements CommandExecutor {
    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        FakeDeathBan.isEnabled = !FakeDeathBan.isEnabled;
        String toSend;
        if (FakeDeathBan.isEnabled){toSend = "zapli";}else{toSend = "vypli";}
        sender.sendMessage(FakeDeathBan.prefix + ChatColor.GREEN + Messages.getMessage("toggle", toSend));
        return true;
    }
}
