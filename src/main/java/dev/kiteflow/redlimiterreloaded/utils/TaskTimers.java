package dev.kiteflow.redlimiterreloaded.utils;

import com.tcoded.folialib.FoliaLib;
import com.tcoded.folialib.wrapper.WrappedTask;
import dev.kiteflow.redlimiterreloaded.RedLimiterReloaded;
import dev.kiteflow.redlimiterreloaded.events.OnRedStoneEvent;

import java.util.concurrent.TimeUnit;

public class TaskTimers {

    private FoliaLib foliaLib = new FoliaLib(RedLimiterReloaded.getPlugin());

    public WrappedTask task1;
    public WrappedTask task2;

    public void runClearBlocks(){
        task1 = foliaLib.getImpl().runTimerAsync(new Runnable() {
            @Override
            public void run() {
                OnRedStoneEvent.clearBlocks();
            }
        }, 10L, 15L, TimeUnit.SECONDS);
    }

    public void runClearSignalCount(){
        task2 = foliaLib.getImpl().runTimerAsync(new Runnable() {
            @Override
            public void run() {
                OnRedStoneEvent.clearSignalCount();
            }
        }, 10L, 300L, TimeUnit.SECONDS);
    }
}
