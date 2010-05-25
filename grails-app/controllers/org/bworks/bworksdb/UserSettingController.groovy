package org.bworks.bworksdb

class UserSettingController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def configSettingService
    def userService

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
        def userSettingInstance = configSettingService.getSetting(params.configKey)
        if (!userSettingInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'userSetting.label', default: 'UserSetting'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [userSettingInstance: userSettingInstance]
        }
    }

    def update = {
        def userSettingInstance = 
            configSettingService.setUserSetting(params.configKey.toString(), params.value)
        flash.message = "${message(code: 'default.updated.message', args: [message(code: 'userSetting.label', default: 'UserSetting'), userSettingInstance.id])}"
        redirect(contoller:'userSetting', action: "list")
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
