# 💬 Application Java RMI – Chat d'Entreprise

Cette application est une messagerie instantanée multi-utilisateur développée en Java, utilisant la technologie **Remote Method Invocation (RMI)**. Elle permet aux utilisateurs de communiquer en temps réel avec une interface graphique Swing, tout en assurant une authentification simple, la validation des messages, et une gestion efficace des clients.

---

## 📁 Structure du Projet

ChatRMIProject/
├── shared/
│ └── ChatService.java # Interface distante
├── server/
│ ├── ChatServiceImpl.java # Implémentation côté serveur
│ └── RMIServer.java # Démarrage du serveur RMI
├── client/
│ ├── ChatClientGUI.java # Interface graphique Swing
│ └── RMIClient.java # Connexion client
├── README.md

---

## 🚀 Fonctionnalités

- ✅ Interface graphique avec Java Swing
- ✅ Envoi et réception de messages via RMI
- ✅ Authentification simple par pseudonyme
- ✅ Rafraîchissement automatique des messages
- ✅ Validation des messages (pas de vide ou contenu interdit)
- ✅ Liste synchronisée des clients connectés

---

## 🛠️ Compilation et Exécution

### Compilation

Dans le dossier du projet, compilez tous les fichiers :

```bash
javac shared/*.java server/*.java client/*.java
Démarrage du Serveur
bash
Copier
Modifier
rmiregistry &
java server.RMIServer
⚠️ Le registre RMI doit être lancé avant l'exécution du serveur.

Lancement du Client
bash
Copier
Modifier
java client.RMIClient
🔧 Détails Techniques
Utilise UnicastRemoteObject pour exposer le service à distance.

Sérialisation automatique des objets (messages, listes).

Enregistrement du service dans le RMIRegistry.

Interface graphique implémentée avec JFrame, JTextArea, JTextField, etc.

🧱 Améliorations Possibles
Ajout du chiffrement SSL (RMI sécurisé)

Gestion de groupes/salons de discussion

Sauvegarde persistante des messages (fichier ou base de données)

Affichage en temps réel des utilisateurs connectés

Interface web (ex: React + Spring RMI wrapper)

👨‍💻 Auteurs
Développé par [Youssef Misaoui & Hamza Mrani Alaoui] – Projet pédagogique sur Java RMI.

📄 Licence
Ce projet est libre d’utilisation à des fins pédagogiques uniquement.