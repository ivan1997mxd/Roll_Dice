package sheridan.tongche.assignment2.ui.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface DiceDao {
    @Insert
    suspend fun insert(dicedata: Dicedata): Long

    @Query("SELECT * FROM dicedata WHERE id=:key")
    fun get(key: Long) : LiveData<Dicedata>

    @Query("SELECT * FROM dicedata ORDER BY id")
    fun getAll() : LiveData<List<Dicedata>>

    @Delete
    suspend fun delete(envelope: Dicedata)

    @Query("DELETE FROM dicedata WHERE id=:key")
    suspend fun delete(key: Long)

    @Query("DELETE FROM dicedata")
    suspend fun deleteAll()
}