package br.com.wuzuy.listeners;

import br.com.wuzuy.HomePlugin;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerTeleportListener implements Listener {

    private final HomePlugin plugin;

    public PlayerTeleportListener(HomePlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        if (event.getCause() == PlayerTeleportEvent.TeleportCause.COMMAND) {
            Location location = event.getTo();
            location.getWorld().spawnParticle(Particle.END_ROD, location, 50);
        }
    }
}
