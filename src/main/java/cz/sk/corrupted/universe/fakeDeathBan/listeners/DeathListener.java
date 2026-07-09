package cz.sk.corrupted.universe.fakeDeathBan.listeners;

import cz.sk.corrupted.universe.fakeDeathBan.FakeDeathBan;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.List;

public class DeathListener implements Listener {

    private final FakeDeathBan plugin;

    public DeathListener(FakeDeathBan plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        if (!FakeDeathBan.isEnabled) {return;}
        Player player = e.getEntity();

        String soundString = plugin.getConfig().getString("death-sound");
        assert soundString != null;
        NamespacedKey key = NamespacedKey.fromString(soundString);

        Sound sound;

        // Pokud proměná soundStringu existuje
        if (key != null) {
            sound = Registry.SOUNDS.get(key);
        } else {
            sound = null;
        }

        List<String> deathbanned = plugin.getConfig().getStringList("deathbanned");

        // Pokud hráč má imunitu proti deathbanu
        if (player.hasPermission("fakedeathban.bypass.deathban")){
            return;
        }
        // Pokud hráč ještě nemá deathban
        if (!deathbanned.contains(player.getUniqueId().toString())) {
            deathbanned.add(player.getUniqueId().toString());
            plugin.getConfig().set("deathbanned", deathbanned);
            plugin.saveConfig();
        }
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            Bukkit.broadcastMessage("§e" + player.getName() + " left the game");
            player.setGameMode(GameMode.SPECTATOR);
            if (sound != null){
                player.getWorld().playSound(e.getEntity().getLastDeathLocation(), sound, 5f, 1);
            }
        }, 2L);
    }
}