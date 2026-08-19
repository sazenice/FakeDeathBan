package cz.sk.corrupted.universe.fakeDeathBan.listeners;

import cz.sk.corrupted.universe.fakeDeathBan.FakeDeathBan;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffectType;

public class DeathListener implements Listener {
    public static String name = "DeathListener";

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

        if (player.getKiller() != null && plugin.getConfig().getBoolean("hide-invis")){
            if (player.getKiller().hasPotionEffect(PotionEffectType.INVISIBILITY)){
                e.setDeathMessage(player.getName() + " was killed by " + ChatColor.MAGIC + player.getKiller().getName());
            }
        }
        if (!FakeDeathBan.deathbanned.contains(player.getUniqueId().toString())) {
            FakeDeathBan.deathbanned.add(player.getUniqueId().toString());
            plugin.saveDeathbanned();
        }
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            Bukkit.broadcastMessage(ChatColor.YELLOW + player.getName() + " left the game");
            player.setGameMode(GameMode.SPECTATOR);
            if (deathSound != null){
                player.getWorld().playSound(e.getEntity().getLastDeathLocation(), deathSound, 5f, 1);
            }
        }, 2L);
    }
}
