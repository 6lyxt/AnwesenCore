package at.dietze.ac.pointsystem;

import at.dietze.ac.Core;
import at.dietze.ac.interfaces.IStringInterface;
import org.bukkit.entity.Player;

/**
 * helper class
 */
public class PlaytimeFetch implements IStringInterface {

    /**
     * @param p Player
     * @param time
     */
    public static void setLastJoin(Player p, long time) {
        Core.getPlugin().getConfig().set("lastjoin_" + p.getUniqueId(), time);
        Core.getPlugin().saveConfig();
        Core.getPlugin().reloadConfig();
    }

    /**
     * @param p Player
     */
    public static void setCurrentPlaytime(Player p) {
        long joinTime = ((Number) Core.getPlugin().getConfig().get("lastjoin_" + p.getUniqueId())).longValue();

        long playtime = ((System.currentTimeMillis()/1000L) - joinTime);

        if (Core.getPlugin().getConfig().get("playtime_" + p.getUniqueId()) != null) {
            playtime += ((Number) Core.getPlugin().getConfig().get("playtime_" + p.getUniqueId())).longValue();
        }

        Core.getPlugin().getConfig().set("playtime_" + p.getUniqueId(), playtime);
        Core.getPlugin().saveConfig();
        Core.getPlugin().reloadConfig();
    }

    /**
     * @param p Player
     * @return
     */
    public static String getPlayHours(Player p) {
        if(Core.getPlugin().getConfig().get("playtime_" + p.getUniqueId()) != null) {

            long timestamp = ((Number) Core.getPlugin().getConfig().get("playtime_" + p.getUniqueId())).longValue();

            return (int)((timestamp) / 3600) + "h " + (int) (((timestamp) / 60) % 60) + "m";
        } else {
            return "§cEs wurde noch keine Spielzeit aufgezeichnet.";
        }
    }

}
