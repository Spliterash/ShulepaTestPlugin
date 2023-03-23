package ru.spliterash.shulepaTestPlugin

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.bukkit.plugin.java.JavaPlugin

class CoroutineHelper(
    plugin: JavaPlugin
) {
    val scope: CoroutineScope = CoroutineScope(
        MinecraftDispatcher(plugin) +
                CoroutineExceptionHandler { _, ex -> ex.printStackTrace() }
    )


    fun launch(block: suspend CoroutineScope.() -> Unit) {
        scope.launch(block = block)
    }

    fun launchAsync(block: suspend CoroutineScope.() -> Unit) {
        scope.launch(context = Dispatchers.IO, block = block)
    }
}