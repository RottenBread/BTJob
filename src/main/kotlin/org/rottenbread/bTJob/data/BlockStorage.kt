package org.rottenbread.bTJob.data

import org.bukkit.Location

object BlockStorage {
    val placedBlocks: MutableSet<Location> = mutableSetOf()

    fun addBlock(location: Location) {
        placedBlocks.add(location)
    }

    fun removeBlock(location: Location) {
        placedBlocks.remove(location)
    }

    fun clearBlocks() {
        placedBlocks.clear()
    }

    fun contains(location: Location): Boolean {
        return placedBlocks.contains(location)
    }
}