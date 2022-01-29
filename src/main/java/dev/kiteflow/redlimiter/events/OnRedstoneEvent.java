package dev.kiteflow.redlimiter.events;

import dev.kiteflow.redlimiter.RedLimiter;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class OnRedstoneEvent implements Listener {
    private static LinkedHashMap<Block, Long> blocks = new LinkedHashMap<>();
    private static HashMap<Block, Integer> signalCount = new HashMap<>();

    @EventHandler
    public void onRedstoneEvent(BlockRedstoneEvent e){
        Block block = e.getBlock();

        if(blocks.containsKey(block)){
            long difference =  System.currentTimeMillis() - blocks.get(block);

            switch(block.getType()){
                case PISTON:
                case STICKY_PISTON:
                    if(difference < RedLimiter.config.pistonTimings){
                        e.setNewCurrent(0);
                    }
                    break;
                case OBSERVER:
                    if(difference < RedLimiter.config.observerTimings){
                        e.setNewCurrent(0);
                    }
                    break;
                case REPEATER:
                    if(difference < RedLimiter.config.repeaterTimings){
                        e.setNewCurrent(0);
                    }
                    break;
                default:
                    if(difference < RedLimiter.config.defaultTimings){
                        e.setNewCurrent(0);
                    }
            }

            signalCount.putIfAbsent(block, 0);
            signalCount.put(block, signalCount.get(block) + 1);

            if(signalCount.get(block) >= 15) Bukkit.getLogger().info(String.format(
                    "Frequently fired block at X: %s Y: %s Z: %s",
                    block.getX(),
                    block.getY(),
                    block.getZ()
            ));
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
