package com.eduardo.task.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.eduardo.task.R
import com.eduardo.task.data.model.Status
import com.eduardo.task.data.model.Task
import com.eduardo.task.helper.FirebaseHelper
import com.eduardo.task.databinding.FragmentTodoBinding
import com.eduardo.task.ui.adapter.TaskAdapter


class TodoFragment : Fragment() {

    private var _binding: FragmentTodoBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskAdapter: TaskAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodoBinding.inflate(inflater,container, false)
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
                task.status = Status.DOING
                updateTask(task)

            }
        }
    }

    private fun initRecyclerViewTask() {
        taskAdapter = TaskAdapter(requireContext() ) { task, option -> optionSelected(task, option) }
        with(binding.recyclerViewTask) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = taskAdapter
        }
    }

    private fun getTask() {
        val taskList = listOf(
            Task(id = "0", description = "Criar nova tela do app", Status.TODO),
            Task(id = "1", description = "Validar informações na tela de login", Status.TODO),
            Task(id = "2", description = "Adicionar nova funcionalidade no app", Status.TODO),
            Task(id = "3", description = "Salvar token localmente", Status.TODO),
            Task(id = "4", description = "Criar funcionalidade de logout no app", Status.TODO)
        )
        taskAdapter.submitList(taskList)
    }

    private fun deleteTask(task: Task) {
        FirebaseHelper.getDatabase()
            .child("task")
            .child(FirebaseHelper.getIdUser())
            .child(task.id)
            .removeValue()
            .addOnCompleteListener { result ->

                if (result.isSuccessful) {

                    Toast.makeText(
                        requireContext(),
                        R.string.text_delete_sucess_task,
                        Toast.LENGTH_SHORT
                    ).show()

                    val oldList = taskAdapter.currentList
                    val newList = oldList.toMutableList().apply { remove(task) }

                    taskAdapter.submitList(newList)

                } else {

                    Toast.makeText(
                        requireContext(),
                        R.string.error_generic,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun updateTask(task: Task) {
        FirebaseHelper.getDatabase()
            .child("task")
            .child(FirebaseHelper.getIdUser())
            .child(task.id)
            .setValue(task)
            .addOnCompleteListener { result ->

                if (result.isSuccessful) {

                    Toast.makeText(
                        requireContext(),
                        R.string.text_save_sucess_form_task_fragment,
                        Toast.LENGTH_SHORT
                    ).show()

                } else {

                    Toast.makeText(
                        requireContext(),
                        R.string.error_generic,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}