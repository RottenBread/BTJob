package org.rottenbread.bTJob.event

import org.bukkit.Material
import org.bukkit.event.Listener
import org.bukkit.event.EventHandler
import org.bukkit.event.block.BlockPlaceEvent
import org.rottenbread.bTJob.data.BlockStorage

class OnBlockPlace : Listener {

    @EventHandler
    fun onBlockPlace(event: BlockPlaceEvent) {
        val block = event.block
        when (block.type) {
            Material.MELON, Material.PUMPKIN,
                 Material.COAL_ORE, Material.COPPER_ORE, Material.IRON_ORE, Material.EMERALD_ORE, Material.REDSTONE_ORE, Material.DIAMOND_ORE, Material.LAPIS_ORE, Material.GOLD_ORE,
                     Material.DEEPSLATE_COAL_ORE, Material.DEEPSLATE_COPPER_ORE, Material.DEEPSLATE_IRON_ORE, Material.DEEPSLATE_EMERALD_ORE, Material.DEEPSLATE_REDSTONE_ORE,
                         Material.DEEPSLATE_DIAMOND_ORE, Material.DEEPSLATE_LAPIS_ORE, Material.DEEPSLATE_GOLD_ORE, Material.ANCIENT_DEBRIS -> {
                val location = block.location
                BlockStorage.addBlock(location)
            } else -> return
        }
    }
}