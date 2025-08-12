package org.rottenbread.bTJob

import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.rottenbread.bTJob.data.DataSave
import org.bukkit.event.block.BlockBreakEvent
import org.rottenbread.bTJob.data.BlockStorage
import kotlin.math.roundToInt

class Miner : Listener {

    private val showBar = ShowBar()

    @EventHandler
    fun onMining(event: BlockBreakEvent) {
        val player = event.player
        val block = event.block
        val location = block.location

        val miner = DataSave("miner")
        val currentExp = miner.loadEXP(player)
        val currentLV = miner.loadLV(player)
        val progressEXP = currentExp.toDouble()/(currentLV.toDouble() * 70)

        when (block.type) {
            Material.COAL_ORE, Material.DEEPSLATE_COAL_ORE -> {
                if (!BlockStorage.contains(location)) {
                    miner.saveEXP(player, currentExp + 3)
                } else {
                    BlockStorage.removeBlock(location)
                }
            }
            Material.COPPER_ORE, Material.DEEPSLATE_COPPER_ORE -> {
                if (!BlockStorage.contains(location)) {
                    miner.saveEXP(player, currentExp + 3)
                } else {
                    BlockStorage.removeBlock(location)
                }
            }
            Material.IRON_ORE, Material.DEEPSLATE_IRON_ORE -> {
                if (!BlockStorage.contains(location)) {
                    miner.saveEXP(player, currentExp + 7)
                } else {
                    BlockStorage.removeBlock(location)
                }
            }
            Material.EMERALD_ORE, Material.DEEPSLATE_EMERALD_ORE -> {
                if (!BlockStorage.contains(location)) {
                    miner.saveEXP(player, currentExp + 100)
                } else {
                    BlockStorage.removeBlock(location)
                }
            }
            Material.REDSTONE_ORE, Material.DEEPSLATE_REDSTONE_ORE -> {
                if (!BlockStorage.contains(location)) {
                    miner.saveEXP(player, currentExp + 5)
                } else {
                    BlockStorage.removeBlock(location)
                }
            }
            Material.DIAMOND_ORE, Material.DEEPSLATE_DIAMOND_ORE -> {
                if (!BlockStorage.contains(location)) {
                    miner.saveEXP(player, currentExp + 50)
                } else {
                    BlockStorage.removeBlock(location)
                }
            }
            Material.LAPIS_ORE, Material.DEEPSLATE_LAPIS_ORE -> {
                if (!BlockStorage.contains(location)) {
                    miner.saveEXP(player, currentExp + 5)
                } else {
                    BlockStorage.removeBlock(location)
                }
            }
            Material.GOLD_ORE, Material.DEEPSLATE_GOLD_ORE -> {
                if (!BlockStorage.contains(location)) {
                    miner.saveEXP(player, currentExp + 7)
                } else {
                    BlockStorage.removeBlock(location)
                }
            } else -> return
        }
        if (currentExp >= currentLV * 70) {
            miner.saveEXP(player, currentExp-(currentLV * 70))
            miner.saveLV(player, currentLV + 1)
            player.sendMessage("§a[직업] §f광부의 레벨이 올랐어요! §e$currentLV 레벨 §f-> §e${currentLV + 1} 레벨")
            val crEXP = miner.loadEXP(player)
            val crLV = miner.loadLV(player)
            val pgEXP = crEXP.toDouble()/(crLV.toDouble() * 70)
            showBar.showBar(player,"§b광부 §f레벨 §b$crLV §f경험치 (${(pgEXP * 100).roundToInt()}%)", pgEXP)
        }
        else {
            showBar.showBar(player, "§b광부 §f레벨 §b$currentLV §f경험치 (${(progressEXP * 100).roundToInt()}%)", progressEXP)
        }
    }
}