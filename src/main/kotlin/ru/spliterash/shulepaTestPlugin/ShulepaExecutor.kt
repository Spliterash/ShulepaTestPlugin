package ru.spliterash.shulepaTestPlugin

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

private val text = """
╔══╗
║╔╗║
║╚╝╠══╦╦══╦═╗
║╔╗║╔╗║║║║║╩╣
╚╝╚╩╝╚╩╩╩╩╩═╝
"""

class ShulepaExecutor(
    private val helper: CoroutineHelper,
    private val printer: AnimePrinter
) : CommandExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        helper.launch {
            printer.print(sender as Player)
        }
        return true
    }
}