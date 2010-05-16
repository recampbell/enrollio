package org.bworks.bworksdb
import org.bworks.bworksdb.auth.ShiroUser
import org.apache.shiro.SecurityUtils

// Generic service to be used as a helper
class UserService {

    ShiroUser loggedInUser() {
        return ShiroUser.findByUsername(SecurityUtils.subject.getPrincipal())
    }

}
