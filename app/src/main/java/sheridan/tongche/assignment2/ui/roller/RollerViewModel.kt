package sheridan.tongche.assignment2.ui.roller

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import sheridan.tongche.assignment2.ui.database.DiceDao
import sheridan.tongche.assignment2.ui.database.DiceDataBase
import sheridan.tongche.assignment2.ui.database.Dicedata

class RollerViewModel(application: Application) : AndroidViewModel(application){
    enum class Status { NEW_DATA, SAVED_DATA}
    data class State(val status: Status, val diceID: Long?)

    companion object{
        val INITIAL_SATE: State = State(Status.NEW_DATA, null)
    }

    private val _state = MutableLiveData(INITIAL_SATE)
    val state: LiveData<State> = _state

    private val diceDao: DiceDao = DiceDataBase.getInstance(application).diceDao

    fun save(dicedata: Dicedata){
        viewModelScope.launch {
            val diceID: Long = diceDao.insert(dicedata)
            _state.value = State(Status.SAVED_DATA, diceID)
        }
    }

    fun reset(){
        _state.value = INITIAL_SATE
    }

}