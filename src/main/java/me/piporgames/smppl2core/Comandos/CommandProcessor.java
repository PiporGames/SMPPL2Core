package me.piporgames.smppl2core.Comandos;

import me.piporgames.smppl2core.Reloadable;
import me.piporgames.smppl2core.Reloader;
import me.piporgames.smppl2core.SMPPL2Core;
import org.bukkit.Sound;import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

public class CommandProcessor implements CommandExecutor, Reloader {

    // Inicialización de la clase y sus variables
    private SMPPL2Core plugin;
    private List<Reloadable> pluginConfigSubscribers;
    public CommandProcessor(SMPPL2Core _plugin) {
        plugin = _plugin;
        pluginConfigSubscribers = new LinkedList<>();
    }

    // Método que contiene toda la lógica de comandos
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length > 0 && args[0].equals("limbo")) {

            if (args.length > 1) {
                /*
                 * LIMBO -> SET PORTAL
                 */
                if (args[1].equals("setPortal")) {
                    if (args.length == 5) {
                        try {
                            plugin.getConfig().set("limbo.portal.x", Double.parseDouble(args[2]));
                            plugin.getConfig().set("limbo.portal.y", Double.parseDouble(args[3]));
                            plugin.getConfig().set("limbo.portal.z", Double.parseDouble(args[4]));
                            plugin.saveConfig();
                            plugin.reloadConfig();
                            sender.sendMessage("§aSe ha establecido el portal correctamente.");
                            notifyReload();
                            return true;
                        } catch (NumberFormatException e) {
                            sender.sendMessage("§cLa entrada de datos es incorrecta.");
                            return true;
                        }
                    } else {
                        sender.sendMessage("§cUso: /smppl2core limbo setPortal {x} {y} {z}");
                        return true;
                    }

                    /*
                     * LIMBO -> SET DEST
                     */
                } else if (args[1].equals("setDest")) {

                    if (sender instanceof Player) {
                        plugin.getConfig().set("limbo.world", ((Player) sender).getLocation());
                        plugin.saveConfig();
                        sender.sendMessage("§aSe ha establecido el destino correctamente.");
                        notifyReload();
                        return true;
                    } else {
                        sender.sendMessage("§cEste comando solo está disponible para jugadores.");
                        return true;
                    }
                }
            }
            sender.sendMessage("§cUso: /smppl2core limbo {setPortal / setDest}");
            return true;



        } else if (args.length > 0 && args[0].equals("eventos")) {
            if (args.length > 1) {
                if (args[1].equals("alga")) {
                    if (args.length == 3 && (args[2].equals("true") || args[2].equals("false"))) {
                        try {
                            plugin.getConfig().set("events.alga", Boolean.parseBoolean(args[2]));
                            plugin.saveConfig();
                            plugin.reloadConfig();
                            sender.sendMessage("§aSe ha cambiado la configuración de 'alga' a " + plugin.getConfig().getBoolean("events.alga"));
                            notifyReload();
                            return true;
                        } catch (NumberFormatException ignored) {

                        }
                    }
                    sender.sendMessage("§cUso: /smppl2core eventos (evento) {true/false}");
                    return true;
                }
            }
            sender.sendMessage("§cUso: /smppl2core eventos {alga}");
            return true;
        } else if (args.length > 0 && args[0].equals("info")) {
            if (sender instanceof Player) {
                ((Player) sender).playSound(((Player) sender).getLocation(), Sound.ENTITY_WANDERING_TRADER_YES, 10, 1.0f);
            }
            sender.sendMessage("§7-----------------------------------------------------\n" +
                                "§r\n" +
                                "§d§lSMP §e§lPiporLandia §a§lFOREVER §rsobre §5Purpur §61.21 §7+ Plugins\n" +
                                "§7Basado en el código base de SMP §bPIPORLANDIA 2\n" +
                                "§r\n" +
                                "§eGracias a todos de SMP PL 1/2 por hacer esta saga posible.\n" +
                                "§7No hay BETA TESTERS.\n" +
                                "§r\n" +
                                "§7Creado por §fPi§cpor§eGames§7. Ofrecido por §bPiporGames Network§7.\n" +
                                "§7-----------------------------------------------------");
            return true;
        }
        sender.sendMessage("§cUso: /smppl2core {limbo / eventos / info}");
        return true;
    }

    // Método utilizado para registrar un Listener
    public void subscribe(Reloadable sub) {
        pluginConfigSubscribers.add(sub);
    }
    // Método utilizado para eliminar un Listener
    public void unsubscribe(Reloadable sub) {
        pluginConfigSubscribers.remove(sub);
    }
    // Método utilizado para notificar a todos los Listener
    public void notifyReload() {
        for (Reloadable sub : pluginConfigSubscribers) {
            sub.reload();
        }
    }

}
