package Day5

class AlchemicalReduction {

    File f = new File("../../resources/day5Input.txt")
    def polymer = f.readLines()

    void findNumberOfUnitsAfterReaction(){
        String test = reactPolymer(polymer.get(0))
        println "number of units left after reaction " + test.size()
    }

    String reactPolymer(String polymer){
        if(polymer.size() >= 2){
            boolean atLeastOneReactionHappened = false
            for(int i=0; i<polymer.size()-1; i++){
                if(polymer[i].toLowerCase() == polymer[i+1].toLowerCase() && polymer[i] != polymer[i+1]){
                    polymer = polymer.minus(polymer[i]+polymer[i+1])
                    atLeastOneReactionHappened = true
                }
            }
            if (atLeastOneReactionHappened){
                polymer=reactPolymer(polymer)
            }
        }
        return polymer
    }

}