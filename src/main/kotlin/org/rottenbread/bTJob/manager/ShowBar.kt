package org.rottenbread.bTJob.manager

import org.bukkit.event.Listener
import org.bukkit.Bukkit
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle
import org.bukkit.boss.BossBar
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitRunnable

class ShowBar : Listener {
    private val plugin: Plugin = Bukkit.getPluginManager().getPlugin("BTJob")!!
    private val playerBossBars: MutableMap<Player, BossBar> = mutableMapOf()
    private val taskMap: MutableMap<Player, BukkitRunnable> = mutableMapOf()

    fun showBar(player: Player, title: String, progress: Double, barColor: String) {
        playerBossBars[player]?.let { existingBossBar ->
            existingBossBar.removePlayer(player)
            playerBossBars.remove(player)
        }

        val bossBar = Bukkit.createBossBar(title, BarColor.valueOf(barColor), BarStyle.SOLID)

        bossBar.progress = progress
        bossBar.addPlayer(player)

        playerBossBars[player] = bossBar

        taskMap[player]?.cancel()

        val task = object : BukkitRunnable() {
            override fun run() {
                bossBar.removePlayer(player)
                playerBossBars.remove(player)
            }
        }
        task.runTaskLater(plugin, 20L * 3)
        taskMap[player] = task
    }
}