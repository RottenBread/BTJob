package org.rottenbread.bTFishnet

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.Material
import org.bukkit.event.block.Action

class BTFishnet : JavaPlugin(), Listener {

    override fun onEnable() {
        server.pluginManager.registerEvents(this, this)
    }

    @EventHandler
    fun onRightClick(event: PlayerInteractEvent) {
        if (event.hand == EquipmentSlot.HAND && event.clickedBlock?.type == Material.GOLD_BLOCK) {
            if (event.action == Action.RIGHT_CLICK_BLOCK) {
                GUIManager.openGUI(event.player)
                event.player.sendMessage("기모띠")
            }
        }
    }
}