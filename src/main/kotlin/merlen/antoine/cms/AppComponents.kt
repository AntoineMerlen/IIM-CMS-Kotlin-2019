package merlen.antoine.cms

import merlen.antoine.cms.control.*

class AppComponents(mySqlUrl: String, mySqlUser: String, MySqlPassword: String) {

    private val pool = ConnectionPool(mySqlUrl, mySqlUser, MySqlPassword)

    fun getPool(): ConnectionPool {
        return pool
    }

    private val model = MysqlModel(getPool())

    fun getModel(): Model {
        return model
    }

    fun getArticleListPresenter(view : ArticleListPresenter.View): ArticleListPresenter {
        return ArticleListPresenterImpl(getModel(), view)
    }

    fun getArticlePresenter(view : ArticlePresenter.View): ArticlePresenter {
        return ArticlePresenterImpl(getModel(), view)
    }


}