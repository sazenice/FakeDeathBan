package cz.sk.corrupted.universe.fakeDeathBan.listeners;

import cz.sk.corrupted.universe.fakeDeathBan.FakeDeathBan;
import cz.sk.corrupted.universe.fakeDeathBan.other.Messages;
import cz.sk.corrupted.universe.fakeDeathBan.other.UpdateChecker;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class JoinQuitListener implements Listener {

    private final FakeDeathBan plugin;

    public JoinQuitListener(FakeDeathBan plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        FakeDeathBan.immortalityBar.addPlayer(event.getPlayer());
        if (!FakeDeathBan.isEnabled) {return;}
        FakeDeathBan.console.sendMessage(FakeDeathBan.prefix + ChatColor.AQUA + Messages.getMessage("join-s"));
        Player player = event.getPlayer();

        if (!player.hasPermission("fakedeathban.bypass.joinquit")){
            event.setJoinMessage(null);
        }

        if(UpdateChecker.UPDATE_AVAILABLE && player.isOp()){
            player.sendMessage(UpdateChecker.UPDATE_MESSAGE);
        }

        plugin.applyImmunities(player);

        if (FakeDeathBan.isImmortality){
            if (player.hasPermission("fakedeathban.bypass.immortality")){return;}
            player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, PotionEffect.INFINITE_DURATION, 255));
            player.setInvulnerable(true);
        }
    }
    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        FakeDeathBan.immortalityBar.removePlayer(event.getPlayer());
        if (!FakeDeathBan.isEnabled) {return;}
        FakeDeathBan.console.sendMessage(FakeDeathBan.prefix + ChatColor.AQUA + Messages.getMessage("leave-s"));

        if (!event.getPlayer().hasPermission("fakedeathban.bypass.joinquit")){
            event.setQuitMessage(null);
        }
    }
}
