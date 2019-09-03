package com.arbol.api;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.arbol.dto.Arbol;
import com.arbol.dto.ArbolEntrada;
import com.arbol.dto.Nodo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ApplicationPath("/")
@Path("/serviciosarbolbinario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ArbolBinarioRest extends Application {

	Arbol arbol;
	
	
	@POST
	  public Response crearArbol(ArbolEntrada arbolEntrada) {
		
		String cadenaEntrada = arbolEntrada.getArbolEntrada();
			String[] nodos = cadenaEntrada.split(",");
		  arbol = new Arbol(Integer.parseInt(nodos[0]));
		  for (int i = 1; i < nodos.length; i++) {
			  Nodo nodo = new Nodo(Integer.parseInt(nodos[i]));
			arbol.addNodo(nodo);
		}

		  
		  ObjectMapper mapper = new ObjectMapper();
		  String jsonString="";
		try {
			jsonString = mapper.writeValueAsString("Se creo el arbol binario");
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	    return Response.ok(jsonString,MediaType.APPLICATION_JSON).build();

	  }
	
	
	
	
	
	@PUT
	public Response LowestCommonAncestor(ArbolEntrada arbolEntrada, @QueryParam("nodoa") String nodoA, @QueryParam("nodob") String nodoB) {
		String cadenaEntrada = arbolEntrada.getArbolEntrada();
		String[] nodos = cadenaEntrada.split(",");
	  arbol = new Arbol(Integer.parseInt(nodos[0]));
	  for (int i = 1; i < nodos.length; i++) {
		  Nodo nodo = new Nodo(Integer.parseInt(nodos[i]));
		arbol.addNodo(nodo);
	}
	  
		Nodo a = new Nodo(Integer.parseInt(nodoA));
		Nodo b = new Nodo(Integer.parseInt(nodoB));
		Nodo nodo = LowestCommonAncestor(arbol.getRaiz(), a, b);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString="";
		try {
			if(nodo!=null)
				jsonString = mapper.writeValueAsString(String.valueOf(nodo.getValor()));
			else
				jsonString = mapper.writeValueAsString("los nodos no existen");
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return Response.ok(jsonString,MediaType.APPLICATION_JSON).build();
	}
	
	
	private  Nodo LowestCommonAncestor(Nodo raiz, Nodo a, Nodo b) {
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
