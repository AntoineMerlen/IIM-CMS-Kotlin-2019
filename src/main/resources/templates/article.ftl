<html>
    <head>
        <head><link rel="stylesheet" href="/static/css/style.css"></head>
        <button><a href="/">Retour page accueil</a></button>
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

        <#list comments as c>
            <li>${c.text}</li>
        </#list>

    </body>
</html>