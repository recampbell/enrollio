class UrlMappings {
    static mappings = {
      "/$controller/$action?/$id?"{
	      constraints {
			 // apply constraints here
		  }
	  }
      "/"(controller:"help", action:'about')
	  "500"(view:'/error')
	}
}
