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
            val currentExp = fisher.loadEXP(player)
            val currentLV = fisher.loadLV(player)
            val progressEXP = currentExp.toDouble()/(currentLV.toDouble() * 70)

            fisher.saveEXP(player, currentExp + 35)

            showBar.showBar(player, "§b어부 §f레벨 §b$currentLV §f경험치 (${(progressEXP * 100).roundToInt()}%)", progressEXP)
            if (currentExp >= currentLV * 70) {
                fisher.saveEXP(player, 0)
                fisher.saveLV(player, currentLV + 1)
                player.sendMessage("§a[직업] §f어부의 레벨이 올랐어요! §e$currentLV 레벨 §f-> §e${currentLV + 1} 레벨")
                showBar.showBar(
                    player,
                    "§b어부 §f레벨 §b$currentLV §f경험치 (${(progressEXP * 100).roundToInt()}%)",
                    progressEXP
                )
            }
        }
    }
}