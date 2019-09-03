# Prueba Técnica

### API REST que permite:

1. Crear un arbol binario.
2. Dado un árbol binario y dos nodos retornar el ancestro común más cercano.

Implementacion de un API REST con JAX-RS


### Requerimientos

Eclipse 2019-06 https://www.eclipse.org/downloads/
Wildfly 11 https://wildfly.org/downloads/


### Uso de la aplicación

Para hacer uso del API, abrir el proyecto con eclipse y ejecutar la clase ArbolBinarioRest dentro del paquete com.arbol.api 


### Endpoints

**Crear árbol**

	[POST]
	http://localhost:8080/arbolBinario-1/serviciosarbolbinario
	
	JSON de entrada
	{
		"arbolEntrada":  "67,39,28,29,44,76,74,85,83,87"
    }


**Consultar ancestro comun mas cercano**

Pasar por parametro en la url nodoa y nodob

	[PUT]
	http://localhost:8080/arbolBinario-1/serviciosarbolbinario?nodoa=29&nodob=44
	
	JSON de entrada
	{
		"arbolEntrada":  "67,39,28,29,44,76,74,85,83,87"
    }