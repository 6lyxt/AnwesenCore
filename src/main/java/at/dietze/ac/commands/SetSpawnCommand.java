package at.dietze.ac.commands;

import at.dietze.ac.Core;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        Player p = (Player)sender;

        if(p.hasPermission("core.admin")){
            if(cmd.getName().equalsIgnoreCase("setspawn")){
                Core.getPlugin().getConfig().set("spawn", p.getLocation());
                Core.getPlugin().saveConfig();
                Core.getPlugin().reloadConfig();
                p.sendMessage("§aSpawn wurde gesetzt.");
            }
        }

        return false;
    }
}
