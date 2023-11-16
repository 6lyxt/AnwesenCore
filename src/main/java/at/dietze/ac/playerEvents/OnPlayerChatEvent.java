package at.dietze.ac.playerEvents;

import at.dietze.ac.interfaces.IStringInterface;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class OnPlayerChatEvent implements Listener, IStringInterface {

    /**
     * @param e AsyncPlayerChatEvent
     */
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        String rawMsg = e.getMessage();
        String msg = prefix + "§7[privat] <§a" + e.getPlayer().getDisplayName() + "§7> " + rawMsg;

        // hardcoded
        int dst = 25;

        Location playerLoc = e.getPlayer().getLocation();

        e.setCancelled(true);

        for (Player pl : e.getRecipients()) {
            if (pl.getLocation().distance(playerLoc) <= dst) {
                pl.sendMessage(msg);
            } else if (rawMsg.toLowerCase().startsWith("@all")) {
                rawMsg = rawMsg.replace("@all", "");
                pl.sendMessage(prefix + "§7[alle] <§a" + e.getPlayer().getDisplayName() + "§7>" + rawMsg);
            }
        }
    }
}
