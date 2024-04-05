package com.wamcdevs.habitsjcapp.home.data.util

import com.google.gson.Gson
import java.lang.reflect.Type

class GsonParser(private val gson: Gson) : JsonParser {
    // Constructor que toma una instancia de Gson
    // Esta instancia de Gson se utilizará para realizar las conversiones JSON
    // Se inyecta a través del constructor al crear una instancia de la clase GsonParser
    // Esto es una técnica de inyección de dependencias que permite una mayor flexibilidad
    // y reutilización del código.

    // En esta clase implementamos las funciones creadas en JsonParser

    // Aquí, estamos implementando la función fromJson de la interfaz JsonParser.
    // Esta función toma una cadena JSON (json) y un objeto de tipo Type (type).
    // Utiliza la instancia de Gson inyectada para convertir la cadena JSON en un objeto del tipo especificado.
    // La función gson.fromJson(json, type) realiza la conversión y devuelve el resultado como un objeto del tipo T.
    // El resultado es nullable (T?) porque la conversión podría fallar si la cadena JSON no es válida.
    override fun <T> fromJson(json: String, type: Type): T? {
        return gson.fromJson(json, type)
    }

    // Aquí, estamos implementando la función toJson de la interfaz JsonParser.
    // Esta función toma un objeto (obj) y un objeto de tipo Type (type).
    // Utiliza la instancia de Gson inyectada para convertir el objeto en una cadena JSON.
    // La función gson.toJson(obj, type) realiza la conversión y devuelve el resultado como una cadena JSON en forma de texto.
    // El resultado es nullable (String?) porque, en algunas circunstancias, la conversión podría fallar.
    override fun <T> toJson(obj: T, type: Type): String? {
        return gson.toJson(obj, type)
    }
}