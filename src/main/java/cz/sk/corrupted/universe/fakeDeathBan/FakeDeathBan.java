package cz.sk.corrupted.universe.fakeDeathBan;

import cz.sk.corrupted.universe.fakeDeathBan.commands.*;
import cz.sk.corrupted.universe.fakeDeathBan.listeners.DeathListener;
import cz.sk.corrupted.universe.fakeDeathBan.listeners.InventoryListener;
import cz.sk.corrupted.universe.fakeDeathBan.listeners.JoinQuitListener;
import cz.sk.corrupted.universe.fakeDeathBan.listeners.MoveListener;
import cz.sk.corrupted.universe.fakeDeathBan.other.AutoComplete;
import cz.sk.corrupted.universe.fakeDeathBan.other.Messages;
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

    public static boolean isPreStart = false;
    public static boolean isEnabled = true;
    public static BossBar preStartBar = Bukkit.createBossBar(ChatColor.GREEN + "Režim Immortality", BarColor.GREEN, BarStyle.SOLID);

    private final AutoComplete autoComplete = new AutoComplete();

    public static List<String> paths = new ArrayList<>();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        saveResource("lang/en_us.yml", false);
        saveResource("lang/cs_cz.yml", false);
        Messages.setup(this);

        sendMessage(ChatColor.AQUA + "===Loading=== 1/3");

        // Inicalizace proměných + setup
        paths.add("deathbanned");
        paths.add("frozen");
        paths.add("default-spectator");
        paths.add("default-gamemode");
        paths.add("death-sound");
        paths.add("revive-sound");
        paths.add("config-version");
        paths.add("language");

        preStartBar.setVisible(false);
        preStartBar.setTitle(ChatColor.GREEN + Messages.getMessage("pre-start"));
        preStartBar.setProgress(1.0);

        if (!Objects.equals(getConfig().getString("config-version"), getDescription().getVersion())){
            sendMessage(ChatColor.RED +
                    "config.yml version is not the same as the plugin version!" +
                    "\nSomething might be able to break." +
                    "\nDelete the plugin folder or change the plugin version to " + getConfig().getString("config-version"));
        }

        sendMessage(ChatColor.AQUA + "===Loading=== 2/3");

        // Registrace posluchačů
        registerEvent(new MoveListener(this));
        registerEvent(new JoinQuitListener());
        registerEvent(new DeathListener(this));
        registerEvent(new InventoryListener());

        sendMessage(ChatColor.AQUA + "===Loading=== 3/3");
        // Registrace příkazů

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

        sendMessage(ChatColor.GREEN + "===  Plugin loaded   ===");
    }

    @Override
    public void onDisable() {
        sendMessage(ChatColor.RED + Messages.getMessage("disabling"));
    }
    private void registerEvent(Listener listener){
        sendMessage(ChatColor.GREEN + Messages.getMessage("r-listener", listener.getClass().getSimpleName()) + ChatColor.YELLOW + listener);
        Bukkit.getPluginManager().registerEvents(listener, this);
    }
    private void registerCommand(String name, @NonNull CommandExecutor command){
        sendMessage(ChatColor.GREEN + Messages.getMessage("r-command", command.toString()) + ChatColor.YELLOW + name);
        Objects.requireNonNull(getCommand(name)).setTabCompleter(autoComplete);
        Objects.requireNonNull(getCommand(name)).setExecutor(command);
    }
}