package Day1

class ChronalCalibration {

    File f = new File("input.txt")
    def lines = f.readLines()

    void phase1(){
        Integer frequencySum = 0
        lines.forEach(){ frequency ->
            frequencySum += Integer.parseInt(frequency)
        }
        println  "resulting frequency " + frequencySum
    }

    void phase2() {

        Integer frequencySum = Integer.valueOf(0)
        List<Integer> listOfFrequencies = new ArrayList<Integer>()
        //listOfFrequencies.add(Integer.valueOf("5"))

        println "number of freqs " + lines.size()

        for (String freq:lines){
            frequencySum = frequencySum + Integer.valueOf(freq)
            //if (listOfFrequencies.contains(Integer.valueOf(frequencySum))){
            if (listOfFrequencies.contains(frequencySum)){
                println listOfFrequencies.toString()
                break
            } else
            listOfFrequencies.add(Integer.valueOf(frequencySum))
        }

        println "size of set of intermediate freqs " + listOfFrequencies.size()
        println "first repeating frequency " + frequencySum

    }
}