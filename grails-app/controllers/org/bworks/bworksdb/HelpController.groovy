package org.bworks.bworksdb

class HelpController {
    
    def index = { redirect(action:'about',params:params) }

    def about = { }

    def thanks = { }
}
