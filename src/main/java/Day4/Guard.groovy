package Day4

import java.time.LocalDateTime

class Guard {

    String ID
    int totalMinutesSlept
    int[] minutesSleptDetail
    Map<LocalDateTime,String> record

    Guard(Integer ID) {
        this.ID = ID
        this.record = new TreeMap<LocalDateTime,String>()
        this.minutesSleptDetail
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Guard guard = (Guard) o

        if (ID != guard.ID) return false

        return true
    }

    int hashCode() {
        return (ID != null ? ID.hashCode() : 0)
    }
}
