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

        when (block.type) {
            Material.WHEAT, Material.POTATOES, Material.CARROTS, Material.BEETROOTS, Material.COCOA, Material.NETHER_WART -> {
                val data = block.blockData
                if (data is Ageable && data.age == data.maximumAge) {
                    farmer.saveEXP(player, farmer.loadEXP(player) + 1)
                }
            }
            Material.MELON, Material.PUMPKIN -> {
                if (!BlockStorage.contains(location)) {
                    farmer.saveEXP(player, farmer.loadEXP(player) + 1)
                } else {
                    BlockStorage.removeBlock(location)
                }
            } else -> return
        }
        if (farmer.loadEXP(player) >= farmer.loadLV(player) * 70) {
            farmer.saveEXP(player, farmer.loadEXP(player)-(farmer.loadLV(player) * 70))
            farmer.saveLV(player, farmer.loadLV(player) + 1)
            player.sendMessage("§a[직업] §f농부의 레벨이 올랐어요! §e${farmer.loadLV(player) - 1} 레벨 §f-> §e${farmer.loadLV(player)} 레벨")
            showBar.showBar(
                player,
                "§b농부 §f레벨 §b$farmer.loadLV(player) §f경험치 (${(farmer.loadEXP(player).toDouble()/(farmer.loadLV(player).toDouble() * 70) * 100).roundToInt()}%)",
                farmer.loadEXP(player).toDouble()/(farmer.loadLV(player).toDouble() * 70)
            )
        }
        else {
            showBar.showBar(
                player,
                "§b농부 §f레벨 §b$farmer.loadLV(player) §f경험치 (${(farmer.loadEXP(player).toDouble()/(farmer.loadLV(player).toDouble() * 70) * 100).roundToInt()}%)",
                farmer.loadEXP(player).toDouble()/(farmer.loadLV(player).toDouble() * 70)
            )
        }
    }
}