package cz.sk.corrupted.universe.fakeDeathBan.listeners;

import cz.sk.corrupted.universe.fakeDeathBan.FakeDeathBan;
import cz.sk.corrupted.universe.fakeDeathBan.other.Messages;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    private final FakeDeathBan plugin;

    public DeathListener(FakeDeathBan plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        if (!FakeDeathBan.isEnabled) {return;}
        Player player = e.getEntity();

        Sound sound = null;
        String soundString = plugin.getConfig().getString("death-sound");
        if (soundString != null) {
            NamespacedKey key = NamespacedKey.fromString(soundString);
            if (key != null) {
                sound = Registry.SOUNDS.get(key);
            }
        }
        final Sound deathSound = sound;

        if (player.hasPermission("fakedeathban.bypass.deathban")){
            return;
        }
        if (!FakeDeathBan.deathbanned.contains(player.getUniqueId().toString())) {
            FakeDeathBan.deathbanned.add(player.getUniqueId().toString());
            plugin.saveDeathbanned();
        }
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            Bukkit.broadcastMessage(ChatColor.YELLOW + Messages.getMessage("death-leave", player.getName()));
            player.setGameMode(GameMode.SPECTATOR);
            if (deathSound != null){
                player.getWorld().playSound(e.getEntity().getLastDeathLocation(), deathSound, 5f, 1);
            }
        }, 2L);
    }
}
