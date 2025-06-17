#!/bin/bash
echo "🚀 Lancement du conteneur RPC Chat sur le port 8080..."
docker run -d -p 8080:8080 --name rpc-chat-server rpc-chat-app
