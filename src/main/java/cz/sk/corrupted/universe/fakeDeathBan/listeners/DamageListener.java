package cz.sk.corrupted.universe.fakeDeathBan.listeners;

import cz.sk.corrupted.universe.fakeDeathBan.FakeDeathBan;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DamageListener implements Listener {
    public static String name = "DamageListener";

    private final FakeDeathBan plugin;

    public DamageListener(FakeDeathBan plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event){
        if (!plugin.getConfig().getBoolean("damage-immortal") || !FakeDeathBan.isImmortality){ return; }
        if (event.getEntity() instanceof Player p){
            if (p.hasPermission("fakedeathban.bypass.immortality")){ return; }
            if (p.getHealth() - event.getDamage() <= 0){
                p.setHealth(1);
                event.setCancelled(true);
            }
            if (p.getHealth() - event.getDamage() <= 6){
                p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 5 * 20, 4));
                p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 5 * 20, 255));
            }
        }
    }

}
