package merlen.antoine.cms.control

import merlen.antoine.cms.ArticleListPresenter
import merlen.antoine.cms.Model

class ArticleListPresenterImpl (val model: Model, val view: ArticleListPresenter.View): ArticleListPresenter {

    override fun start() {
        val list = model.getArticleList()
        view.displayArticleList(list)
    }

    override fun postArticle(title: String, text: String) {
        model.postArticle(title, text)
        start()
    }

    override fun deleteArticle(id: Int){
        model.deleteArticle(id)
    }
}