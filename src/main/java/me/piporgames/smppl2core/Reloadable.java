package me.piporgames.smppl2core;

// Parte del supervisor de recarga de configuración.
// Patrón de diseño: OBSERVER
public interface Reloadable {

    // Método que debe tener la clase implementadora, encargado de recargar las variables internas de dicha clase.
    // Este método será llamado por la clase supervisora/administradora de los cambios del config.yml.
    //      En este momento, la clase encargada es CommandProcessor.
    void reload();

}
