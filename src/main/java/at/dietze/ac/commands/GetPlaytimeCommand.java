package at.dietze.ac.commands;

import at.dietze.ac.Core;
import at.dietze.ac.dataHandler.JsonParser;
import at.dietze.ac.interfaces.IStringInterface;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GetPlaytimeCommand implements CommandExecutor, IStringInterface {

    private String description = "§a/getPlaytime §7- Dieser Befehl zeigt die Spielzeit von einem Spieler an.";

    public GetPlaytimeCommand() {
        Core.addToDescriptions(description);
    }


    /**
     * @param sender
     * @param cmd
     * @param cl
     * @param args
     * @return
     */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String cl, String[] args) {

        if(cmd.getName().equalsIgnoreCase("getPlaytime")) {
            if(sender.hasPermission("core.admin")) {
                if(args[0].length() > 0) {
                    String playerName = args[0];

                    String playerTime = JsonParser.getPlayerTime(playerName);

                    if(playerTime.length() > 0) {
                        sender.sendMessage(prefix + "§aSpielzeit: " + playerTime);
                    } else {
                        sender.sendMessage(prefix + "§cDieser Spieler wurde noch nicht im System registriert.");
                    }
                }
            } else {
                sender.sendMessage(prefix + "§cDu hast keine Berechtigung für diesen Befehl.");
            }
        }

        return false;
    }
}
