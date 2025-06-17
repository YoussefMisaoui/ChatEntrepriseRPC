#!/bin/bash
echo "🛑 Arrêt et suppression du conteneur..."
docker stop rpc-chat-server
docker rm rpc-chat-server
