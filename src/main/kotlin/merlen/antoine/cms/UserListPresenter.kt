package merlen.antoine.cms

import merlen.antoine.cms.model.*

interface UserListPresenter {

    fun start()

    interface View {
        fun displayUserList( users: List<User>) {

        }
    }

}