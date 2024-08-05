package me.piporgames.smppl2core.Eventos;

import me.piporgames.smppl2core.Reloadable;
import me.piporgames.smppl2core.SMPPL2Core;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class AlgaXP implements Listener, Reloadable {

    private SMPPL2Core plugin;
    private boolean active;
    public AlgaXP(SMPPL2Core _plugin) {
        plugin = _plugin;
        reload();
    }

    // Evento - Alga XP
    @EventHandler
    public void algaXP(PlayerInteractEvent event) {
        if (active) {
            Player player = event.getPlayer();
            PlayerInventory inv = player.getInventory();
            // Si estamos haciendo click derecho
            if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                // Si hay kelp en la mano derecha
                ItemStack item = event.getItem();
                if (item != null && item.getType().equals(Material.KELP)) {
                    item.subtract();
                    player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EAT, 10, 1.0f);
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1.0f);
                    player.giveExp(1);
                    player.sendActionBar("§e+1 XP");
                }
            }
        }
    }

    // Llamar a este método fuerza a la clase a recargar la configuración
    public void reload() {
        FileConfiguration config = plugin.getConfig();
        try {
            active = config.getBoolean("events.alga");
            plugin.getComponentLogger().info(Component.text(getClass().getSimpleName(), NamedTextColor.GREEN));
        } catch (Exception e) {
            plugin.getLogger().warning("Error en la configuración ("+ getClass().getSimpleName() + "): " + e);
        }
    }
}
