package org.rottenbread.bTJob

import org.bukkit.Material
import org.bukkit.block.data.Ageable
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.rottenbread.bTJob.data.DataSave
import org.rottenbread.bTJob.data.BlockStorage
import kotlin.math.roundToInt

class Farmer : Listener {

    private val showBar = ShowBar()

    @EventHandler
    fun onHarvest(event: BlockBreakEvent) {
        val block = event.block
        val location = block.location
        val player = event.player

        val farmer = DataSave("farmer")
        val currentExp = farmer.loadEXP(player)
        val currentLV = farmer.loadLV(player)
        val progressEXP = currentExp.toDouble()/(currentLV.toDouble() * 70)

        when (block.type) {
            Material.WHEAT, Material.POTATOES, Material.CARROTS, Material.BEETROOTS, Material.COCOA, Material.NETHER_WART -> {
                val data = block.blockData
                if (data is Ageable && data.age == data.maximumAge) {
                    farmer.saveEXP(player, currentExp + 1)
                }
            }
            Material.MELON, Material.PUMPKIN -> {
                if (!BlockStorage.contains(location)) {
                    farmer.saveEXP(player, currentExp + 1)
                } else {
                    BlockStorage.removeBlock(location)
                }
            } else -> return
        }
        showBar.showBar(player, "§b농부 §f레벨 §b$currentLV §f경험치 (${(progressEXP * 100).roundToInt()}%)", progressEXP)
        if (currentExp >= currentLV * 70) {
            farmer.saveEXP(player, 0)
            farmer.saveLV(player, currentLV + 1)
            player.sendMessage("§a[직업] §f농부의 레벨이 올랐어요! §e$currentLV 레벨 §f-> §e${currentLV + 1} 레벨")
            showBar.showBar(player, "§b농부 §f레벨 §b$currentLV §f경험치 (${(progressEXP * 100).roundToInt()}%)", progressEXP)
        }
    }
}