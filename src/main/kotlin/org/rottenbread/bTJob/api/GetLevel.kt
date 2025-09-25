package org.rottenbread.bTJob.api

import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.rottenbread.bTJob.data.DataSave

class GetLevel: Listener {
    fun getFarmer(player: Player): Int {
        val farmer = DataSave("농부")
        return farmer.loadLV(player)
    }

    fun getMiner(player: Player): Int {
        val miner = DataSave("광부")
        return miner.loadLV(player)
    }

    fun getFisher(player: Player): Int {
        val fisher = DataSave("어부")
        return fisher.loadLV(player)
    }

    fun getHunter(player: Player): Int {
        val hunter = DataSave("전투")
        return hunter.loadLV(player)
    }
}