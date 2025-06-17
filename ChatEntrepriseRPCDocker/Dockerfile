# ✅ Image de base officielle
FROM openjdk:11-jre

# 🏷️ Informations utiles
LABEL maintainer="etudiant@ueu.ma"
LABEL version="1.0"
LABEL description="Application Chat RPC - Projet Docker Java"

# 📁 Dossier de travail dans le conteneur
WORKDIR /app

# 📦 Copier le fichier compilé .jar dans le conteneur
COPY chat-server.jar /app/

# 🌐 Exposer le port utilisé pour les connexions (modifier selon ton app)
EXPOSE 8080

# ▶️ Commande de démarrage
CMD ["java", "-jar", "chat-server.jar"]
