package org.rottenbread.bTJob

import org.bukkit.Material
import org.bukkit.block.data.Ageable
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.rottenbread.bTJob.data.BlockStorage
import org.rottenbread.bTJob.Lvup.lvup

class Farmer : Listener {

    @EventHandler
    fun onHarvest(event: BlockBreakEvent) {
        val block = event.block
        val location = block.location
        val player = event.player

        var currentEXP = 0

        when (block.type) {
            Material.WHEAT, Material.POTATOES, Material.CARROTS, Material.BEETROOTS, Material.COCOA, Material.NETHER_WART -> {
                val data = block.blockData
                if (data is Ageable && data.age == data.maximumAge) {
                    currentEXP += 1
                }
            }
            Material.MELON, Material.PUMPKIN -> {
                if (!BlockStorage.contains(location)) {
                    currentEXP += 1
                } else {
                    BlockStorage.removeBlock(location)
                }
            } else -> return
        }
        lvup(player, "농부", currentEXP,false)
    }
}