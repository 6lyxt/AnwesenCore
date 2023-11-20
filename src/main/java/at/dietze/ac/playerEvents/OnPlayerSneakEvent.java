package at.dietze.ac.playerEvents;

import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

/**
 *
 */
public class OnPlayerSneakEvent implements Listener {

    /**
     * @param e Event
     */
    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e) {
        Player p = e.getPlayer();

        if(p.getVehicle() instanceof Arrow) {
            p.getVehicle().remove();
            Location loc = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 0.4D, p.getLocation().getZ(), p.getLocation().getYaw(), p.getLocation().getPitch());
            p.teleport(loc);
        }
    }
}
