package cz.sk.corrupted.universe.fakeDeathBan.other;

import cz.sk.corrupted.universe.fakeDeathBan.FakeDeathBan;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateChecker implements Listener {

    private static final String MODRINTH_API = "https://api.modrinth.com/v2/project/fakedeathban/version";
    private static final Pattern VERSION_PATTERN = Pattern.compile("\"version_number\"\\s*:\\s*\"([^\"]+)\"");
    public static boolean UPDATE_AVAILABLE = false;
    public static String UPDATE_MESSAGE = "";

    private final FakeDeathBan plugin;

    public UpdateChecker(FakeDeathBan plugin) {
        this.plugin = plugin;
    }

    public void check() {
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(MODRINTH_API))
                    .header("User-Agent", "FakeDeathBan/" + plugin.getDescription().getVersion())
                    .build();

            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenAccept(response -> {
                        if (response.statusCode() != 200) return;
                        String body = response.body();
                        Matcher matcher = VERSION_PATTERN.matcher(body);
                        if (matcher.find()) {
                            String latestVersion = matcher.group(1);
                            String currentVersion = plugin.getDescription().getVersion();
                            if (!latestVersion.equals(currentVersion)) {
                                Bukkit.getScheduler().runTask(plugin, () ->
                                    Bukkit.getConsoleSender().sendMessage(FakeDeathBan.prefix + ChatColor.GREEN +
                                        "New version available: " + ChatColor.YELLOW + latestVersion +
                                        ChatColor.GREEN + " (current: " + ChatColor.YELLOW + currentVersion + ChatColor.GREEN + ")" +
                                        "\n" + FakeDeathBan.prefix + ChatColor.GREEN + "Download at: " + ChatColor.AQUA +
                                        "https://modrinth.com/plugin/fakedeathban")
                                );
                                UPDATE_AVAILABLE = true;
                                UPDATE_MESSAGE = FakeDeathBan.prefix + ChatColor.GREEN +
                                        "New version available: " + ChatColor.YELLOW + latestVersion +
                                        ChatColor.GREEN + " (current: " + ChatColor.YELLOW + currentVersion + ChatColor.GREEN + ")" +
                                        "\n" + FakeDeathBan.prefix + ChatColor.GREEN + "Download at: " + ChatColor.AQUA +
                                        "https://modrinth.com/plugin/fakedeathban";
                            }
                        }
                    })
                    .exceptionally(t -> null);
        }
    }
}
