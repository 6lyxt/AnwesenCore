package at.dietze.ac.pointsystem;

import at.dietze.ac.mysql.SQLConnector;
import org.bukkit.entity.Player;

/**
 * @TODO: implement point system & shop
 */
public class PointSystem {

    public static void addPoints(Player p, int count) {
        SQLConnector conn = new SQLConnector().connect();

        String uuid = String.valueOf(p.getUniqueId());

        if(count > 0) {
            // execute sql statement
            // count - 1
        }
    }

    public static void removePoints(Player p, int count) {
        SQLConnector conn = new SQLConnector().connect();

        String uuid = String.valueOf(p.getUniqueId());

        if(count > 0) {
            // execute sql statement
            // count - 1
        }
    }

}
