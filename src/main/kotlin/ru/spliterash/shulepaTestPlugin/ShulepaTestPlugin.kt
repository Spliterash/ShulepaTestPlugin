package ru.spliterash.shulepaTestPlugin

import org.bukkit.plugin.java.JavaPlugin

class ShulepaTestPlugin : JavaPlugin() {
    override fun onEnable() {
        val printer = AnimePrinter()
        val helper = CoroutineHelper(this)

        getCommand("shulepa")?.setExecutor(ShulepaExecutor(helper, printer))
        server.pluginManager.registerEvents(ShulepaListener(printer), this)
    }
}