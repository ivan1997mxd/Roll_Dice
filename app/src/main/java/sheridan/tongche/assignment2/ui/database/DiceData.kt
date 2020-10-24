package sheridan.tongche.assignment2.ui.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "dicedata")
data class Dicedata (
    @PrimaryKey(autoGenerate = true)
    var id: Long,

    @ColumnInfo(name = "dice_one")
    val dice_one: Int,

    @ColumnInfo(name = "dice_two")
    val dice_two: Int,

    @ColumnInfo(name = "dice_three")
    val dice_three: Int,

    @ColumnInfo(name = "sum")
    val sum: Int
)