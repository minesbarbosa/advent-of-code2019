import scala.io.Source

 object day1p1 {

    def main(args: Array[String]) {
   
		val fileContents = Source.fromFile("input.txt").getLines.toList
		val res = fileContents.map(m => {calculate(m.toInt, 0)}).sum
		println(res)
 	}

	def calculate(number: Int, counter: Int) : Int = {
		if(number/3-2 <= 0) return counter

		return calculate(number/3 - 2, counter + number/3 - 2)
	}
}
