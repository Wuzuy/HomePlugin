package br.com.wuzuy.commands;

import br.com.wuzuy.HomePlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Location;

import java.sql.SQLException;

public class HomeCommand implements CommandExecutor {

    private final HomePlugin plugin;

    public HomeCommand(HomePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Este comando só pode ser executado por um jogador.");
            return false;
        }

        Player player = (Player) sender;
        String homeLocation = null;
        try {
            homeLocation = plugin.getDataBaseManager().getHomeLocation(player.getName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (homeLocation == null || homeLocation.isEmpty()) {
            player.sendMessage("Você não tem uma home definida.");
            return false;
        }

        String[] parts = homeLocation.split(":");
        if (parts.length != 4) {
            player.sendMessage("Localização da home inválida.");
            return false;
        }

        String worldName = parts[0];
        double x, y, z;

        try {
            x = Double.parseDouble(parts[1].replace(',', '.'));
            y = Double.parseDouble(parts[2].replace(',', '.'));
            z = Double.parseDouble(parts[3].replace(',', '.'));
        } catch (NumberFormatException e) {
            player.sendMessage("Formato de coordenadas inválido.");
            return false;
        }

        Location home = new Location(player.getServer().getWorld(worldName), x, y, z);
        player.teleport(home);
        player.sendMessage("Teletransportado para sua home!");

        return true;
    }
}
