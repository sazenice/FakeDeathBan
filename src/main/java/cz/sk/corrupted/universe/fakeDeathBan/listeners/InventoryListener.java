package cz.sk.corrupted.universe.fakeDeathBan.listeners;

import cz.sk.corrupted.universe.fakeDeathBan.gui.Shared;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InventoryListener implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        String guiName = event.getView().getTitle();

        if (player.hasPermission("fakedeathban.gui")) {
            if (guiName.equals(Shared.title)) {
                event.setCancelled(true);
                ItemStack item = event.getCurrentItem();
                if (item != null && item.getType() == Material.PLAYER_HEAD) {
                    SkullMeta meta = (SkullMeta) item.getItemMeta();
                    if (meta != null) {
                        Player skullPlayer = Objects.requireNonNull(meta.getOwningPlayer()).getPlayer();
                        if (skullPlayer != null && !Bukkit.getOnlinePlayers().contains(skullPlayer)) {
                            item.setType(Material.BARRIER);
                            ItemMeta fMeta = item.getItemMeta();
                            List<String> lore = new ArrayList<>();
                            lore.add(ChatColor.RED + "Nastala chyba!");
                            lore.add(ChatColor.YELLOW + "\nHráč " + ChatColor.AQUA + skullPlayer.getName() + ChatColor.YELLOW + " není online!");
                            fMeta.setLore(lore);
                            fMeta.setItemName(ChatColor.BOLD + "" + ChatColor.RED + "X");
                            player.playSound(player, Sound.ENTITY_VILLAGER_NO, 1, 1);
                        }else{
                            Inventory inventory = Bukkit.createInventory(null, 27, Shared.title + " - " + ChatColor.AQUA + Objects.requireNonNull(skullPlayer).getName());
                            int index = 0;
                            // Beacon
                            ItemStack beacon = new ItemStack(Material.BEACON);
                            ItemMeta beaconMeta = Shared.getMetaData(beacon);
                            beacon.setItemMeta(beaconMeta);
                            // Diamond sword
                            ItemStack diamond_sword = new ItemStack(Material.DIAMOND_SWORD);
                            ItemMeta swordMeta = Shared.getMetaData(diamond_sword);
                            diamond_sword.setItemMeta(swordMeta);
                            // Ender eye
                            ItemStack ender_eye = new ItemStack(Material.ENDER_EYE);
                            ItemMeta eyeMeta = Shared.getMetaData(ender_eye);
                            ender_eye.setItemMeta(eyeMeta);
                            // Snowball
                            ItemStack snowball = new ItemStack(Material.SNOWBALL);
                            ItemMeta snowballMeta = Shared.getMetaData(snowball);
                            snowball.setItemMeta(snowballMeta);

                            // Totem of undying
                            ItemStack totem_of_undying = new ItemStack(Material.TOTEM_OF_UNDYING);
                            ItemMeta totemMeta = Shared.getMetaData(totem_of_undying);
                            totem_of_undying.setItemMeta(totemMeta);
                            // Hay block
                            ItemStack hay_block = new ItemStack(Material.HAY_BLOCK);
                            ItemMeta hayMeta = Shared.getMetaData(hay_block);
                            hay_block.setItemMeta(hayMeta);
                            // Piston
                            ItemStack piston = new ItemStack(Material.PISTON);
                            ItemMeta pistonMeta = Shared.getMetaData(piston);
                            piston.setItemMeta(pistonMeta);
                            // Snow block
                            ItemStack snow_block = new ItemStack(Material.SNOW_BLOCK);
                            ItemMeta snowMeta = Shared.getMetaData(snow_block);
                            snow_block.setItemMeta(snowMeta);
                            // Oak door
                            ItemStack door = new ItemStack(Material.OAK_DOOR);
                            ItemMeta doorMeta = Shared.getMetaData(door);
                            door.setItemMeta(doorMeta);

                            inventory.setItem(index, beacon); index++;
                            inventory.setItem(index, diamond_sword); index++;
                            inventory.setItem(index, ender_eye); index++;
                            inventory.setItem(index, snowball);
                            index = 9;
                            inventory.setItem(index, totem_of_undying); index++;
                            inventory.setItem(index, hay_block); index++;
                            inventory.setItem(index, piston); index++;
                            inventory.setItem(index, snow_block); index++;
                            inventory.setItem(index, door);

                            player.openInventory(inventory);
                        }
                    }
                } else if (item != null && item.getType() == Material.BARRIER) {
                    player.playSound(player, Sound.ENTITY_VILLAGER_NO, 1, 1);
                }
            } else if (guiName.contains(Shared.title + " - ")) {
                event.setCancelled(true);
                String titleNP = guiName.replace(Shared.title + " - ", "");
                String playerName = ChatColor.stripColor(titleNP);
                Player skullPlayer = Bukkit.getPlayer(playerName);
                if (skullPlayer != null){
                    Material clicked = Objects.requireNonNull(event.getCurrentItem()).getType();
                    String skullPlayerName = skullPlayer.getName();
                    switch (clicked){
                        case BEACON:
                            sendCommand(player, "undeathban", skullPlayerName, "");
                            break;
                        case DIAMOND_SWORD:
                            sendCommand(player, "kill", skullPlayerName, "");
                            break;
                        case ENDER_EYE:
                            sendCommand(player, "setspectate", skullPlayerName, "");
                            break;
                        case SNOWBALL:
                            sendCommand(player, "freeze", skullPlayerName, "");
                            break;
                        case TOTEM_OF_UNDYING:
                            sendCommand(player, "setimmunity", skullPlayerName, " deathban");
                            break;
                        case HAY_BLOCK:
                            sendCommand(player, "setimmunity", skullPlayerName, " prestart");
                            break;
                        case PISTON:
                            sendCommand(player, "setimmunity", skullPlayerName, " move");
                            break;
                        case SNOW_BLOCK:
                            sendCommand(player, "setimmunity", skullPlayerName, " freeze");
                            break;
                        case OAK_DOOR:
                            sendCommand(player, "setimmunity", skullPlayerName, " joinquit");
                            break;
                    }
                    if (clicked != Material.AIR){
                        player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                    }
                }
            }
        }
    }

    private static void sendCommand(Player sender, String name, String target, String arg){
        Bukkit.dispatchCommand(sender, name + " " + target + arg);
    }
}