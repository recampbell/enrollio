

package org.bworks.bworksdb

class LessonController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ programInstanceList : Program.list(), lessonInstanceList: Lesson.list( params ), lessonInstanceTotal: Lesson.count() ]
    }

    def show = {
        def lessonInstance = Lesson.get( params.id )

        if(!lessonInstance) {
            flash.message = "Lesson not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ lessonInstance : lessonInstance ] }
    }

    def delete = {
        def lessonInstance = Lesson.get( params.id )
        if(lessonInstance) {
            try {
                lessonInstance.delete(flush:true)
                flash.message = "Lesson ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "Lesson ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "Lesson not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def lessonInstance = Lesson.get( params.id )

        if(!lessonInstance) {
            flash.message = "Lesson not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ lessonInstance : lessonInstance ]
        }
    }

    def update = {
        def lessonInstance = Lesson.get( params.id )
        if(lessonInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(lessonInstance.version > version) {
                    
                    lessonInstance.errors.rejectValue("version", "lesson.optimistic.locking.failure", "Another user has updated this Lesson while you were editing.")
                    render(view:'edit',model:[lessonInstance:lessonInstance])
                    return
                }
            }
            lessonInstance.properties = params
            if(!lessonInstance.hasErrors() && lessonInstance.save()) {
                flash.message = "Lesson ${params.id} updated"
                redirect(action:show,id:lessonInstance.id)
            }
            else {
                render(view:'edit',model:[lessonInstance:lessonInstance])
            }
        }
        else {
            flash.message = "Lesson not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def lessonInstance = new Lesson()
        // If no program is specified, then pick the first Program in the list.
        // Also, link back to lesson/list if user Cancels
        def cancelLink
        if (!params.program?.id) {
            params.program = Program.list(maxResults:1)[0]
            cancelLink = g.createLink(action:'list')
        }
        lessonInstance.properties = params
        return ['lessonInstance':lessonInstance, cancelLink : cancelLink ]
    }

    def save = {
        def lessonInstance = new Lesson(params)
        def program = Program.get(params.program.id)

        if(!lessonInstance.hasErrors() && lessonInstance.save()) {
            program.addToLessons(lessonInstance)
            flash.message = "Lesson ${lessonInstance.id} created"
            redirect(action:show,id:lessonInstance.id)
        }
        else {
            render(view:'create',model:[lessonInstance:lessonInstance])
        }
    }
}
