package at.dietze.ac.playerEvents;

import at.dietze.ac.interfaces.IStringInterface;
import at.dietze.ac.pointsystem.PlaytimeFetch;
import at.dietze.ac.realism.thirst.ThirstSimulator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnPlayerLeaveEvent implements Listener, IStringInterface {

    /**
     * @param e PlayerQuitEvent
     */
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        PlaytimeFetch.setLastQuit(p, (System.currentTimeMillis()/1000L));

        e.setQuitMessage(prefix + "§9" + p.getDisplayName() + "§a verlässt das Anwesen.");
        ThirstSimulator.stopAndRequeue(p, false);
    }
}
