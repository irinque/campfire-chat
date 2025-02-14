package me.irinque.CampfireChat.commands;

import me.irinque.CampfireChat.parsers.Message;
import org.bukkit.command.CommandExecutor;
import me.irinque.CampfireChat.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reload implements CommandExecutor
{
    static Main plugin = Main.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args)
    {
        Player player = (Player) sender;
        if (player.hasPermission("campfire-chat.reload-cfc"))
        {
            plugin.reloadConfig();
            player.sendMessage(Message.getMsg("Reload"));
        }
        else
        {
            player.sendMessage(Message.getMsg("NoRights"));
        }
        return true;
    }
}
