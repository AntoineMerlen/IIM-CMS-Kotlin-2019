package merlen.antoine.cms

import merlen.antoine.cms.model.*

interface ArticlePresenter {

    fun start(id: Int)
    fun postComment(text: String?, articleId: Int)
    fun deleteComment(id : Int)

    interface View {
        fun displayArticle(article: Article?, comments: List<Comment>)
        fun displayNotFound()
        fun redirect()
    }
}