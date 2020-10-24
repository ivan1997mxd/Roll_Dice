package sheridan.tongche.assignment2.ui.roller

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import sheridan.tongche.assignment2.MainActivity
import sheridan.tongche.assignment2.R
import sheridan.tongche.assignment2.databinding.FragmentRollerBinding
import sheridan.tongche.assignment2.ui.database.Dicedata
import sheridan.tongche.assignment2.ui.history.HistroyFragment
import kotlin.random.Random

class RollerFragment : Fragment() {

    private lateinit var binding: FragmentRollerBinding
    private val viewModel: RollerViewModel by viewModels()

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = findNavController()
        binding = FragmentRollerBinding.inflate(inflater, container, false)
        binding.rollButton.setOnClickListener { roll() }
        binding.historyButton.setOnClickListener { history() }

        viewModel.state.observe(viewLifecycleOwner){
            if (it.status == RollerViewModel.Status.SAVED_DATA){
                Toast.makeText(context,"Save to Room successful",Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root

    }

    private fun roll() {
        val dice_one_value = Random.nextInt(1, 7)
        val dice_two_value = Random.nextInt(1, 7)
        val dice_three_value = Random.nextInt(1, 7)
        val dice_sum = dice_one_value + dice_two_value + dice_three_value

        binding.DiceOne.text = dice_one_value.toString()
        binding.DiceTwo.text = dice_two_value.toString()
        binding.DiceThree.text = dice_three_value.toString()
        binding.rollResult.text = dice_sum.toString()
        viewModel.save(Dicedata(0, dice_one_value, dice_two_value, dice_three_value, dice_sum))
    }

    private fun history(){
        viewModel.reset()
        val action = RollerFragmentDirections.actionRollerFragmentToHistroyFragment()
        navController.navigate(action)

    }
}