package at.dietze.ac.commands;

import at.dietze.ac.Core;
import at.dietze.ac.interfaces.ICommandInterface;
import at.dietze.ac.interfaces.IStringInterface;
import at.dietze.ac.realism.thirst.ThirstSimulator;
import at.dietze.ac.realism.thirst.ThirstTask;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DurstCommand implements CommandExecutor, ICommandInterface, IStringInterface {

    /**
     * command action
     */
    private final String action = "durst";

    /**
     * command description
     */
    private final String description = "Zeigt Informationen bzw. Tipps für Durst an.";

    /**
     *
     */
    public DurstCommand() {
        Core.addToDescriptions(this.getAction(), this.getDescription());
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        Player p = (Player)sender;

        if(cmd.getName().equalsIgnoreCase(this.getAction())) {
            p.sendMessage(prefix + "Wie du bemerkst, wirst du manchmal durstig.");
            p.sendMessage(prefix + "Schau darauf, immer ein Getränk bei dir zu haben - andererseits wirds schnell ungemütlich.");
            p.sendMessage(prefix + "Es gibt keine Möglichkeit, den Durst zu beeinflussen - du musst einfach darauf hydriert zu bleiben.");
            p.sendMessage(prefix + "Apropos - das solltest du auch außerhalb von hier... hast du Wasser griffbereit?");
        }

        return false;
    }

    @Override
    public String getAction() {
        return this.action;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
