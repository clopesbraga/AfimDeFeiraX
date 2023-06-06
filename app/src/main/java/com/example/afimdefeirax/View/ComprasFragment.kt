package com.example.afimdefeirax.View


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.afimdefeirax.ViewModel.ComprasViewModel
import com.example.afimdefeirax.databinding.FragmentComprasBinding
import com.example.afimdefeirax.R


class ComprasFragment : Fragment(){

    companion object {
        fun newInstance() = ComprasFragment()
    }

    private lateinit var _viewModel: ComprasViewModel
    private var _binding: FragmentComprasBinding? = null

    private lateinit var _spinnerprodutos: Spinner
    private lateinit var _produtos_adapter: ArrayAdapter<*>

    private lateinit var adapterview : AdapterView.OnItemClickListener


    val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentComprasBinding.inflate(inflater, container, false)
        val root: View = binding.root

        _viewModel=ViewModelProvider(this).get(ComprasViewModel::class.java)

        _spinnerprodutos = binding.spinVerduras

        _produtos_adapter = ArrayAdapter.createFromResource(
            root.context,
            R.array.produtos,
            androidx.transition.R.layout.support_simple_spinner_dropdown_item
        )
        _spinnerprodutos.setAdapter(_produtos_adapter)

        adapterview.onItemClick(){

            when(_spinnerprodutos.selectedItemPosition){

            }

        }

        return root
    }

    fun mostrarProdutos(){
        _viewModel.listaProdutos()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

private fun AdapterView.OnItemClickListener.onItemClick(function: () -> Unit) {

}
