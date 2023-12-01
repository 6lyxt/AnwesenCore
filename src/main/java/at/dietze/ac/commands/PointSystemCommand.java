package at.dietze.ac.commands;

import at.dietze.ac.Core;
import at.dietze.ac.interfaces.ICommandInterface;
import at.dietze.ac.interfaces.IStringInterface;
import at.dietze.ac.pointsystem.PointSystem;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PointSystemCommand implements CommandExecutor, IStringInterface, ICommandInterface {

    private final String action = "pointSystem";
    private final String description = "Dieser Befehl liefert Informationen zum Punktesystem.";

    public PointSystemCommand() {
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

    /**
     * @param sender
     * @param cmd
     * @param s
     * @param args
     * @return
     */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        Player p = (Player)sender;

        if(cmd.getName().equalsIgnoreCase(this.getAction())) {
            if(args.length > 0 && args[0].length() > 0 && !args[0].equalsIgnoreCase("help")) {
                if(args[0].equalsIgnoreCase("balance")) {
                    if(args.length > 1  && Bukkit.getServer().getPlayer(args[1]) != null) {
                        Player balancePlayer = Bukkit.getServer().getPlayer(args[1]);
                        assert balancePlayer != null;
                        if(PointSystem.getCurrentCoins(balancePlayer) != -1) {
                            p.sendMessage(prefix + balancePlayer.getName() + "s aktuelle Punkteanzahl: §a" + PointSystem.getCurrentCoins(balancePlayer));
                        } else {
                            p.sendMessage(prefix + "§cVon diesem Spieler haben wir noch keine Aufzeichnungen.");
                        }
                    } else {
                        if(PointSystem.getCurrentCoins(p) != -1) {
                            p.sendMessage(prefix + "Deine aktuelle Punkteanzahl: §a" + PointSystem.getCurrentCoins(p));
                        } else {
                            p.sendMessage(prefix + "Du hast noch keine Punkte!");
                        }
                    }
                } else if(args[0].equalsIgnoreCase("trade")) {
                    if(args[1] != null && args[2] != null) {
                        PointSystem.tradingProcess(p, args);
                    } else {
                        p.sendMessage(prefix + "§cBitte fülle den Befehl vollständig aus:");
                        p.sendMessage(prefix + "§7/pointsystem trade <Spielername> <Coins>");
                    }
                } else {
                    p.sendMessage(prefix + "§cBitte überprüfe deine Eingabe.");

                }
            } else {
                p.sendMessage(prefix + "§7/pointsystem trade <Spielername> <Coins>");
                p.sendMessage(prefix + "§7/pointsystem balance <Spielername>");
            }
        }

        return false;
    }

}
