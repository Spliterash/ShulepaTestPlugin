package ru.spliterash.shulepaTestPlugin

import kotlinx.coroutines.CoroutineDispatcher
import org.bukkit.plugin.java.JavaPlugin
import kotlin.coroutines.CoroutineContext

class MinecraftDispatcher(
    private val plugin: JavaPlugin
) : CoroutineDispatcher() {
    override fun isDispatchNeeded(context: CoroutineContext): Boolean {
        return !plugin.server.isPrimaryThread
    }

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        plugin.server.scheduler.runTask(plugin, block)
    }
}