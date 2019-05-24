package merlen.antoine.cms

import merlen.antoine.cms.model.*
import java.awt.SystemColor.*

class MysqlModel(val pool: ConnectionPool): Model {

    override fun getArticleComments(id: Int): List<Comment> {
        val list = ArrayList<Comment>()

        pool.useConnection { connection ->
            connection.prepareStatement("SELECT * FROM comments WHERE articleId = ?").use { stmt ->
                stmt.setInt(1, id)
                stmt.executeQuery().use { result ->
                    while (result.next()) {
                        list += Comment(
                            result.getInt("id"),
                            result.getInt("articleId"),
                            result.getString("text")
                        )
                    }
                }
            }
        }

        return list
    }

    override fun getArticleList(): List<Article> {
        val list = ArrayList<Article>()

        pool.useConnection { connection ->
            val stmt = connection.prepareStatement("SELECT * FROM articles")
            stmt.executeQuery().use {result ->
                while (result.next())
                {
                    list += Article(
                        result.getInt("id"),
                        result.getString("title"),
                        result.getString("text")
                    )
                }
            }
        }
        return list
    }

    override fun getArticle(id: Int): Article? {
        pool.useConnection { connection ->
            connection.prepareStatement("SELECT * FROM articles WHERE id = ?").use {stmt ->
                stmt.setInt(1, id)

                stmt.executeQuery().use { result ->
                    if(result.next())
                    {
                        return Article(
                            result.getInt("id"),
                            result.getString("title"),
                            result.getString("text")
                        )
                    }
                }
            }
        }
        return null
    }

    override fun postCreateComment(text: String?, articleId: Int) {
        pool.useConnection { connection ->
            connection.prepareStatement("INSERT INTO comments (articleId, text) VALUES (?, ?)").use {stmt ->
                stmt.setInt(1, articleId)
                stmt.setString(2, text)

                stmt.executeUpdate()
            }
        }
    }

    override fun deleteComment(id : Int) {
        pool.useConnection { connection ->
            connection.prepareStatement("DELETE FROM comments WHERE id = ?").use {stmt ->
                stmt.setInt(1, id)
                stmt.execute()
            }
        }
    }

    override fun postArticle(title: String, text: String) {
        pool.useConnection { connection ->
            connection.prepareStatement("INSERT INTO articles (title, text) VALUES (?, ?)").use {stmt ->
                stmt.setString(1, title)
                stmt.setString(2, text)

                stmt.executeUpdate()
            }
        }
    }

    override fun deleteArticle(id: Int) {
        pool.useConnection { connection ->
            connection.prepareStatement("DELETE FROM articles WHERE id = ?").use {stmt ->
                stmt.setInt(1, id)
                stmt.executeUpdate()
            }
            connection.prepareStatement("DELETE FROM comments WHERE articleId = ?").use {stmt ->
                stmt.setInt(1, id)
                stmt.executeUpdate()
            }
        }
    }

//    override fun getUser(id: Int): User? {
//        pool.useConnection { connection ->
//            connection.prepareStatement("SELECT * FROM users WHERE id = ?").use {stmt ->
//                stmt.setInt(1, id)
//
//                stmt.executeQuery().use { result ->
//                    if(result.next())
//                    {
//                        return User(
//                            result.getInt("id"),
//                            result.getString("username"),
//                            result.getString("password"),
//                            result.getString("name"),
//                            result.getString("role")
//                        )
//                    }
//                }
//            }
//        }
//        return null
//    }
//
//    override fun getUsersList(): List<User> {
//        val list = ArrayList<User>()
//
//        pool.useConnection { connection ->
//            val stmt = connection.prepareStatement("SELECT * FROM users")
//            stmt.executeQuery().use {result ->
//                while (result.next())
//                {
//                    list += User(
//                        result.getInt("id"),
//                        result.getString("username"),
//                        result.getString("password"),
//                        result.getString("name"),
//                        result.getString("role")
//                    )
//                }
//            }
//        }
//        return list
//    }

}