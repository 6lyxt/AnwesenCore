package at.dietze.ac.pointsystem;

import at.dietze.ac.Core;
import at.dietze.ac.interfaces.IStringInterface;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;


public class PointSystem implements IStringInterface {

    public static void tradingProcess(Player sender, String[] args) {
        String receiverName = args[1];

        try {
            int coins = Integer.parseInt(args[2]);

            if (Bukkit.getPlayer(receiverName) != null) {
                Player receiver = Bukkit.getPlayer(receiverName);

                assert receiver != null;

                if (coins > getCurrentCoins(sender)) {
                    receiver.sendMessage(prefix + "§cDu hast nicht genügend Coins, um diesen Handel auszuführen!");
                    return;
                }

                if (coins < 0) {
                    sender.sendMessage(prefix + "§cDu kannst doch nicht einfach " + receiver.getDisplayName() + "s Coins wegnehmen, du Schlawiner ;)");
                    return;
                }

                addPoints(receiver, coins);
                receiver.sendMessage(prefix + "§7Du hast §a" + coins + " Coins §7von §9" + sender.getDisplayName() + " §7erhalten.");

                sender.sendMessage(prefix + "§7Du hast §c" + coins + " Coins §7an §9" + receiver.getDisplayName() + " §7gesendet.");
                removePoints(sender, coins, true);

            } else {
                sender.sendMessage(prefix + "§cDieser Spieler wurde nicht gefunden.");
            }

        } catch (NumberFormatException e) {
            sender.sendMessage(prefix + "§cDu hast eine fehlerhafte Coinanzahl eingegeben.");
        }
    }

    /**
     * @param p Player
     */
    public static void setDailyCoins(Player p) {
        // requires some trickery
        if (!PlaytimeFetch.hasJoinedToday(p) && p.hasPlayedBefore()) {
            int currPlaytime = PlaytimeFetch.getPlayTimeHours(p);

            int coins = (currPlaytime % 2);

            if (coins > 0) {
                PointSystem.addPoints(p, (coins * 2));
            } else {
                p.sendMessage(prefix + "§cDa du keine weitere volle Stunde gespielt hast, erhälst du keine Coins.");
            }
        }
        PlaytimeFetch.setLastJoin(p, System.currentTimeMillis() / 1000L);
    }

    /**
     * @param p Player
     * @return coin amount, returns -1 when player has no coins yet
     */
    public static int getCurrentCoins(Player p) {
        if (Core.getPlugin().getConfig().get("points_" + p.getUniqueId()) != null) {
            return (int) Core.getPlugin().getConfig().get("points_" + p.getUniqueId());
        }

        return -1;
    }

    /**
     * @param p     Player
     * @param count int
     */
    public static void addPoints(Player p, int count) {

        int currentCoins = 0;

        if (getCurrentCoins(p) != -1) {
            currentCoins = getCurrentCoins(p);
        }

        Core.getPlugin().getConfig().set("points_" + p.getUniqueId(), currentCoins + count);
        Core.getPlugin().saveConfig();
        Core.getPlugin().reloadConfig();
        p.sendMessage(prefix + "Du hast §c" + count + "§7 Coins erhalten!");
    }

    /**
     * @param p     Player
     * @param count int
     */
    public static void removePoints(Player p, int count, boolean showMessage) {
        if (getCurrentCoins(p) != -1) {
            int currentCoins = getCurrentCoins(p);
            Core.getPlugin().getConfig().set("points_" + p.getUniqueId(), currentCoins - count);
            Core.getPlugin().saveConfig();
            Core.getPlugin().reloadConfig();

            if (showMessage)
                p.sendMessage(prefix + "Dir wurden §c" + count + "§7 Coins abgezogen. Du hast noch §a" + (currentCoins - count) + "§7 Coins übrig.");
        } else {
            p.sendMessage(prefix + "§aDa du noch keine Coins hast, wurde dir nichts abgezogen.");
        }

    }

}
