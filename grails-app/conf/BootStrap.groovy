import org.bworks.bworksdb.*
import org.bworks.bworksdb.auth.*
import org.apache.shiro.crypto.hash.Sha1Hash

class BootStrap {
    def testDataService

    def init = { servletContext ->
        def totalUsers = ShiroUser.count()
        
        if (totalUsers == 0) {
            loadUserRoles()

            // loadDevData will load test users, so make sure that
            // admin user is already created before you call loadDevData
            loadAdminUser()
        }

        if (grails.util.GrailsUtil.environment == "development") {
            testDataService.loadDevData()
        }
        
        if (grails.util.GrailsUtil.environment == "test") {
            testDataService.loadIntegrationTestData()
        }

        if (grails.util.GrailsUtil.environment == "production") {
            testDataService.loadDefaultConfigSettings()
        }
    }

    def destroy = {
    }

    def loadAdminUser() {

        // Administrator user and role. 
        def adminRole = ShiroRole.findByName("Administrator")
        def adminUser = new ShiroUser(username: "admin", 
            firstName : 'admin',
            lastName : 'admin',
            password : 'admin0',
            passwordConfirm : 'admin0',
            passwordHash: new Sha1Hash("admin0").toHex()
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

    def loadUserRoles() {
        // Initialize User role
        def userRole = new ShiroRole(name: "User").save() 
        def adminRole = new ShiroRole(name: "Administrator").save() 
    }

}

