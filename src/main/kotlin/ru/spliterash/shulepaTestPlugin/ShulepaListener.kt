package ru.spliterash.shulepaTestPlugin

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot

class ShulepaListener(
    private val animePrinter: AnimePrinter
) : Listener {
    @EventHandler
    fun onInteract(event: PlayerInteractEvent) {
        if (event.hand != EquipmentSlot.HAND)
            return

        animePrinter.click(event)
    }
}