package org.bworks.bworksdb
import org.bworks.bworksdb.auth.ShiroUser
import org.apache.shiro.SecurityUtils

// Generic service to be used as a helper
class UserService {

    ShiroUser loggedInUser() {
        def principal = SecurityUtils.subject.getPrincipal()
        if (principal) {
            return ShiroUser.findByUsername(principal)
        }
        return null
    }

}
