package me.irinque.CampfireChat.parsers;

import me.irinque.CampfireChat.Main;

public class Message
{
    static Main plugin = Main.getInstance();

    public static String getMsg(String path)
    {
        String message = plugin.getConfig().getString("message." + path);
        return "[CFC]" + message;
    }
}
