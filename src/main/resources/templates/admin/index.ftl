<html>
<head>
    <link rel="stylesheet" href="/static/css/style.css">
</head>

<button><a href="/logout">Déconnexion</a></button>

<h1>Bienvenue, vous êtes actuellement connecté en tant que Admin !</h1>

<h2>Liste des articles</h2>

<#list list as l>
    <hr>
    <h3>${l.id}</h3>
    <p><a href="/admin/article/${l.id}">${l.title}</a>
    <p>${l.text}</p>
    <button><a href="deleteArticle/${l.id}">Supprimer</a></button>
</#list>


<hr>
<hr>
<hr>

<p>Ajout d'un nouvel article</p>
<form method="post">
    <div>Nom de l'article</div>
    <div><input type="text" name="title"/></div>
    <div>Texte</div>
    <div><input type="text" name="text"/></div>
    <br>
    <div><input type="submit" value="Créer"/></div>
</form>

</html>