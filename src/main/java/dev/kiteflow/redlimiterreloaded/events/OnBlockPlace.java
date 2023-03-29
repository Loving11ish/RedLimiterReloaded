package dev.kiteflow.redlimiterreloaded.events;

import dev.kiteflow.redlimiterreloaded.RedLimiterReloaded;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class OnBlockPlace implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        Material blockType = e.getBlock().getType();
        Chunk chunk = e.getBlock().getChunk();

        int blockCount = 0;

        for (Material material : RedLimiterReloaded.getPlugin().getMaterialsList()){
            if (blockType == material){
                for(int y = -64; y < 319; y++){
                    for(int z = 0; z < 16; z++){
                        for(int x = 0; x < 16; x++){
                            if (chunk.getBlock(x, y, z).getType() == material) {
                                blockCount++;
                            }
                        }
                    }
                }
            }

            if (blockType == material && blockCount + 1 > RedLimiterReloaded.configManager.limitValue){
                e.getPlayer().sendMessage(RedLimiterReloaded.configManager.limitwarning);
                e.setCancelled(true);
            }
        }
    }
}
