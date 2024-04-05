package com.wamcdevs.habitsjcapp.home.data.util
import java.lang.reflect.Type

interface JsonParser {
    // Esta interfaz contendra dos funciones para convertir

    // En esta funcion obtenemos un objeto apartir de una cadena Json,

    //              donde recibe Una cadena de texto JSON que se desea convertir en un objeto

    //                           Un objeto de tipo Type que se utiliza para indicar el tipo del objeto
    //                           al que se debe convertir la cadena JSON.

    //                                       La función intenta analizar la cadena JSON (json) y
    //                                       convertirla en un objeto del tipo especificado (type).
    //                                       El resultado de la conversión se devuelve como un objeto
    //                                       de tipo T (genérico).
    fun <T> fromJson(json:String, type:Type): T?


    //Esta función realiza la operación inversa, obtenemos una cadena Json apartir de un objeto.
    // Toma dos parámetros:

    //              obj: El objeto que se desea convertir en una cadena JSON.

    //                          type: Un objeto de tipo Type que indica el tipo del objeto que se
    //                          va a convertir en JSON.

    //                                  La función toma el objeto y lo convierte en una cadena JSON
    //                                  que representa el objeto en ese formato.

    //                                      El resultado se devuelve como una cadena JSON en forma de texto.
    //                                      También se utiliza un tipo nullable (String?) porque, en algunas
    //                                      circunstancias, la conversión podría fallar y, en ese caso,
    //                                      la función devuelve null.
    //                                      El otro sera encargado de recibir nuestro objeto y el tipo,
    //                                      y devolvera un String json que puede ser null
    fun <T> toJson(obj:T, type: Type):String?



}