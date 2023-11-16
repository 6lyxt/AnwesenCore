package at.dietze.ac.realism.thirst;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerSimulateThirst implements Listener {

    /**
     * @param e event
     * @return
     */
    public boolean isDrink(PlayerItemConsumeEvent e){
        return e.getItem().getType().equals(Material.POTION) && e.getItem().getDurability() == 0;
    }

    @EventHandler
    public void playerConsumeItem(PlayerItemConsumeEvent e) {
        Player p = e.getPlayer();

        if(isDrink(e)) {
            p.setHealth(p.getHealth() + 2);
            p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * 100, 1));
            ThirstSimulator.stopAndRequeue(p, true);
        }
    }
}
