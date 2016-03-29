package fil.m1.car.akkads.parse;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import com.github.mdr.ascii.graph.Graph;

import scala.Tuple2;
import scala.collection.JavaConverters;
import scala.collection.convert.Decorators.AsScala;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Set;

public class TestAsciiGraph {

	public static void main(String[] args) {
		/*
		 * val graph = Graph( vertices = List( "V1", "V2", "V3", "V4", "V5",
		 * "V6", "V7"), edges = List( "V1" -> "V2", "V7" -> "V1", "V1" -> "V3",
		 * "V1" -> "V4", "V2" -> "V5", "V2" -> "V6"))
		 * 
		 * val ascii = Layouter.renderGraph(graph)
		 * 
		 * println(ascii)
		 */
		
		AsScala<Set<String>> a = JavaConverters.asScalaSetConverter(new HashSet<String>(Arrays.asList("a", "b")));
		AsScala<Buffer<Tuple2<String, String>>> b = JavaConverters.asScalaBufferConverter(Arrays.asList(new Tuple2<String, String>("a", "b")));
		scala.collection.immutable.List<String> c = a.asScala().toList();
		scala.collection.immutable.List<Tuple2<String, String>> d = b.asScala().toList();
		//new Graph<String>(a.asScala().toList(), b.asScala().toList());
		// new Graph<>(vertices, edges)
		// new Graph(set, test);
		// new Graph<String>(new HashSet<String>(), new List<A>() {});
	}

}
