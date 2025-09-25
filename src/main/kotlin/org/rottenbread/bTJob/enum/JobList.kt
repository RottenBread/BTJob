package org.rottenbread.bTJob.enum

enum class JobType(val displayName: String, val colorCode: String, val barColor: String = "BLUE") {
    FARMER("농부", "§b"),
    MINER("광부", "§b"),
    HUNTER("전투", "§c", "RED"),
    FISHER("어부", "§b");
}