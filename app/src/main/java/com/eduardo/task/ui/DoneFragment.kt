package com.eduardo.task.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.eduardo.task.R
import com.eduardo.task.data.model.Status
import com.eduardo.task.data.model.Task
import com.eduardo.task.databinding.FragmentDoneBinding
import com.eduardo.task.ui.adapter.TaskAdapter
import androidx.navigation.fragment.findNavController



class DoneFragment : Fragment() {

    private var _binding: FragmentDoneBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskAdapter: TaskAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoneBinding.inflate(inflater, container, false)
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

    private fun getTask() {
        val taskList = listOf(
            Task(id = "7", description = "Terminar o AGREGARURAL", Status.DONE),)
        taskAdapter.submitList(taskList)
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

    private fun initRecyclerViewTask() {

        taskAdapter = TaskAdapter(requireContext(), ) { task, option -> optionSelected(task, option) }

        with(binding.recyclerViewTask) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = taskAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}