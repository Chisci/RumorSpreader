package fr.uds.info901.RumorYAP;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

/**
 * Hello world!
 *
 */
public class App 
{
	private static final int NB_PERSONNE = 50;
	
	private static List<Personne> getInitialGraph(){
		List<Personne> reseau = new ArrayList<Personne>();
		
		double nodeNumber = Math.random()*NB_PERSONNE;
		for(int i =0; i<=nodeNumber; ++i){
			reseau.add(new Personne("personne"+i));
		}
		for(Personne personne : reseau){
			for(Personne personne2 : reseau){
				if(!personne.equals(personne2)){
					if(Math.random()<0.5){
						if(!personne2.isFriendWith(personne)){
							personne.amis.add(personne2);
						}	
					}
				}
			}
		}
		
		return reseau;
	}
	
	
    public static void main( String[] args )
    {
    	Scanner sc = new Scanner(System.in);
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
    		graph.addNode(ami.getName());
		}

    	//Afficher le graph
    	graph.display();
    	//Algo pour créer le graph
    	for (Personne ami : reseau) {
    		for (Personne relation : ami.amis)
			graph.addEdge(ami.getName()+relation.getName(),ami.getName(),relation.getName());
    		if(ami.getRumor()>10)
    			graph.getNode(ami.getName()).setAttribute("ui.style", "fill-color: rgb(255,0,0);");
    		//sc.nextLine();
		}
    	
    	sc.close();
    	
    }
}
