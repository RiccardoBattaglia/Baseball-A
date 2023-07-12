package it.polito.tdp.exam.model;

import java.util.Objects;

public class Dettaglio implements Comparable<Dettaglio> {
	
	int anno;
	double peso;
	public Dettaglio(int anno, double peso) {
		super();
		this.anno = anno;
		this.peso = peso;
	}
	public int getAnno() {
		return anno;
	}
	public void setAnno(int anno) {
		this.anno = anno;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	@Override
	public int hashCode() {
		return Objects.hash(anno, peso);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dettaglio other = (Dettaglio) obj;
		return anno == other.anno && peso == other.peso;
	}
	@Override
	public int compareTo(Dettaglio o) {
		// TODO Auto-generated method stub
		return Double.compare(o.peso, this.peso);
	}
	
	

}
