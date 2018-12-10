package Day3

class Rectangle {

    String ID
    int x
    int y
    int width
    int height

    Rectangle(String ID, String x, String y, String width, String height){
        this.ID = ID
        this.x = x.toInteger()
        this.y = y.toInteger()
        this.height = height.toInteger()
        this.width = width.toInteger()
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Rectangle rectangle = (Rectangle) o

        if (ID != rectangle.ID) return false

        return true
    }

    int hashCode() {
        return (ID != null ? ID.hashCode() : 0)
    }
}
