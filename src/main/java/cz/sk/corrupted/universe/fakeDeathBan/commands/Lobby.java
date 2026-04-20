package cz.sk.corrupted.universe.fakeDeathBan.commands;

import cz.sk.corrupted.universe.fakeDeathBan.FakeDeathBan;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Lobby implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Bukkit.dispatchCommand(sender, "undeathban");

        if (!FakeDeathBan.isLobby){
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, PotionEffect.INFINITE_DURATION, 255));
                player.setInvulnerable(true);
                player.sendTitle(ChatColor.GREEN + "Režim Lobby", ChatColor.GREEN + "Režim Lobby byl zapnut!", 10, 40, 10);
                player.playSound(player, Sound.BLOCK_BEACON_ACTIVATE, SoundCategory.MASTER,  1, 1);
                FakeDeathBan.lobbyBar.setVisible(true);
            }
            sender.sendMessage(FakeDeathBan.prefix + ChatColor.GREEN + "Režim lobby byl zapnut");
        }else{
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.removePotionEffect(PotionEffectType.SATURATION);
                player.setInvulnerable(false);
                player.sendTitle(ChatColor.YELLOW + "Režim Lobby", ChatColor.YELLOW + "Režim Lobby byl vypnut!", 10, 30, 10);
                player.playSound(player, Sound.BLOCK_BEACON_DEACTIVATE, SoundCategory.MASTER,  1, 1);
                FakeDeathBan.lobbyBar.setVisible(false);
            }
            sender.sendMessage(FakeDeathBan.prefix + ChatColor.YELLOW + "Režim lobby byl vypnut");
        }

        FakeDeathBan.isLobby = !FakeDeathBan.isLobby;
        return true;
    }
}
