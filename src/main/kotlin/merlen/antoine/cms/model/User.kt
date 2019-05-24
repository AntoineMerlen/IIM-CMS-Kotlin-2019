package merlen.antoine.cms.model

data class User(
    val id: Int,
    val username: String?,
    val password: String?,
    val name: String?,
    val role: String?
)