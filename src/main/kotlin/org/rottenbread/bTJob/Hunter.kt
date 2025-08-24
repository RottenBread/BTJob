package org.rottenbread.bTJob

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.rottenbread.bTJob.data.DataSave
import org.bukkit.entity.Monster
import org.bukkit.attribute.Attribute
import org.bukkit.event.entity.EntityDeathEvent
import kotlin.math.roundToInt

class Hunter : Listener {

    private val showBar = ShowBar()

    @EventHandler
    fun onMonsterDeath(event: EntityDeathEvent) {
        if (event.entity !is Monster) return

        val player = event.entity.killer ?: return
        val monster = event.entity as Monster
        val hunter = DataSave("hunter")
        val totalHealth = monster.getAttribute(Attribute.MAX_HEALTH)?.value ?: 20.0
        val calculateEXP = (totalHealth / 10).toInt().coerceAtLeast(1)

        hunter.saveEXP(player, hunter.loadEXP(player) + calculateEXP)

        if (hunter.loadEXP(player) >= hunter.loadLV(player) * 100) {
            hunter.saveEXP(player, hunter.loadEXP(player)-(hunter.loadLV(player) * 100))
            hunter.saveLV(player, hunter.loadLV(player) + 1)
            player.sendMessage("§a[직업] §f전투의 레벨이 올랐어요! §e${hunter.loadLV(player) - 1} 레벨 §f-> §e${hunter.loadLV(player)} 레벨")
            showBar.showBar(
                player,
                "§c전투 §f레벨 §c${hunter.loadLV(player)} §f경험치 (${(hunter.loadEXP(player).toDouble()/(hunter.loadLV(player).toDouble() * 100) * 100).roundToInt()}%)",
                hunter.loadEXP(player).toDouble()/(hunter.loadLV(player).toDouble() * 100),
                true
            )
        }
        else {
            showBar.showBar(
                player,
                "§c전투 §f레벨 §c${hunter.loadLV(player)} §f경험치 (${(hunter.loadEXP(player).toDouble()/(hunter.loadLV(player).toDouble() * 100) * 100).roundToInt()}%)",
                hunter.loadEXP(player).toDouble()/(hunter.loadLV(player).toDouble() * 100),
                true
            )
        }
    }
}