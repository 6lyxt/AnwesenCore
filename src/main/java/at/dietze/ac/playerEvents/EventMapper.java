package at.dietze.ac.playerEvents;

import at.dietze.ac.interfaces.IStringInterface;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;

public class EventMapper implements Listener, IStringInterface {

    /**
     * @param e PlayerEvent
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        e.setJoinMessage(prefix + "§9" + p.getDisplayName() + "§a betritt das Anwesen.");

        if(!p.hasPlayedBefore()) {
            p.sendMessage(prefix + "§a----------------");
            p.sendMessage(prefix + "§Willkommen auf dem Anwesen Minecraft Server!");
            p.sendMessage(prefix + "§aBitte wirf einen Blick auf unser Regelwerk,");
            p.sendMessage(prefix + "§awelches du auf dem Anwesen Discord Server findest.");
            p.sendMessage(prefix + "§Viel Spaß auf dem Server!");
            p.sendMessage(prefix + "§a----------------");
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        e.setQuitMessage(prefix + "§9" + p.getDisplayName() + "§a verlässt das Anwesen.");
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity().getPlayer();

        e.setDeathMessage(prefix + "§9 " + Objects.requireNonNull(p).getDisplayName() + "§c ist gestorben.");
    }
}
