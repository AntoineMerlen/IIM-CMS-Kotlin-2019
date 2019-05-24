package merlen.antoine.cms.control

import merlen.antoine.cms.*

class UserListPresenterImpl (val model: Model, val view: UserListPresenter.View): UserListPresenter {

    override fun start() {
        val list = model.getUsersList()
        view.displayUserList(list)
    }
}