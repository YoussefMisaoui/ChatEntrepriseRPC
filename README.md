<<<<<<< HEAD
# projetRPC-RMI-Docker
=======
# 💬 Application Java RPC – Chat Sécurisé

Cette application est un système de chat multi-utilisateur développé en Java en utilisant le modèle RPC via `Socket`. Elle inclut une interface graphique Swing, une authentification simple avec hachage SHA-256, un filtrage de contenu et un enregistrement de l’historique.

---

## 📁 Structure du Projet

RPCChatProject/
├── server/
│ └── ServerWorker.java
├── client/
│ ├── ChatClient.java
│ ├── LoginFrame.java
│ └── MainFrame.java
├── utils/
│ ├── SecurityUtils.java
│ └── MessageFilter.java
├── history.log
├── README.md

yaml
Copier
Modifier

---

## 🚀 Fonctionnalités

- ✅ Authentification via interface graphique
- ✅ Hachage SHA-256 des mots de passe (`SecurityUtils`)
- ✅ Interface Swing ergonomique (`LoginFrame`, `MainFrame`)
- ✅ Filtrage automatique des propos inappropriés (`MessageFilter`)
- ✅ Historique des messages dans `history.log`
- ✅ Communication bidirectionnelle via `Socket`
- ✅ Support multi-clients avec threads (`ServerWorker`)

---

## 🛠️ Compilation et Exécution

### Compilation

Dans le dossier racine :

```bash
javac server/*.java client/*.java utils/*.java
Lancement du Serveur
bash
Copier
Modifier
java server.ServerWorker
Lancement du Client
bash
Copier
Modifier
java client.ChatClient
⚠️ Le serveur doit être lancé avant le client.

🔒 Sécurité
SHA-256 appliqué côté client avant transmission du mot de passe.

Filtrage de mots interdits (définis dans MessageFilter).

Aucune donnée sensible transmise en clair.

📂 Fichier history.log
Ce fichier contient tous les messages échangés entre les utilisateurs. Il est relu lors de la connexion au client pour affichage de l’historique.

💡 Améliorations Possibles
Ajout de chiffrement SSL/TLS

Gestion des utilisateurs via base de données

Interface web (JavaFX ou React via serveur REST)

Groupes de discussion

Système de notifications

👨‍💻 Auteurs
Développé par [Youssef Misaoui & Hamza Mrani Alaoui] dans le cadre d’un projet académique Java avancé.


>>>>>>> 8117dcf (C)
