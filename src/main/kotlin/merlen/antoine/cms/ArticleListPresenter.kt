package merlen.antoine.cms

import merlen.antoine.cms.model.*

interface ArticleListPresenter {

    fun start()

    fun postArticle(title: String, text: String)

    fun deleteArticle(id: Int)

    interface View {
        fun displayArticleList(list: List<Article>)
    }

}