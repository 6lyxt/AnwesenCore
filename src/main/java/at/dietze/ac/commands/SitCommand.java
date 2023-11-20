package at.dietze.ac.commands;

import at.dietze.ac.Core;
import at.dietze.ac.interfaces.ICommandInterface;
import at.dietze.ac.interfaces.IStringInterface;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class SitCommand implements ICommandInterface, CommandExecutor, IStringInterface {

    private final String action = "sit";

    private final String description = "Setzt dich hin :)";

    public SitCommand() {
        Core.addToDescriptions(this.action, this.description);
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
            this.spawnHelperEntity(p);
        }

        return false;
    }

    /**
     * @param p Player
     */
    private void spawnHelperEntity(Player p) {
        if(p.isInsideVehicle()) {
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(prefix + "§cDu sitzt bereits!"));
            return;
        }

        if(!p.isOnGround()) {
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(prefix + "§cDu musst stehen, um zu sitzen!"));
            return;
        }

        Entity arrow = p.getWorld().spawnEntity(p.getLocation().subtract(0D, 0.4D, 0D), EntityType.ARROW);
        arrow.setGravity(false);

        // setPassenger is deprecated?
        arrow.addPassenger(p);
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(prefix + "§aUm aufzustehen, drücke SHIFT."));

    }
}
