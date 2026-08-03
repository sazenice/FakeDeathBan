package cz.sk.corrupted.universe.fakeDeathBan.commands;

import cz.sk.corrupted.universe.fakeDeathBan.FakeDeathBan;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jspecify.annotations.NonNull;

public class Immortality implements CommandExecutor {
    private final FakeDeathBan plugin;

    public Immortality(FakeDeathBan plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {

        if (!FakeDeathBan.isImmortality){
            Bukkit.dispatchCommand(sender, "revive");
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission("fakedeathban.bypass.immortality")){continue;}
                player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, PotionEffect.INFINITE_DURATION, 0));
                player.setInvulnerable(!plugin.getConfig().getBoolean("damage-immortal"));
                player.sendTitle(ChatColor.GREEN + "Immortality", ChatColor.GREEN + "Immortality ON!", 10, 40, 10);
                player.playSound(player, Sound.BLOCK_BEACON_ACTIVATE, SoundCategory.MASTER,  1, 1);
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "Immortality ON!"));
                FakeDeathBan.immortalityBar.setVisible(true);
            }
            sender.sendMessage(FakeDeathBan.prefix + ChatColor.GREEN + "Immortality ON");
        }else{
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission("fakedeathban.bypass.immortality")){continue;}
                player.removePotionEffect(PotionEffectType.SATURATION);
                player.removePotionEffect(PotionEffectType.REGENERATION);
                player.setInvulnerable(false);
                player.sendTitle(ChatColor.YELLOW + "Immortality", ChatColor.YELLOW + "Immortality OFF!", 10, 30, 10);
                player.playSound(player, Sound.BLOCK_BEACON_DEACTIVATE, SoundCategory.MASTER,  1, 1);
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED + "Immortality OFF!"));
                FakeDeathBan.immortalityBar.setVisible(false);
            }
            sender.sendMessage(FakeDeathBan.prefix + ChatColor.YELLOW + "Immortality OFF");
        }

        FakeDeathBan.isImmortality = !FakeDeathBan.isImmortality;
        return true;
    }
}
