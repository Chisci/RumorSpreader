package fr.uds.info901.rumoryap;

import java.util.ArrayList;
import java.util.List;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

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
	public static void main(String[] args){
		List<Personne> network = Main.getInitialGraph();
		Graph graph = new SingleGraph("Rumor");
    	
		for(Personne personne : network){
			graph.addNode(personne.getName()).setAttribute(STYLE, personne.getColorInGraph());;
		}
		for(Personne personne : network){
			for(SocialLink socialLink : personne.getFriendList()){
				Personne friendPersonne = socialLink.getFriend();
				if((graph.getEdge(personne.getName()+SEPARATOR+friendPersonne.getName())==null)&&(graph.getEdge(friendPersonne.getName()+SEPARATOR+personne.getName())==null)){
					graph.addEdge(personne.getName()+SEPARATOR+friendPersonne.getName(),personne.getName(),friendPersonne.getName());
				}
			}
		}
		
		for (Personne personne : network) {
			graph.getNode(personne.getName()).setAttribute(STYLE, personne.getColorInGraph());
			if(personne.isInfected())
			{
				for(SocialLink socialLink : personne.getFriendList())
				{
					Personne friend = socialLink.getFriend();
					if(friend.isInfected())
					{
						if(graph.getEdge(personne.getName()+friend.getName())!=null)
						{
							graph.getEdge(personne.getName()+friend.getName()).setAttribute(STYLE, "fill-color: rgb(255,0,0);");
						}
						else
						{
							graph.getEdge(friend.getName()+personne.getName()).setAttribute(STYLE, "fill-color: rgb(255,0,0);");
						}
					}
					else
					{
						if (graph.getEdge(personne.getName()+friend.getName())!=null)
						{
							graph.getEdge(personne.getName()+friend.getName()).setAttribute(STYLE, "fill-color: rgb(0,255,0);");
						}
						else
						{
							graph.getEdge(friend.getName()+personne.getName()).setAttribute(STYLE, "fill-color: rgb(0,255,0);");
						}
					}
				}
			}
			
		}
		
		graph.display();
	}
}










