package at.dietze.ac.playerEvents;

import at.dietze.ac.Core;
import at.dietze.ac.interfaces.IStringInterface;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Team;

import java.util.Objects;

public class OnPlayerJoinEvent implements Listener, IStringInterface {

    /**
     * @param e PlayerEvent
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        if(Core.getPlugin().getConfig().get("customname_" + p.getUniqueId()) != null) {
            p.setDisplayName(String.valueOf(Core.getPlugin().getConfig().get("customname_" + p.getUniqueId())));
        }

        Team team;

        if(Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard().getTeam("mainTeam") != null) {
            team = Bukkit.getScoreboardManager().getMainScoreboard().getTeam("mainTeam");
        } else {
            team = Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard().registerNewTeam("mainTeam");
        }

        assert team != null;
        team.setNameTagVisibility(NameTagVisibility.NEVER);

        team.addEntry(p.getName());

        p.setPlayerListName(null);
        e.setJoinMessage(prefix + "§9" + p.getDisplayName() + "§a betritt das Anwesen.");

        if(!p.hasPlayedBefore()) {
            p.sendMessage(prefix + "§a----------------");
            p.sendMessage(prefix + "§Willkommen auf dem Anwesen Minecraft Server!");
            p.sendMessage(prefix + "§aBitte wirf einen Blick auf unser Regelwerk,");
            p.sendMessage(prefix + "§awelches du auf dem Anwesen Discord Server findest.");
            p.sendMessage(prefix + "§aVergiss nicht, einen eigenen Nicknamen mit /setnick zu setzen!");
            p.sendMessage(prefix + "§Viel Spaß auf dem Server!");
            p.sendMessage(prefix + "§a----------------");
        }
    }
}
