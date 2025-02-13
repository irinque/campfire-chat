package me.irinque.CampfireChat.loaders;

import me.irinque.CampfireChat.Main;

import java.io.File;

public class Config implements Runnable
{
    File file_config = new File("plugins/CampfireChat/config.yml");
    public File get_file_config() {return file_config;}

    static Main plugin = Main.getInstance();

    public void run()
    {
        if (!get_file_config().exists())
        {
            plugin.getConfig().set("custom-join.toggle", true);
            plugin.getConfig().set("custom-join.message", "§a[+] {player}");

            plugin.saveConfig();

        }
    }
}
