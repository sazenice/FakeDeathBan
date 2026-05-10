package cz.sk.corrupted.universe.fakeDeathBan.commands;

import cz.sk.corrupted.universe.fakeDeathBan.FakeDeathBan;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jspecify.annotations.NonNull;

import java.util.Objects;
import java.util.UUID;

public class BanList implements CommandExecutor {
    private final FakeDeathBan plugin;

    public BanList(FakeDeathBan plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        StringBuilder sb = new StringBuilder();
        sb.append(FakeDeathBan.prefix).append(ChatColor.GREEN).append("Banlist:\n").append(ChatColor.YELLOW);

        for (Object o : Objects.requireNonNull(plugin.getConfig().getList("deathbanned"))) {
            if (!(o instanceof String uuidStr)) continue;
            try {
                UUID uuid = UUID.fromString(uuidStr);
                var offline = Bukkit.getOfflinePlayer(uuid);
                String name = offline.getName() != null ? offline.getName() : uuidStr;
                sb.append("- ").append(name).append(" (").append(uuidStr).append(")\n");
            } catch (IllegalArgumentException ex) {
                sb.append("- ").append(uuidStr).append("\n");
            }
        }

        for (String line : sb.toString().split("\n")) {
            sender.sendMessage(line);
        }
        return true;
    }

}
