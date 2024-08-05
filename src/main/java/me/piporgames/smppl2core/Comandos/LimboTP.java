package me.piporgames.smppl2core.Comandos;

import me.piporgames.smppl2core.Reloadable;
import me.piporgames.smppl2core.SMPPL2Core;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class LimboTP implements Listener, Reloadable {

    private SMPPL2Core plugin;
    private Location worldLocation;
    private double teleporterX;
    private double teleporterY;
    private double teleporterZ;

    public LimboTP(SMPPL2Core _plugin) {
        plugin = _plugin;

        reload();
    }

    @EventHandler
    public void teleportToLimbo(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        // Si estamos haciendo click derecho a un bloque
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Block target = event.getClickedBlock();

            if (target != null && target.getLocation().getBlockX() == teleporterX && target.getLocation().getBlockY() == teleporterY && target.getLocation().getBlockZ() == teleporterZ) {
                player.teleport(worldLocation);
                player.sendMessage("§e¡Bienvenido al Limbo™!");
                player.sendMessage("§eGracias por ser parte de SMP PiporLandia 2");
                player.sendMessage("§ePara volver al mundo principal, reconectate al server.");
            }
        }
    }

    // Llamar a este método fuerza a la clase a recargar la configuración
    public void reload() {
        FileConfiguration config = plugin.getConfig();
        try {
            worldLocation = config.getLocation("limbo.world");
            teleporterX = config.getDouble("limbo.portal.x");
            teleporterY = config.getDouble("limbo.portal.y");
            teleporterZ = config.getDouble("limbo.portal.z");
            plugin.getComponentLogger().info(Component.text(getClass().getSimpleName(), NamedTextColor.GREEN));
        } catch (Exception e) {
            plugin.getLogger().warning("Error en la configuración ("+ getClass().getSimpleName() + "): " + e);
        }
    }
}
