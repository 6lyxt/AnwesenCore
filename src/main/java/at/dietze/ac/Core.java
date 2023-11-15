package at.dietze.ac;

import at.dietze.ac.commands.GetPlaytime;
import at.dietze.ac.commands.SetSpawnCommand;
import at.dietze.ac.interfaces.IStringInterface;
import at.dietze.ac.playerEvents.EventMapper;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Core extends JavaPlugin implements IStringInterface {

    public static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        Bukkit.getConsoleSender().sendMessage("§a AnwesenCore Plugin aktiviert");
        registerCommands();
        registerEvents();
    }

    @Override
    public void onDisable() {

    }

    public static Plugin getPlugin(){
        return plugin;
    }

    /**
     *
     */
    private void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new EventMapper(), this);
        Bukkit.getConsoleSender().sendMessage(prefix + "§a Events wurden registriert.");
    }

    /**
     *
     */
    private void registerCommands() {
        Objects.requireNonNull(this.getCommand("getplaytime")).setExecutor(new GetPlaytime());
        Objects.requireNonNull(this.getCommand("setspawn")).setExecutor(new SetSpawnCommand());
        Bukkit.getConsoleSender().sendMessage(prefix + "§a Befehle wurden registriert.");

    }

}