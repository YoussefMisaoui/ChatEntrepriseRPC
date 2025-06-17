# 🐳 Application Java RPC – Chat Sécurisé avec Docker

Cette application est un système de messagerie instantanée client-serveur, développé en Java avec une interface Swing. Le serveur est conteneurisé via Docker pour un déploiement simple et rapide.

---

## 📦 Structure du Projet

chat-docker-project/
├── Dockerfile # Définition de l’image Docker pour le serveur
├── chat-server.jar # Application serveur compilée
├── build.sh # Script de construction de l’image
├── run.sh # Script de lancement du conteneur
├── stop.sh # Script d’arrêt et de suppression du conteneur
├── client/
│ ├── ChatClient.java
│ ├── LoginFrame.java
│ ├── MainFrame.java
│ ├── MessageFilter.java
│ ├── SecurityUtils.java
│ └── TestClient.java
└── server/
└── ServerWorker.java

---

## 🚀 Fonctionnalités

- ✅ Serveur Java RPC (basé sur `Socket`)
- ✅ Interface graphique Swing pour le client
- ✅ Hachage SHA-256 des mots de passe (`SecurityUtils`)
- ✅ Filtrage de messages inappropriés (`MessageFilter`)
- ✅ Communication réseau sécurisée via Docker
- ✅ Démarrage/arrêt simplifiés avec scripts Bash

---

## 🔧 Installation et Lancement

### 1. Construire l’image Docker

```bash
./build.sh
2. Lancer le serveur dans un conteneur
bash
Copier
Modifier
./run.sh
Le serveur écoute sur le port 8080.

3. Arrêter et supprimer le conteneur
bash
Copier
Modifier
./stop.sh
💻 Lancer le client
Sur ta machine hôte (en dehors du conteneur Docker) :

bash
Copier
Modifier
javac client/*.java
java client.ChatClient
⚠️ Assure-toi que le client Swing utilise l’adresse localhost et le port 8080 pour se connecter au serveur Dockerisé.

🧠 Améliorations Possibles
Ajout de chiffrement TLS (SSL Socket)

Utilisation d’une base de données pour la persistance des messages

Interface web ou mobile

Gestion des salons/groupes de discussion

Affichage des utilisateurs connectés

👨‍💻 Auteur
Développé par [Youssef Misaoui & Hamza Mrani Alaoui] – Projet de déploiement Java sécurisé avec Docker.

📄 Licence
Ce projet est distribué sous licence MIT pour un usage académique et pédagogique.