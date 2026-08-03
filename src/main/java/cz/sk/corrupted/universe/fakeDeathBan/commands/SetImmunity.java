package cz.sk.corrupted.universe.fakeDeathBan.commands;

import cz.sk.corrupted.universe.fakeDeathBan.FakeDeathBan;
import cz.sk.corrupted.universe.fakeDeathBan.other.ImmunityManager;
import cz.sk.corrupted.universe.fakeDeathBan.other.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NonNull;

import java.util.UUID;

public class SetImmunity implements CommandExecutor {
    private final FakeDeathBan plugin;

    public SetImmunity(FakeDeathBan plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(FakeDeathBan.prefix + ChatColor.RED + Messages.getMessage("setimmunity-1-f"));
            return true;
        }

        Player player = Bukkit.getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage(FakeDeathBan.prefix + ChatColor.RED + Messages.getMessage("player-not-found", args[0]));
            return true;
        }

        String nodePart = args[1].toLowerCase();

        boolean valid = false;
        for (String type : ImmunityManager.IMMUNITY_TYPES) {
            if (type.equals(nodePart)) {
                valid = true;
                break;
            }
        }
        if (!valid) {
            sender.sendMessage(FakeDeathBan.prefix + ChatColor.RED + "Invalid immunity type.");
            return true;
        }

        UUID uuid = player.getUniqueId();
        String node = "fakedeathban.bypass." + nodePart;

        if (plugin.immunityManager.hasImmunity(uuid, nodePart)) {
            plugin.immunityManager.setImmunity(uuid, nodePart, false);
            plugin.immunityManager.revokeImmunity(player, nodePart);
            sender.sendMessage(FakeDeathBan.prefix + ChatColor.GREEN + Messages.getMessage("setimmunity-2-s", player.getName(), node));
            if (nodePart.equals("immortality")){
                FakeDeathBan.immortalityBar.removePlayer(player);
            }
        } else {
            plugin.immunityManager.setImmunity(uuid, nodePart, true);
            plugin.immunityManager.grantImmunity(player, nodePart);
            sender.sendMessage(FakeDeathBan.prefix + ChatColor.GREEN + Messages.getMessage("setimmunity-1-s", player.getName(), node));
            if (nodePart.equals("immortality")){
                FakeDeathBan.immortalityBar.addPlayer(player);
            }
        }

        return true;
    }
}
