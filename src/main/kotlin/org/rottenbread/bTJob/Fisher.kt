package org.rottenbread.bTJob

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.rottenbread.bTJob.data.DataSave
import org.bukkit.event.player.PlayerFishEvent
import org.bukkit.event.player.PlayerFishEvent.State
import kotlin.math.roundToInt

class Fisher : Listener {

    private val showBar = ShowBar()

    @EventHandler
    fun onFish(event: PlayerFishEvent) {
        val player = event.player
        if (event.state == State.CAUGHT_FISH) {
            val fisher = DataSave("fisher")
            fisher.saveEXP(player, fisher.loadEXP(player) + 35)


            if (fisher.loadEXP(player) >= fisher.loadLV(player) * 70) {
                fisher.saveEXP(player, fisher.loadEXP(player)-(fisher.loadLV(player) * 70))
                fisher.saveLV(player, fisher.loadLV(player) + 1)
                player.sendMessage("§a[직업] §f어부의 레벨이 올랐어요! §e${fisher.loadLV(player) - 1} 레벨 §f-> §e${fisher.loadLV(player)} 레벨")
                showBar.showBar(
                    player,
                    "§b어부 §f레벨 §b$(fisher.loadLV(player) §f경험치 (${(fisher.loadEXP(player).toDouble()/(fisher.loadLV(player).toDouble() * 70) * 100).roundToInt()}%)",
                    fisher.loadEXP(player).toDouble()/(fisher.loadLV(player).toDouble() * 70)
                )
            }
            else {
                showBar.showBar(
                    player,
                    "§b어부 §f레벨 §b$fisher.loadLV(player) §f경험치 (${(fisher.loadEXP(player).toDouble()/(fisher.loadLV(player).toDouble() * 70) * 100).roundToInt()}%)",
                    fisher.loadEXP(player).toDouble()/(fisher.loadLV(player).toDouble() * 70)
                )
            }
        }
    }
}