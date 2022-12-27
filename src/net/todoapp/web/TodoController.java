package net.todoapp.web;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.todoapp.dao.TodoDao;
import net.todoapp.dao.TodoDaoImpl;
import net.todoapp.model.LoginBean;
import net.todoapp.model.Todo;

@WebServlet("/")
public class TodoController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private TodoDao todoDAO;
    
    public void init() {
        todoDAO = new TodoDaoImpl();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        
        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertTodo(request, response);
                    break;
                case "/delete":
                    deleteTodo(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateTodo(request, response);
                    break;
                case "/list":
                    listTodo(request, response);
                    break;
                case "/logout":
                    Cookie cookies[] = request.getCookies();
                    if (cookies != null) {
                        for (Cookie cookie : cookies) {
                            if (cookie.getName().equals("user")) {
                                cookie.setMaxAge(0);
                                response.addCookie(cookie);
                            }
                        }
                    }

                default:
                    RequestDispatcher dispatcher = request.getRequestDispatcher("login/login.jsp");
                    dispatcher.forward(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }
    
    private void listTodo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        Cookie cookies[] = request.getCookies();
        String user="";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user")) {
                    user = cookie.getValue();
                }
            }
        }
        List<Todo> listTodo = todoDAO.selectAllTodos(user);
        request.setAttribute("listTodo", listTodo);
        RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-list.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-form.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
        Todo existingTodo = todoDAO.selectTodo(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-form.jsp");
        request.setAttribute("todo", existingTodo);
        dispatcher.forward(request, response);
        
    }
    
    private void insertTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException {
        
        String title = request.getParameter("title");
        Cookie cookies[] = request.getCookies();
        String username = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user")) {
                    username = cookie.getValue();
                }
            }
        }
        
        System.out.println("usernam: " + username);
        String description = request.getParameter("description");
        
        boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
        Todo newTodo = new Todo(title, username, description, LocalDate.now(), isDone);
        todoDAO.insertTodo(newTodo);
        response.sendRedirect("list");
    }
    
    private void updateTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        String title = request.getParameter("title");
        HttpSession sess = request.getSession(false);
        String username = sess.getAttribute("username").toString();
        System.out.println("user: " + username);
        String description = request.getParameter("description");
        //DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"));
        
        boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
        Todo updateTodo = new Todo(id, title, username, description, targetDate, isDone);
        
        todoDAO.updateTodo(updateTodo);
        
        response.sendRedirect("list");
    }
    
    private void deleteTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
        todoDAO.deleteTodo(id);
        response.sendRedirect("list");
    }
}
