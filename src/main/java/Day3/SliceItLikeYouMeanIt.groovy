package Day3

class SliceItLikeYouMeanIt {

    File f = new File("../../resources/day3Input.txt")
    def lines = f.readLines()
    List<Rectangle> rectangles = parseRectangles()


    void findTotalMultiClaimedSurface(){
        int xMax
        int yMax

        // find the minimal size of the canvas able to contain all the patches
        for(Rectangle rectangle:rectangles){
            if (rectangle.x + rectangle.width > xMax) {
                xMax = rectangle.x  + rectangle.width
            }
            if (rectangle.y + rectangle.height > yMax) {
                yMax = rectangle.y + rectangle.height
            }
        }

        int[][] canvas = new int[xMax][yMax]

        // Mark the square inches claimed by patches on the canvas
        for (Rectangle rectangle:rectangles){
            for (int i = rectangle.x-1; i < rectangle.x + rectangle.width-1; i++){
                for (int j = rectangle.y-1; j< rectangle.y + rectangle.height-1; j++){
                    canvas[i][j] = canvas[i][j] + 1
                }
            }
        }

        // Count the number of square inches claimed more than once
        int squareInchesMultiClaimed = 0

        for (int i=0; i < xMax; i++){
            for (int j=0; j < yMax; j++){
                if (canvas[i][j] > 1){
                    squareInchesMultiClaimed++
                }
            }
        }

        println "Number of square inches claimed by more than one patch " + squareInchesMultiClaimed
    }

    void findSingleNonOverlappingPatch(){
        Set<Rectangle> rectangleWithNoOverlap = new HashSet<Rectangle>()
        boolean currentHasOverlap
        for(int i=0; i < rectangles.size()-1; i++){

            currentHasOverlap = false

            for(int j=0; j < rectangles.size(); j++){
                if (rectanglesOverlap(rectangles.get(i),rectangles.get(j))){
                    currentHasOverlap = true
                }
            }

            if (currentHasOverlap){
                rectangleWithNoOverlap.remove(rectangles.get(i))
            } else {
                rectangleWithNoOverlap.add(rectangles.get(i))
            }
        }

        println rectangleWithNoOverlap.size()
    }

    List<Rectangle> parseRectangles(){
        List<Rectangle> rectangleList = new ArrayList<Rectangle>()
        String rectangleDefinition
        String[] rectangleCoordinates
        String[] rectangleSize
        String id
        for(String line:lines){
            line = line.replace(' ', '')
            id = line.split('@')[0].replace('#','')
            rectangleDefinition = line.split('@')[1]
            rectangleCoordinates = rectangleDefinition.split('\\:')[0].split(',')
            rectangleSize = rectangleDefinition.split('\\:')[1].split('x')
            rectangleList.add(new Rectangle(id, rectangleCoordinates[0], rectangleCoordinates[1], rectangleSize[0], rectangleSize[1]))

        }
        return rectangleList
    }

    boolean rectanglesOverlap (Rectangle a, Rectangle b){

        Rectangle leftMostRectangle
        Rectangle rightMostRectangle
        Rectangle topMostRectangle
        Rectangle bottomMostRectangle

        if (a.x < b.x){
            leftMostRectangle = a
            rightMostRectangle = b
        } else {
            leftMostRectangle = b
            rightMostRectangle = a
        }

        if (a.y < b.y){
            topMostRectangle = b
            bottomMostRectangle = a
        }  else {
            topMostRectangle = a
            bottomMostRectangle = b
        }

        if((leftMostRectangle.x + leftMostRectangle.width) - rightMostRectangle.x >= 0 && (bottomMostRectangle.y + bottomMostRectangle.height) - topMostRectangle.y >= 0){
            return true
        } else return false
    }
}