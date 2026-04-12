package cz.sk.corrupted.universe.fakeDeathBan;

import cz.sk.corrupted.universe.fakeDeathBan.commands.SetSpectate;
import cz.sk.corrupted.universe.fakeDeathBan.commands.Spectate;
import cz.sk.corrupted.universe.fakeDeathBan.commands.UnDeathban;
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

import java.util.Objects;

public final class FakeDeathBan extends JavaPlugin implements Listener {

    public static final ConsoleCommandSender console = Bukkit.getConsoleSender();
    public static final String prefix = "[" + ChatColor.BOLD + ChatColor.LIGHT_PURPLE + "FakeDeathBan" + ChatColor.RESET + "] ";
    public static void sendMessage(String message){console.sendMessage(prefix + message);}

    @Override
    public void onEnable() {
        Messages.setup(this);
        Messages.get().options().copyDefaults(true);
        Messages.save();

        sendMessage(ChatColor.AQUA + Messages.getMessage("enabling"));

        registerEvent(new MoveListener(this));
        registerEvent(new HideJoinLeave());
        registerEvent(new DeathListener(this));

        registerCommand("setspectate", new SetSpectate(this));
        registerCommand("undeathban", new UnDeathban(this));
        registerCommand("spectate", new Spectate());
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
        sendMessage(ChatColor.GREEN + Messages.getMessage("r-command", name) + ChatColor.YELLOW + name);
        Objects.requireNonNull(getCommand(name)).setExecutor(command);
    }
}