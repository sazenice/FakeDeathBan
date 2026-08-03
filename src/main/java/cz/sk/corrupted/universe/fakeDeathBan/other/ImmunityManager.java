package cz.sk.corrupted.universe.fakeDeathBan.other;

import cz.sk.corrupted.universe.fakeDeathBan.FakeDeathBan;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ImmunityManager {

    public static final String[] IMMUNITY_TYPES = {"deathban", "freeze", "move", "joinquit", "immortality"};

    private final FakeDeathBan plugin;
    private final File file;
    private final YamlConfiguration data;
    private final Map<UUID, Map<String, PermissionAttachment>> attachments = new HashMap<>();

    public ImmunityManager(FakeDeathBan plugin) {
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder(), "immunities.yml");
        this.data = YamlConfiguration.loadConfiguration(this.file);
    }

    public boolean hasImmunity(UUID uuid, String type) {
        List<String> list = data.getStringList("immunity." + type);
        return list.contains(uuid.toString());
    }

    public void setImmunity(UUID uuid, String type, boolean value) {
        List<String> list = new ArrayList<>(data.getStringList("immunity." + type));
        if (value) {
            if (!list.contains(uuid.toString())) list.add(uuid.toString());
        } else {
            list.remove(uuid.toString());
        }
        data.set("immunity." + type, list);
        save();
    }

    public void applyImmunities(Player player) {
        UUID uuid = player.getUniqueId();
        removeAttachments(uuid);
        for (String type : IMMUNITY_TYPES) {
            String node = "fakedeathban.bypass." + type;
            if (hasImmunity(uuid, type) && !player.hasPermission(node)) {
                attach(player, node);
            }
        }
    }

    public void grantImmunity(Player player, String type) {
        String node = "fakedeathban.bypass." + type;
        if (!player.hasPermission(node)) {
            attach(player, node);
        }
    }

    public void revokeImmunity(Player player, String type) {
        removeAttachment(player.getUniqueId(), "fakedeathban.bypass." + type);
    }

    public void removeAttachments(UUID uuid) {
        Map<String, PermissionAttachment> map = attachments.remove(uuid);
        if (map == null) return;
        Player player = Bukkit.getPlayer(uuid);
        for (PermissionAttachment attachment : map.values()) {
            if (player != null) player.removeAttachment(attachment);
        }
    }

    public void removeAllAttachments() {
        for (UUID uuid : new ArrayList<>(attachments.keySet())) {
            removeAttachments(uuid);
        }
    }

    private void attach(Player player, String node) {
        PermissionAttachment attachment = player.addAttachment(plugin);
        attachment.setPermission(node, true);
        attachments.computeIfAbsent(player.getUniqueId(), k -> new HashMap<>()).put(node, attachment);
    }

    private void removeAttachment(UUID uuid, String node) {
        Map<String, PermissionAttachment> map = attachments.get(uuid);
        if (map == null) return;
        PermissionAttachment attachment = map.remove(node);
        if (attachment != null) {
            Player player = Bukkit.getPlayer(uuid);
            if (player != null) player.removeAttachment(attachment);
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
