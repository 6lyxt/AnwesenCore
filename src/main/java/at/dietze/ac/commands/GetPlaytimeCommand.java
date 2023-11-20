package at.dietze.ac.commands;

import at.dietze.ac.Core;
import at.dietze.ac.interfaces.ICommandInterface;
import at.dietze.ac.interfaces.IStringInterface;
import at.dietze.ac.pointsystem.PlaytimeFetch;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class GetPlaytimeCommand implements CommandExecutor, IStringInterface, ICommandInterface {

    /**
     * command action
     */
    private final String action = "getPlaytime";

    /**
     * command description
     */
    private final String description = "Dieser Befehl zeigt die Spielzeit von einem Spieler an.";

    @Override
    public String getAction() {
        return this.action;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * initialization adds command description
     */
    public GetPlaytimeCommand() {
        Core.addToDescriptions(this.getAction(), this.getDescription());
    }

    /**
     * @param sender Player
     * @param cmd command
     * @param cl string
     * @param args arguments
     * @return
     */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String cl, String[] args) {
        Player p = (Player) sender;

        if(cmd.getName().equalsIgnoreCase(this.getAction())) {
            if(p.hasPermission("core.admin")) {
                if(args.length > 0 && args[0].length() > 0) {
                    String playerName = args[0];

                    String playerTime = PlaytimeFetch.getPlayTime(Objects.requireNonNull(Bukkit.getPlayer(playerName)));

                    if(playerTime.length() > 0) {
                        p.sendMessage(prefix + "§aSpielzeit: " + playerTime);
                    } else {
                        p.sendMessage(prefix + "§cDieser Spieler wurde noch nicht im System registriert.");
                    }
                } else {
                    String playerTime = PlaytimeFetch.getPlayTime(p);
                    p.sendMessage(prefix + "§aDeine Spielzeit: " + playerTime);
                }
            } else {
                String playerTime = PlaytimeFetch.getPlayTime(p);
                p.sendMessage(prefix + "§aDeine Spielzeit: " + playerTime);
            }
        }

        return false;
    }

}
