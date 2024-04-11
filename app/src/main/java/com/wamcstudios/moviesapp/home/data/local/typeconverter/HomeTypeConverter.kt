package com.wamcstudios.moviesapp.home.data.local.typeconverter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.wamcdevs.habitsjcapp.home.data.util.JsonParser
import com.wamcstudios.moviesapp.home.domain.model.BelongsToCollection
import com.wamcstudios.moviesapp.home.domain.model.CreatedBy
import com.wamcstudios.moviesapp.home.domain.model.Genre
import com.wamcstudios.moviesapp.home.domain.model.KnownFor
import com.wamcstudios.moviesapp.home.domain.model.LastEpisodeToAir
import com.wamcstudios.moviesapp.home.domain.model.Network
import com.wamcstudios.moviesapp.home.domain.model.NextEpisodeToAir
import com.wamcstudios.moviesapp.home.domain.model.ProductionCompany
import com.wamcstudios.moviesapp.home.domain.model.ProductionCountry
import com.wamcstudios.moviesapp.home.domain.model.Season
import com.wamcstudios.moviesapp.home.domain.model.SpokenLanguage

@ProvidedTypeConverter
class HomeTypeConverter(val jsonParser: JsonParser) {

    // Este metodo s utiliza para convertir la lista de GenreIds a un string
    /*@TypeConverter
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
    }*/


    @TypeConverter
    fun fromIntegerList(list: List<Int>): String {
        return jsonParser.toJson(list, object : TypeToken<ArrayList<Int>>() {}.type) ?: "[]"
    }

