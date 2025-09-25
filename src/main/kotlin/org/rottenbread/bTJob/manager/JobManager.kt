package org.rottenbread.bTJob.manager

import org.bukkit.entity.Player
import org.rottenbread.bTJob.data.DataSave
import org.rottenbread.bTJob.enum.JobType
import kotlin.math.roundToInt

object JobManager {

    private val showBar = ShowBar()
    private const val EXP_MULTIPLIES = 70 // TODO: 기본 경험치통 수정 config.yml 바꾸기
    private val maxLevels = listOf(19, 39, 59, 79, 99) // TODO: maxLevels config.yml 바꾸기

    fun jobLevelup(player: Player, jobType: JobType, addEXP: Int) {
        val dataSave = DataSave(jobType.name)

        var currentLV = dataSave.loadLV(player)
        var currentEXP = dataSave.loadEXP(player)

        currentEXP += addEXP

        if (currentEXP >= currentLV * EXP_MULTIPLIES) {
            if (currentLV in maxLevels) {
                player.sendMessage("§a[직업] §f지식의 책으로 레벨을 확장해야 합니다!")
                return
            } else {
                currentLV += 1
                currentEXP -= (currentLV - 1) * EXP_MULTIPLIES
                player.sendMessage("§a[직업] ${jobType.colorCode}${jobType.displayName}§f의 레벨이 올랐어요! §e${currentLV - 1} 레벨 §f-> §e${currentLV} 레벨")
            }
        }

        dataSave.saveLV(player, currentLV)
        dataSave.saveEXP(player, currentEXP)

        showBar.showBar(
            player,
            "${jobType.colorCode}${jobType.displayName} §f레벨 ${jobType.colorCode}$currentLV §f경험치 (${(currentEXP.toDouble() / (currentLV.toDouble() * EXP_MULTIPLIES) * 100).roundToInt()}%)",
            currentEXP.toDouble() / (currentLV.toDouble() * EXP_MULTIPLIES),
            jobType.barColor
        )
    }
}