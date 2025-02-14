package me.irinque.CampfireChat.loaders;

import me.irinque.CampfireChat.Main;

import java.io.File;

public class Config implements Runnable
{
    File file_config = new File("plugins/campfire-chat/config.yml");
    public File get_file_config() {return file_config;}

    static Main plugin = Main.getInstance();

    public void run()
    {
        if (!get_file_config().exists())
        {
            plugin.getConfig().set("custom-join.toggle", true);
            plugin.getConfig().set("custom-join.message", "§a[+] §f{player}");

            plugin.getConfig().set("custom-quit.toggle", true);
            plugin.getConfig().set("custom-quit.message", "§c[-] §f{player}");

            plugin.getConfig().set("global-chat.toggle", true);
            plugin.getConfig().set("global-chat.message", "{world_prefix} §f{player} >> {message}");

            plugin.getConfig().set("local-chat.toggle", true);
            plugin.getConfig().set("local-chat.radius", 50);
            plugin.getConfig().set("local-chat.message", "§7{player} >> {message}");

            plugin.getConfig().set("message.NoRecipients", "§4 Your message was not seen by anyone.");

            plugin.saveConfig();

        }
    }
}
