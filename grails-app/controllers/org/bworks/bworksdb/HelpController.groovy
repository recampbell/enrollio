package org.bworks.bworksdb

class HelpController {
    
    def index = { redirect(action:'about',params:params) }

    static navigation = [
        group:'mainMenu',
        action:'index',
        title:'Help',
        order:200
    ]

    def about = { }

    def thanks = { }
}
