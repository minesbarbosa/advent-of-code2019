import scala.io.Source

object day1p1 {

  def main(args: Array[String]) {

    val input: Array[Int] = Source.fromFile("input.txt").mkString.split(",").map(s => s.toInt)

    input(1) = 12
    input(2) = 2
    
  calculate(input,0)

    println(input(0))
  }

def calculate(input: Array[Int], pos: Int) {
  if (input(pos) == 99) return

  input(pos) match {
        case 1 => input(input(pos + 3)) = input(input(pos + 1)) + input(input(pos + 2))
        case 2 => input(input(pos + 3)) = input(input(pos + 1)) * input(input(pos + 2))
      }
      calculate(input, pos+4)
}

}