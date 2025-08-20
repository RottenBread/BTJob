package org.rottenbread.bTJob

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.event.Listener
import org.rottenbread.bTJob.api.GetLevel
import org.rottenbread.bTJob.data.DataSave
import org.rottenbread.bTJob.event.OnBlockPlace
import org.rottenbread.bTJob.data.BlockStorage

class BTJob : JavaPlugin(), Listener, CommandExecutor {

    override fun onEnable() {
        BlockStorage.clearBlocks()
        this.getCommand("job")?.tabCompleter = TabCompleter()
        server.pluginManager.registerEvents(this, this)

        server.pluginManager.registerEvents(Farmer(), this)
        server.pluginManager.registerEvents(Miner(), this)
        server.pluginManager.registerEvents(Fisher(), this)

        server.pluginManager.registerEvents(GetLevel(), this)
        server.pluginManager.registerEvents(DataSave(), this)
        server.pluginManager.registerEvents(ShowBar(), this)
        server.pluginManager.registerEvents(OnBlockPlace(), this)
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (command.name.equals("job", ignoreCase = true)) {
            if (sender is Player) {
                if (sender.isOp) {
                    val api = GetLevel()
                    if (args.isNotEmpty()) {
                        when (args[0].lowercase()) {
                            "miner" -> {
                                sender.sendMessage("Your Miner Level: §7${api.getMiner(sender)}")
                            }

                            "fisher" -> {
                                sender.sendMessage("Your Fisher Level: §7${api.getFisher(sender)}")
                            }

                            "farmer" -> {
                                sender.sendMessage("Your Farmer Level: §7${api.getFarmer(sender)}")
                            }
                        }
                    }
                }
            }
        }
        return true
    }
}

