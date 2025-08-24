package org.rottenbread.bTJob

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class TabCompleter : TabCompleter {
    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): List<String>? {
        if (args.size == 1) {
            return listOf("miner", "fisher", "farmer", "hunter").filter { it.startsWith(args[0], true) }
        }
        return null
    }
}