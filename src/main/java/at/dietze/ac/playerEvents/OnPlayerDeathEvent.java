package at.dietze.ac.playerEvents;

import at.dietze.ac.interfaces.IStringInterface;
import at.dietze.ac.pointsystem.PointSystem;
import at.dietze.ac.realism.thirst.ThirstSimulator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Objects;

public class OnPlayerDeathEvent implements Listener, IStringInterface {

    /**
     * @param e PlayerDeathEvent
     */
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity().getPlayer();

        if(e.getEntity().getKiller() != null) {
            PointSystem.addPoints(e.getEntity().getKiller(), 1);
        }

        PointSystem.removePoints(Objects.requireNonNull(p), 1, true);

        e.setDeathMessage(prefix + "§9 " + Objects.requireNonNull(p).getDisplayName() + "§c ist gestorben.");
        ThirstSimulator.stopAndRequeue(p, true);
    }
}
