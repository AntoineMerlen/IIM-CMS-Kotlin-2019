package merlen.antoine.cms.control

import merlen.antoine.cms.UserPresenter
import merlen.antoine.cms.Model

class UserPresenterImpl(val model: Model, val view: UserPresenter.View): UserPresenter {

    override fun start(id: Int){
        val user = model.getUser(id)
        if(user != null){
            view.displayUser(user)
        }else{
            view.displayNotFound()
        }

    }

}