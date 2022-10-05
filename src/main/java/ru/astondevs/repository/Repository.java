package ru.astondevs.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import ru.astondevs.model.Report;
import ru.astondevs.model.Task;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
public class Repository implements Interface {
    private final DataSource dataSource;


    @SneakyThrows
    public void insert(Task task) {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO tasks (id,time_of_task, user_name,task, preort_id) VALUES (?,?,?,?,?)");
    }

    @Override
    public Optional<Task> getByUser(String username) throws SQLException {
        Task task;
        Connection connection = dataSource.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT id, time_of_task, user_name, task, report_id, time_of_report" +
                " FROM tasks JOIN reports ON id_report = report_id WHERE user_name = " + username);

        task = getTask(rs);

        return Optional.of(task);
    }

    @Override
    public Optional<Task> getByDate(Timestamp date) throws SQLException {
        Task task;
        Connection connection = dataSource.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT id, time_of_task, user_name, task, report_id, time_of_report" +
                " FROM tasks JOIN reports ON id_report = report_id WHERE time_of_task = " + date);
        task = getTask(rs);

        return Optional.of(task);
    }


    @Override
    public String getAllTask() throws SQLException {
        List<Task> tasks = new ArrayList<>();

        Connection connection = dataSource.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT id, time_of_task, user_name, task," +
                " report_id, time_of_report FROM tasks JOIN reports ON id_report = report_id");

        while (rs.next()) {
            Task task = getTask(rs);
            tasks.add(task);
        }
        return mapper(tasks);
    }

    private Task getTask(ResultSet rs) throws SQLException {
        Task task = new Task();
        task.setId(rs.getInt("id"));
        task.setTimeOfTask(rs.getTimestamp("time_of_task"));
        task.setUsername(rs.getString("user_name"));
        task.setTask(rs.getString("task"));
        task.setReportId(rs.getInt("report_id"));

        Report report = new Report();
        report.setReportId(rs.getInt("id"));
        report.setTimeOfReport(rs.getTimestamp("time_of_report"));
        task.setReport(report);
        return task;
    }

    @SneakyThrows
    @Override
    public String mapper(List<Task> tasks) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(tasks);
        return json;
    }
}

