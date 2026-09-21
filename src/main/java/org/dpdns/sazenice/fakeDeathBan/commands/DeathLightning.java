package org.dpdns.sazenice.fakeDeathBan.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.dpdns.sazenice.fakeDeathBan.FakeDeathBan;
import org.dpdns.sazenice.fakeDeathBan.other.Messages;
import org.jspecify.annotations.NonNull;

public class DeathLightning implements CommandExecutor {
    private final FakeDeathBan plugin;

    public DeathLightning(FakeDeathBan plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        String toSend = "ON";
        if (plugin.getConfig().getBoolean("deathlightning")){
            toSend = "OFF";
        }
        sender.sendMessage(Messages.getMessage("deathlightning-toggle", toSend));
        plugin.getConfig().set("deathlightning", !plugin.getConfig().getBoolean("deathlightning"));
        plugin.saveConfig();
        return true;
    }
}
