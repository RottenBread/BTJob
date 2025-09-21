package org.rottenbread.bTJob

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerFishEvent
import org.bukkit.event.player.PlayerFishEvent.State
import org.rottenbread.bTJob.Lvup.lvup

class Fisher : Listener {

    @EventHandler
    fun onFish(event: PlayerFishEvent) {
        val player = event.player
        if (event.state != State.CAUGHT_FISH) {
            return
        } else {
            var currentEXP = 0
            currentEXP += 35

            lvup(player, "어부", currentEXP,false)
        }
    }
}