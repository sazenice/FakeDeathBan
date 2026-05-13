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
                meta.setDisplayName("JoinQuit");
                List<String> doorLore = new ArrayList<>();
                doorLore.add(ChatColor.GREEN + "Click to allow Join/Quit messages");
                meta.setLore(doorLore);
                meta.setEnchantmentGlintOverride(true);
                meta.setRarity(ItemRarity.RARE);
                break;
            case SNOW_BLOCK:
                meta.setDisplayName("Freeze");
                List<String> snowLore = new ArrayList<>();
                snowLore.add(ChatColor.GREEN + "Click to allow freeze immunity");
                meta.setLore(snowLore);
                meta.setEnchantmentGlintOverride(true);
                meta.setRarity(ItemRarity.RARE);
                break;
            case PISTON:
                meta.setDisplayName("Move");
                List<String> pistonLore = new ArrayList<>();
                pistonLore.add(ChatColor.GREEN + "Click to allow movement");
                meta.setLore(pistonLore);
                meta.setEnchantmentGlintOverride(true);
                meta.setRarity(ItemRarity.RARE);
            case HAY_BLOCK:
                meta.setDisplayName("Immortality");
                List<String> hayLore = new ArrayList<>();
                hayLore.add(ChatColor.GREEN + "Click to allow Immortality immunity");
                meta.setLore(hayLore);
                meta.setEnchantmentGlintOverride(true);
                meta.setRarity(ItemRarity.RARE);
            case TOTEM_OF_UNDYING:
                meta.setDisplayName("Deathban");
                List<String> totemLore = new ArrayList<>();
                totemLore.add(ChatColor.GREEN + "Click to allow deathban immunity");
                meta.setLore(totemLore);
                meta.setEnchantmentGlintOverride(true);
                meta.setRarity(ItemRarity.RARE);
            case SNOWBALL:
                meta.setDisplayName("Freeze");
                List<String> snowballLore = new ArrayList<>();
                snowballLore.add(ChatColor.GREEN + "Click to freeze a player");
                meta.setLore(snowballLore);
                meta.setRarity(ItemRarity.UNCOMMON);
            case ENDER_EYE:
                meta.setDisplayName("Default spectator");
                List<String> eyeLore = new ArrayList<>();
                eyeLore.add(ChatColor.GREEN + "Click to set the default spector");
                meta.setLore(eyeLore);
                meta.setRarity(ItemRarity.UNCOMMON);
            case DIAMOND_SWORD:
                meta.setDisplayName("Kill");
                List<String> swordLore = new ArrayList<>();
                swordLore.add(ChatColor.GREEN + "Click to kill the player");
                meta.setLore(swordLore);
                meta.setRarity(ItemRarity.UNCOMMON);
            case BEACON:
                meta.setDisplayName("Revive");
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
