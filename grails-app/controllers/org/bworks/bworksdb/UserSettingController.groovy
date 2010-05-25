package org.bworks.bworksdb

class UserSettingController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def configSettingService

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        def settingsList = configSettingService.userSettingsList()
        
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [ userSettingInstanceList: settingsList, userSettingInstanceTotal:settingsList.size() ] 
    }

    def create = {
        def userSettingInstance = new UserSetting()
        userSettingInstance.properties = params
        return [userSettingInstance: userSettingInstance]
    }

    def save = {
        def userSettingInstance = new UserSetting(params)
        if (userSettingInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'userSetting.label', default: 'UserSetting'), userSettingInstance.id])}"
            redirect(action: "show", id: userSettingInstance.id)
        }
        else {
            render(view: "create", model: [userSettingInstance: userSettingInstance])
        }
    }

    def show = {
        def userSettingInstance = UserSetting.get(params.id)
        if (!userSettingInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'userSetting.label', default: 'UserSetting'), params.id])}"
            redirect(action: "list")
        }
        else {
            [userSettingInstance: userSettingInstance]
        }
    }

    def edit = {
        def userSettingInstance = UserSetting.get(params.id)
        if (!userSettingInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'userSetting.label', default: 'UserSetting'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [userSettingInstance: userSettingInstance]
        }
    }

    def update = {
        def userSettingInstance = UserSetting.get(params.id)
        if (userSettingInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (userSettingInstance.version > version) {
                    
                    userSettingInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'userSetting.label', default: 'UserSetting')] as Object[], "Another user has updated this UserSetting while you were editing")
                    render(view: "edit", model: [userSettingInstance: userSettingInstance])
                    return
                }
            }
            userSettingInstance.properties = params
            if (!userSettingInstance.hasErrors() && userSettingInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'userSetting.label', default: 'UserSetting'), userSettingInstance.id])}"
                redirect(action: "show", id: userSettingInstance.id)
            }
            else {
                render(view: "edit", model: [userSettingInstance: userSettingInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'userSetting.label', default: 'UserSetting'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def userSettingInstance = UserSetting.get(params.id)
        if (userSettingInstance) {
            try {
                userSettingInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'userSetting.label', default: 'UserSetting'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'userSetting.label', default: 'UserSetting'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'userSetting.label', default: 'UserSetting'), params.id])}"
            redirect(action: "list")
        }
    }
}
