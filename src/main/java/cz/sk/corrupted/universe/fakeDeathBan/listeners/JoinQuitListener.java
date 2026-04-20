package cz.sk.corrupted.universe.fakeDeathBan.listeners;

import cz.sk.corrupted.universe.fakeDeathBan.FakeDeathBan;
import cz.sk.corrupted.universe.fakeDeathBan.files.Messages;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class JoinQuitListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        if (!FakeDeathBan.isEnabled) {return;}
        FakeDeathBan.console.sendMessage(FakeDeathBan.prefix + ChatColor.AQUA + Messages.getMessage("join-s"));
        Player player = event.getPlayer();

        // Pokud nemá oprávnění ukazovat připojovací zprávy
        if (!player.hasPermission("fakedeathban.bypass.joinquit")){
            event.setJoinMessage(null);
        }

        // Pokud je zapnutý režim lobby
        if (FakeDeathBan.isLobby){
            player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, PotionEffect.INFINITE_DURATION, 255));
            player.setInvulnerable(true);

        // Pokud není zapnutý režim lobby a hráč je v režimu lobby
        } else if (player.isInvulnerable() && !FakeDeathBan.isLobby){
            player.removePotionEffect(PotionEffectType.SATURATION);
            player.setInvulnerable(false);
        }
    }
    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        FakeDeathBan.console.sendMessage(FakeDeathBan.prefix + ChatColor.AQUA + Messages.getMessage("leave-s"));

        // Pokud nemá oprávnění ukazovat odpojovací zprávy
        if (!event.getPlayer().hasPermission("fakedeathban.bypass.joinquit")){
            event.setQuitMessage(null);
        }
    }
}
