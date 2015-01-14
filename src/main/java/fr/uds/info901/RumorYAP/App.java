package fr.uds.info901.RumorYAP;
import java.util.ArrayList;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	//Test d'un reseau et d'une rumeur
    	ArrayList<Personne> Reseau = new ArrayList<Personne>();
    	Personne p1 = new Personne("Challut");
    	Personne p2 = new Personne("Cyril");
    	Personne p3 = new Personne("PE");
    	Personne p4 = new Personne("Tristan");
    	Personne p5 = new Personne("Yvon");
    	p1.addFriend(p2);
    	p1.addFriend(p4);
    	p1.addFriend(p3);
    	p3.addFriend(p5);
    	p4.addFriend(p3);
    	Reseau.add(p1);
    	Reseau.add(p2);
    	Reseau.add(p3);
    	Reseau.add(p4);
    	Reseau.add(p5);
    	p1.setRumor(70);
    	for (Personne ami : Reseau) {
			ami.spread();
		}
    	for (Personne ami : Reseau) {
			System.out.println(ami.getRumor());
		}
    	
    	//Test graphstream
    	Graph graph = new SingleGraph("Rumor");
    	for (Personne ami : Reseau) {
		Node test = graph.addNode(ami.getName());	
		test.setAttribute("ui.color", 120);
		test.setAttribute("ui.size", 240);
		test.setAttribute("ui.tag", "coucou");
		}
    	//Algo pour créer le graph
    	for (Personne ami : Reseau) {
    		for (Personne relation : ami.amis)
			graph.addEdge(ami.getName()+relation.getName(),ami.getName(),relation.getName());
		}
    	//Afficher le graph
    	graph.display();
    }
    
}
