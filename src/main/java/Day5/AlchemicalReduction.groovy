package Day5

class AlchemicalReduction {

    File f = new File("../../resources/day5Input.txt")
    def polymer = f.readLines()

    void findNumberOfUnitsAfterReaction(){
        String reactedPolymer = reactPolymer(polymer.get(0))
        println "number of units left after reaction " + reactedPolymer.size()
    }

    void findShortestPolymer(){

        String strippedPolymer
        String reactedPolymer
        List<Integer> reactedPolymersSizes = new ArrayList<Integer>()

        for(char unit = 'a'; unit <= 'z'; unit++){
            strippedPolymer = polymer.get(0).replace(unit.toString(),'').replace(unit.toUpperCase().toString(),'')
            reactedPolymer = reactPolymer(strippedPolymer)
            reactedPolymersSizes.add(reactedPolymer.size())
        }

        reactedPolymersSizes.sort()

        println "size of the shortest polymer produce after removing one single type of unit: " + reactedPolymersSizes.get(0)
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