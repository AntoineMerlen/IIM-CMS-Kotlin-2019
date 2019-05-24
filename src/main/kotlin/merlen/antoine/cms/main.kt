package merlen.antoine.cms

import freemarker.cache.ClassTemplateLoader
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.*
import io.ktor.freemarker.FreeMarker
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.sessions.*
import kotlinx.coroutines.launch
import merlen.antoine.cms.control.ArticlePresenterImpl
import merlen.antoine.cms.control.ArticleListPresenterImpl
import merlen.antoine.cms.model.*
import merlen.antoine.cms.tpl.IndexContext

data class AuthSession(val user: String)
class App

fun main() {
    val appComponents = AppComponents("jdbc:mysql://localhost:8889/CMS?serverTimezone=UTC", "root", "root")

    embeddedServer(Netty, 8080) {
        install(FreeMarker) {
            templateLoader = ClassTemplateLoader(App::class.java.classLoader, "templates")
        }
        routing {
            static("static") {
                resources("static") }

            get("article/{id}") {

                val controller = appComponents.getArticlePresenter(object : ArticlePresenter.View{
                    override fun displayArticle(article: Article?, comments: List<Comment>) {
                        launch {
                            val context = object {
                            val article: Article? = article
                            val comments: List<Comment> = comments
                        }
                            call.respond(FreeMarkerContent("article.ftl", context, "e"))
                        }
                    }

                    override fun displayNotFound() {
                        launch {
                            call.respond(HttpStatusCode.NotFound)
                        }
                    }
                })

                val id = call.parameters["id"]!!.toIntOrNull()
                if(id == null){
                    call.respond(HttpStatusCode.NotFound)
                }else{
                    controller.start(id)
                }

            }
            post("article/{id}") {
                val controller = appComponents.getArticlePresenter(object : ArticlePresenter.View {
                    override fun displayNotFound() {
                        launch {
                            call.respond(HttpStatusCode.NotFound)
                        }
                    }
                    override fun displayArticle(article: Article?, comments: List<Comment>) {
                        val context = mapOf(
                            "article" to article,
                            "comments" to comments
                        )
                        launch {
                            call.respond(FreeMarkerContent("article.ftl", context, "e"))
                        }
                    }

                })

                val postParameters : Parameters = call.receiveParameters()
                val text: String? = postParameters["comments"]
                val id = call.parameters["id"]!!.toIntOrNull()
                if (id == null){
                    call.respond(HttpStatusCode.NotFound)
                } else{
                    controller.postComment(text, id)
                }

            }

            get("/") {

                val controller = appComponents.getArticleListPresenter(object : ArticleListPresenter.View {
                    override fun displayArticleList(list: List<Article>) {
                            val context = IndexContext(list)
                            launch {
                                call.respond(FreeMarkerContent("index.ftl", context, "e"))
                            }
                        }
                })
            controller.start()
            }



            install(Sessions) {
                cookie<AuthSession> ("Auth_SESSION", SessionStorageMemory())
            }

            install(Authentication) {
                form("login") {
                    userParamName = "user"
                    passwordParamName = "password"
                    challenge = FormAuthChallenge.Redirect { "/login" }
                    validate { credentials ->
                        if (credentials.name == "admin" && credentials.password == "admin") {
                            UserIdPrincipal(credentials.name)
                        } else {
                            null
                        }
                    }
                    skipWhen { call -> call.sessions.get<AuthSession>() != null }
                }
            }

                get ("/login") {
                    call.respond(FreeMarkerContent("login.ftl", null))
                }

//                post ("/login") {
//                    val post = call.receiveParameters()
//                    if (post["user"] != null && post["user"] == "admin" && post["password"] == "admin") {
//                        call.respondRedirect("/admin", permanent = false)
//                    } else {
//                        call.respond(FreeMarkerContent("login.ftl", mapOf("error" to "Invalid login")))
//                    }
//                }

                authenticate("login") {
                    post("/login") {
                        val principal = call.authentication.principal<UserIdPrincipal>()
                        call.sessions.set(AuthSession(principal!!.name))
                        call.respondRedirect("/admin")
                    }

                    get("/logout") {
                        call.sessions.clear<AuthSession>()
                        call.respondRedirect("/")
                    }

                    get("/admin") {

                        val controller = appComponents.getArticleListPresenter(object : ArticleListPresenter.View {
                            override fun displayArticleList(list: List<Article>) {
                                val context = merlen.antoine.cms.tpl.admin.IndexContext(list)
                                launch {
                                    call.respond(FreeMarkerContent("admin/index.ftl", context, "e"))
                                }
                            }
                        })
                        controller.start()
                    }
                    route("/deleteArticle/{id}") {
                        get("") {
                            val controller = appComponents.getArticleListPresenter(object : ArticleListPresenter.View {
                                override fun displayArticleList(list: List<Article>) {
                                    launch {
                                        call.respondRedirect("/")
                                    }
                                }
                            })
                            controller.start()

                            val id = call.parameters["id"]!!.toIntOrNull()
                            if (id != null) {
                                controller.deleteArticle(id)
                            }
                        }
                    }

                }


        }
    }.start(wait = true)
}