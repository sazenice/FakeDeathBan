package cz.sk.corrupted.universe.fakeDeathBan.listeners;

import cz.sk.corrupted.universe.fakeDeathBan.FakeDeathBan;
import cz.sk.corrupted.universe.fakeDeathBan.files.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.List;

public class MoveListener implements Listener {

    private final FakeDeathBan plugin;

    public MoveListener(FakeDeathBan plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        Player player = e.getPlayer();
        List<String> deathbanned = plugin.getConfig().getStringList("deathbanned");
        if (deathbanned.contains(player.getUniqueId().toString())) {
            player.setGameMode(GameMode.SPECTATOR);
            String defaultPlayer = plugin.getConfig().getString("default-spectator");

            if (defaultPlayer != null) {
                Player target = Bukkit.getPlayer(defaultPlayer);

                if (target == e.getPlayer()){ return; }

                if (target != null) {
                    player.setSpectatorTarget(target);
                    return;
                }
            }
            player.sendMessage(FakeDeathBan.prefix + ChatColor.RED + Messages.getMessage("move-not-allowed"));
            e.setCancelled(true);
        }
    }
}