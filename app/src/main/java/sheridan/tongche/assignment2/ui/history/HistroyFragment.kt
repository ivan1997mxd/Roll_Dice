package sheridan.tongche.assignment2.ui.history

import android.app.Activity
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import sheridan.tongche.assignment2.R
import sheridan.tongche.assignment2.databinding.FragmentHistroyBinding
import sheridan.tongche.assignment2.ui.database.Dicedata
import sheridan.tongche.assignment2.ui.dialog.ConfirmationDialog
import sheridan.tongche.assignment2.ui.settings.KittySettings

class HistroyFragment : Fragment() {
    companion object {
        const val CONFIRM_CLEAR: Int = 2
    }

    private lateinit var binding: FragmentHistroyBinding

    private lateinit var adapter: HistoryRecyclerViewAdapter

    private val viewModel: HistoryViewModel by viewModels()

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistroyBinding.inflate(inflater, container, false)

        adapter = HistoryRecyclerViewAdapter(requireContext())

        with(binding) {
            val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            recyclerView.addItemDecoration(divider)
            recyclerView.adapter = adapter
        }

        viewModel.history.observe(viewLifecycleOwner) {
            refreshHistory(it)
        }
        navController = findNavController()

        val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
        savedStateHandle?.getLiveData<ConfirmationDialog.ConfirmationResult>(ConfirmationDialog.CONFIRMATION_RESULT)
            ?.observe(viewLifecycleOwner) {
                if (it.requestCode == CONFIRM_CLEAR && it.resultCode == Activity.RESULT_OK) {
                    clear()
                }
            }

        return binding.root
    }

    private fun refreshHistory(list: List<Dicedata>?) {
        adapter.history = list
        val count = list?.size ?: 0
        binding.historyTotal.text =
            resources.getQuantityString(R.plurals.history_total, count, count)
    }

    private fun clear() {
        viewModel.clear()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_history, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_clear -> {
                val settings = KittySettings(requireContext())
                if (settings.confirmClear) {
                    val action =
                        HistroyFragmentDirections.actionHistroyFragmentToConfirmationDialog(
                            "Do you want to clear the history?",
                            CONFIRM_CLEAR
                        )
                    navController.navigate(action)
                } else {
                    clear()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}