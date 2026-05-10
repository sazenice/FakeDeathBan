package cz.sk.corrupted.universe.fakeDeathBan.commands;

import cz.sk.corrupted.universe.fakeDeathBan.FakeDeathBan;
import cz.sk.corrupted.universe.fakeDeathBan.gui.Shared;
import cz.sk.corrupted.universe.fakeDeathBan.other.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jspecify.annotations.NonNull;

public class Gui implements CommandExecutor {
    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        if (sender instanceof Player player){
            Inventory inventory = Bukkit.createInventory(null, 27, Shared.title);
            for (Player p : Bukkit.getOnlinePlayers()){
                if (p != null){
                    ItemStack head = new ItemStack(Material.PLAYER_HEAD);
                    SkullMeta meta = (SkullMeta) head.getItemMeta();
                    if (meta != null){
                        meta.setOwnerProfile(p.getPlayerProfile());
                        meta.setItemName(p.getName());
                    }
                    head.setItemMeta(meta);

                    inventory.addItem(head);
                }
            }


            player.openInventory(inventory);
        }else{
            sender.sendMessage(FakeDeathBan.prefix + ChatColor.RED + Messages.getMessage("p-f"));
        }
        return true;
    }
}
