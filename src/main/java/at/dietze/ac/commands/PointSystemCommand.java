package at.dietze.ac.commands;

import at.dietze.ac.Core;
import at.dietze.ac.interfaces.IStringInterface;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PointSystemCommand implements CommandExecutor, IStringInterface {

    public static String description = "§a/pointSystem §7- Dieser Befehl liefert Informationen zum Punktesystem.";

    public PointSystemCommand() {
        Core.addToDescriptions(description);
    }

    /**
     * @param sender
     * @param cmd
     * @param s
     * @param args
     * @return
     */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        Player p = (Player)sender;

        if(cmd.getName().equalsIgnoreCase("pointsystem")) {
            if(args.length > 0 && args[0].length() > 0 && !args[0].equalsIgnoreCase("help")) {
                if(args[0].equalsIgnoreCase("balance") && args[1].length() > 0) {
                    if(Core.getPlugin().getConfig().get("points_" + p.getUniqueId()) != null) {
                        p.sendMessage(prefix + "§7Deine aktuelle Punkteanzahl: §a" + Core.getPlugin().getConfig().get("points_" + p.getUniqueId()));
                    }
                }
            } else {
                p.sendMessage(prefix + "§aDas Punktesystem funktioniert wiefolgt:");
                p.sendMessage(prefix + "§7Pro Join +5 Punkte (hier wird auf die Spielzeit geachtet - ein Join zählt nur 1x pro Tag!)");
                p.sendMessage(prefix + "§7Pro Stunde Spielzeit +2 Punkte (nur jede volle Stunde!)");
                p.sendMessage(prefix + "§7Pro Tod -1 Punkt");
                p.sendMessage(prefix + "§7Pro Kill +1 Punkt (maximal 2 in der Stunde)");
                p.sendMessage(prefix + "§7Das Punktesystem ist noch nicht ausgereift und befindet sich in der Testphase - Infos zu den Belohnungen findest du auf dem Anwesen Discord Server!");
            }
        }

        return false;
    }
}
