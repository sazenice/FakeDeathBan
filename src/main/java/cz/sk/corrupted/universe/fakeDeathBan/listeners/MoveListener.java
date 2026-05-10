package cz.sk.corrupted.universe.fakeDeathBan.listeners;

import cz.sk.corrupted.universe.fakeDeathBan.FakeDeathBan;
import cz.sk.corrupted.universe.fakeDeathBan.other.Messages;
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
        if (!FakeDeathBan.isEnabled) {return;}
        Player player = e.getPlayer();
        List<String> deathbanned = plugin.getConfig().getStringList("deathbanned");
        List<String> frozen = plugin.getConfig().getStringList("frozen");

        boolean isDeathbanned = deathbanned.contains(player.getUniqueId().toString());
        boolean canBypassMove = player.hasPermission("fakedeathban.bypass.move");

        if (isDeathbanned && !canBypassMove) {
            if (frozen.contains(player.getUniqueId().toString())
                    && !player.hasPermission("fakedeathban.bypass.freeze")) {
                player.sendMessage(FakeDeathBan.prefix + ChatColor.RED + Messages.getMessage("freeze-2-f"));
                e.setCancelled(true);
                return;
            }

            String defaultPlayer = plugin.getConfig().getString("default-spectator");
            Player target = defaultPlayer != null ? Bukkit.getPlayer(defaultPlayer) : null;

            if (target != null && target != player) {
                player.setGameMode(GameMode.SPECTATOR);
                player.setSpectatorTarget(target);
            } else {
                player.sendMessage(FakeDeathBan.prefix + ChatColor.RED + Messages.getMessage("move-f"));
                e.setCancelled(true);
            }
        }
    }
}