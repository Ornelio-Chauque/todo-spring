package com.neodoli.Todo.service;

import com.neodoli.Todo.domain.Task;
import com.neodoli.Todo.repository.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {
    TaskRepository  taskRepository;
    public TaskService(TaskRepository taskRepository){
        this.taskRepository=taskRepository;
    }

    public List<Task> getTasks(){
       return null;
    }


}
