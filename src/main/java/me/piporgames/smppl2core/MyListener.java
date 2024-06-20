package me.piporgames.smppl2core;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;

public class MyListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        player.sendMessage("hello :)");
        event.setJoinMessage(null);
    }


    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {
        //Por cada jugador en línea, mandar un mensaje si tienen el permiso específico
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            if (player.hasPermission(ServerPermissions.listen.world.toString())) {

                //Determinamos si está entrando o saliendo del overworld
                if (Objects.equals(event.getFrom().getName(), "world")) {
                    // Si está saliendo, entonces entramos en otra dimensión
                    if (Objects.equals(player.getWorld().getName(), "world_nether")) {
                        // nether
                        player.sendMessage("§e" + player.getName() + " ha entrado al §cInframundo");
                    } else if (Objects.equals(player.getWorld().getName(), "world_the_end")) {
                        // end
                        player.sendMessage("§e" + player.getName() + " ha entrado a §fEl Fin");
                    } else if (Objects.equals(player.getWorld().getName(), "limbo")) {
                        player.sendMessage("§e" + player.getName() + " ha entrado a El Limbo");
                    } else { // Si a donde vamos no es ninguno registrado, entonces estamos entrando a otra dimensión desconocida.
                        player.sendMessage("§e" + player.getName() + " ha entrando en una dimensión oculta");
                    }

                } else {
                    // Si está entrando, entonces estamos saliendo de otra dimensión
                    if (Objects.equals(event.getFrom().getName(), "world_nether")) {
                        player.sendMessage("§e" + player.getName() + " ha salido del §cInframundo");
                    } else if (Objects.equals(event.getFrom().getName(), "world_the_end")) {
                        player.sendMessage("§e" + player.getName() + " ha salido de §fEl Fin");
                    } else if (Objects.equals(event.getFrom().getName(), "limbo")) {
                        player.sendMessage("§e" + player.getName() + " ha salido de El Limbo");
                    } else if (Objects.equals(event.getFrom().getName(), "world")) {
                        // Si de donde salimos es el overworld, entonces estamos entrando a otra dimensión desconocida.
                        player.sendMessage("§e" + player.getName() + " ha salido de una dimensión oculta");
                    }
                }

            }
        }
    }
}
