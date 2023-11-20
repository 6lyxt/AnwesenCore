package at.dietze.ac.commands;

import at.dietze.ac.Core;
import at.dietze.ac.interfaces.ICommandInterface;
import at.dietze.ac.interfaces.IStringInterface;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor, IStringInterface, ICommandInterface {

    /**
     * command action
     */
    private final String action = "setSpawn";

    /**
     * command description
     */
    private final String description = "Dieser Befehl setzt den globalen Spawnpunkt.";

    public SetSpawnCommand() {
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

        if(p.hasPermission("core.admin")){
            if(cmd.getName().equalsIgnoreCase(this.getAction())){
                Core.getPlugin().getConfig().set("spawn", p.getLocation());
                Core.getPlugin().saveConfig();
                Core.getPlugin().reloadConfig();
                p.sendMessage(prefix + "§aSpawn wurde gesetzt.");
            }
        } else {
            p.sendMessage(prefix + "§cDu hast keine Berechtigung für diesen Befehl.");
        }

        return false;
    }

}
