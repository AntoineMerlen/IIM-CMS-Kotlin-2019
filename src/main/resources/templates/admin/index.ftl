<#-- @ftlvariable name="list" type="merlen.antoine.cms.tpl.IndexContext" -->
<html>
<head><link rel="stylesheet" href="/static/css/style.css"></head>
<h2>Liste des articles</h2>


<button><a href="/logout">Déconnexion</a></button>


<#list list as l>

        <h1>${l.id}</h1>
        <p>${l.text}</p>
        <button><a href="deleteArticle/${l.id}">Supprimer</a></button>
</#list>


<hr>

<p>Ajout d'un nouvel article</p>
<form>
        <div>Nom de l'article</div>
        <div><input type="text" name="nameArticle" /></div>
        <div>Texte</div>
        <div><input type="text" name="textArticle" /></div>
        <br>
        <div><input type="submit" value="CreateArticle" /></div>
</form>

</html>