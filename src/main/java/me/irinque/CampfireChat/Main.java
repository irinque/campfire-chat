package me.irinque.CampfireChat;

import me.irinque.CampfireChat.handlers.PlayerJoin;
import me.irinque.CampfireChat.handlers.PlayerMessage;
import me.irinque.CampfireChat.handlers.PlayerQuit;
import me.irinque.CampfireChat.loaders.Config;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import java.io.File;

public final class Main extends JavaPlugin
{
    public static Main instance;
    public static Main getInstance() {return instance;}

    private File directory_plugin = new File("plugins/campfire-chat");
    public File get_directory_plugin() {return directory_plugin;}

    @Override
    public void onEnable()
    {
        if (instance == null) {instance = this;}
        if (!get_directory_plugin().exists()) {get_directory_plugin().mkdirs();}

        Bukkit.getScheduler().runTaskAsynchronously(this, new Config());

        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(), this);
        getServer().getPluginManager().registerEvents(new PlayerMessage(), this);

        getServer().getLogger().info("§a[CampfireChat] Plugin is ready");
    }

    @Override
    public void onDisable()
    {
        if (instance != null) {instance = null;}
        getServer().getLogger().info("§4[CampfireChat] Plugin was shutdown!");
    }
}
