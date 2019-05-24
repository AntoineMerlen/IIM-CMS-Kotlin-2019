package merlen.antoine.cms.control

import merlen.antoine.cms.ArticlePresenter
import merlen.antoine.cms.Model

class ArticlePresenterImpl(val model: Model, val view: ArticlePresenter.View): ArticlePresenter {

    override fun start(id: Int){
        val article = model.getArticle(id)
        if(article != null){
            val comments = model.getArticleComments(id)
            view.displayArticle(article, comments)
        }else{
            view.displayNotFound()
        }

    }

    override fun postComment(text: String?, articleId: Int){
        if (text != null || text!!.length > 0){
            model.postCreateComment(text, articleId)
            start(articleId)
        }
    }
}