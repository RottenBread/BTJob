package org.rottenbread.bTJob.data

import org.bukkit.Bukkit
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin
import java.io.File
import java.util.UUID

class DataSave (private val job: String) : Listener {

    private val plugin: Plugin = Bukkit.getPluginManager().getPlugin("BTJob")!!
    private val jobDataFolder = File(plugin.dataFolder, job)

    init {
        if (!jobDataFolder.exists()) {
            jobDataFolder.mkdirs()
        }
    }

    fun saveEXP(player: Player, experience: Int) {
        val playerFile = getPlayerFile(player.uniqueId)
        val config: FileConfiguration = YamlConfiguration.loadConfiguration(playerFile)
        config.set("experience", experience)
        config.save(playerFile)
    }

    fun loadEXP(player: Player): Int {
        val playerFile = getPlayerFile(player.uniqueId)
        val config: FileConfiguration = YamlConfiguration.loadConfiguration(playerFile)
        return config.getInt("experience", 0)
    }

    fun saveLV(player: Player, level: Int) {
        val playerFile = getPlayerFile(player.uniqueId)
        val config: FileConfiguration = YamlConfiguration.loadConfiguration(playerFile)
        config.set("level", level)
        config.save(playerFile)
    }

    fun loadLV(player: Player): Int {
        val playerFile = getPlayerFile(player.uniqueId)
        val config: FileConfiguration = YamlConfiguration.loadConfiguration(playerFile)
        return config.getInt("level", 1)
    }

    private fun getPlayerFile(uuid: UUID): File {
        return File(jobDataFolder, "$uuid.yml")
    }
}
