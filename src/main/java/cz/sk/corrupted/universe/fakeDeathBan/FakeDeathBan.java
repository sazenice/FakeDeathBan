package cz.sk.corrupted.universe.fakeDeathBan;

import cz.sk.corrupted.universe.fakeDeathBan.commands.*;
import cz.sk.corrupted.universe.fakeDeathBan.files.Messages;
import cz.sk.corrupted.universe.fakeDeathBan.listeners.DeathListener;
import cz.sk.corrupted.universe.fakeDeathBan.listeners.HideJoinLeave;
import cz.sk.corrupted.universe.fakeDeathBan.listeners.MoveListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class FakeDeathBan extends JavaPlugin implements Listener {

    public static final ConsoleCommandSender console = Bukkit.getConsoleSender();
    public static final String prefix = "[" + ChatColor.BOLD + ChatColor.LIGHT_PURPLE + "FakeDeathBan" + ChatColor.RESET + "] ";
    public static void sendMessage(String message){console.sendMessage(prefix + message);}

    public static List<String> paths = new ArrayList<String>();

    @Override
    public void onEnable() {
        Messages.setup(this);
        Messages.get().options().copyDefaults(true);
        Messages.save();

        sendMessage(ChatColor.AQUA + "===Načítání=== fáze 1/3");

        paths.add("deathbanned");
        paths.add("frozen");
        paths.add("default-spectator");
        paths.add("default-gamemode");

        for (String path : paths){
            if (!this.getConfig().contains(path)){
                sendMessage(ChatColor.DARK_RED + "ZÁVAŽNÁ CHYBA!" +
                        "\nChybí cesta " + path + " v config.yml!!!!!!" +
                        "\nOdstraň config.yml a restartuj server" +
                        "\nnebo přidej tuto cestu do config.yml a restartuj server!" +
                        "\n\n===    Plugin se teď vypne.  ===\n");
                Bukkit.getPluginManager().disablePlugin(this);
            }else{
                sendMessage(ChatColor.DARK_GREEN + "Nalezeno " + path + " v config.yml");
            }
        }
        sendMessage(ChatColor.AQUA + "===Načítání=== fáze 2/3");

        registerEvent(new MoveListener(this));
        registerEvent(new HideJoinLeave());
        registerEvent(new DeathListener(this));

        sendMessage(ChatColor.AQUA + "===Načítání=== fáze 3/3");

        registerCommand("setspectate", new SetSpectate(this));
        registerCommand("undeathban", new UnDeathban(this));
        registerCommand("spectate", new Spectate());
        registerCommand("freeze", new Freeze(this));
        registerCommand("unfreeze", new Unfreeze(this));
        registerCommand("defg", new DefaultGamemode(this));
        registerCommand("version", new Version());
        registerCommand("check", new Check(this));

        sendMessage(ChatColor.GREEN + "===  Plugin načten   ===");
    }

    @Override
    public void onDisable() {
        sendMessage(ChatColor.RED + Messages.getMessage("disabling"));
    }
    private void registerEvent(Listener listener){
        sendMessage(ChatColor.GREEN + Messages.getMessage("r-listener", listener.toString()) + ChatColor.YELLOW + listener);
        Bukkit.getPluginManager().registerEvents(listener, this);
    }
    private void registerCommand(String name, @NonNull CommandExecutor command){
        sendMessage(ChatColor.GREEN + Messages.getMessage("r-command", command.toString()) + ChatColor.YELLOW + name);
        Objects.requireNonNull(getCommand(name)).setExecutor(command);
    }
}