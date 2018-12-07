package Day3

class SliceItLikeYouMeanIt {

    File f = new File("../../resources/day3Input.txt")
    def lines = f.readLines()

    void findTotalMultiClaimedSurface(){
        int xMax
        int yMax
        List<Rectangle> rectangles = parseRectangles()

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

    List<Rectangle> parseRectangles(){
        List<Rectangle> rectangleList = new ArrayList<Rectangle>()
        String rectangleDefinition
        String[] rectangleCoordinates
        String[] rectangleSize
        for(String line:lines){
            rectangleDefinition = line.replace(' ',''). split('@')[1]
            rectangleCoordinates = rectangleDefinition.split('\\:')[0].split(',')
            rectangleSize = rectangleDefinition.split('\\:')[1].split('x')
            rectangleList.add(new Rectangle(rectangleCoordinates[0], rectangleCoordinates[1], rectangleSize[0], rectangleSize[1]))

        }
        return rectangleList
    }

}