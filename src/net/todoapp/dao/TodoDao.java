package net.todoapp.dao;

import java.sql.SQLException;
import java.util.List;

import net.todoapp.model.Todo;

public interface TodoDao {

	void insertTodo(Todo todo) throws SQLException, ClassNotFoundException;

        Todo selectTodo(long todoId) throws ClassNotFoundException;

	List<Todo> selectAllTodos(String user);

	boolean deleteTodo(int id) throws SQLException, ClassNotFoundException;

	boolean updateTodo(Todo todo) throws SQLException, ClassNotFoundException;

}