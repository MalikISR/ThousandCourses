package com.example.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM favorites")
    suspend fun getFavorites(): List<FavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(f: FavoriteEntity)

    @Delete
    suspend fun removeFavorite(f: FavoriteEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE id = :id)")
    suspend fun isFavorite(id: Int): Boolean
}