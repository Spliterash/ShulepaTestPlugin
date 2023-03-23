package ru.spliterash.shulepaTestPlugin

import kotlinx.coroutines.delay
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withTimeoutOrNull
import org.apache.logging.log4j.LogManager
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import java.util.function.Consumer
import kotlin.coroutines.resume

private val text = """
╔══╗
║╔╗║
║╚╝╠══╦╦══╦═╗
║╔╗║╔╗║║║║║╩╣
╚╝╚╩╝╚╩╩╩╩╩═╝
"""
private val log = LogManager.getLogger(AnimePrinter::class.java)

class AnimePrinter {

    private val map = hashMapOf<Player, MutableList<Consumer<PlayerInteractEvent>>>()
    fun click(event: PlayerInteractEvent) {
        val listeners = map.remove(event.player) ?: return
        for (listener in listeners) {
            listener.accept(event)
        }
    }


    private suspend fun waitInteract(player: Player): PlayerInteractEvent = suspendCancellableCoroutine { cont ->
        val consumer = Consumer<PlayerInteractEvent> {
            cont.resume(it)
        }
        val clickListeners = map.computeIfAbsent(player) { arrayListOf() }

        cont.invokeOnCancellation {
            clickListeners -= consumer
            if (clickListeners.isEmpty())
                map.remove(player)

        }
        clickListeners += consumer
    }

    suspend fun print(player: Player) {
        val lines = text.split(Regex("[\n\r]+"))
        for (line in lines) {
            player.sendMessage(line)

            val interact = withTimeoutOrNull(5000) {
                waitInteract(player)
            }
            if (interact == null) {
                player.sendMessage("Ну вот, ты не успел нажать, поэтому положу тебе в инвентарь железный слиток через одну секунду")
                delay(1000)
                player.inventory.addItem(ItemStack(Material.IRON_INGOT))
                return
            }
            log.info("Interact with ${interact.item?.type ?: "HAND"}")
        }
    }
}