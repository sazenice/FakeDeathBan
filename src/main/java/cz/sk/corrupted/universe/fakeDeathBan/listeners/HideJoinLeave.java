package cz.sk.corrupted.universe.fakeDeathBan.listeners;

import cz.sk.corrupted.universe.fakeDeathBan.FakeDeathBan;
import cz.sk.corrupted.universe.fakeDeathBan.files.Messages;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class HideJoinLeave implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        FakeDeathBan.console.sendMessage(FakeDeathBan.prefix + ChatColor.AQUA + Messages.getMessage("join-silenced"));
        event.setJoinMessage(null);
    }
    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        FakeDeathBan.console.sendMessage(FakeDeathBan.prefix + ChatColor.AQUA + Messages.getMessage("leave-silenced"));
        event.setQuitMessage(null);
    }
}
