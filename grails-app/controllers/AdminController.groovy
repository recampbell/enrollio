import org.apache.shiro.SecurityUtils

class AdminController {


    def index = { }

    static navigation = [
        group:'mainMenu',
        action:'index',
        title:'Admin',
        order:100,
		isVisible: { SecurityUtils.subject?.hasRole("Administrator") }
    ]

}
