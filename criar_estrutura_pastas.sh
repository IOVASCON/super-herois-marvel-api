#!/bin/bash
#Execute o script com o comando: sh criar_estrutura_pastas.sh

# Caminho base do projeto
BASE_PATH="L:/VSCode/JAVA/DIO/super-herois-marvel-api/src/main/java/iovascon/projetando"

# Criando as pastas conforme a estrutura
mkdir -p $BASE_PATH/config
mkdir -p $BASE_PATH/constants
mkdir -p $BASE_PATH/controller
mkdir -p $BASE_PATH/document
mkdir -p $BASE_PATH/repository
mkdir -p $BASE_PATH/service

# Criando o arquivo HeroesApiApplication.java dentro do diretório base
touch $BASE_PATH/HeroesApiApplication.java

# Mensagem de confirmação
echo "Estrutura de pastas criada com sucesso em $BASE_PATH"
