package com.arbol.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.arbol.dto.Arbol;
import com.arbol.dto.ArbolEntrada;
import com.arbol.dto.Nodo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Path("/serviciosarbolbinario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ArbolBinarioRest {

	Arbol arbol;
	
	/*
	 * 
	 {
    "arbolEntrada":  "67,39,28,29,44,76,74,85,83,87"
    
}
	 */
	@POST
	  public Response crearArbol(ArbolEntrada arbolEntrada) {
		
		String cadenaEntrada = arbolEntrada.getArbolEntrada();
			String[] nodos = cadenaEntrada.split(",");
		  Arbol arbol = new Arbol(Integer.parseInt(nodos[0]));
		  for (int i = 1; i < nodos.length; i++) {
			  Nodo nodo = new Nodo(Integer.parseInt(nodos[i]));
			arbol.addNodo(nodo);
		}
		  System.out.println(cadenaEntrada);
		  preorden(arbol.getRaiz());
		  
		  ObjectMapper mapper = new ObjectMapper();
		  String jsonString="";
		try {
			jsonString = mapper.writeValueAsString("Se creo el arbol binario");
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	    return Response.ok(jsonString,MediaType.APPLICATION_JSON).build();

	  }
	
	
	public void preorden(Nodo nodo) {
		if(nodo==null)
			return;
		preorden(nodo.getHojaIzquierda());
		preorden(nodo.getHojaDerecha());
	}
	
	
	@GET
	
	public Response LowestCommonAncestor(@QueryParam("nodoa") String nodoA, @QueryParam("nodob") String nodoB) {
		Nodo a = new Nodo(Integer.parseInt(nodoA));
		Nodo b = new Nodo(Integer.parseInt(nodoB));
		Nodo nodo = LowestCommonAncestor(arbol.getRaiz(), a, b);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString="";
		try {
			jsonString = mapper.writeValueAsString(nodo);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return Response.ok(jsonString,MediaType.APPLICATION_JSON).build();
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
