package dev.kiteflow.redlimiterreloaded.events;

import dev.kiteflow.redlimiterreloaded.RedLimiterReloaded;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class OnRedStoneEvent implements Listener {
    public static LinkedHashMap<Block, Long> blocks = new LinkedHashMap<>();

    public static boolean redStoneEnabled = RedLimiterReloaded.configManager.redstoneEnabled;
    public static HashMap<Block, Integer> signalCount = new HashMap<>();

    @EventHandler
    public void onRedStoneEvent(BlockRedstoneEvent e){
        if(!redStoneEnabled) e.setNewCurrent(0);

        Block block = e.getBlock();

        if(blocks.containsKey(block)){
            long difference =  System.currentTimeMillis() - blocks.get(block);

            switch(block.getType()){
                case PISTON:
                case STICKY_PISTON:
                    if(difference < RedLimiterReloaded.configManager.pistonTimings){
                        e.setNewCurrent(0);
                    }
                    break;
                case OBSERVER:
                    if(difference < RedLimiterReloaded.configManager.observerTimings){
                        e.setNewCurrent(0);
                    }
                    break;
                case REPEATER:
                    if(difference < RedLimiterReloaded.configManager.repeaterTimings){
                        e.setNewCurrent(0);
                    }
                    break;
                default:
                    if(difference < RedLimiterReloaded.configManager.defaultTimings){
                        e.setNewCurrent(0);
                    }
            }

            signalCount.putIfAbsent(block, 0);
            signalCount.put(block, signalCount.get(block) + 1);
        }

        blocks.put(block, System.currentTimeMillis());
    }

    public static void clearBlocks(){
        ArrayList<Block> removal = new ArrayList<>();

        for(Map.Entry<Block, Long> entry : blocks.entrySet()){
            if(System.currentTimeMillis() - entry.getValue() > 10000L){
                removal.add(entry.getKey());
            } else {
                break;
            }
        }

        removal.forEach(block -> blocks.remove(block));
    }

    public static void clearSignalCount(){
        signalCount = new HashMap<>();
    }
}
