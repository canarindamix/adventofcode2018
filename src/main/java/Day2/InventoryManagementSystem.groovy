package Day2

import com.google.common.base.CharMatcher

class InventoryManagementSystem {

    File f = new File("../../resources/day2Input.txt")
    def lines = f.readLines()

    int numberOfIdsWithAtLeastOneLetterRepeatedExactlyTwice = 0
    int numberOfIdsWithAtLeastOneLetterRepeatedExactlyThrice = 0

    void computeChecksum(){

        for(String line:lines){
            if (isThereAnyRepeatedLetter(line,2)) numberOfIdsWithAtLeastOneLetterRepeatedExactlyTwice += 1
            if (isThereAnyRepeatedLetter(line,3)) numberOfIdsWithAtLeastOneLetterRepeatedExactlyThrice += 1
        }

        println "result checksum " + (numberOfIdsWithAtLeastOneLetterRepeatedExactlyTwice * numberOfIdsWithAtLeastOneLetterRepeatedExactlyThrice)
    }

    void findCorrectBoxesCommonId(){

        String difference
        String id1
        String id2

        for(int i=0; i < lines.size()-1; i++ ){
            for(int j=i+1; j < lines.size(); j++){

                id1 = lines.get(i)
                id2 = lines.get(j)

                //IDs we search differ by exactly ONE char, so they must have the same length
                if(id1.size() == id2.size()){
                    difference = stripChars(id1,id2)
                    //they must have only one char that differs
                    if (difference.size() == 1){
                        //let'remove the char that differs from both strings
                        id1 = stripChars(id1,difference)
                        difference = stripChars(id2, id1)
                        id2 = stripChars(id2, difference)
                        //when the diff char is removed we should get the same sequence of chars remaining
                        if(id1 == id2){
                            println "common ID is " + stripChars(id1, difference)
                            break
                        }
                    }
                }
            }
        }
    }

    boolean isThereAnyRepeatedLetter(String input, int numberOfExactRepetition){

        //Will store each character and it's count
        HashMap<Character, Integer> map = new HashMap<Character, Integer>()
        for (int i = 0; i <input.length(); i++) {
            Character chr = input.charAt(i)
            if(map.containsKey(chr)){
                map.put(chr,map.get(chr)+1)
            }else{
                map.put(chr, 1)
            }
        }
        //Iterate the string and return true for the first character appearing exactly "numberOfExactRepetition" times
        for (int i = 0; i <input.length() ; i++) {
            if(map.get(input.charAt(i)) == numberOfExactRepetition){
                return true
            }
        }
        return false
    }

    //removes all charsToRemove from stringToFilter
    String stripChars(String stringToFilter, String charsToRemove) {
        String filtered = CharMatcher.anyOf(charsToRemove).removeFrom(stringToFilter)
        return filtered
    }

}