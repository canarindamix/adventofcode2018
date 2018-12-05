package Day2

class InventoryManagementSystem {

    File f = new File("input.txt")
    def lines = f.readLines()

    int numberOfIdsWithAtLeastOnLetterRepeatedExactlyTwice = 0
    int numberOfIdsWithAtLeastOnLetterRepeatedExactlyThrice = 0

    void computeChecksum(){

        for(String line:lines){
            if (isThereAnyRepetition(line,2)) numberOfIdsWithAtLeastOnLetterRepeatedExactlyTwice += 1
            if (isThereAnyRepetition(line,3)) numberOfIdsWithAtLeastOnLetterRepeatedExactlyThrice += 1
        }

        println "result checksum " + (numberOfIdsWithAtLeastOnLetterRepeatedExactlyTwice * numberOfIdsWithAtLeastOnLetterRepeatedExactlyThrice)
    }

    boolean isThereAnyRepetition(String input, int numberOfExactRepetition){

        Character rptChar = null;
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
}