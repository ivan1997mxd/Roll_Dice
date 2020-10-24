package sheridan.tongche.assignment2.ui.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import sheridan.tongche.assignment2.ui.database.DiceDao
import sheridan.tongche.assignment2.ui.database.DiceDataBase
import sheridan.tongche.assignment2.ui.database.Dicedata

class HistoryViewModel(application: Application) : AndroidViewModel(application){
    private val diceDao: DiceDao = DiceDataBase.getInstance(application).diceDao

    val history: LiveData<List<Dicedata>> = diceDao.getAll()

    fun clear(){
        viewModelScope.launch {
            diceDao.deleteAll()
        }
    }
}