package br.com.wuzuy;

import br.com.wuzuy.commands.HomeCommand;
import br.com.wuzuy.commands.SetHomeCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public class HomePlugin extends JavaPlugin {

    private DataBaseManager dataBaseManager;

    @Override
    public void onEnable() {
        getLogger().info("HomePlugin Enabled");

        // Cria o arquivo de configuração se não existir
        saveDefaultConfig();

        // Configurações do banco de dados
        String host = getConfig().getString("database.host");
        int port = getConfig().getInt("database.port");
        String database = getConfig().getString("database.database");
        String username = getConfig().getString("database.username");
        String password = getConfig().getString("database.password");

        // Inicializar o banco de dados
        dataBaseManager = new DataBaseManager(host, port, database, username, password);
        try {
            dataBaseManager.connect();
        } catch (SQLException e) {
            getLogger().severe("Não foi possível conectar ao banco de dados.");
            e.printStackTrace();
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        // Registrar comandos
        getCommand("home").setExecutor(new HomeCommand(this));
        getCommand("sethome").setExecutor(new SetHomeCommand(this));
    }

    @Override
    public void onDisable() {
        // Desconectar do banco de dados
        if (dataBaseManager != null) {
            dataBaseManager.disconnect();
        }
    }

    public DataBaseManager getDataBaseManager() {
        return dataBaseManager;
    }
}
