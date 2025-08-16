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

        when (block.type) {
            Material.COAL_ORE, Material.DEEPSLATE_COAL_ORE -> {
                if (!BlockStorage.contains(location)) {
                    miner.saveEXP(player, miner.loadEXP(player) + 3)
                } else {
                    BlockStorage.removeBlock(location)
                }
            }
            Material.COPPER_ORE, Material.DEEPSLATE_COPPER_ORE -> {
                if (!BlockStorage.contains(location)) {
                    miner.saveEXP(player, miner.loadEXP(player) + 3)
                } else {
                    BlockStorage.removeBlock(location)
                }
            }
            Material.IRON_ORE, Material.DEEPSLATE_IRON_ORE -> {
                if (!BlockStorage.contains(location)) {
                    miner.saveEXP(player, miner.loadEXP(player) + 7)
                } else {
                    BlockStorage.removeBlock(location)
                }
            }
            Material.EMERALD_ORE, Material.DEEPSLATE_EMERALD_ORE -> {
                if (!BlockStorage.contains(location)) {
                    miner.saveEXP(player, miner.loadEXP(player) + 100)
                } else {
                    BlockStorage.removeBlock(location)
                }
            }
            Material.REDSTONE_ORE, Material.DEEPSLATE_REDSTONE_ORE -> {
                if (!BlockStorage.contains(location)) {
                    miner.saveEXP(player, miner.loadEXP(player) + 5)
                } else {
                    BlockStorage.removeBlock(location)
                }
            }
            Material.DIAMOND_ORE, Material.DEEPSLATE_DIAMOND_ORE -> {
                if (!BlockStorage.contains(location)) {
                    miner.saveEXP(player, miner.loadEXP(player) + 50)
                } else {
                    BlockStorage.removeBlock(location)
                }
            }
            Material.LAPIS_ORE, Material.DEEPSLATE_LAPIS_ORE -> {
                if (!BlockStorage.contains(location)) {
                    miner.saveEXP(player, miner.loadEXP(player) + 5)
                } else {
                    BlockStorage.removeBlock(location)
                }
            }
            Material.GOLD_ORE, Material.DEEPSLATE_GOLD_ORE -> {
                if (!BlockStorage.contains(location)) {
                    miner.saveEXP(player, miner.loadEXP(player) + 7)
                } else {
                    BlockStorage.removeBlock(location)
                }
            } else -> return
        }
        if (miner.loadEXP(player) >= miner.loadLV(player) * 70) {
            miner.saveEXP(player, miner.loadEXP(player)-(miner.loadLV(player) * 70))
            miner.saveLV(player, miner.loadLV(player) + 1)
            player.sendMessage("§a[직업] §f광부의 레벨이 올랐어요! §e${miner.loadLV(player) - 1} 레벨 §f-> §e${miner.loadLV(player)} 레벨")

            showBar.showBar(
                player,
                "§b광부 §f레벨 §b${miner.loadLV(player)} §f경험치 (${(miner.loadEXP(player).toDouble()/(miner.loadLV(player).toDouble() * 70) * 100).roundToInt()}%)",
                miner.loadEXP(player).toDouble()/(miner.loadLV(player).toDouble() * 70)
            )
        }
        else {
            showBar.showBar(
                player,
                "§b광부 §f레벨 §b${miner.loadLV(player)} §f경험치 (${(miner.loadEXP(player).toDouble()/(miner.loadLV(player).toDouble() * 70) * 100).roundToInt()}%)",
                miner.loadEXP(player).toDouble()/(miner.loadLV(player).toDouble() * 70)
            )
        }
    }
}