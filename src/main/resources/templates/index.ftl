<#-- @ftlvariable name="list" type="merlen.antoine.cms.tpl.IndexContext" -->
<html>
<head><link rel="stylesheet" href="/static/css/style.css"></head>
<h2>Liste des articles</h2>

<button><a href="/login">Connexion</a></button>


<#list list as l>

        <h1>${l.id}</h1>
        <p><a href="/article/${l.id}">${l.title}</a></p>
        <p>${l.text}</p>
</#list>


</html>