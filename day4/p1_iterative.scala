import scala.io.Source

object day3p1 {

  case class Coordinate (x: Int, y : Int)

  def main(args: Array[String]) {
    val min = "137683"
    val max = "596253"
    var count = 0

    for (i<- min.toInt to max.toInt) {
     if(hasAdjacentDouble(i.toString) && digitsNeverDecrease(i.toString)) {
       count += 1
     }
    }
    println(count)
  }

  def hasAdjacentDouble(number : String): Boolean = {
    for (i<- 0 to number.length-2) {
      if(number.charAt(i).equals(number.charAt(i+1))) {
        return true
      }
    }
    return false
  }

  def digitsNeverDecrease(number : String): Boolean = {
    for (i<- 0 to number.length-2) {
      if(number.charAt(i).toInt.compare(number.charAt(i+1).toInt) > 0) {
        return false
      }
    }
    return true
  }

  }
