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
                sender.sendMessage(FakeDeathBan.prefix + ChatColor.GREEN + "Jazyk nastaven na: Čeština" +
                        ChatColor.YELLOW + "Pokud se jazyk nezmění, bude potřeba restartovat server!");
                return true;
            case "en_us":
                plugin.getConfig().set("language", "en_us");
                sender.sendMessage(FakeDeathBan.prefix + ChatColor.GREEN + "Language set to: English" +
                        ChatColor.YELLOW + "If the language isn't changed, a server restart is needed!");
                return true;
            default:
                sender.sendMessage(FakeDeathBan.prefix + ChatColor.RED + "Language not found");
        }
        return true;
    }
}
