package it.polito.tdp.exam.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.util.Pair;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.exam.db.BaseballDAO;


public class Model {
	

	private BaseballDAO dao;
	private Graph<Integer, DefaultWeightedEdge> grafo;
//	private Map<String, User> userMap;
	private List<Team> teamList;
	
	public Model() {
		this.dao = new BaseballDAO();
		
//		this.userMap=new HashMap<>();
		this.teamList = this.dao.readAllTeams();
//		for(User i : userList) {
//			this.userMap.put(i.getUserId(), i);
//		}
		
	}
	

	public void creaGrafo(String team) {
		// TODO Auto-generated method stub

	this.grafo = new SimpleWeightedGraph<Integer, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
	// Aggiunta VERTICI 
	List<Integer> vertici=new LinkedList<>();
	
	vertici.addAll(this.dao.getAnniPerTeam(team));
	
	Graphs.addAllVertices(this.grafo, vertici);

	
	// Aggiunta ARCHI
	
	for (Integer v1 : vertici) {
		for (Integer v2 : vertici) {
			if(v1!=v2){ 
		      this.grafo.addEdge(v1,v2);
		      this.grafo.setEdgeWeight(this.grafo.getEdge(v1, v2), this.dao.getPeso(team, v1, v2));
			}
			}
			}

	}

public int nVertici() {
	return this.grafo.vertexSet().size();
}

public int nArchi() {
	return this.grafo.edgeSet().size();
}

public Set<Integer> getVertici(){
	
	Set<Integer> vertici=this.grafo.vertexSet();
	
	return vertici;
}

public Set<DefaultWeightedEdge> getArchi(){
	
	Set<DefaultWeightedEdge> archi=this.grafo.edgeSet();
	
	return archi;
}

public int getPesoArco(Integer v1, Integer v2){
	
	int peso;
	
	peso=(int) (this.grafo.getEdgeWeight(this.grafo.getEdge(v1, v2)));
	
	return peso;
}

public List<Pair<Integer, Double>> gPA(Integer v1) {
	
	List<Pair<Integer, Double>> ris= new LinkedList<>();
	
	for(DefaultWeightedEdge i : this.grafo.edgesOf(v1)) {
		if(this.grafo.getEdgeSource(i)!=v1) {
		ris.add(new Pair<Integer, Double>(this.grafo.getEdgeSource(i) ,this.grafo.getEdgeWeight(i)));}
		if(this.grafo.getEdgeTarget(i)!=v1) {
			ris.add(new Pair<Integer, Double>(this.grafo.getEdgeTarget(i) ,this.grafo.getEdgeWeight(i)));}
	}
	return ris;
		
}

public List<Set<Integer>> getComponente() {
	ConnectivityInspector<Integer, DefaultWeightedEdge> ci = new ConnectivityInspector<>(this.grafo) ;
	return ci.connectedSets() ;
}

public List<String> getNomiTeams(){
	
	List<String> result = new LinkedList<>();
	
	for (Team i : teamList) {
		if(!result.contains(i.getName())) {
			result.add(i.getName());
		}
		
	}
	
	Collections.sort(result);
	
	return result;
}

}
