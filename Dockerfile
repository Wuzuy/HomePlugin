# Use uma imagem base do OpenJDK
FROM openjdk:17-jdk

# Diretório de trabalho no contêiner
WORKDIR /app

# Copie o arquivo JAR do plugin e o arquivo de configuração para o diretório de trabalho
COPY build/libs/HomePlugin.jar /app/HomePlugin.jar
COPY src/main/resources/config.yml /app/config.yml


# Baixe o servidor Minecraft (ou você pode usar uma versão já disponível no Docker Hub)
RUN wget -O minecraft_server.jar https://launcher.mojang.com/v1/objects/0c5f723a7b6c8a5f63e30bd5e5c5e342f72f4e6c/server.jar

# Exponha a porta padrão do Minecraft
EXPOSE 25565

# Comando para iniciar o servidor Minecraft
CMD ["java", "-Xmx2G", "-Xms1G", "-jar", "minecraft_server.jar", "nogui"]
