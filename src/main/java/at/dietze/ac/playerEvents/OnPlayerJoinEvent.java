package at.dietze.ac.playerEvents;

import at.dietze.ac.Core;
import at.dietze.ac.interfaces.IStringInterface;
import at.dietze.ac.realism.thirst.ThirstTask;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Team;

import java.util.Objects;

public class OnPlayerJoinEvent implements Listener, IStringInterface {

    /**
     * @param e PlayerJoinEvent
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        // nicknames
        if(Core.getPlugin().getConfig().get("customname_" + p.getUniqueId()) != null) {
            p.setDisplayName(String.valueOf(Core.getPlugin().getConfig().get("customname_" + p.getUniqueId())));
        }

        // player tag invisibility
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
        ThirstTask thirstTask = new ThirstTask(p);
        thirstTask.start();


        if(!p.hasPlayedBefore()) {
            p.sendMessage(prefix + "§a----------------");
            p.sendMessage(prefix + "§Willkommen auf dem Anwesen Minecraft Server!");
            p.sendMessage(prefix + "§aBitte wirf einen Blick auf unser Regelwerk,");
            p.sendMessage(prefix + "§awelches du auf dem Anwesen Discord Server findest.");
            p.sendMessage(prefix + "§aVergiss nicht, einen eigenen Nicknamen mit /setnick zu setzen!");
            p.sendMessage(prefix + "§Viel Spaß auf dem Server!");
            p.sendMessage(prefix + "§a----------------");

            Location spawn =  (Location) Core.getPlugin().getConfig().get("spawn");

            if(spawn != null) {
                p.teleport(spawn);
            } else {
                p.sendMessage(prefix + "§cEs wurde noch kein Spawn gesetzt. Bitte kontaktiere einen Administrator.");
            }
        } else {
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(prefix + "§aWillkommen zurück!"));
        }
    }
}
