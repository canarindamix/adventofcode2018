package Day4

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class ReposeRecord {

    File f = new File("../../resources/day4Input.txt")
    def lines = f.readLines()


    void getStrategyOneAnswer(){

        Map<LocalDateTime,String> logEntries = new TreeMap<LocalDateTime,String>()
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        LocalDateTime parsedDate
        String parsedRecord
        Map<Integer,Guard> guards = new HashMap<Integer,Guard>()

        // Parses the record to get a map of Date <-> Log Record , ordered by date
        for(String line:lines){
            parsedDate = LocalDateTime.parse(line.split(']')[0].replace('[', ''), dateFormatter)
            parsedRecord = line.split(']')[1].replaceFirst(' ','')
            logEntries.put(parsedDate,parsedRecord)
        }

        // Builds a list of all the guards appearing in the logs, for each guard we build the list of record specific to him
        Guard currentGuard
        for(entry in logEntries){

            if (entry.value.contains('#')){
                Integer guardID = entry.value.split('#')[1].split(' ')[0].toInteger()
                if(guards.get(guardID) == null){
                    currentGuard = new Guard(guardID)
                    guards.put(guardID,currentGuard)
                } else {
                    currentGuard = guards.get(guardID)
                }
            } else {
                currentGuard.getRecord().put(entry.key,entry.value)
            }
        }


        // For each guard, iterates the log to know how many minutes were spent sleeping, and how many times each minute has been slept
        // We don't care about the actual dates and assume that a "falls asleep" record is always followed by a "wakes up" record during the same day

        for(Guard guard in guards.values()){
            int minuteSleepStarts = -1
            int minuteSleepEnds = -1
            int[] minutesSleptDetail = new int[60]
            int totalMinutesSlept
            for(entry in guard.getRecord()){
                if(entry.value == "falls asleep") {
                    minuteSleepStarts = entry.key.getMinute()

                }
                if(entry.value == "wakes up") {
                    minuteSleepEnds = entry.key.getMinute()
                }

                if(minuteSleepStarts != -1 && minuteSleepEnds != -1){
                    for (int i = minuteSleepStarts; i < minuteSleepEnds; i++){
                        minutesSleptDetail[i] += 1
                        totalMinutesSlept += 1
                    }
                    // reinit minutes for next sleep segment
                    minuteSleepStarts = -1
                    minuteSleepEnds = -1
                }
            }
            guard.setTotalMinutesSlept(totalMinutesSlept)
            guard.setMinutesSleptDetail(minutesSleptDetail)
        }

        // Finds guard that slept the most
        Guard guardWhoSleptTheMost
        int mostMinutesSleptByAGuard
        for(Guard guard:guards.values()){
            if(guard.getTotalMinutesSlept() > mostMinutesSleptByAGuard){
                mostMinutesSleptByAGuard = guard.getTotalMinutesSlept()
                guardWhoSleptTheMost = guard
            }
        }

        String IDofSelectedGuard = guardWhoSleptTheMost.getID()

        //Finds which minute he slept the most
        int mostSleptInAMinute
        int mostSleptMinute
        int[] minuteSleptDetails = guardWhoSleptTheMost.getMinutesSleptDetail()
        for(int i=0; i<60; i++) {
            if (minuteSleptDetails[i] > mostSleptInAMinute) {
                mostSleptInAMinute = minuteSleptDetails[i]
                mostSleptMinute = i
            }
        }


        println "Answer to phase 1 is: " + IDofSelectedGuard + " x " + mostSleptMinute + " = " +  IDofSelectedGuard.toInteger() * mostSleptMinute
    }

}