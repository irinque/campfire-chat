package me.irinque.CampfireChat.commands;

import me.irinque.CampfireChat.Main;
import me.irinque.CampfireChat.parsers.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CampfireChat  implements CommandExecutor
{
    static Main plugin = Main.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args)
    {
        Player player = (Player) sender;

        if (args[0].equals("reload") & player.hasPermission("campfire-chat.reload"))
        {
            plugin.reloadConfig();
            player.sendMessage(Message.getMsg("Reload"));
        }
        else if (!player.hasPermission("campfire-chat.reload"))
        {
            player.sendMessage(Message.getMsg("NoRights"));
        }
        return true;
    }
}
