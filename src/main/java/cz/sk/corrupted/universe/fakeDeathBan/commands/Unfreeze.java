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

import java.util.List;

public class Unfreeze implements CommandExecutor {

    private final FakeDeathBan plugin;

    public Unfreeze(FakeDeathBan plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {

        List<String> frozen = plugin.getConfig().getStringList("frozen");

        // Pokud nemá argumenty
        if (args.length == 0) {

            // Pro každého online hráče "player"
            for (Player player : Bukkit.getOnlinePlayers()) {
                frozen.remove(player.getUniqueId().toString());
            }
            sender.sendMessage(FakeDeathBan.prefix + ChatColor.GREEN + Messages.getMessage("unfreeze-1-s"));
        } else {

            // Pro každý argument "arg"
            for (String arg : args) {
                Player target = Bukkit.getPlayer(arg);

                // Pokud hráč "arg" neexistuje
                if (target == null) {
                    sender.sendMessage(FakeDeathBan.prefix + ChatColor.RED + Messages.getMessage("p-f", arg));
                    continue;
                }
                String uuid = target.getUniqueId().toString();

                // Pokud se úspěšně odstranil
                if (frozen.remove(uuid)) {
                    sender.sendMessage(FakeDeathBan.prefix + ChatColor.GREEN + Messages.getMessage("unfreeze-2-s", target.getName()));
                } else {
                    sender.sendMessage(FakeDeathBan.prefix + ChatColor.YELLOW + Messages.getMessage("unfreeze-1-f", target.getName()));
                }
            }
        }

        plugin.getConfig().set("frozen", frozen);
        plugin.saveConfig();
        return true;
    }
}