package at.dietze.ac.commands;

import at.dietze.ac.interfaces.IStringInterface;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PointSystem implements CommandExecutor, IStringInterface {

    /**
     * @param sender
     * @param cmd
     * @param s
     * @param args
     * @return
     */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if(cmd.getName().equalsIgnoreCase("pointsystem")) {
            if(args[0].length() > 0 && !args[0].equalsIgnoreCase("help")) {

            } else {
                sender.sendMessage(prefix + "§aDas Punktesystem funktioniert wiefolgt:");
                sender.sendMessage(prefix + "§7Pro Join +5 Punkte (hier wird auf die Spielzeit geachtet - ein Join zählt nur 1x pro Tag!)");
                sender.sendMessage(prefix + "§7Pro Stunde Spielzeit +2 Punkte (nur jede volle Stunde!)");
                sender.sendMessage(prefix + "§7Pro Tod -1 Punkt");
                sender.sendMessage(prefix + "§7Pro Kill +1 Punkt (maximal 2 in der Stunde)");
                sender.sendMessage(prefix + "§7Das Punktesystem ist noch nicht ausgereift und befindet sich in der Testphase - Infos zu den Belohnungen findest du auf dem Anwesen Discord Server!");
            }
        }

        return false;
    }
}
