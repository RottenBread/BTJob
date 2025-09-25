package org.rottenbread.bTJob.listeners

import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.rottenbread.bTJob.data.DataSave

class ItemListener : Listener {

    private val gateLevels = listOf(19, 39, 59, 79, 99)

    private val jobBooks = mapOf(
        "§a농부§f의 지식의 책" to "농부",
        "§b어부§f의 지식의 책" to "어부",
        "§7광부§f의 지식의 책" to "광부",
        "§c전투§f의 지식의 책" to "전투"
    )

    @EventHandler
    fun onBookUse(event: PlayerInteractEvent) {
        if (!event.action.isRightClick) return

        val player = event.player
        val item = player.inventory.itemInMainHand
        val itemName = item.itemMeta?.displayName ?: return

        val jobKey = jobBooks[itemName] ?: return

        val dataSave = DataSave(jobKey)
        val currentLV = dataSave.loadLV(player)

        if (currentLV in gateLevels) {
            if (currentLV >= 100) {
                player.sendMessage("§a[직업] §f이미 최고 레벨입니다.")
                return
            }

            dataSave.saveLV(player, currentLV + 1)
            dataSave.saveEXP(player, 0)

            player.sendMessage("§a[직업] §f지식의 책의 힘으로 한계를 돌파했습니다!")
            player.sendMessage("§a[직업] §f${jobKey}의 레벨이 §e${currentLV + 1}§a가 되었습니다!")
            player.playSound(player.location, Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 2.0f)
            item.amount -= 1
        } else {
            player.sendMessage("§c[알림] §f이 책은 ${gateLevels.joinToString(", ")} 레벨에만 사용할 수 있습니다.")
            player.playSound(player.location, Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1.0f, 0.5f)
        }
    }
}