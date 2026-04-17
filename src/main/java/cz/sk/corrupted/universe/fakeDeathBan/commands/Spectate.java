package cz.sk.corrupted.universe.fakeDeathBan.commands;

import cz.sk.corrupted.universe.fakeDeathBan.FakeDeathBan;
import cz.sk.corrupted.universe.fakeDeathBan.files.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NonNull;

public class Spectate implements CommandExecutor {

    @Override

    public boolean onCommand(@NonNull CommandSender commandSender,@NonNull Command command,@NonNull String s, String @NonNull [] strings) {

        if (!(commandSender instanceof Player player)) {
            commandSender.sendMessage(FakeDeathBan.prefix + ChatColor.RED + Messages.getMessage("p-f"));
            return true;
        }
        if(strings.length < 0){
            commandSender.sendMessage(FakeDeathBan.prefix + ChatColor.RED + Messages.getMessage("spectate-1-f"));
        }

        Player target = Bukkit.getPlayer(strings[0]);

        if (target == null) {
            player.sendMessage(FakeDeathBan.prefix + ChatColor.RED + Messages.getMessage("spectate-2-f", strings[0]));
            return true;
        }

        player.setSpectatorTarget(target);
        player.sendMessage(FakeDeathBan.prefix + ChatColor.GREEN + Messages.getMessage("spectate-s", target.getName()));

        return true;
    }
}
