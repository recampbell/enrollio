class SecurityFilters {
    def filters = {
        // Ensure that all controllers and actions require an authenticated user,
        // except for the "public" controller
        auth(controller: "*", action: "*") {
            before = {
                // This just means that the user must be authenticated. He does 
                // not need any particular role or permission. 
                accessControl { true } 
            } 
        }
    }
}

