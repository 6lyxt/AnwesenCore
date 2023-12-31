package at.dietze.ac.commands;

import at.dietze.ac.Core;
import at.dietze.ac.interfaces.ICommandInterface;
import at.dietze.ac.interfaces.IStringInterface;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetNickCommand implements CommandExecutor, IStringInterface, ICommandInterface {

    private final String action = "setNick";
    private final String description = "Dieser Befehl erlaubt es, den Spielernamen zu ändern.";

    /**
     *
     */
    public SetNickCommand() {
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

        if (cmd.getName().equalsIgnoreCase(this.getAction())) {
            Player p = (Player) sender;
            if (args[0].length() > 0) {

                p.setDisplayName(args[0]);
                p.setCustomName(args[0]);

                Core.getPlugin().getConfig().set("customname_" + p.getUniqueId(), args[0]);
                Core.getPlugin().saveConfig();
                Core.getPlugin().reloadConfig();


                p.sendMessage(prefix + "§7Du wirst ab jetzt als §a" + args[0] + "§7 angezeigt.");
            } else {
                p.sendMessage(prefix + "§cDu musst einen Namen angeben!");
            }
        }

        return false;
    }

}
