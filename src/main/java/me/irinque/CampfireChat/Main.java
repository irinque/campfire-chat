package me.irinque.CampfireChat;

import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;

public final class Main extends JavaPlugin
{
    public static Main instance;
    public static Main getInstance() {return instance;}

    private File directory_plugin = new File("plugins/CampfireChat");
    public File get_directory_plugin() {return directory_plugin;}

    @Override
    public void onEnable()
    {
        if (instance == null) {instance = this;}
        if (!get_directory_plugin().exists()) {get_directory_plugin().mkdirs();}

        getServer().getLogger().info("§a[CampfireChat] Plugin is ready");
    }

    @Override
    public void onDisable() {
        if (instance != null) {instance = null;}
        getServer().getLogger().info("§4[CampfireChat] Plugin was shutdown!");
    }
}
