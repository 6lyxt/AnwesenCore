package at.dietze.ac.playerEvents;

import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class OnEntityDamage implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e) {
        if(e.getDamager() instanceof Snowball && e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            p.damage(0.5);
            p.setFreezeTicks(5);
        }
    }
}
