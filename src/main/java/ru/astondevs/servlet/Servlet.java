package ru.astondevs.servlet;

import lombok.SneakyThrows;
import ru.astondevs.data.BaseConnection;
import ru.astondevs.model.Task;
import ru.astondevs.repository.Interface;
import ru.astondevs.repository.Repository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet(name = "GetTaskServlet", urlPatterns = "/")
public class Servlet extends HttpServlet {
    private Interface repository;

    @Override
    public void init() {
        repository = new Repository(BaseConnection.getInstance());
    }


    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getServletPath();
        req.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        switch (action) {
            case ("/task"):
                getAllTask(req);
                out.print(getAllTask(req));
                out.flush();
                break;
        }
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        String action = req.getServletPath();
        req.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        switch (action) {
            case ("/username"):
                getUser(req);
                break;
            case ("/date"):
                getDate(req);
                break;
        }
    }


    private String getAllTask(HttpServletRequest req) throws SQLException {
        String allTask = repository.getAllTask();
        req.setAttribute("/task", allTask);
        return allTask;
    }

    private void getUser(HttpServletRequest req) throws SQLException {
        Optional<Task> user = repository.getByUser(req.getParameter("username"));
        if (user.isPresent()) req.setAttribute("username", user.get());
        else req.setAttribute("username", "User not found");

    }

    private void getDate(HttpServletRequest req) throws SQLException {
        Optional<Task> date = repository.getByUser(req.getParameter("date"));
        if (date.isPresent()) req.setAttribute("date", date.get());
        else req.setAttribute("date", "Date not found");

    }
}