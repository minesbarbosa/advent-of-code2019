import scala.io.Source.{fromFile}
import scala.collection.mutable.Map

object day1p1 {
  def main(args: Array[String]) {
    val edges: Array[String] = fromFile("input2.txt").mkString.split("\n").map(element => element.trim())
    var nodes: Map[String, Int] = getAMapWithEachNodePositionPair(edges)
    val graph = buildGraphMatrix(edges, nodes)
    val allConnectionsGraph = calculateTransitiveClosure(graph, nodes.size)

    printResult(graph,edges,nodes,"YOU","SAN")
  }

  def getAMapWithEachNodePositionPair(edges: Array[String]): Map[String, Int] = {
    var nodesSet: Set[String] = Set()
    edges.map(orbit => orbit.split(')').foreach(e => nodesSet += e))
    var nodesMap: Map[String, Int] = Map[String, Int]().empty

    nodesSet.map(element => {
      nodesMap.put(element, nodesMap.size)
    })
    nodesMap
  }

  def buildGraphMatrix(edges: Array[String], nodes: Map[String, Int]): Array[Array[Int]] = {
    val matrix = Array.ofDim[Int](nodes.size, nodes.size)
    for (i <- 0 to nodes.size - 1) {
      for (j <- 0 to nodes.size - 1) {
        if (i == j) {
          matrix(i)(j) = 1
          matrix(j)(i) = 1
        }
      }
    }

    for (currentEdge <- 0 to edges.size - 1) {
      val currentEdgeNodes = edges(currentEdge).split(')')
      matrix(getNodeIndex(currentEdgeNodes(0), nodes))(getNodeIndex(currentEdgeNodes(1), nodes)) = 1
      matrix(getNodeIndex(currentEdgeNodes(1), nodes))(getNodeIndex(currentEdgeNodes(0), nodes)) = 1
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
          if ((graph(i)(j) != 0) || ((graph(i)(k) != 0) && (graph(k)(j) != 0))) {
            if (graph(i)(j) == 0) {
              graph(i)(j) = graph(i)(k) + graph(k)(j)
            }
          } else graph(i)(j) = 0
        }
      }
    }
    graph
  }

  def printResult(graph: Array[Array[Int]],edges: Array[String],nodes: Map[String, Int],start: String,finish: String) {
    var youNeighbourName = ""
    var sanNeighbourName = ""
    edges.foreach(e => {
      if(e.contains(start)) {
        youNeighbourName = e.split(')')(0)
      }
      if(e.contains(finish)) {
        sanNeighbourName = e.split(')')(0)
      }
    })
    println("Number of edges on the way: " + graph(nodes.get(youNeighbourName).get)(nodes.get(sanNeighbourName).get))
  }
}