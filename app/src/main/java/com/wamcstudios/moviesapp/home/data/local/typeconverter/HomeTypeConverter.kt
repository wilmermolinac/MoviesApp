package com.wamcstudios.moviesapp.home.data.local.typeconverter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.wamcdevs.habitsjcapp.home.data.util.JsonParser

@ProvidedTypeConverter
class HomeTypeConverter(val jsonParser: JsonParser) {

    // Este metodo s utiliza para convertir la lista de GenreIds a un string
    @TypeConverter
    fun fromGenreIds(genreIds: List<Int>): String {
        return jsonParser.toJson(genreIds, object : TypeToken<ArrayList<Int>>() {}.type) ?: "[]"
    }

    @TypeConverter
    fun toGenreIds(json: String): List<Int> {

        // jsonParser.fromJson(...) Se utiliza para convertir la cadena JSON en una lista de enteros.
        // La función fromJson analiza la cadena JSON y la convierte en una lista de objetos de tipo Int.
        return jsonParser.fromJson<ArrayList<Int>>(
            json = json,
            // object : TypeToken<ArrayList<Int>>() {}.type: Se utiliza un TypeToken para especificar el
            // tipo que se va a convertir, que es una lista de enteros (List<Int>).
            type = object : TypeToken<ArrayList<Int>>() {}.type
        ) ?: emptyList()

        //?: emptyList(): Esto es un operador de elvis (?:) que significa que, si la conversión desde JSON
        // a una lista de enteros falla (por ejemplo, si la cadena JSON es nula o no se puede analizar),
        // se devuelve una lista vacía (emptyList()).
    }


    @TypeConverter
    fun fromOriginCountry(originCountry: List<String>): String {
        return jsonParser.toJson(
            originCountry,
            object : TypeToken<ArrayList<String>>() {}.type
        ) ?: "[]"

    }

    @TypeConverter
    fun toOriginCountry(json: String): List<String> {
        return jsonParser.fromJson<ArrayList<String>>(
            json = json, type = object : TypeToken<ArrayList<String>>() {}.type
        ) ?: emptyList()
    }

}