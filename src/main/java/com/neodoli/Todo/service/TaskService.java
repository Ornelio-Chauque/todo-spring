package com.neodoli.Todo.service;

import com.neodoli.Todo.domain.Task;
import com.neodoli.Todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    //TaskRepository  taskRepository;
    private DataSource dataSource;
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    private final String QUERY_ALL_TASK="SELECT * FROM tasks";
    private final String QUERY_TASK_BY_ID="SELECT * FROM Tasks WHERE id=?";
    private final String INSERT_TASK="INSERT INTO Tasks(id, name, description, startAt, endAt, lastModification) VALUES(?,?,?,?,?,?)";
    private final String UPDATE_TASK="UPDATE Tasks SET name=?, description=?, startAt=?, endAt=?, lastModification=? WHERE id=?";
    private final String DELETE_TASK="DELETE Tasks WHERE id=?";
    private List<Task> taskList;

    @Autowired
    public TaskService(DataSource dataSource){
        this.dataSource=dataSource;
    }

    public List<Task> getTasks(){
       taskList= new ArrayList<Task>();
        try {
            connection=this.dataSource.getConnection();
            preparedStatement= connection.prepareStatement(QUERY_ALL_TASK);
            resultSet=preparedStatement.executeQuery();

           while( resultSet.next()){
               Task task = new Task();
               task.setName(resultSet.getString("name"));
               task.setDescription(resultSet.getString("description"));
               task.setEndAt(resultSet.getString("endAt"));
               task.setId(resultSet.getInt("id"));
               task.setLastModification(resultSet.getString("lastModification"));
               task.setStartAt(resultSet.getString("startAt"));
               taskList.add(task);
           }
            return taskList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                preparedStatement.close();
                connection.close();
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
       return null;
    }

    public Task findOne(int id){
        try {
             connection=dataSource.getConnection();
             preparedStatement= connection.prepareStatement(QUERY_TASK_BY_ID);
             preparedStatement.setInt(1, id);
             resultSet= preparedStatement.executeQuery();
             if(resultSet.next()){
                 Task task= new Task();
                 task.setName(resultSet.getString("name"));
                 task.setLastModification(resultSet.getString("lastModification"));
                 task.setId(resultSet.getInt("id"));
                 task.setDescription(resultSet.getString("description"));
                 task.setStartAt(resultSet.getString("startAt"));
                 task.setEndAt(resultSet.getString("endAt"));
                 return task;
             }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if(connection!=null){
                try {
                    connection.close();
                    if (preparedStatement!=null) preparedStatement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        }

        return null;
    }
    public boolean save(Task task){
        try {
            connection= dataSource.getConnection();
            preparedStatement= connection.prepareStatement(INSERT_TASK);
            preparedStatement.setInt(1, 7);
            preparedStatement.setString(2, task.getName());
            preparedStatement.setString(3, task.getDescription());
            preparedStatement.setString(4, task.getStartAt());
            preparedStatement.setString(5, task.getEndAt());
            preparedStatement.setString(6, task.getLastModification());

           return preparedStatement.execute();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if(connection==null){
                try {
                    connection.close();
                    if (preparedStatement!=null) preparedStatement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        return false;
    }

    public boolean update(Task task){

        try {
            connection= dataSource.getConnection();
            preparedStatement= connection.prepareStatement(UPDATE_TASK);
            preparedStatement.setString(1, task.getName());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setString(3, task.getStartAt());
            preparedStatement.setString(4, task.getEndAt());
            preparedStatement.setString(5, task.getLastModification());
            preparedStatement.setInt(6, task.getId());

            return preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if(connection!=null){
                try {
                    connection.close();
                    if(preparedStatement!=null) preparedStatement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        return false;
    }

    public boolean delete(int id){
        try {
            connection= dataSource.getConnection();
            preparedStatement= connection.prepareStatement(DELETE_TASK);
            preparedStatement.setInt(1, id);
            return preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if(connection!=null){
                try {
                    connection.close();
                    preparedStatement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        return false;
    }


}
