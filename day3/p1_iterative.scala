import scala.io.Source

object day3p1 {

 case class Coordinate (x: Int, y : Int)

  def main(args: Array[String]) {

    val input = Source.fromFile("input.txt").mkString.split("\n")

    val paths = Array(
      scala.collection.mutable.ArrayBuffer.empty[Coordinate],
      scala.collection.mutable.ArrayBuffer.empty[Coordinate]
    )

    for (i<-0 to 1) {calculateCoordinates(paths(i), input(i))}

    val intersection = println(paths(0).intersect(paths(1))
      .map(el => Math.abs(el.x) + Math.abs(el.y))
      .min)
  }


  def calculateCoordinates(list :  scala.collection.mutable.ArrayBuffer[Coordinate], input : String): Unit = {
    var x,y = 0
    input.split(",").foreach(element => {
      val el = element.charAt(0) match {
        case 'R' => {
          for(i<- 1 to element.substring(1).toInt) {
            x += 1
            list += new Coordinate(x,y)
          }
        }
        case 'L' => {
          for(i<- 1 to element.substring(1).toInt) {
            x -= 1
            list += new Coordinate(x,y)
          }
        }
        case 'U' => {
          for(i<- 1 to element.substring(1).toInt) {
            y += 1
            list += new Coordinate(x,y)
          }
        }
        case 'D' => {
          for(i<- 1 to element.substring(1).toInt) {
            y -= 1
            list += new Coordinate(x,y)
          }
        }
      }
    })
  }
}