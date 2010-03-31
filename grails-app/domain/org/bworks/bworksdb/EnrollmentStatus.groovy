package org.bworks.bworksdb

public enum EnrollmentStatus {

    IN_PROGRESS('In Progress'),
    DROPPED_OUT('Dropped Out'),
    GRADUATED('Graduated')

    String name

    EnrollmentStatus(String name) {
        this.name = name
    }

    static list() {
        [ IN_PROGRESS, DROPPED_OUT, GRADUATED ]
    }

    static constraints = {
    }
}
