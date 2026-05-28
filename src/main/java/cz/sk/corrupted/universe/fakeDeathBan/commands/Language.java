package cz.sk.corrupted.universe.fakeDeathBan.commands;

import cz.sk.corrupted.universe.fakeDeathBan.FakeDeathBan;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jspecify.annotations.NonNull;

public class Language implements CommandExecutor {
    private final FakeDeathBan plugin;

    public Language(FakeDeathBan plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        if (args.length < 1){
            sender.sendMessage(FakeDeathBan.prefix + ChatColor.RED + "There must be at least one argument!");
            return true;
        }
        switch (args[0]){
            case "cs_cz":
                plugin.getConfig().set("language", "cs_cz");
                plugin.saveConfig();
                sender.sendMessage(FakeDeathBan.prefix + ChatColor.GREEN + "Jazyk nastaven na: Čeština" +
                        ChatColor.YELLOW + "\nBude potřeba restartovat server aby se projevila změna!");
                return true;
            case "en_us":
                plugin.getConfig().set("language", "en_us");
                plugin.saveConfig();
                sender.sendMessage(FakeDeathBan.prefix + ChatColor.GREEN + "Language set to: English" +
                        ChatColor.YELLOW + "\nA server restart is needed for the changes to appear!");
                return true;
            case "sk_sk":
                plugin.getConfig().set("language", "sk_sk");
                plugin.saveConfig();
                sender.sendMessage(FakeDeathBan.prefix + ChatColor.GREEN + "Jazyk nastavený na: Slovenčina" +
                        ChatColor.YELLOW + "\nBude potrebné reštartovať server, aby sa zmena prejavila!");
            default:
                sender.sendMessage(FakeDeathBan.prefix + ChatColor.RED + "Language not found");
        }
        return true;
    }
}
