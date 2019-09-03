package com.arbol.test;

import com.arbol.dto.Arbol;
import com.arbol.dto.Nodo;

public class Test {

	public static void main(String[] args) {
		Test t = new Test();
		

		String cadenaEntrada = "67,39,28,29,44,76,74,85,83,87";//
		String[] nodos = cadenaEntrada.split(",");
	  Arbol arbol = new Arbol(Integer.parseInt(nodos[0]));
	  for (int i = 1; i < nodos.length; i++) {
		  Nodo nodo = new Nodo(Integer.parseInt(nodos[i]));
		arbol.addNodo(nodo);
	}
	  System.out.println(cadenaEntrada);
	  t.preorden(arbol.getRaiz());
	  
	  Nodo a = new Nodo(29);
	  Nodo b = new Nodo(44);
	  Nodo z = t.LowestCommonAncestor(arbol.getRaiz(), a, b);
	  System.out.println("LowestCommonAncestor(tree, 29, 44)" + z.getValor());
	  
	  a = new Nodo(44);
	  b = new Nodo(85);
	  z = t.LowestCommonAncestor(arbol.getRaiz(), a, b);
	  System.out.println("LowestCommonAncestor(tree, 44, 85)"+z.getValor());
	  
	  a = new Nodo(83);
	  b = new Nodo(87);
	  z = t.LowestCommonAncestor(arbol.getRaiz(), a, b);
	  System.out.println("LowestCommonAncestor(tree, 83, 87)"+z.getValor());
		
	}
	
	public void preorden(Nodo nodo) {
		if(nodo==null)
			return;
		System.out.println("Nodo "+ nodo.getValor());
		preorden(nodo.getHojaIzquierda());
		preorden(nodo.getHojaDerecha());
	}
	
	
	public  Nodo LowestCommonAncestor(Nodo raiz, Nodo a, Nodo b) {
		   if (raiz == null) {
		       return null;
		   }
		   if (raiz.getValor() == a.getValor() || raiz.getValor() == b.getValor()) {
		       return raiz;
		   }

		   Nodo izq = LowestCommonAncestor(raiz.getHojaIzquierda(), a, b);
		   Nodo der = LowestCommonAncestor(raiz.getHojaDerecha(), a, b);

		   if (izq != null && der != null) {
		      return raiz;
		   }

		   return (izq != null) ? izq : der; 
		}

}
