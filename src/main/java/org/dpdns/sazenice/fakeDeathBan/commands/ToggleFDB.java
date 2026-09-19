package org.dpdns.sazenice.fakeDeathBan.commands;

import org.dpdns.sazenice.fakeDeathBan.FakeDeathBan;
import org.dpdns.sazenice.fakeDeathBan.other.Messages;
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
        if (FakeDeathBan.isEnabled){toSend = "-on";}else{toSend = "-off";}
        sender.sendMessage(FakeDeathBan.prefix + ChatColor.GREEN + Messages.getMessage("toggle" + toSend));
        return true;
    }
}
