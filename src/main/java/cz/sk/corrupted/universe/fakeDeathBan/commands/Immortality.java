package cz.sk.corrupted.universe.fakeDeathBan.commands;

import cz.sk.corrupted.universe.fakeDeathBan.FakeDeathBan;
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

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {

        if (!FakeDeathBan.isPreStart){
            Bukkit.dispatchCommand(sender, "undeathban");
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission("fakedeathban.bypass.immortality")){return true;}
                player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, PotionEffect.INFINITE_DURATION, 255));
                player.setInvulnerable(true);
                player.sendTitle(ChatColor.GREEN + "Immortality", ChatColor.GREEN + "Immortality ON!", 10, 40, 10);
                player.playSound(player, Sound.BLOCK_BEACON_ACTIVATE, SoundCategory.MASTER,  1, 1);
                FakeDeathBan.preStartBar.setVisible(true);
            }
            sender.sendMessage(FakeDeathBan.prefix + ChatColor.GREEN + "Immortality ON");
        }else{
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission("fakedeathban.bypass.pre-start")){return true;}
                player.removePotionEffect(PotionEffectType.SATURATION);
                player.setInvulnerable(false);
                player.sendTitle(ChatColor.YELLOW + "Immortality", ChatColor.YELLOW + "Immortality OFF!", 10, 30, 10);
                player.playSound(player, Sound.BLOCK_BEACON_DEACTIVATE, SoundCategory.MASTER,  1, 1);
                FakeDeathBan.preStartBar.setVisible(false);
            }
            sender.sendMessage(FakeDeathBan.prefix + ChatColor.YELLOW + "Immortality OFF");
        }

        FakeDeathBan.isPreStart = !FakeDeathBan.isPreStart;
        return true;
    }
}
