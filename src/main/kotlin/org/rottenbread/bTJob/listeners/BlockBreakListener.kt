package org.rottenbread.bTJob.listeners

import org.bukkit.event.Listener
import org.bukkit.Material
import org.bukkit.block.data.Ageable
import org.bukkit.event.EventHandler
import org.bukkit.event.block.BlockBreakEvent
import org.rottenbread.bTJob.data.BlockStorage
import org.rottenbread.bTJob.enum.JobType
import org.rottenbread.bTJob.manager.JobManager.jobLevelup

class BlockBreakListener: Listener {

    private val expList = mapOf(
        // 광물
        Material.COAL_ORE to 3, Material.DEEPSLATE_COAL_ORE to 3,
        Material.COPPER_ORE to 3, Material.DEEPSLATE_COPPER_ORE to 3,
        Material.REDSTONE_ORE to 5, Material.DEEPSLATE_REDSTONE_ORE to 5,
        Material.LAPIS_ORE to 5, Material.DEEPSLATE_LAPIS_ORE to 5,
        Material.IRON_ORE to 7, Material.DEEPSLATE_IRON_ORE to 7,
        Material.GOLD_ORE to 7, Material.DEEPSLATE_GOLD_ORE to 7,
        Material.DIAMOND_ORE to 50, Material.DEEPSLATE_DIAMOND_ORE to 50,
        Material.EMERALD_ORE to 100, Material.DEEPSLATE_EMERALD_ORE to 100,
        // 농작물
        Material.WHEAT to 1, Material.POTATOES to 1,
        Material.CARROTS to 1, Material.BEETROOTS to 1,
        Material.COCOA to 1, Material.NETHER_WART to 1,
        Material.MELON to 1, Material.PUMPKIN to 1
    )

    private val oreList = setOf(
        Material.COAL_ORE, Material.DEEPSLATE_COAL_ORE,
        Material.COPPER_ORE, Material.DEEPSLATE_COPPER_ORE,
        Material.REDSTONE_ORE, Material.DEEPSLATE_REDSTONE_ORE,
        Material.LAPIS_ORE, Material.DEEPSLATE_LAPIS_ORE,
        Material.IRON_ORE, Material.DEEPSLATE_IRON_ORE,
        Material.GOLD_ORE, Material.DEEPSLATE_GOLD_ORE,
        Material.DIAMOND_ORE, Material.DEEPSLATE_DIAMOND_ORE,
        Material.EMERALD_ORE, Material.DEEPSLATE_EMERALD_ORE
    )

    private val cropGrownList = setOf(
        Material.WHEAT,
        Material.POTATOES,
        Material.CARROTS,
        Material.BEETROOTS,
        Material.COCOA,
        Material.NETHER_WART
    )

    private val cropBlockList = setOf(
        Material.MELON,
        Material.PUMPKIN
    )

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        val block = event.block
        val location = block.location
        val player = event.player

        val expToAdd = expList[block.type] ?: return

        if (block.type in oreList) {
            if (!BlockStorage.contains(location)) {
                jobLevelup(player, JobType.MINER, expToAdd)
            } else {
                BlockStorage.removeBlock(location)
            }
            return
        }

        if (block.type in cropGrownList) {
            val data = block.blockData
            if (data is Ageable && data.age == data.maximumAge) {
                jobLevelup(player, JobType.FARMER, expToAdd)
            }
            return
        }

        if (block.type in cropBlockList) {
            if (!BlockStorage.contains(location)) {
                jobLevelup(player, JobType.FARMER, expToAdd)
            } else {
                BlockStorage.removeBlock(location)
            }
            return
        }

    }
}