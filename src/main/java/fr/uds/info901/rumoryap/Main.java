package fr.uds.info901.rumoryap;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

import fr.uds.info901.rumoryap.rumor.BelieverActivist;

public class Main {
	private static final int NB_PERSONNE = 50;
	private static final double FRIEND_COEF = 0.5;
	private static final String SEPARATOR = "<->";
	private static final String STYLE = "ui.style";
	
	private static List<Personne> getInitialGraph(){
		List<Personne> network = new ArrayList<Personne>();
		
		double nodeNumber = Math.random()*NB_PERSONNE;
		for(int i = 0; i<=nodeNumber; ++i){
			network.add(new Personne());
		}
		
		for(Personne personne : network){
			for(Personne personne2 : network){
				if(!personne.equals(personne2)){
					if(Math.random()<FRIEND_COEF){						
						personne.addFriend(personne2);
						personne2.addFriend(personne);
					}
				}
			}
		}
		
		return network;
	}
	
	private static String createEdgeName(Personne p1, Personne p2){
		if(p1.getName()>p2.getName()){
			return p1+SEPARATOR+p2;
		}
		return p2+SEPARATOR+p1;
	}
	
	public static void updateGraph(Graph graph, List<Personne> network){
		for (Personne personne : network) {
			graph.getNode(personne.getName()+"").setAttribute(STYLE, personne.getColorInGraph());
			if(personne.isInfected())
			{
				for(SocialLink socialLink : personne.getFriendList())
				{
					Personne friendPersonne = socialLink.getFriend();
					if(friendPersonne.isInfected())
					{
						if(graph.getEdge(createEdgeName(personne,friendPersonne))!=null)
						{
							graph.getEdge(createEdgeName(personne,friendPersonne)).setAttribute(STYLE, "fill-color: rgb(255,0,0);");
						}
					}
					else
					{
						if (createEdgeName(personne,friendPersonne)!=null)
						{
							graph.getEdge(createEdgeName(personne,friendPersonne)).setAttribute(STYLE, "fill-color: rgb(0,255,0);");
						}
					}
				}
			}	
		}
	}
	
	public static void main(String[] args){
		List<Personne> network = Main.getInitialGraph();
		Graph graph = new SingleGraph("Rumor");
    	
		for(Personne personne : network){
			graph.addNode(personne.getName()+"").setAttribute(STYLE, personne.getColorInGraph());;
		}
		for(Personne personne : network){
			for(SocialLink socialLink : personne.getFriendList()){
				Personne friendPersonne = socialLink.getFriend();
				if(graph.getEdge(createEdgeName(personne,friendPersonne))==null){
					graph.addEdge(createEdgeName(personne,friendPersonne),personne.getName()+"",friendPersonne.getName()+"");
				}
			}
		}

		graph.display();
		
		network.get(0).getRumor().setRumorState(new BelieverActivist());
		network.get(0).spread();
		
		Scanner scanner = new Scanner(System.in);
		String line = scanner.nextLine();
		while(!line.contains("exit")){
			
			for(Personne personne : network){
				personne.spread();
			}
			
			updateGraph(graph, network);
			line = scanner.nextLine();
		}
		scanner.close();
		System.out.println(Personne.freq);
	}
}










