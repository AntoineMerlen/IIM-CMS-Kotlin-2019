package merlen.antoine.cms

import merlen.antoine.cms.model.*

interface Model {


    fun getArticleList(): List<Article>

    fun getArticle(id: Int): Article?

    fun getArticleComments(id: Int): List<Comment>

    fun postArticle(title: String, text: String)

    fun deleteComment(id: Int)

    fun deleteArticle(id: Int)

    fun postCreateComment(text: String?, articleId: Int)

}