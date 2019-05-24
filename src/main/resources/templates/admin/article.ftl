<html>
    <head>
        <head><link rel="stylesheet" href="/static/css/style.css"></head>
        Page de l'article n°${article.id}
    </head>
    <body>
        <h1>
            ${article.title}
        </h1>
        <p>
            ${article.text}
        </p>

        <form method="post">
                    <div>Commentaire :</div>
                    <div><input type="text" name="comments" /></div>
                    <div><input type="submit" value="Valider" /></div>
        </form>

        <#list comments as c>
            <li><p>${c.text}</p></li>
        </#list>

    </body>
</html>