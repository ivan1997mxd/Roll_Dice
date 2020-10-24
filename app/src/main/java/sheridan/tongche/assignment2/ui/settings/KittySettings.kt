package sheridan.tongche.assignment2.ui.settings

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import sheridan.tongche.assignment2.R


class KittySettings(private val context: Context){

    private val preferences: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    val urgent: Boolean
        get() = preferences.getBoolean("urgent", true)

    val messageText: String
        get(){
            val mew = context.getString(R.string.cat_mew)
            return preferences.getString("message", mew) ?: mew
        }

    val confirmDelete: Boolean
        get() = preferences.getBoolean("confirm_delete", true)

    val confirmClear: Boolean
        get() = preferences.getBoolean("confirm_clear", true)
}