package dev.kiteflow.redlimiterreloaded.utils;

import dev.kiteflow.redlimiterreloaded.RedLimiterReloaded;
import dev.kiteflow.redlimiterreloaded.events.OnRedStoneEvent;
import org.bukkit.Bukkit;

public class TaskTimers {

    public Integer taskID1;
    public Integer taskID2;

    public void runClearBlocks(){
        taskID1 = Bukkit.getScheduler().scheduleSyncRepeatingTask(RedLimiterReloaded.getPlugin(), new Runnable() {
            @Override
            public void run() {
                OnRedStoneEvent.clearBlocks();
            }
        }, 200, 300);
    }

    public void runClearSignalCount(){
        taskID2 = Bukkit.getScheduler().scheduleSyncRepeatingTask(RedLimiterReloaded.getPlugin(), new Runnable() {
            @Override
            public void run() {
                OnRedStoneEvent.clearSignalCount();
            }
        }, 200, 6000);
    }
}
