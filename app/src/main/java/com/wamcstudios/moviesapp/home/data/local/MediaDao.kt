package com.wamcstudios.moviesapp.home.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.wamcstudios.moviesapp.home.data.local.entity.GenreEntity
import com.wamcstudios.moviesapp.home.data.local.entity.MediaEntity

@Dao
interface MediaDao {


    /*
    * upsertMediaList: El término “upsert” es una combinación de “update” y “insert”.
    * Si la entidad ya existe en la base de datos (basándose en la clave primaria), entonces se
    * actualiza. Si no existe, se inserta. La anotación @Upsert indica que este método realiza una
    * operación de “upsert”.
    * */
    @Upsert
    suspend fun upsertMediaList(mediaList: List<MediaEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMediaList(mediaList: List<MediaEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMediaItem(mediaEntity: MediaEntity)

    @Update
    suspend fun updateMediaItem(mediaEntity: MediaEntity)

    @Query("SELECT * FROM tb_mediaentity WHERE id = :id")
    suspend fun getMediaById(id: Int): MediaEntity

    @Query("SELECT * FROM tb_mediaentity WHERE mediaType = :mediaType AND mediaCategory = :category")
    suspend fun getMediaListByTypeAndCategory(
        mediaType: String,
        category: String,
    ): List<MediaEntity>

    @Query("UPDATE tb_mediaentity SET isFavorite = :isFavorite WHERE id = :mediaId")
    suspend fun updateMediaFavorite(mediaId: Int, isFavorite: Boolean)

    /*@Query(
        """
            DELETE FROM tb_mediaentity 
            WHERE mediaType = :mediaType AND mediaType = :mediaCategory
        """
    )
    suspend fun deleteMediaByTypeAndCategory(mediaType: String, mediaCategory: String)*/


    @Query(
        """
        DELETE FROM tb_mediaentity 
        WHERE mediaType = :mediaType AND mediaType = :mediaCategory AND isFavorite = :isFavorite
    """
    )
    suspend fun deleteMediaByTypeAndCategory(
        mediaType: String,
        mediaCategory: String,
        isFavorite: Boolean,
    )


    @Query(
        """
            SELECT * 
            FROM tb_mediaentity 
            WHERE mediaCategory = :category
        """
    )
    suspend fun getTrendingMediaList(category: String): List<MediaEntity>

    @Query("DELETE FROM tb_mediaentity WHERE mediaCategory = :category AND isFavorite = :isFavorite")
    suspend fun deleteTrendingMedia(category: String, isFavorite: Boolean)


    @Upsert
    suspend fun upsertGenres(genres: List<GenreEntity>)

    @Query("SELECT * FROM tb_genre")
    suspend fun getAllGenres(): List<GenreEntity>

    @Query("DELETE FROM tb_genre")
    suspend fun deleteAllGenres()

}