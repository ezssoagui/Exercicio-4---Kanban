package com.eduardo.task.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.eduardo.task.data.model.Status
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.eduardo.task.R
import com.eduardo.task.data.model.Task
import com.eduardo.task.databinding.FragmentDoingBinding
import com.eduardo.task.ui.adapter.TaskAdapter


class DoingFragment : Fragment() {

    private var _binding: FragmentDoingBinding? = null
    private val binding get() = _binding!!
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initRecyclerViewTask()
        getTask()
    }

    private fun initListeners() {
        binding.floatingActionButton2.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_formTaskFragment)
        }
    }

    private fun initRecyclerViewTask() {
        taskAdapter = TaskAdapter(requireContext(), ) { task, option -> optionSelected(task, option) }
        with(binding.recyclerViewTask) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = taskAdapter
        }
    }

    private fun optionSelected(task: Task, option: Int) {
        when (option) {

            TaskAdapter.SELECT_REMOVER -> {
                Toast.makeText(
                    requireContext(),
                    "Removendo ${task.description}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            TaskAdapter.SELECT_EDIT -> {
                Toast.makeText(
                    requireContext(),
                    "Editando ${task.description}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            TaskAdapter.SELECT_DETAILS -> {
                Toast.makeText(
                    requireContext(),
                    "Detalhes ${task.description}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            TaskAdapter.SELECT_NEXT -> {
                Toast.makeText(
                    requireContext(),
                    "Próximo",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    private fun getTask() {
        val taskList = listOf(
            Task(id = "5", description = "Comprar comida do gato", Status.DOING),
            Task(id = "6", description = "Verificar a porta", Status.DOING),
            )
        taskAdapter.submitList(taskList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}