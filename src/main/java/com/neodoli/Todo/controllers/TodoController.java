package com.neodoli.Todo.controllers;

import com.neodoli.Todo.domain.Task;
import com.neodoli.Todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/task")
public class TodoController {
    TaskService taskService;

    @Autowired
    public TodoController(TaskService taskService){
        this.taskService=taskService;
    }

    @GetMapping("/")
    public String getTask(Model model){
        List<Task> taskList= taskService.getTasks();
        model.addAttribute("tasks", taskList);
        return "list";
    }
    @GetMapping("/{id}")
    public String findOne(@PathVariable("id") int id, Model model){
        model.addAttribute("task", taskService.findOne(id));
        return "task";
    }
    @GetMapping("/add-task")
    public String addTask(){
        return "add-task";
    }

    // for test propose, trying to set a getMapping with the same url
    @GetMapping("/add")
    public String test(){
        return "list";
    }

    @PostMapping("/add")
    public String saveTask(Task task){
        taskService.save(task);
        return "redirect:/task/";
    }

    @GetMapping("/{id}/editForm")
    public String editTask(@PathVariable("id") int id, Model model){
        Task task=taskService.findOne(id);
        model.addAttribute("task", task);
        return "showEditTask";
    }

    @PostMapping("/{id}/edit")
    public String editTask(@PathVariable("id") int id, Task task){
        task.setId(id);
        taskService.update(task);
        return "redirect:/task/"+id;
    }

    @GetMapping("/{id}/showDelete")
    public String showDeletePage(@PathVariable("id") int id, Model model){
        Task task= taskService.findOne(id);
        model.addAttribute("task", task);
      return "showDelete";
    }
    @GetMapping("/{id}/delete")
    public String deleteTask(@PathVariable("id") int id){
        taskService.delete(id);
        return "redirect:/task/";
    }
}
