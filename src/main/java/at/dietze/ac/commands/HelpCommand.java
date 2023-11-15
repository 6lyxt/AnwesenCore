package at.dietze.ac.commands;

import at.dietze.ac.Core;
import at.dietze.ac.interfaces.IStringInterface;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class HelpCommand implements CommandExecutor, IStringInterface {

    public static String description = "Dieser Befehl zeigt diese Übersicht an.";

    public HelpCommand() {
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

        Player p = (Player) sender;
        
        if(cmd.getName().equalsIgnoreCase("help")) {
            p.sendMessage(prefix + "Befehlsübersicht:");

            for (String info : Core.getDescriptions()) {
                p.sendMessage(prefix + "§7" + info);
            }
        }
        
        return false;
    }
}
