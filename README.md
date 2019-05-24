# IIM-CMS-Kotlin-2019

Rendu pour la semaine Kotlin du 29/04 au 03/05
> Antoine MERLEN IIM A4 IWM

## Getting Started
- URL du site en local: http://localhost:8080 
- URL de l'Admin: http://localhost:8080/admin
```
Login Admin : admin / admin
```

Bdd : [dump.sql]('/dump.sql')

## Exigences Kotlin :

En plus du projet actuel :
- Style (moche) via CSS
- Affichage des commentaires d'un article (table commentaire : id, idArticle, text).
- Possibilite de poster un commentaire (sans etre connecté) depuis la page d'un article.
- Connection a une interface d'administration avec login / mot de passe.
- Gestion de la session, possibilité de se déconnecter.
- Une fois connecté en tant qu'admin, possibilité d'ajouter ou supprimer un article.
- Une fois connecté en tant qu'admin, possibilité de supprimer un commentaire.
- Si on supprime un article, ses commentaires doivent etre supprimés (soit manuellement, soit via les MySQL Foreign keys).
- Les presenters correspondant a ces nouvelles fonctionalités doivent etre correctement testés.


Le projet doit être accessible sur un dépot Git.
L'adresse du dépot Git doit être envoyée par mail à salomon@kodein.net au plus tard le dimanche 26 mai.
Tout commit sur le dépot Git datant d'après le dimanche 26 mai sera ignoré.
Le projet doit inclure un fichier dump.sql correspondant à un dump de votre base de donnees.
Le projet doit inclure un fichier README affichant le login / mot de passe a utiliser pour se connecter.
Le code doit etre propre !!!

#### Bonus possible :
Tout bonus sera apprecie a sa juste valeur A CONDITION que les exigences obligatoires soient remplies.
Quelques exemples :
- test du model via H2
- utilisation de BCrypt ou Argon2 pour le mot de passe
- Gestion des utilisateurs et de leurs droits
- URL / Identifiants de connexion à la DB dans un fichier (java) properties.
Je reste disponible pour toutes questions par mail.