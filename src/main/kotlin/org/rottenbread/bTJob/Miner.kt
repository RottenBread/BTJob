package org.rottenbread.bTJob

import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.rottenbread.bTJob.Lvup.lvup
import org.rottenbread.bTJob.data.BlockStorage

class Miner : Listener {

    @EventHandler
    fun onMining(event: BlockBreakEvent) {
        val player = event.player
        val block = event.block
        val location = block.location

        var currentEXP = 0

        when (block.type) {
            Material.COAL_ORE, Material.DEEPSLATE_COAL_ORE -> {
                if (!BlockStorage.contains(location)) {
                    currentEXP += 3
                } else {
                    BlockStorage.removeBlock(location)
                }
            }
            Material.COPPER_ORE, Material.DEEPSLATE_COPPER_ORE -> {
                if (!BlockStorage.contains(location)) {
                    currentEXP += 3
                } else {
                    BlockStorage.removeBlock(location)
                }
            }
            Material.IRON_ORE, Material.DEEPSLATE_IRON_ORE -> {
                if (!BlockStorage.contains(location)) {
                    currentEXP += 7
                } else {
                    BlockStorage.removeBlock(location)
                }
            }
            Material.EMERALD_ORE, Material.DEEPSLATE_EMERALD_ORE -> {
                if (!BlockStorage.contains(location)) {
                    currentEXP += 100
                } else {
                    BlockStorage.removeBlock(location)
                }
            }
            Material.REDSTONE_ORE, Material.DEEPSLATE_REDSTONE_ORE -> {
                if (!BlockStorage.contains(location)) {
                    currentEXP += 5
                } else {
                    BlockStorage.removeBlock(location)
                }
            }
            Material.DIAMOND_ORE, Material.DEEPSLATE_DIAMOND_ORE -> {
                if (!BlockStorage.contains(location)) {
                    currentEXP += 50
                } else {
                    BlockStorage.removeBlock(location)
                }
            }
            Material.LAPIS_ORE, Material.DEEPSLATE_LAPIS_ORE -> {
                if (!BlockStorage.contains(location)) {
                    currentEXP += 5
                } else {
                    BlockStorage.removeBlock(location)
                }
            }
            Material.GOLD_ORE, Material.DEEPSLATE_GOLD_ORE -> {
                if (!BlockStorage.contains(location)) {
                    currentEXP += 7
                } else {
                    BlockStorage.removeBlock(location)
                }
            } else -> return
        }
        lvup(player, "광부", currentEXP,false)
    }
}