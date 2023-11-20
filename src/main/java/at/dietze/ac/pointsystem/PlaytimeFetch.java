package at.dietze.ac.pointsystem;

import at.dietze.ac.Core;
import at.dietze.ac.interfaces.IStringInterface;
import org.bukkit.entity.Player;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Objects;

/**
 * helper class
 */
public class PlaytimeFetch implements IStringInterface {


    /**
     * @param p Player
     * @return boolean
     */
    public static boolean hasJoinedToday(Player p) {
        long previousJoin = PlaytimeFetch.getLastJoin(p);
        Instant instant = Instant.ofEpochSecond(previousJoin);
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate date = instant.atZone(zoneId).toLocalDate();
        LocalDate currentDate = LocalDate.now(zoneId);
        return date.getDayOfYear() == currentDate.getDayOfYear();
    }

    public static long getLastJoin(Player p) {
        if(Core.getPlugin().getConfig().get("lastjoin_" + p.getUniqueId()) != null) {
            return ((Number) Core.getPlugin().getConfig().get("lastjoin_" + p.getUniqueId())).longValue();
        }

        return 0L;
    }

    /**
     * @param p Player
     * @param time long
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
        long joinTime = getLastJoin(p);

        long playtime = ((System.currentTimeMillis()/1000L) - joinTime);

        if (Core.getPlugin().getConfig().get("playtime_" + p.getUniqueId()) != null) {
            playtime += ((Number) Core.getPlugin().getConfig().get("playtime_" + p.getUniqueId())).longValue();
        }

        Core.getPlugin().getConfig().set("playtime_" + p.getUniqueId(), playtime);
        Core.getPlugin().saveConfig();
        Core.getPlugin().reloadConfig();
    }

    /**
     * @param p
     * @return
     */
    public static int getPlayTimeHours(Player p) {
        if(Core.getPlugin().getConfig().get("playtime_" + p.getUniqueId()) != null) {
            long timestamp = ((Number) Objects.requireNonNull(Core.getPlugin().getConfig().get("playtime_" + p.getUniqueId()))).longValue();

          return (int) ((timestamp) / 3600);
        }
        return 0;
    }

    /**
     * @param p Player
     * @return
     */
    public static String getPlayTime(Player p) {
        if(Core.getPlugin().getConfig().get("playtime_" + p.getUniqueId()) != null) {

            long timestamp = ((Number) Core.getPlugin().getConfig().get("playtime_" + p.getUniqueId())).longValue();

            int playtimeHour = (int)((timestamp) / 3600);
            int playtimeMin = (int) (((timestamp) / 60) % 60);

            return playtimeHour + "h " + playtimeMin + "m";
        } else {
            return "§cEs wurde noch keine Spielzeit aufgezeichnet.";
        }
    }

}
