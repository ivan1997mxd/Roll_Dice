package sheridan.tongche.assignment2.ui.database

import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [Dicedata::class], version = 1, exportSchema = false)
abstract class DiceDataBase: RoomDatabase() {
    abstract val diceDao: DiceDao

    companion object{
        @Volatile
        private var INSTANCE: DiceDataBase? = null

        fun getInstance(context: Context): DiceDataBase{
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DiceDataBase::class.java,
                        "dice_database"
                    ).fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}