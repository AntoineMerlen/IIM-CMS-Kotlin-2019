package merlen.antoine.cms

import merlen.antoine.cms.model.*

interface UserPresenter {

    fun start(id: Int)

    interface View {
        fun displayUser(user: User?)
        fun displayNotFound()
    }
}