package algorithms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/*import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
*/
import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;

/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms{

	private graph graph;
	
	@Override
	public void init(graph g) {
		for(node_data n : g.getV())
		{
			this.graph.addNode(n);
		}	
		Collection<edge_data> al; 
		for(node_data n : this.graph.getV())
		{
			al = g.getE(n.getKey());
			for(edge_data e : al)
			{
				int s = e.getSrc();
				int d = e.getDest();
				double w = e.getWeight();
				if(!this.graph.getE(n.getKey()).contains(e))
				{
					this.graph.connect(s, d, w);
				}
			}
			
		}
	}

	@Override
	public void init(String file_name) {
		/*try {
			BufferedReader br = new BufferedReader(new FileReader(file_name));
			Gson gson = new Gson();
			this.graph = gson.fromJson(br, DGraph.class);
			
		}
		catch (IOException e) 
        {  
            e.printStackTrace();  
        }  
	*/	
	}

	@Override
	public void save(String file_name) {
		/*try(Writer fn = new FileWriter(file_name))
		{
			Gson gson = new GsonBuilder().create();
			gson.toJson(this.graph, fn);
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	*/	
	}

	@Override
	public boolean isConnected() {
		int counter = 0;
		for(node_data n : this.graph.getV())
		{
			counter = 0;
			for(edge_data e : this.graph.getE(n.getKey()))
			{
				if(e.getSrc() == n.getKey() && e.getDest() != n.getKey())
				{
					counter++;
				}
			}
			if(counter != this.graph.getV().size())
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public double shortestPathDist(int src, int dest) {
	double weight = 0;
	List<node_data> ln = shortestPath(src, dest);
	for(node_data n : ln)
	{
		for(edge_data e : this.graph.getE(n.getKey()))
		{
			if(ln.contains(this.graph.getNode(e.getDest())) && n.getKey() == e.getSrc())
			{
				weight = weight + e.getWeight();
			}
		}
	}
	return weight;
	}
	
	@Override
	public List<node_data> shortestPath(int src, int dest) {
		HashMap<Integer, Double> hmw = new HashMap<Integer, Double>();
		HashMap<Integer, ArrayList<node_data>> hml = new HashMap<Integer, ArrayList<node_data>>();
		for(node_data n : this.graph.getV())
		{
			hmw.put(n.getKey(), Double.MAX_VALUE);
		}
		hmw.put(src, 0.0);
		hml.get(src).add(this.graph.getNode(src));
		for(int i : hmw.keySet())
		{
			for(edge_data e : this.graph.getE(i))
			{
				if(e.getSrc() == i)
				{
					double w = hmw.get(i)+e.getWeight();
					if(w < hmw.get(e.getDest()))
					{
						hmw.put(e.getDest(), w);
						hml.get(e.getDest()).addAll(hml.get(i));
						hml.get(e.getDest()).add(this.graph.getNode(e.getDest()));
					}
				}
			}
		}
		
		
		return hml.get(dest);
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		ArrayList<node_data> ln =new ArrayList<node_data>() ; 
		for(int i=0; i<targets.size()-1; i++)
		{
			ln.addAll(shortestPath(targets.get(i), targets.get(i+1)));
		}
		return ln;
	}

	@Override
	public graph copy() {
		DGraph g = new DGraph();
		for(node_data n : this.graph.getV())
		{
			g.addNode(n);
		}
		Collection<edge_data> al; 
		for(node_data n : g.getV())
		{
			al = g.getE(n.getKey());
			for(edge_data e : al)
			{
				int s = e.getSrc();
				int d = e.getDest();
				double w = e.getWeight();
				if(!g.getE(n.getKey()).contains(e))
				{
					g.connect(s, d, w);
				}
			}
			
		}
		return g;
	}

}
