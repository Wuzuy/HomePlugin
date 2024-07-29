#!/bin/bash

# Nome do contêiner Docker
CONTAINER_NAME="minecraft-server"

# Nome da imagem Docker
IMAGE_NAME="minecraft-server-image"

# Verifique se o contêiner já está em execução e pare-o se necessário
if docker ps -q -f name=$CONTAINER_NAME; then
    echo "Parando o contêiner em execução..."
    docker stop $CONTAINER_NAME
fi

# Remova o contêiner se ele existir
if docker ps -a -q -f name=$CONTAINER_NAME; then
    echo "Removendo o contêiner existente..."
    docker rm $CONTAINER_NAME
fi

# Construa a imagem Docker
docker build -t $IMAGE_NAME .

# Inicie o contêiner
docker run -d -p 25565:25565 --name $CONTAINER_NAME $IMAGE_NAME
