package at.dietze.ac.playerEvents;

import at.dietze.ac.interfaces.IStringInterface;
import at.dietze.ac.pointsystem.PointSystem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Objects;

public class OnPlayerDeathEvent implements Listener, IStringInterface {

    /**
     * @param e
     */
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity().getPlayer();

        PointSystem.removePoints(Objects.requireNonNull(p), 1);

        e.setDeathMessage(prefix + "§9 " + Objects.requireNonNull(p).getDisplayName() + "§c ist gestorben.");
    }
}
