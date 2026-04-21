package cz.sk.corrupted.universe.fakeDeathBan.other;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class AutoComplete implements TabCompleter {
    @SuppressWarnings("deprecation")
    @Override
    public List<String> onTabComplete(@NonNull CommandSender sender, Command cmd, @NonNull String alias, String @NonNull [] args) {
        String name = cmd.getName().toLowerCase();
        switch (name) {
            case "setsound":
                if (args.length == 1) {
                    String t = args[0] == null ? "" : args[0].toLowerCase();
                    List<String> completions = new ArrayList<>();
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (p.getName().toLowerCase().startsWith(t)) completions.add(p.getName());
                    }
                    return completions;
                } else if (args.length == 2) {
                    String token = args[1] == null ? "" : args[1].toLowerCase();
                    List<String> sounds = new ArrayList<>();

                    for (Sound s : Sound.values()) {
                        NamespacedKey key = s.getKeyOrThrow(); // Paper API
                        String soundName = key.toString(); // fallback
                        if (soundName.toLowerCase().startsWith(token)) sounds.add(soundName);
                    }
                    return sounds;
                }
                break;

            case "undeathban":
            case "setspectate":
            case "spectate":
            case "unfreeze":
            case "freeze":
                if (args.length > 0) {
                    String t = args[0] == null ? "" : args[0].toLowerCase();
                    List<String> completions = new ArrayList<>();
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (p.getName().toLowerCase().startsWith(t)) completions.add(p.getName());
                    }
                    return completions;
                }
                break;

            case "defaultgamemode":
                if (args.length == 1) {
                    String t = args[0] == null ? "" : args[0].toLowerCase();
                    return Stream.of("survival", "adventure", "creative", "spectator")
                            .filter(s -> s.startsWith(t))
                            .sorted()
                            .toList();
                }
                break;

            case "setimmunity":
                if (args.length == 1) {
                    String t = args[0] == null ? "" : args[0].toLowerCase();
                    List<String> completions = new ArrayList<>();
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (p.getName().toLowerCase().startsWith(t)) completions.add(p.getName());
                    }
                    return completions;
                } else if (args.length == 2) {
                    String t = args[1] == null ? "" : args[1].toLowerCase();
                    return Stream.of("deathban", "joinquit", "move", "freeze")
                            .filter(s -> s.startsWith(t))
                            .sorted()
                            .toList();
                }
                break;
        }

        return Collections.emptyList();
    }
}
