package at.dietze.ac.commands;

import at.dietze.ac.Core;
import at.dietze.ac.interfaces.ICommandInterface;
import at.dietze.ac.interfaces.IStringInterface;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor, ICommandInterface, IStringInterface {

    /**
     * command action
     */
    private final String action = "spawn";

    /**
     * command description
     */
    private final String description = "Dieser Befehl teleportiert dich zum Spawn.";

    /**
     *
     */
    public SpawnCommand() {
        Core.addToDescriptions(this.getAction(), this.getDescription());
    }

    @Override
    public String getAction() {
        return this.action;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        Player p = (Player)sender;

        if(cmd.getName().equalsIgnoreCase(this.getAction())) {
            Location spawn =  (Location) Core.getPlugin().getConfig().get("spawn");
            assert spawn != null;
            p.teleport(spawn);
        }
        return false;
    }

}
