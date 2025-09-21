package org.rottenbread.bTJob

import org.bukkit.entity.Player
import org.rottenbread.bTJob.data.DataSave
import kotlin.math.roundToInt

object Lvup {

    private val showBar = ShowBar()
    private const val EXP_POINTS = 70 // TODO: 기본 경험치통 수정 config.yml 바꾸기

    fun lvup(player: Player, jobName: String, addEXP: Int, isHunter: Boolean) {
        var currentLV = DataSave(jobName).loadLV(player)
        var currentEXP = DataSave(jobName).loadEXP(player)

        currentEXP += addEXP

        if (currentEXP >= currentLV * EXP_POINTS) {
            currentLV += 1
            currentEXP -= (currentLV - 1) * EXP_POINTS
            player.sendMessage("§a[직업] §f${jobName}의 레벨이 올랐어요! §e${currentLV - 1} 레벨 §f-> §e${currentLV} 레벨")
        }

        DataSave(jobName).saveLV(player, currentLV)
        DataSave(jobName).saveEXP(player, currentEXP)

        if (isHunter) {
            showBar.showBar(
                player,
                "§c$jobName §f레벨 §c$currentLV §f경험치 (${(currentEXP.toDouble() / (currentLV.toDouble() * EXP_POINTS) * 100).roundToInt()}%)",
                currentEXP.toDouble() / (currentLV.toDouble() * EXP_POINTS),
                true
            )
        } else {
            showBar.showBar(
                player,
                "§b$jobName §f레벨 §b$currentLV §f경험치 (${(currentEXP.toDouble() / (currentLV.toDouble() * EXP_POINTS) * 100).roundToInt()}%)",
                currentEXP.toDouble() / (currentLV.toDouble() * EXP_POINTS)
            )
        }
    }
}