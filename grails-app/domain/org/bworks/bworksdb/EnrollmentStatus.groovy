package org.bworks.bworksdb

public enum EnrollmentStatus {

    DROPPED_OUT('Dropped Out'),
    GRADUATED('Graduated'),
    IN_PROGRESS('In Progress')

    String name

    EnrollmentStatus(String name) {
        this.name = name
    }

    static list() {
        [ DROPPED_OUT, GRADUATED, IN_PROGRESS ]
    }

    static constraints = {
    }
}
