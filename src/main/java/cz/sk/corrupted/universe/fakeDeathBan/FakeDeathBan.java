package cz.sk.corrupted.universe.fakeDeathBan;

import cz.sk.corrupted.universe.fakeDeathBan.commands.*;
import cz.sk.corrupted.universe.fakeDeathBan.listeners.DeathListener;
import cz.sk.corrupted.universe.fakeDeathBan.listeners.InventoryListener;
import cz.sk.corrupted.universe.fakeDeathBan.listeners.JoinQuitListener;
import cz.sk.corrupted.universe.fakeDeathBan.listeners.MoveListener;
import cz.sk.corrupted.universe.fakeDeathBan.other.AutoComplete;
import cz.sk.corrupted.universe.fakeDeathBan.other.Messages;
import cz.sk.corrupted.universe.fakeDeathBan.other.UpdateChecker;
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
    private void sendDebug(String message){
        if(this.getConfig().getBoolean("debug")){
            console.sendMessage(prefix + ChatColor.YELLOW + " DEBUG " + ChatColor.RESET + message);
        }
    }

    public static boolean isPreStart = false;
    public static boolean isEnabled = true;
    public static BossBar preStartBar = Bukkit.createBossBar(ChatColor.GREEN + "Režim Immortality", BarColor.GREEN, BarStyle.SOLID);

    private final AutoComplete autoComplete = new AutoComplete();

    public static List<String> paths = new ArrayList<>();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        saveResource("lang/en_us.yml", false);
        saveResource("lang/sk_sk.yml", false);
        saveResource("lang/cs_cz.yml", false);
        Messages.setup(this);

        sendDebug(ChatColor.AQUA + "===Loading=== 1/4");
        // Register paths
        paths.add("deathbanned");
        paths.add("frozen");
        paths.add("default-spectator");
        paths.add("default-gamemode");
        paths.add("death-sound");
        paths.add("revive-sound");
        paths.add("language");

        sendDebug(ChatColor.GREEN + "Paths registered");
        // Initalize bossbar
        preStartBar.setVisible(false);
        preStartBar.setTitle(ChatColor.GREEN + Messages.getMessage("pre-start"));
        preStartBar.setProgress(1.0);

        sendDebug(ChatColor.GREEN + "Bossbar initalized");

        sendDebug(ChatColor.AQUA + "===Loading=== 2/4");
        // Register event listeners
        registerEvent(new MoveListener(this));
        registerEvent(new JoinQuitListener());
        registerEvent(new DeathListener(this));
        registerEvent(new InventoryListener());

        sendDebug(ChatColor.GREEN + "Event listeners registered");
        // LOAD STAGE 3
        sendDebug(ChatColor.AQUA + "===Loading=== 3/4");
        // Register commands
        registerCommand("setspectate", new SetSpectate(this));
        registerCommand("revive", new Revive(this));
        registerCommand("spectate", new Spectate());
        registerCommand("freeze", new Freeze(this));
        registerCommand("unfreeze", new Unfreeze(this));
        registerCommand("defaultgamemode", new DefaultGamemode(this));
        registerCommand("version", new Version());
        registerCommand("check", new Check(this));
        registerCommand("setsound", new SetSound(this));
        registerCommand("immortality", new Immortality());
        registerCommand("setimmunity", new SetImmunity(this));
        registerCommand("togglefdb", new ToggleFDB());
        registerCommand("gui", new Gui());
        registerCommand("banlist", new BanList(this));
        registerCommand("language", new Language(this));

        sendDebug(ChatColor.GREEN + "Commands registered");
        sendDebug(ChatColor.AQUA + "===Loading=== 4/4");
        // Register update notification
        new UpdateChecker(this).check();

        sendDebug(ChatColor.AQUA + "Update notification registered");
        sendMessage(ChatColor.GREEN + "===  Plugin loaded   ===");
    }

    @Override
    public void onDisable() {
        sendMessage(ChatColor.RED + Messages.getMessage("disabling"));
    }
    private void registerEvent(Listener listener){
        sendDebug(ChatColor.GREEN + Messages.getMessage("r-listener", listener.getClass().getSimpleName()) + ChatColor.YELLOW + listener);
        Bukkit.getPluginManager().registerEvents(listener, this);
    }
    private void registerCommand(String name, @NonNull CommandExecutor command){
        sendDebug(ChatColor.GREEN + Messages.getMessage("r-command", command.toString()) + ChatColor.YELLOW + name);
        Objects.requireNonNull(getCommand(name)).setTabCompleter(autoComplete);
        Objects.requireNonNull(getCommand(name)).setExecutor(command);
    }
}