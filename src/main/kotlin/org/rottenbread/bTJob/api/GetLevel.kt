package org.rottenbread.bTJob.api

import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.rottenbread.bTJob.data.DataSave

class GetLevel: Listener {
    fun getFarmer(player: Player): Int {
        val farmer = DataSave("farmer")
        return farmer.loadLV(player)
    }

    fun getMiner(player: Player): Int {
        val miner = DataSave("miner")
        return miner.loadLV(player)
    }

    fun getFisher(player: Player): Int {
        val fisher = DataSave("fisher")
        return fisher.loadLV(player)
    }
}
