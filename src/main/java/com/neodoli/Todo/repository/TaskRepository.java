package com.neodoli.Todo.repository;

import com.neodoli.Todo.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskRepository {
    private final String SELECT_TASK="SELECT * FROM Tasks";
    DataSource dataSource;

    @Autowired
    public TaskRepository(DataSource  dataSource){
        this.dataSource=dataSource;
    }

    public List<Task> getTask(){
        ArrayList<Task> list= new ArrayList<Task>();
        try {
            PreparedStatement statement= dataSource.getConnection().prepareStatement(SELECT_TASK);
            ResultSet resultSet=statement.executeQuery();

            while(resultSet.next()){
                Task task = new Task();
                resultSet.getString("name");
                list.add(task);
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

}
