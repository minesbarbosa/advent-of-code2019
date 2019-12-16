import scala.io.Source

object day1p1 {
  def main(args: Array[String]) {
    var noun = 0
    var verb = 0

    for (i <- 0 to 99) {
      for (j <- 0 to 99) {
        val input: Array[Int] =
        Source.fromFile("input.txt").mkString.split(",").map(s => s.toInt)
        input(1) = i
        input(2) = j

        var pos = 0
        while (input(pos) != 99) {
          input(pos) match {
            case 1 =>
              input(input(pos + 3)) = input(input(pos + 1)) + input(
                input(pos + 2))
            case 2 =>
              input(input(pos + 3)) = input(input(pos + 1)) * input(
                input(pos + 2))
          }
          pos += 4
        }
        if (input(0) == 19690720) {
          val res = 100 * i + j
          println("i: " + i + " j: " + j)
          println(res)
          return
        }
      }
    }
  }
}
