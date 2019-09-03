package com.arbol.dto;

public class Arbol {

	/* Atributos */
	private Nodo raiz;

	/* Contructores */
	public Arbol(int valor) {
		this.raiz = new Nodo(valor);
	}

	public Arbol(Nodo raiz) {
		this.raiz = raiz;
	}

	private void addNodo( Nodo nodo, Nodo raiz ) {

        if ( raiz == null ) {
   
            this.setRaiz(nodo);
        }
        else {
            
            if ( nodo.getValor() <= raiz.getValor() ) {
                
                if (raiz.getHojaIzquierda() == null) {
                    raiz.setHojaIzquierda(nodo);
                }
                else {
                    addNodo( nodo , raiz.getHojaIzquierda() );
                }
            }
            else {
                
                if (raiz.getHojaDerecha() == null) {
                    raiz.setHojaDerecha(nodo);
                }
                else {
                    addNodo( nodo, raiz.getHojaDerecha() );
                }
            }
        }
    }

	public void addNodo(Nodo nodo) {
		this.addNodo(nodo, this.raiz);
	}

	/* Setters y Getters */
	public Nodo getRaiz() {
		return raiz;
	}

	public void setRaiz(Nodo raiz) {
		this.raiz = raiz;
	}

}