package at.dietze.ac.commands;

import at.dietze.ac.Core;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
    public static String description = "§a/spawn§7 - Dieser Befehl teleportiert dich zum Spawn.";
    public SpawnCommand() {
        Core.addToDescriptions(description);
    }


    private final Location spawn =  (Location) Core.getPlugin().getConfig().get("spawn");

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        Player p = (Player)sender;

        if(cmd.getName().equalsIgnoreCase("spawn")) {
            assert spawn != null;
            p.teleport(spawn);
        }
        return false;
    }
}
