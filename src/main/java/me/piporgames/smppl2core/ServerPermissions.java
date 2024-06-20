package me.piporgames.smppl2core;

// Contiene todos los permisos del plugin en un bonito Enum
public class ServerPermissions {

    public enum listen {
        world, // Mensajes de cambio de mundo
        join; // Mensajes de unir al mundo

        public String toString() { return ("smppl2." + getDeclaringClass().getSimpleName() + "." + name());}
    }

}


