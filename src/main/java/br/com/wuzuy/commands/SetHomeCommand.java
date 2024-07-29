package br.com.wuzuy.commands;

import br.com.wuzuy.HomePlugin;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class SetHomeCommand implements CommandExecutor {

    private final HomePlugin plugin;

    public SetHomeCommand(HomePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Apenas jogadores podem usar este comando.");
            return false;
        }

        Player player = (Player) sender;
        Location location = player.getLocation();
        String homeLocation = String.format("%s:%.2f:%.2f:%.2f", location.getWorld().getName(), location.getX(), location.getY(), location.getZ());

        try {
            plugin.getDataBaseManager().setHomeLocation(player.getName(), homeLocation);
            player.sendMessage("Home configurada com sucesso!");
        } catch (SQLException e) {
            player.sendMessage("Erro ao configurar a home.");
            e.printStackTrace();
        }

        return true;
    }
}
