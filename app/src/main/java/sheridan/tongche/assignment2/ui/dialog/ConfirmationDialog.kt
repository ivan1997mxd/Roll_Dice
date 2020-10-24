package sheridan.tongche.assignment2.ui.dialog

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import sheridan.tongche.assignment2.ui.dialog.ConfirmationDialog.Companion.CONFIRMATION_RESULT
import java.io.Serializable

class ConfirmationDialog : DialogFragment() {
    companion object{
        const val CONFIRMATION_RESULT = "confirmation_result"
    }

    private val safeArgs: ConfirmationDialogArgs by navArgs()

    private lateinit var navController: NavController

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        navController = findNavController()

        return AlertDialog.Builder(requireContext()).apply {
            setTitle("Roll Dice")
            setMessage(safeArgs.message)
            setPositiveButton(android.R.string.ok){ _, _ -> confirmed()}
            setNegativeButton(android.R.string.cancel, null)
        }.create()
    }

    private fun confirmed(){
        val savedStateHandle = navController.previousBackStackEntry?.savedStateHandle
        savedStateHandle?.set(
            CONFIRMATION_RESULT, ConfirmationResult(safeArgs.requestCode, Activity.RESULT_OK)
        )
    }
    data class ConfirmationResult(val requestCode: Int, val resultCode: Int): Serializable
}