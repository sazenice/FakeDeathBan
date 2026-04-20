package cz.sk.corrupted.universe.fakeDeathBan.commands;

import cz.sk.corrupted.universe.fakeDeathBan.FakeDeathBan;
import cz.sk.corrupted.universe.fakeDeathBan.files.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

public class SetImmunity implements CommandExecutor {
    private final FakeDeathBan plugin;

    public SetImmunity(FakeDeathBan plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
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

        String node = "fakedeathban.bypass." + nodePart;
        PermissionAttachment attachment = player.addAttachment(plugin);

        if (player.hasPermission(node)){
            attachment.setPermission(node, false);
            sender.sendMessage(FakeDeathBan.prefix + ChatColor.GREEN + Messages.getMessage("setimmunity-2-s", player.getName(), node));
        }else{
            attachment.setPermission(node, true);
            sender.sendMessage(FakeDeathBan.prefix + ChatColor.GREEN + Messages.getMessage("setimmunity-1-s", player.getName(), node));
        }

        return true;
    }
}
