package org.rottenbread.bTFishnet

import org.bukkit.entity.Player
import org.bukkit.Bukkit

object GUIManager {
    fun openGUI(player: Player) {
        val inventory = Bukkit.createInventory(null, 9, "Custom Inventory")
        player.openInventory(inventory)
    }
}
