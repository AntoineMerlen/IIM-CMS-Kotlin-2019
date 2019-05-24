package merlen.antoine.cms

import merlen.antoine.cms.model.Article

interface ArticleListPresenter {

    fun start()

    fun deleteArticle(id: Int)

    interface View {
        fun displayArticleList( list: List<Article>) {

        }
    }

}