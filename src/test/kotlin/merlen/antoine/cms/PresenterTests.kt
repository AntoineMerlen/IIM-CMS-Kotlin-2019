package merlen.antoine.cms

import com.nhaarman.mockitokotlin2.*
import merlen.antoine.cms.control.*
import merlen.antoine.cms.model.*
import org.junit.jupiter.api.*

class PresenterTests {

    @Test
    fun testArticleListPresenter() {


        val list = listOf(Article(1, "Un", null), Article(2, "Deux", null))

        val model = mock<Model>() {
            on { getArticleList() } doReturn list
        }

        val view = mock<ArticleListPresenter.View>()


        val presenter = ArticleListPresenterImpl(model, view)
        presenter.start()

        verify(model).getArticleList()
        verify(view).displayArticleList(list)
        verifyNoMoreInteractions(model, view)

    }


    @Test
    fun testArticlePresenter() {


        val comments = listOf<Comment>(
            Comment(1, 1, "Blabla"),
            Comment(2, 1, "Blabla 2"),
            Comment(3, 2, "Blabla 3")
        )
        val article = Article(1, "Un", null)

        val id = 1
        val model = mock<Model> {
            on { getArticle(id) } doReturn article
            on { getArticleComments(id) } doReturn comments
        }

        val view = mock<ArticlePresenter.View>()


        val presenter = ArticlePresenterImpl(model, view)
        presenter.start(id)

        verify(model).getArticle(id)
        verify(model).getArticleComments(id)
        verify(view).displayArticle(article, comments)
        verifyNoMoreInteractions(model, view)

    }

    @Test
    fun testInvalidArticlePresenter() {

        val model = mock<Model> {
            on { getArticle(any()) } doReturn null
        }

        val view = mock<ArticlePresenter.View>()


        val presenter = ArticlePresenterImpl(model, view)
        presenter.start(42)

        verify(model).getArticle(42)
        verify(view).displayNotFound()
        verifyNoMoreInteractions(model, view)

    }

    @Test
    fun testAddArticle() {
        val title = "Blabla"
        val text = "Toubiiii"
        val articles: List<Article> = listOf(Article(1, "Un", "blabla 1"), Article(2, "Deux", "blabla 2"))

        val model = mock<Model> { }

        val view = mock<ArticleListPresenter.View>()

        val presenter = ArticleListPresenterImpl(model, view)
        presenter.postArticle(title, text)

        verify(model).postArticle(title, text)
        verify(model).getArticleList()
        verify(view).displayArticleList(listOf())
        verifyNoMoreInteractions(model, view)
    }

    @Test
    fun testAddComment() {
        val article = Article(1, "Un", "efede")

        val text = "Test"
        val comments: List<Comment> = listOf(Comment(1, 4, "blabla 1"), Comment(2, 3, "blabla 2"))

        val model = mock<Model> {
            on { getArticle(article.id) } doReturn article
            on { getArticleComments(article.id) } doReturn comments
        }

        val view = mock<ArticlePresenter.View>()

        val presenter = ArticlePresenterImpl(model, view)
        presenter.postComment(text, article.id)

        verify(model).postCreateComment(text, article.id)
        verify(model).getArticle(article.id)
        verify(model).getArticleComments(article.id)
        verify(view).displayArticle(article, comments)
        verifyNoMoreInteractions(model, view)
    }
}