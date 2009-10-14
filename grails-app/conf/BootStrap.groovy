import org.bworks.bworksdb.*
import org.apache.shiro.crypto.hash.Sha1Hash

class BootStrap {
    def testDataService

    def init = { servletContext ->
        if (grails.util.GrailsUtil.environment == "development") {
            testDataService.loadDevData()
        }

        def totalUsers = ShiroUser.count()
        if (totalUsers == 0) {
            loadAdminUser()
        }


    }

    def destroy = {
    }

    def loadAdminUser() {

        // Administrator user and role. 
        def adminRole = new ShiroRole(name: "Administrator").save() 
        def adminUser = new ShiroUser(username: "admin", 
            firstName : 'admin',
            lastName : 'admin',
            passwordHash: new Sha1Hash("admin").toHex()
        )
        if (!adminUser.validate()) {
            println "User didn't validate!"
            println adminUser.errors.allErrors
        }
        else {
            adminUser.save()
            new ShiroUserRoleRel(user: adminUser, role: adminRole).save()
        }
    }

}

