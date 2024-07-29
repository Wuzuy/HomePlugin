# HomePlugin
### O HomePlugin é um plugin para Minecraft que permite aos jogadores definir e teletransportar-se para suas casas. Com este plugin, os jogadores podem usar comandos simples para salvar e acessar suas localizações de home no jogo.

## Funcionalidades
Comando /sethome
Descrição: Define a localização atual do jogador como sua home.
Uso: /sethome

### Funcionamento: Salva a posição atual do jogador em um banco de dados. As coordenadas são armazenadas no formato world:x:y:z.
Comando /home
Descrição: Teletransporta o jogador para a localização salva como sua home.
Uso: /home

### Funcionamento: Recupera a localização salva no banco de dados e teletransporta o jogador para essas coordenadas.
Estrutura do Banco de Dados

## Tabela homes
A tabela homes armazena as localizações de casa dos jogadores. Ela possui a seguinte estrutura:

```sql
Copiar código
CREATE TABLE `homes` (
  `player_name` VARCHAR(255) NOT NULL,
  `location` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`player_name`)
);
```
```
player_name: Nome do jogador (chave primária).
location: Localização da home no formato world:x:y:z.
```

## Instalação

Baixe o Plugin: Obtenha o arquivo .jar do plugin HomePlugin.
Coloque na Pasta de Plugins: Coloque o arquivo .jar na pasta plugins do seu servidor Minecraft.
Reinicie o Servidor: Reinicie o servidor Minecraft para carregar o plugin.

## Configuração
O plugin utiliza um banco de dados MySQL para armazenar as localizações das casas. Certifique-se de configurar o banco de dados no arquivo de configuração do plugin (config.yml).

## Exemplo de configuração:
```yaml
Copiar código
database:
  host: localhost
  port: 3306
  database: homeplugin
  username: root
  password: sua_senha
```
Uso
Comando /sethome
Descrição: Define a localização da casa do jogador.
Uso: Execute o comando /sethome no jogo.
Exemplo: O jogador está em uma localização e executa /sethome para definir essa localização como sua casa.
Comando /home
Descrição: Teletransporta o jogador para sua localização de casa.
Uso: Execute o comando /home no jogo.
Exemplo: O jogador executa /home para ser teletransportado para a localização salva anteriormente.
Troubleshooting
Erro java.sql.SQLSyntaxErrorException: Table 'homeplugin.homes' doesn't exist: Verifique se a tabela homes foi criada corretamente no banco de dados. Utilize o comando SQL fornecido na seção "Estrutura do Banco de Dados" para criar a tabela.

Erro java.lang.NumberFormatException: For input string: Isso geralmente ocorre se as coordenadas armazenadas no banco de dados têm um formato inválido. Certifique-se de que as coordenadas estão no formato world:x:y:z e que os valores numéricos utilizam ponto (.) como separador decimal.

# Contribuição
## Se você deseja contribuir para o desenvolvimento do HomePlugin, siga estes passos:

Clone o Repositório: Clone o repositório para sua máquina local.
Faça Alterações: Realize as alterações e melhorias desejadas.
Submeta um Pull Request: Envie um pull request para revisar suas alterações.

# Licença
O HomePlugin é licenciado sob a Licença MIT.

Contato
Para mais informações ou suporte, entre em contato com o desenvolvedor através do e-mail: lucaslimasandin@gmail.com.

