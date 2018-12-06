package Day1

class ChronalCalibration {

    File f = new File("../../resources/day1Input.txt")
    def lines = f.readLines()

    void phase1(){
        Integer sum = 0
        lines.forEach(){ frequency ->
            sum += Integer.parseInt(frequency)
        }
        println  "Phase 1: resulting frequency " + sum
    }

    void phase2() {

        Integer frequencySum = Integer.valueOf(0)
        boolean foundFirstRepeatingFrequency = false
        List<Integer> listOfFrequencies = new ArrayList<Integer>()

        while (!foundFirstRepeatingFrequency) {

            for (String freq:lines){
                frequencySum = frequencySum + freq.toInteger()

                if (listOfFrequencies.contains(frequencySum)){
                    println "Phase 2: first repeating frequency " + frequencySum
                    foundFirstRepeatingFrequency = true
                    break
                }
                listOfFrequencies.add(frequencySum)
            }
        }
    }
}