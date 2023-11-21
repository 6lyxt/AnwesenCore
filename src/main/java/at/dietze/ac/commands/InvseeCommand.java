package at.dietze.ac.commands;

import at.dietze.ac.Core;
import at.dietze.ac.interfaces.ICommandInterface;
import at.dietze.ac.interfaces.IStringInterface;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class InvseeCommand implements ICommandInterface, CommandExecutor, IStringInterface {

    private final String action = "invsee";

    private final String description = "Öffnet das Inventar eines anderen Spielers.";

    public InvseeCommand() {
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

        Player p = (Player) sender;

        if(cmd.getName().equalsIgnoreCase(this.getAction())) {
            if(p.hasPermission("core.admin")) {
                if(args.length > 0) {
                    Player invPlayer = Bukkit.getPlayer(args[0]);
                    if(invPlayer != null) {
                        Inventory inv = invPlayer.getInventory();
                        p.openInventory(inv);
                    } else {
                        p.sendMessage(prefix + "§cDieser Spieler wurde leider nicht gefunden.");
                    }
                }
            } else {
                p.sendMessage(prefix + "§cDu hast keine Rechte, um diesen Befehl auszuführen.");
            }
        }

        return false;
    }
}
