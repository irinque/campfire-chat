package me.irinque.CampfireChat.commands;

import me.irinque.CampfireChat.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Sound;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class Message implements CommandExecutor
{
    static Main plugin = Main.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player player_sender = (Player) sender;
        StringBuilder string_builder = new StringBuilder();
        String message_receiver_template = plugin.getConfig().getString("custom-private-message.message-receiver");
        String message_sender_template = plugin.getConfig().getString("custom-private-message.message-sender");

        if (Objects.equals(plugin.getConfig().getString("custom-private-message.toggle"), "true"))
        {
            if (args.length != 0)
            {
                Player player_receiver = plugin.getServer().getPlayer(args[0]);
                if (player_receiver != null)
                {
                    List<String> message_content = Arrays.asList(args);
                    if (message_content.size() > 1)
                    {
                        for (int i = 1; i < message_content.size(); i++)
                        {
                            string_builder.append(message_content.get(i));
                            if (i < message_content.size() - 1)
                            {
                                string_builder.append(" ");
                            }
                        }
                        String message_processed = string_builder.toString();
                        player_receiver.sendMessage(message_receiver_template.replace("{private_message_sign}", "[✉]").replace("{sender}", player_sender.getDisplayName()).replace("{message}", message_processed));
                        player_sender.sendMessage(message_sender_template.replace("{private_message_sign}", "[✉]").replace("{sender}", player_sender.getDisplayName()).replace("{message}", message_processed).replace("{receiver}", player_receiver.getDisplayName()));
                        if (plugin.getConfig().getString("custom-private-message.notification").equals("true"))
                        {
                            player_receiver.playSound(player_receiver.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
                        }
                    }
                    else
                    {
                        player_sender.sendMessage(me.irinque.CampfireChat.parsers.Message.getMsg("EmptyMessage"));
                    }
                }
                else
                {
                    player_sender.sendMessage(me.irinque.CampfireChat.parsers.Message.getMsg("RecipientOffline"));
                }
            }
            else
            {
                player_sender.sendMessage(me.irinque.CampfireChat.parsers.Message.getMsg("RecipientOffline"));
            }
        }
        return true;
    }
}
