package me.irinque.CampfireChat;

import me.irinque.CampfireChat.commands.Reload;
import me.irinque.CampfireChat.handlers.PlayerDeath;
import me.irinque.CampfireChat.handlers.PlayerJoin;
import me.irinque.CampfireChat.handlers.PlayerMessage;
import me.irinque.CampfireChat.handlers.PlayerQuit;
import me.irinque.CampfireChat.tabcompleters.ReloadTabCompleter;
import org.bukkit.plugin.java.JavaPlugin;
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

        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(), this);
        getServer().getPluginManager().registerEvents(new PlayerMessage(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeath(), this);

        getServer().getPluginCommand("reload-cfc").setExecutor(new Reload());
        getServer().getPluginCommand("reload-cfc").setTabCompleter(new ReloadTabCompleter());

        getServer().getLogger().info("§a[CFC] Plugin is ready");
    }

    @Override
    public void onDisable()
    {
        if (instance != null) {instance = null;}
        getServer().getLogger().info("§4[CFC] Plugin was shutdown!");
    }
}
