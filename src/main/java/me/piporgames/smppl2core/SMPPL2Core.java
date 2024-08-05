package me.piporgames.smppl2core;

import me.piporgames.smppl2core.Comandos.CommandProcessor;
import me.piporgames.smppl2core.Comandos.LimboTP;
import me.piporgames.smppl2core.Eventos.AlgaXP;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class SMPPL2Core extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        //Probar permiso
        //getLogger().info(ServerPermissions.listen.world.toString());

        // Generar config inicial, si no existe
        saveDefaultConfig();



        /*
         * REGISTRO DE COMANDOS Y EVENTOS
         */
        // --- EVENTOS ---
        GeneralListener ml = new GeneralListener();
        LimboTP ltp = new LimboTP(this);
        AlgaXP axp = new AlgaXP(this);
        getServer().getPluginManager().registerEvents(ml, this);
        getServer().getPluginManager().registerEvents(ltp, this);
        getServer().getPluginManager().registerEvents(axp, this);

        // --- COMANDOS ---
        CommandProcessor cp = new CommandProcessor(this);
        cp.subscribe(ltp);
        cp.subscribe(axp);
        getCommand("smppl2core").setExecutor(cp);



        this.getComponentLogger().info(Component.text("SMPPL2Core cargado.", NamedTextColor.AQUA));
        //getLogger().warning("Prueba warning");
        //getLogger().severe("Prueba error");
    }

    @Override
    public void onDisable() {
        this.getComponentLogger().info(Component.text("SMPPL2Core descargado.", NamedTextColor.AQUA));
    }
}
