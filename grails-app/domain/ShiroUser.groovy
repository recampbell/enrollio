class ShiroUser {
    String username
    String firstName
    String lastName
    String passwordHash
    String passwordConfirm
    String password

    static transients = ['passwordConfirm', 'password']

    static constraints = {
        username(nullable: false, blank: false)
        firstName(nullable:false, blank:false)
        lastName(nullable:false, blank:false)

    }
}
