package cz.sk.corrupted.universe.fakeDeathBan;

import cz.sk.corrupted.universe.fakeDeathBan.commands.*;
import cz.sk.corrupted.universe.fakeDeathBan.files.Messages;
import cz.sk.corrupted.universe.fakeDeathBan.listeners.DeathListener;
import cz.sk.corrupted.universe.fakeDeathBan.listeners.JoinQuitListener;
import cz.sk.corrupted.universe.fakeDeathBan.listeners.MoveListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
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

    public static boolean isLobby = false;
    public static boolean isEnabled = true;
    public static BossBar lobbyBar = Bukkit.createBossBar(ChatColor.GREEN + "Režim Lobby", BarColor.GREEN, BarStyle.SOLID);

    public static List<String> paths = new ArrayList<String>();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        Messages.setup(this);
        Messages.get().options().copyDefaults(true);
        Messages.save();

        sendMessage(ChatColor.AQUA + "===Načítání=== fáze 1/3");

        // Inicalizace proměných + setup
        paths.add("deathbanned");
        paths.add("frozen");
        paths.add("default-spectator");
        paths.add("default-gamemode");
        paths.add("death-sound");
        paths.add("revive-sound");

        lobbyBar.setVisible(false);
        lobbyBar.setProgress(1.0);

        if (!Objects.equals(getConfig().getString("config-version"), getDescription().getVersion().toString())){
            sendMessage(ChatColor.RED +
                    "Verze config.yml není stejná, jako verze pluginu!" +
                    "\nJe možné, že se něco pokazí." +
                    "\nOdstraň složku pluginu nebo plugin změň na verzi " + getConfig().getString("config-version"));
        }

        sendMessage(ChatColor.AQUA + "===Načítání=== fáze 2/3");

        // Registrace posluchačů
        registerEvent(new MoveListener(this));
        registerEvent(new JoinQuitListener());
        registerEvent(new DeathListener(this));

        sendMessage(ChatColor.AQUA + "===Načítání=== fáze 3/3");
        // Registrace příkazů

        registerCommand("setspectate", new SetSpectate(this));
        registerCommand("undeathban", new UnDeathban(this));
        registerCommand("spectate", new Spectate());
        registerCommand("freeze", new Freeze(this));
        registerCommand("unfreeze", new Unfreeze(this));
        registerCommand("defg", new DefaultGamemode(this));
        registerCommand("version", new Version());
        registerCommand("check", new Check(this));
        registerCommand("setsound", new SetSound(this));
        registerCommand("lobby", new Lobby());
        registerCommand("setimmunity", new SetImmunity(this));
        registerCommand("togglefdb", new ToggleFDB());

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