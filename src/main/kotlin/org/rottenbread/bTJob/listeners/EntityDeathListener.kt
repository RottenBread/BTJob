package org.rottenbread.bTJob.listeners

import org.bukkit.attribute.Attribute
import org.bukkit.entity.Monster
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent
import org.rottenbread.bTJob.manager.JobManager.jobLevelup
import org.rottenbread.bTJob.enum.JobType

class EntityDeathListener: Listener {

    @EventHandler
    fun onMonsterDeath(event: EntityDeathEvent) {
        if (event.entity !is Monster) return
            val player = event.entity.killer ?: return
            val monster = event.entity as Monster
            val totalHealth = monster.getAttribute(Attribute.MAX_HEALTH)?.value ?: 20.0
            val calculateEXP = (totalHealth / 10).toInt().coerceAtLeast(1)
            jobLevelup(player, JobType.HUNTER, calculateEXP)
    }
}