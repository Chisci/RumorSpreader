package fr.uds.info901.RumorYAP;
import java.util.ArrayList;
import java.util.List;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

/**
 * Hello world!
 *
 */
public class App 
{
	private static List<Personne> getInitialGraph(){
		List<Personne> reseau = new ArrayList<Personne>();
		
		double nodeNumber = Math.random()*500;
		for(int i =0; i<=nodeNumber; ++i){
			reseau.add(new Personne("personne"+i));
		}
		
		return reseau;
	}
	
	
    public static void main( String[] args )
    {
    	//Test d'un reseau et d'une rumeur
    	List<Personne> reseau = App.getInitialGraph();
    	reseau.get(0).setRumor(70);
    	for (Personne ami : reseau) {
			ami.spread();
		}
    	for (Personne ami : reseau) {
			System.out.println(ami.getRumor());
		}
    	
    	//Test graphstream
    	Graph graph = new SingleGraph("Rumor");
    	for (Personne ami : reseau) {
		Node test = graph.addNode(ami.getName());	
		test.setAttribute("ui.color", 120);
		test.setAttribute("ui.size", 240);
		test.setAttribute("ui.tag", "coucou");
		}
    	//Algo pour créer le graph
    	for (Personne ami : reseau) {
    		for (Personne relation : ami.amis)
			graph.addEdge(ami.getName()+relation.getName(),ami.getName(),relation.getName());
		}
    	//Afficher le graph
    	graph.display();
    }
    
}
