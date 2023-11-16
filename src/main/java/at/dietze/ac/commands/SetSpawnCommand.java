package at.dietze.ac.commands;

import at.dietze.ac.Core;
import at.dietze.ac.interfaces.IStringInterface;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor, IStringInterface {

    public static String description = "§a/setSpawn §7- Dieser Befehl setzt den globalen Spawnpunkt.";

    public SetSpawnCommand() {
        Core.addToDescriptions(description);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        Player p = (Player)sender;

        if(p.hasPermission("core.admin")){
            if(cmd.getName().equalsIgnoreCase("setspawn")){
                Core.getPlugin().getConfig().set("spawn", p.getLocation());
                Core.getPlugin().saveConfig();
                Core.getPlugin().reloadConfig();
                p.sendMessage(prefix + "§aSpawn wurde gesetzt.");
            }
        }

        return false;
    }
}
