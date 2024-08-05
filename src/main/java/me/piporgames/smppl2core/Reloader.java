package me.piporgames.smppl2core;

// Parte del supervisor de recarga de configuración.
// Patrón de diseño: OBSERVER
public interface Reloader {

    // Método que debe tener la clase implementadora, encargado de registrar un nuevo suscriptor.
    // Este método será llamado por la clase suscriptora, con tal de que se le notifique de nuevos cambios en el config.yml
    void subscribe(Reloadable sub);

    // Método que debe tener la clase implementadora, encargado de eliminar un suscriptor.
    // Este método será llamado por la clase suscriptora, con tal de que se le notifique de nuevos cambios en el config.yml
    // En principio, este método es irrelevante.
    void unsubscribe(Reloadable sub);

    // Método que debe tener la clase implementadora, encargado de notificar a todos los suscriptores.
    // Este método será llamado por la clase administradora, con tal de que se notifique de nuevos cambios en el config.yml
    // La clase administradora debe administrar por su propia cuenta la lista de suscriptores.
    void notifyReload();

}
