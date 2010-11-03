package org.bworks.bworksdb

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.codehaus.groovy.grails.web.context.ServletContextHolder;
import org.springframework.context.ApplicationContext
import org.apache.shiro.util.ThreadContext


class ErrorController {

    def beforeInterceptor = [action:this.&bugWorkaround]
    /**
     * Works around a problem with the security manager not being put in the thread context when using response code url mappings
     * @See http://jira.codehaus.org/browse/GRAILS-5234
     */
    def bugWorkaround = {

        def securityManager = ThreadContext.get(ThreadContext.SECURITY_MANAGER_KEY)

        if (!securityManager) {

            ApplicationContext ctx =
            WebApplicationContextUtils.getWebApplicationContext(ServletContextHolder.getServletContext());

            securityManager = ctx.getBean("shiroSecurityManager")

            org.apache.shiro.web.WebUtils.bind(request)
            org.apache.shiro.web.WebUtils.bind(response) 

            ThreadContext.put(ThreadContext.SECURITY_MANAGER_KEY, securityManager)
        }
    }


    def pageNotFound = {    


    }

    def oops = {
    }
}
