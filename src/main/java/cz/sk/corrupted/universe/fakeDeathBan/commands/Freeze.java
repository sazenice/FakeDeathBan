package cz.sk.corrupted.universe.fakeDeathBan.commands;

import cz.sk.corrupted.universe.fakeDeathBan.FakeDeathBan;
import cz.sk.corrupted.universe.fakeDeathBan.other.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class Freeze implements CommandExecutor {

    private final FakeDeathBan plugin;

    public Freeze(FakeDeathBan plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {

        List<String> deathbanned = plugin.getConfig().getStringList("deathbanned");
        List<String> frozen = plugin.getConfig().getStringList("frozen");

        // Pokud nemá argumenty
        if (args.length == 0) {

            // Pro každého online hráče "player"
            for (Player player : Bukkit.getOnlinePlayers()) {
                String uuid = player.getUniqueId().toString();

                // Pokud "player" má deathban a není ještě zmražen a nemá imunitu
                if (deathbanned.contains(uuid) && !frozen.contains(uuid) && !player.hasPermission("fakedeathban.bypass.freeze")) {
                    frozen.add(uuid);
                }
            }

            plugin.getConfig().set("frozen", frozen);
            plugin.saveConfig();

            sender.sendMessage(FakeDeathBan.prefix + ChatColor.GREEN + Messages.getMessage("freeze-1-s"));
            return true;
        }

        // Pro každy argument "arg"
        for (String arg : args) {
            Player target = Bukkit.getPlayer(arg);

            // Pokud hráč "arg" neexistuje
            if (target == null) {
                sender.sendMessage(FakeDeathBan.prefix + ChatColor.RED + Messages.getMessage("player-not-found", arg));
                continue;
            }

            // Pokud hráč nemá imunitu
            if (target.hasPermission("fakedeathban.bypass.freeze")){
                sender.sendMessage(FakeDeathBan.prefix + ChatColor.RED + Messages.getMessage("p-immune", arg));
                continue;
            }
            String uuid = target.getUniqueId().toString();

            // Pokud hráč nemá deathban
            if (!deathbanned.contains(uuid)) {
                sender.sendMessage(FakeDeathBan.prefix + ChatColor.RED + Messages.getMessage("p-db-f", target.getName()));
                continue;
            }

            // Pokud hráč není zmražen
            if (!frozen.contains(uuid)) {
                frozen.add(uuid);
                sender.sendMessage(FakeDeathBan.prefix + ChatColor.GREEN + Messages.getMessage("freeze-2-s", target.getName()));
            } else {
                sender.sendMessage(FakeDeathBan.prefix + ChatColor.YELLOW + Messages.getMessage("freeze-1-f", target.getName()));
            }
        }

        plugin.getConfig().set("frozen", frozen);
        plugin.saveConfig();
        return true;
    }
}