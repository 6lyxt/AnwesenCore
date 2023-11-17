package at.dietze.ac.realism.thirst;

import org.bukkit.entity.Player;

/**
 * dump class, objects are being initiated but never destroyed
 * instantiated in ThirstTask
 */
public class ThirstSimulator {

    /**
     * pid of running scheduler
     */
    private int schedulerID;

    /**
     * playerobject of assigned player
     */
    private Player player;

    /**
     * related ThirstTask
     */
    private ThirstTask thirstTask;

    public ThirstSimulator(Player player, int schedulerID, ThirstTask thirstTask) {
        this.player = player;
        this.schedulerID = schedulerID;
        this.thirstTask = thirstTask;
    }

    /**
     * @return int
     */
    public int getSchedulerID() {
        return schedulerID;
    }

    /**
     * @return Player
     */
    public Player getPlayer() {
        return player;
    }

    public ThirstTask getThirstTask() {
        return thirstTask;
    }

    /**
     * @param p calling player object
     */
    public static void stopAndRequeue(Player p, boolean requeue) {
        for (int i = 0; i < ThirstTask.activeThirstSchedulers.size(); i++) {
            if(ThirstTask.activeThirstSchedulers.get(i).getPlayer().getUniqueId() == p.getUniqueId()) {
                ThirstTask.activeThirstSchedulers.get(i).getThirstTask().stop(i);
                if(requeue) {
                    ThirstTask thirstTask = new ThirstTask(p);
                    thirstTask.start();
                }
            }
        }
    }
}
