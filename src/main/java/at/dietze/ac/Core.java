package at.dietze.ac;

import at.dietze.ac.commands.*;
import at.dietze.ac.interfaces.IStringInterface;
import at.dietze.ac.playerEvents.*;
import at.dietze.ac.realism.thirst.PlayerSimulateThirst;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Objects;

public class Core extends JavaPlugin implements IStringInterface {

    public static Plugin plugin;
    public static ArrayList<String> descriptions = new ArrayList<>();

    public static ArrayList<String> getDescriptions() {
        return descriptions;
    }

    @Override
    public void onEnable() {
        plugin = this;
        Bukkit.getConsoleSender().sendMessage(prefix + "§a AnwesenCore Plugin aktiviert");
        registerCommands();
        registerEvents();
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(prefix + "§a AnwesenCore Plugin deaktiviert");
    }

    /**
     * @param action calling command, for example /help
     * @param desc command description
     */
    public static void addToDescriptions(String action, String desc) {
        Core.descriptions.add("§a/" + action + "§7 - " + desc);
        Bukkit.getConsoleSender().sendMessage(prefix + "§a" + action + " §7 wurde registiert.");
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    /**
     * helper method to require all events
     */
    private void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new OnPlayerLeaveEvent(), this);
        Bukkit.getPluginManager().registerEvents(new OnPlayerDeathEvent(), this);
        Bukkit.getPluginManager().registerEvents(new OnPlayerJoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new OnPlayerChatEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerSimulateThirst(), this);
        Bukkit.getPluginManager().registerEvents(new OnPlayerSneakEvent(), this);

        Bukkit.getConsoleSender().sendMessage(prefix + "§a Events wurden registriert.");
    }

    /**
     * helper method to require all commands
     */
    private void registerCommands() {
        Objects.requireNonNull(this.getCommand("getplaytime")).setExecutor(new GetPlaytimeCommand());
        Objects.requireNonNull(this.getCommand("setspawn")).setExecutor(new SetSpawnCommand());
        Objects.requireNonNull(this.getCommand("pointsystem")).setExecutor(new PointSystemCommand());
        Objects.requireNonNull(this.getCommand("spawn")).setExecutor(new SpawnCommand());
        Objects.requireNonNull(this.getCommand("setnick")).setExecutor(new SetNickCommand());
        Objects.requireNonNull(this.getCommand("help")).setExecutor(new HelpCommand());
        Objects.requireNonNull(this.getCommand("durst")).setExecutor(new DurstCommand());
        Objects.requireNonNull(this.getCommand("sit")).setExecutor(new SitCommand());
        Bukkit.getConsoleSender().sendMessage(prefix + "§a Befehle wurden registriert.");
    }

}