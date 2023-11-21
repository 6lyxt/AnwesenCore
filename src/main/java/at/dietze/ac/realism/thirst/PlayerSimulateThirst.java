package at.dietze.ac.realism.thirst;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class PlayerSimulateThirst implements Listener {

    /**
     * @param e event
     * @return bool
     */
    public boolean isDrink(PlayerItemConsumeEvent e){
        return e.getItem().getType().equals(Material.POTION);
    }

    @EventHandler
    public void playerConsumeItem(PlayerItemConsumeEvent e) {
        Player p = e.getPlayer();

        if(isDrink(e)) {
            if(p.getHealth() <= 18) {
                p.setHealth(p.getHealth() + 2);
            }
            ThirstSimulator.stopAndRequeue(p, true);
        }
    }
}
