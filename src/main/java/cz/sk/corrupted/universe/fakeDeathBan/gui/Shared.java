package cz.sk.corrupted.universe.fakeDeathBan.gui;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemRarity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Shared {
    public static String title = ChatColor.GREEN + "" + ChatColor.BOLD + "FDB";
    public static ItemMeta getMetaData(ItemStack itemType){
        ItemMeta meta = itemType.getItemMeta();
        if (meta == null) {
            throw new IllegalStateException("ItemMeta cannot be null");
        }
        switch (itemType.getType()){
            case OAK_DOOR:
                meta.setItemName("JoinQuit");
                List<String> doorLore = new ArrayList<>();
                doorLore.add(ChatColor.GREEN + "Click to allow Join/Quit messages");
                meta.setLore(doorLore);
                meta.setEnchantmentGlintOverride(true);
                meta.setRarity(ItemRarity.RARE);
                break;
            case SNOW_BLOCK:
                meta.setItemName("Freeze");
                List<String> snowLore = new ArrayList<>();
                snowLore.add(ChatColor.GREEN + "Click to allow freeze immunity");
                meta.setLore(snowLore);
                meta.setEnchantmentGlintOverride(true);
                meta.setRarity(ItemRarity.RARE);
                break;
            case PISTON:
                meta.setItemName("Move");
                List<String> pistonLore = new ArrayList<>();
                pistonLore.add(ChatColor.GREEN + "Click to allow movement");
                meta.setLore(pistonLore);
                meta.setEnchantmentGlintOverride(true);
                meta.setRarity(ItemRarity.RARE);
            case HAY_BLOCK:
                meta.setItemName("PreStart");
                List<String> hayLore = new ArrayList<>();
                hayLore.add(ChatColor.GREEN + "Click to allow PreStart immunity");
                meta.setLore(hayLore);
                meta.setEnchantmentGlintOverride(true);
                meta.setRarity(ItemRarity.RARE);
            case TOTEM_OF_UNDYING:
                meta.setItemName("Deathban");
                List<String> totemLore = new ArrayList<>();
                totemLore.add(ChatColor.GREEN + "Click to allow deathban immunity");
                meta.setLore(totemLore);
                meta.setEnchantmentGlintOverride(true);
                meta.setRarity(ItemRarity.RARE);
            case SNOWBALL:
                meta.setItemName("Freeze");
                List<String> snowballLore = new ArrayList<>();
                snowballLore.add(ChatColor.GREEN + "Click to freeze a player");
                meta.setLore(snowballLore);
                meta.setRarity(ItemRarity.UNCOMMON);
            case ENDER_EYE:
                meta.setItemName("Default spectator");
                List<String> eyeLore = new ArrayList<>();
                eyeLore.add(ChatColor.GREEN + "Click to set the default spector");
                meta.setLore(eyeLore);
                meta.setRarity(ItemRarity.UNCOMMON);
            case DIAMOND_SWORD:
                meta.setItemName("Kill");
                List<String> swordLore = new ArrayList<>();
                swordLore.add(ChatColor.GREEN + "Click to kill the player");
                meta.setLore(swordLore);
                meta.setRarity(ItemRarity.UNCOMMON);
            case BEACON:
                meta.setItemName("Revive");
                List<String> beaconLore = new ArrayList<>();
                beaconLore.add(ChatColor.GREEN + "Click to revive the player");
                beaconLore.add(ChatColor.YELLOW + "Works only if the target is dead");
                meta.setLore(beaconLore);
                meta.setRarity(ItemRarity.UNCOMMON);
            default:
                meta = itemType.getItemMeta();
        }
        return meta;
    }
}
