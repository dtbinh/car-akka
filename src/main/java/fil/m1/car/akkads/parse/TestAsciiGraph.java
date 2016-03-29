package fil.m1.car.akkads.parse;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import com.github.mdr.ascii.graph.Graph;

import scala.collection.JavaConversions.*;
import scala.Tuple2;


public class TestAsciiGraph {
    
    public static void main(String[] args) {
        /*
         * val graph = Graph(
  vertices = List(
    "V1", "V2", "V3", "V4", "V5", "V6", "V7"),
  edges = List(
    "V1" -> "V2",
    "V7" -> "V1",
    "V1" -> "V3",
    "V1" -> "V4",
    "V2" -> "V5",
    "V2" -> "V6"))

val ascii = Layouter.renderGraph(graph)

println(ascii)*/
        final List<String> set = Arrays.asList("a", "b");
        final List<Tuple2<String, String>> test = Arrays.asList(new Tuple2<String, String>("a", "b"));
        //new Graph<>(vertices, edges)
        //new Graph(set, test);
        //new Graph<String>(new HashSet<String>(), new List<A>() {});
    }

}
