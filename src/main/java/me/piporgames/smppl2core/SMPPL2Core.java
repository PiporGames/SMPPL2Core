package me.piporgames.smppl2core;

import org.bukkit.plugin.java.JavaPlugin;

public final class SMPPL2Core extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("SMPPL2Core cargado.");
        getLogger().warning("Prueba warning");
        getLogger().severe("Prueba error");

        getLogger().info(ServerPermissions.listen.world.toString());

        getServer().getPluginManager().registerEvents(new MyListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
