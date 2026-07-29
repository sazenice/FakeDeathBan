package cz.sk.corrupted.universe.fakeDeathBan;

import cz.sk.corrupted.universe.fakeDeathBan.commands.*;
import cz.sk.corrupted.universe.fakeDeathBan.listeners.DeathListener;
import cz.sk.corrupted.universe.fakeDeathBan.listeners.InventoryListener;
import cz.sk.corrupted.universe.fakeDeathBan.listeners.JoinQuitListener;
import cz.sk.corrupted.universe.fakeDeathBan.listeners.MoveListener;
import cz.sk.corrupted.universe.fakeDeathBan.other.AutoComplete;
import cz.sk.corrupted.universe.fakeDeathBan.other.ImmunityManager;
import cz.sk.corrupted.universe.fakeDeathBan.other.Messages;
import cz.sk.corrupted.universe.fakeDeathBan.other.UpdateChecker;
import org.bstats.bukkit.Metrics;
import org.bstats.charts.SimplePie;
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

import java.util.*;

public final class FakeDeathBan extends JavaPlugin implements Listener {

    public static final ConsoleCommandSender console = Bukkit.getConsoleSender();
    public static final String prefix = "[" + ChatColor.BOLD + ChatColor.LIGHT_PURPLE + "FakeDeathBan" + ChatColor.RESET + "] ";
    public static void sendMessage(String message){console.sendMessage(prefix + message);}
    private void sendDebug(String message){
        if(this.getConfig().getBoolean("debug")){
            console.sendMessage(prefix + ChatColor.YELLOW + " DEBUG " + ChatColor.RESET + message);
        }
    }

    public static boolean isImmortality = false;
    public static boolean isEnabled = true;
    public static BossBar immortalityBar = Bukkit.createBossBar(ChatColor.GREEN + "Immortality mode", BarColor.GREEN, BarStyle.SOLID);

    private final AutoComplete autoComplete = new AutoComplete();

    public static List<String> paths = new ArrayList<>();

    public static List<String> deathbanned = new ArrayList<>();
    public static List<String> frozen = new ArrayList<>();

    public ImmunityManager immunityManager;

    public void saveDeathbanned() {
        getConfig().set("deathbanned", deathbanned);
        saveConfig();
    }

    public void saveFrozen() {
        getConfig().set("frozen", frozen);
        saveConfig();
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        saveResource("lang/en_us.yml", false);
        saveResource("lang/sk_sk.yml", false);
        saveResource("lang/cs_cz.yml", false);
        Messages.setup(this);

        deathbanned = new ArrayList<>(getConfig().getStringList("deathbanned"));
        frozen = new ArrayList<>(getConfig().getStringList("frozen"));

        sendDebug(ChatColor.AQUA + "===Loading=== 1/5");
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
        immortalityBar.setVisible(false);
        immortalityBar.setTitle(ChatColor.GREEN + Messages.getMessage("pre-start"));
        immortalityBar.setProgress(1.0);

        sendDebug(ChatColor.GREEN + "Bossbar initalized");

        sendDebug(ChatColor.AQUA + "===Loading=== 2/5");
        // Register event listeners
        registerEvent(new MoveListener(this));
        registerEvent(new JoinQuitListener(this));
        registerEvent(new DeathListener(this));
        registerEvent(new InventoryListener());

        sendDebug(ChatColor.GREEN + "Event listeners registered");
        // LOAD STAGE 3
        sendDebug(ChatColor.AQUA + "===Loading=== 3/5");
        // Register immunity manager
        immunityManager = new ImmunityManager(this);
        // Register commands
        registerCommand("setspectate", new SetSpectate(this));
        registerCommand("revive", new Revive(this));
        registerCommand("spectate", new Spectate());
        registerCommand("freeze", new Freeze(this));
        registerCommand("unfreeze", new Unfreeze(this));
        registerCommand("defaultgamemode", new DefaultGamemode(this));
        registerCommand("check", new Check(this));
        registerCommand("setsound", new SetSound(this));
        registerCommand("immortality", new Immortality());
        registerCommand("setimmunity", new SetImmunity(this));
        registerCommand("togglefdb", new ToggleFDB());
        registerCommand("gui", new Gui());
        registerCommand("banlist", new BanList());
        registerCommand("language", new Language(this));

        sendDebug(ChatColor.GREEN + "Commands registered");
        sendDebug(ChatColor.AQUA + "===Loading=== 4/5");
        // Register update notification
        new UpdateChecker(this).check();

        sendDebug(ChatColor.AQUA + "Update notification registered");
        sendDebug(ChatColor.AQUA + "===Loading=== 5/5");
        // Register bStats
        int pluginId = 32939;
        Metrics metrics = new Metrics(this, pluginId);

        metrics.addCustomChart(new SimplePie("language", () -> switch (getConfig().getString("language")) {
            case "en_us" -> "English (US)";
            case "cs_cz" -> "Czech";
            case "sk_sk" -> "Slovak";
            case null, default -> "Unknown";
        }));
        metrics.addCustomChart(new SimplePie("debug_enabled", () -> getConfig().getBoolean("debug") ? "Debug enabled" : "Debug disabled"));
        sendDebug(ChatColor.AQUA + "bStats registered");
        sendMessage(ChatColor.GREEN + "===  Plugin loaded   ===");

        sendMessage(ChatColor.GOLD + "Support the developer <3 https://github.com/sponsors/sazenice");
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
