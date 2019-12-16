import scala.io.Source.{fromFile}
import scala.collection.mutable.Map

object day1p1 {

  def main(args: Array[String]) {

    val edges: Array[String] = fromFile("input.txt").mkString.split("\n").map(element => element.trim())
    var nodes: Map[String, Int] = getAMapWithEachNodePositionPair(edges)
    val graph = buildGraphMatrix(edges, nodes)
    val finalGraph = calculateTransitiveClosure(graph, nodes.size)
    var numberOfConnections = 0
    finalGraph.foreach(row => row.foreach(value => if (value == 1) {
      numberOfConnections = numberOfConnections + 1
    }))
    println("The total number of connections is " + numberOfConnections + "!")
  }

  def getAMapWithEachNodePositionPair(edges: Array[String]): Map[String, Int] = {
    var nodesSet: Set[String] = Set()
    edges.map(orbit => orbit.split(')').foreach(e => nodesSet += e))
    var nodesMap: Map[String, Int] = Map[String, Int]().empty

    nodesSet.map(element => nodesMap.put(element, nodesMap.size))
    nodesMap
  }

  def buildGraphMatrix(edges: Array[String], nodes: Map[String, Int]): Array[Array[Int]] = {
    val matrix = Array.ofDim[Int](nodes.size, nodes.size)
    for (currentEdge <- 0 to edges.size - 1) {
      val currentEdgeNodes = edges(currentEdge).split(')')
      matrix(getNodeIndex(currentEdgeNodes(0), nodes))(getNodeIndex(currentEdgeNodes(1), nodes)) = 1
    }
    matrix
  }

  def getNodeIndex(node: String, nodes: Map[String, Int]): Int = {
    val nodeOne = node.trim()
    nodes.get(node).get
  }

  def calculateTransitiveClosure(graph: Array[Array[Int]], numberOfNodes: Int): Array[Array[Int]] = {

    for (k <- 0 to numberOfNodes - 1) {
      // Pick all vertices as source one by one
      for (i <- 0 to numberOfNodes - 1) {
        // Pick all vertices as destination for the above picked source
        for (j <- 0 to numberOfNodes - 1) {
          // If vertex k is on a path from i to j, then make sure that the value of reach[i][j] is 1
          if ((graph(i)(j) != 0) || ((graph(i)(k) != 0) && (graph(k)(j) != 0)))
            graph(i)(j) = 1
          else
            graph(i)(j) = 0
        }
      }
    }
    graph
  }

  def printSolution(graph: Array[Array[Int]], numberOfNodes: Int): Unit = {
    for (i <- 0 to numberOfNodes - 1) {
      for (j <- 0 to numberOfNodes - 1) {
        print(graph(i)(j) + " ")
      }
      println()
    }
  }
}