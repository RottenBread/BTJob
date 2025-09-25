package org.rottenbread.bTJob.listeners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerFishEvent
import org.bukkit.event.player.PlayerFishEvent.State
import org.rottenbread.bTJob.enum.JobType
import org.rottenbread.bTJob.manager.JobManager.jobLevelup

class PlayerFishListener : Listener {

    @EventHandler
    fun onFish(event: PlayerFishEvent) {
        val player = event.player
        if (event.state != State.CAUGHT_FISH) {
            return
        } else {
            jobLevelup(player, JobType.FISHER, 35)
        }
    }
}