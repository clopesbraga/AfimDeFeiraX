package com.example.afimdefeirax.View


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.afimdefeirax.ViewModel.ComprasViewModel
import com.example.afimdefeirax.databinding.FragmentComprasBinding
import com.example.afimdefeirax.R


class ComprasFragment : Fragment(){

    companion object {
        fun newInstance() = ComprasFragment()
    }

    private lateinit var comprasViewModel: ComprasViewModel
    private var _binding: FragmentComprasBinding? = null

    private lateinit var spinnerselecaodacidade: Spinner
    private lateinit var produtos_adapter: ArrayAdapter<*>

    val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentComprasBinding.inflate(inflater, container, false)
        val root: View = binding.root

        spinnerselecaodacidade = binding.spinVerduras

        produtos_adapter = ArrayAdapter.createFromResource(
            root.context,
            R.array.produtos,
            androidx.transition.R.layout.support_simple_spinner_dropdown_item
        )
        spinnerselecaodacidade.setAdapter(produtos_adapter)



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}