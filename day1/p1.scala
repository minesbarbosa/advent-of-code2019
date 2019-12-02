import scala.io.Source

 object day1p1 {
    def main(args: Array[String]) {
   
	val fileContents = Source.fromFile("input.txt").getLines.toList
// val lines = Source.fromFile("/Users/Al/.bash_profile").getLines.toList
	val res = fileContents.map(m => m.toInt / 3 - 2).sum

				println(res)


 }
}
