<html>
    <head>
        <head><link rel="stylesheet" href="/static/css/style.css"></head>
        <button><a href="/admin">Retour page accueil</a></button>
    </head>
    <body>
        <br>
        <h1>Page de l'article n°${article.id}</h1>
        <h2>
            ${article.title}
        </h2>
        <p>
            ${article.text}
        </p>

        <form method="post">
                    <div>Commentaire :</div>
                    <div><input type="text" name="comments" /></div>
                    <br>
                    <div><input type="submit" value="Valider" /></div>
        </form>

        <ul>
        <#list comments as comment>
            <li>
                <p>${comment.text}</p>
                <br>
                <button><a href="/admin/article/${article.id}/comment/${comment.id}">Supprimer</a></button> <br>
            </li>
        </#list>
        </ul>

    </body>
</html>