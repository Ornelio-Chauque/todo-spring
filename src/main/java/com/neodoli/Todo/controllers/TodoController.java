package com.neodoli.Todo.controllers;

import com.neodoli.Todo.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/todo")
public class TodoController {
    TaskService taskService;

    public TodoController(TaskService taskService){
        this.taskService=taskService;
    }

    @GetMapping("/")
    public String getTask(){
        return "list";
    }
}