    @TypeConverter
    fun toIntegerList(json: String): List<Int> {

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

    /*fun fromStringList(list: List<String>): String {
        return jsonParser.toJson(
            list,
            object : TypeToken<ArrayList<String>>() {}.type
        ) ?: "[]"

    }

    @TypeConverter
    fun toStringList(json: String): List<String> {
        return jsonParser.fromJson<ArrayList<String>>(
            json = json, type = object : TypeToken<ArrayList<String>>() {}.type
        ) ?: emptyList()
    }*/

    @TypeConverter
    fun fromKnownFor(KnownFor: List<KnownFor>): String {

        return jsonParser.toJson(KnownFor, object : TypeToken<ArrayList<KnownFor>>() {}.type)
            ?: "[]"
    }

    @TypeConverter
    fun toKnownFor(json: String): List<KnownFor> {
        return jsonParser.fromJson<ArrayList<KnownFor>>(
            json = json, type = object : TypeToken<ArrayList<KnownFor>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun fromBelongsToCollection(belongsToCollection: BelongsToCollection?): String? {
        return jsonParser.toJson(
            obj = belongsToCollection,
            type = object : TypeToken<BelongsToCollection>() {}.type
        )
    }

    @TypeConverter
    fun toBelongsToCollection(json: String): BelongsToCollection? {
        return jsonParser.fromJson<BelongsToCollection>(
            json = json,
            type = object : TypeToken<BelongsToCollection>() {}.type
        ) //?: BelongsToCollection(backdropPath = "", id = 0, name = "", posterPath = "")
    }

    @TypeConverter
    fun fromGenresModelListDetail(genresModelListDetail: List<Genre>): String {
        return jsonParser.toJson(
            genresModelListDetail,
            type = object : TypeToken<ArrayList<Genre>>() {}.type
        ) ?: "[]"
    }


    @TypeConverter
    fun toGenresModelListDetail(json: String): List<Genre> {
        return jsonParser.fromJson<ArrayList<Genre>>(
            json,
            type = object : TypeToken<ArrayList<Genre>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun fromProductionCompaniesDetail(productionCompaniesDetail: List<ProductionCompany>): String {
        return jsonParser.toJson(
            productionCompaniesDetail,
            type = object : TypeToken<ArrayList<ProductionCompany>>() {}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun toProductionCompaniesDetail(json: String): List<ProductionCompany> {
        return jsonParser.fromJson<ArrayList<ProductionCompany>>(
            json,
            type = object : TypeToken<ArrayList<ProductionCompany>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun fromProductionCountriesDetail(productionCountriesDetail: List<ProductionCountry>): String {
        return jsonParser.toJson(
            productionCountriesDetail,
            type = object : TypeToken<ArrayList<ProductionCountry>>() {}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun toProductionCountriesDetail(json: String): List<ProductionCountry> {
        return jsonParser.fromJson<ArrayList<ProductionCountry>>(
            json,
            type = object : TypeToken<ArrayList<ProductionCountry>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun fromSpokenLanguageDetail(spokenLanguageDetail: List<SpokenLanguage>): String {
        return jsonParser.toJson(
            spokenLanguageDetail,
            type = object : TypeToken<ArrayList<SpokenLanguage>>() {}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun toSpokenLanguageDetail(json: String): List<SpokenLanguage> {
        return jsonParser.fromJson<ArrayList<SpokenLanguage>>(
            json,
            type = object : TypeToken<ArrayList<SpokenLanguage>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun fromCreatedByTvDetail(createdByTvDetail: List<CreatedBy>): String {
        return jsonParser.toJson(
            createdByTvDetail,
            type = object : TypeToken<ArrayList<CreatedBy>>() {}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun toCreatedByTvDetail(json: String): List<CreatedBy> {
        return jsonParser.fromJson<ArrayList<CreatedBy>>(
            json,
            type = object : TypeToken<ArrayList<CreatedBy>>() {}.type
        ) ?: emptyList()
    }

    /*@TypeConverter
    fun fromEpisodeRunTimeTvDetail(episodeRunTimeTvDetail: List<Int>): String {
        return jsonParser.toJson(
            episodeRunTimeTvDetail,
            type = object : TypeToken<ArrayList<Int>>() {}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun toEpisodeRunTimeTvDetail(json: String): List<Int> {
        return jsonParser.fromJson<ArrayList<Int>>(
            json,
            type = object : TypeToken<ArrayList<Int>>() {}.type
        ) ?: emptyList()
    }*/

    /*@TypeConverter
    fun fromLanguagesTvDetail(languagesTvDetail: List<String>): String {
        return jsonParser.toJson(
            languagesTvDetail,
            type = object : TypeToken<ArrayList<String>>() {}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun toLanguagesTvDetail(json: String): List<String> {
        return jsonParser.fromJson<ArrayList<String>>(
            json,
            type = object : TypeToken<ArrayList<String>>() {}.type
        ) ?: emptyList()
    }*/

    @TypeConverter
    fun fromLastEpisodeToAirTvDetail(lastEpisodeToAirTvDetail: LastEpisodeToAir?): String? {
        return jsonParser.toJson(
            lastEpisodeToAirTvDetail,
            type = object : TypeToken<LastEpisodeToAir>() {}.type
        )
    }

    @TypeConverter
    fun toLastEpisodeToAirTvDetail(json: String): LastEpisodeToAir? {
        return jsonParser.fromJson<LastEpisodeToAir>(
            json,
            type = object : TypeToken<LastEpisodeToAir>() {}.type
        ) /*?: LastEpisodeToAir(
            airDate = "",
            episodeNumber = 0,
            episodeType = "",
            id = 0,
            name = "",
            overview = "",
            productionCode = "",
            runtime = 0,
            seasonNumber = 0,
            showId = 0,
            stillPath = "",
            voteCount = 0,
            voteAverage = 0
        )*/
    }

    @TypeConverter
    fun fromNetworksTvDetail(networksTvDetail: List<Network>): String {
        return jsonParser.toJson(
            networksTvDetail,
            type = object : TypeToken<ArrayList<Network>>() {}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun toNetworksTvDetail(json: String): List<Network> {
        return jsonParser.fromJson<ArrayList<Network>>(
            json,
            type = object : TypeToken<ArrayList<Network>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun fromNextEpisodeToAirTvDetail(nextEpisodeToAirTvDetail: NextEpisodeToAir?): String? {
        return jsonParser.toJson(
            nextEpisodeToAirTvDetail,
            type = object : TypeToken<NextEpisodeToAir>() {}.type
        )
    }

    @TypeConverter
    fun toNextEpisodeToAirTvDetail(json: String): NextEpisodeToAir? {
        return jsonParser.fromJson(json, type = object : TypeToken<NextEpisodeToAir>() {}.type)
    }

    @TypeConverter
    fun fromSeasonsTvDetail(seasonsTvDetail: List<Season>): String {
        return jsonParser.toJson(
            seasonsTvDetail,
            type = object : TypeToken<ArrayList<Season>>() {}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun toSeasonsTvDetail(json: String): List<Season> {
        return jsonParser.fromJson<ArrayList<Season>>(
            json,
            type = object : TypeToken<ArrayList<Season>>() {}.type
        ) ?: emptyList()
    }

}