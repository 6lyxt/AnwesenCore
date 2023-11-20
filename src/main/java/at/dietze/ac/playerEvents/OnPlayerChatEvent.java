package at.dietze.ac.playerEvents;

import at.dietze.ac.interfaces.IStringInterface;
import org.bukkit.Bukkit;
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
        int dst = 100;

        Location playerLoc = e.getPlayer().getLocation();

        for (Player pl : Bukkit.getOnlinePlayers()) {
            if (pl.getLocation().distanceSquared(playerLoc) <= dst && !rawMsg.toLowerCase().startsWith("@all")) {
                pl.sendMessage(msg);
            } else if(rawMsg.toLowerCase().startsWith("@all")) {
                rawMsg = rawMsg.replace("@all", "");
                Bukkit.broadcastMessage(prefix + "§7[alle] <§a" + e.getPlayer().getDisplayName() + "§7>" + rawMsg);
            }
        }

        e.setCancelled(true);

    }
}
