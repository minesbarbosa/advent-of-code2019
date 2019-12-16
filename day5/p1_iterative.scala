import scala.io.Source.{fromFile}
import scala.io.StdIn

object day1p1 {
  var pos = 0

  def main(args: Array[String]) {
    val instructions: Array[Int] = fromFile("input.txt").mkString.split(",").map(element => element.toInt)

    while (instructions(pos) != 99) {
      val n = instructions(pos)
      if (n != 1 && n != 2 && n != 3 && n != 4) { // parameter mode _ B C D E
        var opcode: Int = Integer.parseInt(Integer.toString(instructions(pos)).substring(2)) // tirar opcode de D e E
        var writeToA, paramB, paramC: Int = 0

        if (instructions(pos) < 1000) {
          paramB = instructions(pos + 2)
          paramC = pos + 1
        } else {
          paramB = pos + 2
          if (instructions(pos).toString.substring(1,2).toInt == 0) {
            paramC = instructions(pos + 1)
          } else {
            paramC = pos + 1
          }
        }

        writeToA = instructions(pos + 3) // buscar o número naquela posicao

        process(instructions, opcode, writeToA, paramB, paramC)
      } else {
        process(instructions, n, instructions(pos + 3), instructions(pos + 2), instructions(pos + 1))
      }
    }
  }

  def process(instructions: Array[Int], opcode: Int, a: Int, b: Int, c: Int): Unit = {
    opcode match {
      case 1 => {
        instructions(a) = instructions(b) + instructions(c)
        pos = pos + 4
      }
      case 2 => {
        instructions(a) = instructions(b) * instructions(c)
        pos = pos + 4
      }
      case 3 => {
        println("insert one digit")
        instructions(instructions(pos + 1)) = StdIn.readInt()
        pos = pos + 2
      }
      case 4 => {
        println("output")
        println(instructions(c))
        pos = pos + 2
      }
    }
  }
}

