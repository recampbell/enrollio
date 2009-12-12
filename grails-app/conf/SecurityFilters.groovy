
import org.bworks.bworksdb.auth.*; 

class SecurityFilters {
    def filters = {
        // Ensure that all controllers and actions require an authenticated user,
        auth(controller: "*", action: "*") {
            before = {
                // except for the "help" controller
                if (controllerName == "help") {
                    return true
                }
                // This just means that the user must be authenticated. He does 
                // not need any particular role or permission. 
                accessControl { true } 
            } 
        }

        adminOnlyForUserManagement(controller:"shiroUser", action:"edit|create|delete") {
            before = {
                accessControl {
                     // Only admins
                     role("Administrator")
                }
            }
        }
        adminOnlyForAdmin(controller:"admin", action:"*") {
            before = {
                accessControl {
                     // Only admins
                     role("Administrator")
                }
            }
        }
        adminOnlyForConfig(controller:"configSettings", action:"*") {
            before = {
                accessControl {
                     // Only admins
                     role("Administrator")
                }
            }
        }

    }
}

