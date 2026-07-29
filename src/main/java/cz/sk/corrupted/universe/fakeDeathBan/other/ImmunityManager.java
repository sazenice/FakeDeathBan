package cz.sk.corrupted.universe.fakeDeathBan.other;

import cz.sk.corrupted.universe.fakeDeathBan.FakeDeathBan;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImmunityManager {

    public static final String[] IMMUNITY_TYPES = {"deathban", "freeze", "move", "joinquit", "immortality", "pre-start"};

    private final FakeDeathBan plugin;
    private final File file;
    private final YamlConfiguration data;

    public ImmunityManager(FakeDeathBan plugin) {
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder(), "immunities.yml");
        this.data = YamlConfiguration.loadConfiguration(this.file);
    }

    public boolean hasImmunity(String uuid, String type) {
        List<String> list = data.getStringList("immunity." + type);
        return list.contains(uuid);
    }

    public void setImmunity(String uuid, String type, boolean value) {
        List<String> list = new ArrayList<>(data.getStringList("immunity." + type));
        if (value) {
            if (!list.contains(uuid)) list.add(uuid);
        } else {
            list.remove(uuid);
        }
        data.set("immunity." + type, list);
        save();
    }

    public void applyImmunities(Player player) {
        String uuid = player.getUniqueId().toString();
        for (String type : IMMUNITY_TYPES) {
            if (hasImmunity(uuid, type) && !player.hasPermission("fakedeathban.bypass." + type)) {
                String node = "fakedeathban.bypass." + type;
                PermissionAttachment attachment = player.addAttachment(plugin);
                attachment.setPermission(node, true);
            }
        }
    }

    private void save() {
        try {
            data.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
