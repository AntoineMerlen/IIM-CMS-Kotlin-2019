package merlen.antoine.cms.model

data class Comment(
    val id: Int,
    val articleId: Int,
    val text: String?
)