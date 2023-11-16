package at.dietze.ac.playerEvents;

import at.dietze.ac.interfaces.IStringInterface;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnPlayerLeaveEvent implements Listener, IStringInterface {

    /**
     * @param e
     */
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        e.setQuitMessage(prefix + "§9" + p.getDisplayName() + "§a verlässt das Anwesen.");
    }
}