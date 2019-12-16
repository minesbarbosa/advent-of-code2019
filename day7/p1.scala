import scala.io.Source.{fromFile}
import scala.io.StdIn
import scala.collection.mutable.ArrayBuffer

object day1p1 {
  var pos = 0
  var allPermutations: Set[String] = Set()
  var nextInput = 0
  var isDemandingSettingInput = true
  var isAmplifierA = true
  val instructions: Array[Int] = fromFile("input.txt").mkString.split(",").map(element => element.toInt)
  var allOutputs: ArrayBuffer[Int] = ArrayBuffer[Int]()

  def main(args: Array[String]) {
    var phases: String = "01234"
    calculatePermutations("", phases);

    println("permut size " + allPermutations.size)

    allPermutations.foreach(phaseSetting => {
      isAmplifierA = true
      nextInput = 0
      if (phaseSetting.size == 4) {
        runAmplificationCircuit(0 + phaseSetting)
      } else {
        runAmplificationCircuit(phaseSetting)
      }
    })
    println("maximum output " + allOutputs.max)
  }

  def calculatePermutations(prefix: String, str: String): Unit = {
    val n: Int = str.size
    if (n == 0) {
      allPermutations += prefix
    }
    else {
      for (i <- 0 to n - 1) {
        calculatePermutations(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n))
      }
    }
  }

  def runAmplificationCircuit(phaseSetting: String): Unit = {
    for (i <- 0 to 4) {
      isDemandingSettingInput = true
      runProgram(phaseSetting.charAt(i).asDigit)
      pos = 0
      isAmplifierA = false
    }
  }

  def runProgram(phaseSetting: Int): Unit = {
    while (instructions(pos) != 99) {
      val n = instructions(pos)
      if (n > 10) {
        var opcode: Int = Integer.parseInt(Integer.toString(instructions(pos)).substring(2))
        var writeToA, paramB, paramC: Int = 0

        if (instructions(pos) < 1000) {
          paramB = instructions(pos + 2)
          paramC = pos + 1
        } else {
          paramB = pos + 2
          if (instructions(pos).toString.substring(1, 2).toInt == 0) {
            paramC = instructions(pos + 1)
          } else {
            paramC = pos + 1
          }
        }
        writeToA = instructions(pos + 3)
        process(instructions, opcode, writeToA, paramB, paramC, phaseSetting)
      } else {
        process(instructions, n, instructions(pos + 3), instructions(pos + 2), instructions(pos + 1), phaseSetting)
      }
    }
  }

  def process(instructions: Array[Int], opcode: Int, a: Int, b: Int, c: Int, phaseSetting: Int): Unit = {
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
        if (isDemandingSettingInput) {
          println(" em pos+1 " + pos + 1 + " :" + instructions(pos + 1) + " pôr phaseSetting " + phaseSetting)
          instructions(instructions(pos + 1)) = phaseSetting
          isDemandingSettingInput = false
        } else {
          if (isAmplifierA) {
            instructions(instructions(pos + 1)) = 0
            isAmplifierA = false
          } else {
            instructions(instructions(pos + 1)) = nextInput
          }
        }
        pos = pos + 2
      }
      case 4 => {
        allOutputs += instructions(c)
        nextInput = instructions(c)
        pos = pos + 2
      }
      case 5 => {
        if (instructions(c) != 0) {
          pos = instructions(b)
        } else pos = pos + 3
      }
      case 6 => {
        if (instructions(c) == 0) {
          pos = instructions(b)
        } else pos = pos + 3
      }
      case 7 => {
        if (instructions(c) < instructions(b)) {
          instructions(a) = 1
        } else {
          instructions(a) = 0
        }
        pos = pos + 4
      }
      case 8 => {
        if (instructions(c) == instructions(b)) {
          instructions(a) = 1
        } else {
          instructions(a) = 0
        }
        pos = pos + 4
      }
    }
  }
}

