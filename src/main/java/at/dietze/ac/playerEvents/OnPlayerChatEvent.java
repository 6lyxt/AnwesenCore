package at.dietze.ac.playerEvents;

import at.dietze.ac.interfaces.IStringInterface;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Objects;

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

        for (Player pl : Objects.requireNonNull(Bukkit.getServer().getWorld(e.getPlayer().getWorld().getUID())).getPlayers()) {
            if (pl.getLocation().distanceSquared(playerLoc) <= dst && !rawMsg.toLowerCase().startsWith("@all")) {
                pl.sendMessage(msg);
                Bukkit.getConsoleSender().sendMessage(msg);
            } else if(rawMsg.toLowerCase().startsWith("@all")) {
                Bukkit.getConsoleSender().sendMessage(prefix + "§7[alle] <§a" + e.getPlayer().getDisplayName() + "§7>" + rawMsg.replace("@all", ""));
                Bukkit.broadcastMessage(prefix + "§7[alle] <§a" + e.getPlayer().getDisplayName() + "§7>" + rawMsg.replace("@all", ""));
                break;
            }
        }

        e.setCancelled(true);

    }
}
