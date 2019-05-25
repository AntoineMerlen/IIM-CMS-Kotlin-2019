<html>
<head>
    <link rel="stylesheet" href="/static/css/style.css">
</head>


<button><a href="/login">Connexion</a></button>

<h2>Liste des articles</h2>


<#list list as l>

    <h3>${l.id}</h3>
    <p><a href="/article/${l.id}">${l.title}</a></p>
    <p>${l.text}</p>
</#list>


</html>