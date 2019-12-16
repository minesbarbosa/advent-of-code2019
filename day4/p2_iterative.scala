import scala.io.Source

object day4p1 {


  def main(args: Array[String]) {

    val min = "137683"
    val max = "596253"
    var count = 0

    for (i<- min.toInt to max.toInt) {

      if(hasIsolatedAdjacentDouble(i.toString) && digitsNeverDecrease(i.toString)) {
        count += 1
      }
    }
    println(count)
  }

  def hasIsolatedAdjacentDouble(number : String): Boolean = {
  var countMap = scala.collection.mutable.Map(
    '1' -> 0,
    '2' -> 0,
    '3' -> 0,
    '4' -> 0,
    '5' -> 0,
    '6' -> 0,
    '7' -> 0,
    '8' -> 0,
    '9' -> 0,
    '0' -> 0,
    )

    for (i<- 0 to number.length-1) {
      countMap(number.charAt(i)) +=1
    }

    countMap.map(e => if (e._2 == 2){
      println(e)
      return true
    })
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
