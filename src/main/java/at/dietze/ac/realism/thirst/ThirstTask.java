package at.dietze.ac.realism.thirst;

import at.dietze.ac.Core;
import at.dietze.ac.interfaces.IStringInterface;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;


/**
 * task helper class to run bukkit task in background
 * instantiated in PlayerJoinEvent and on requeue (after consuming)
 */
public class ThirstTask implements IStringInterface {

    /**
     * arraylist of currently active thirst schedulers
     */
    public static ArrayList<ThirstSimulator> activeThirstSchedulers = new ArrayList<>();

    /**
     * duration between damage ticks
     */
    private final int duration = 200;

    /**
     * delay after join
     */
    private int delay = (int) (Math.random() * (7600 - 1000)) + 1000;;

    /**
     * connected bukkit player
     */
    private Player player;

    /**
     * active scheduler id
     */
    private int schedulerID;

    /**
     * thirstlevel - used for delaying and progressbar (in the future)
     */
    private int thirstLevel = 0;

    /**
     * warning of notifying the player of thirst
     */
    private boolean warningSent = false;

    /**
     * @param p related player
     *
     */
    public ThirstTask(Player p) {
        this.player = p;

    }

    /**
     * method to initiate the "thirst process"
     */
    public void start() {
        this.schedulerID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.getPlugin(), () -> {
            this.thirstLevel++;
            if(!this.warningSent) {
                this.player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(prefix + "§cDu wirst langsam durstig..."));
                this.warningSent = true;
            }
            if(this.thirstLevel > 20) {
                this.player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(prefix + "§cDu verdurstest!"));
                this.player.damage(2);
            }
        }, delay, duration);

        // forgive me dear garbage collector
        activeThirstSchedulers.add(new ThirstSimulator(this.player, this.schedulerID, this));
    }

    public void stop(int index) {
        activeThirstSchedulers.remove(index);
        Bukkit.getScheduler().cancelTask(this.schedulerID);
    }


}
