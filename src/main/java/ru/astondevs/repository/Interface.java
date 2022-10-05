package ru.astondevs.repository;

import ru.astondevs.model.Task;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface Interface {


    Optional<Task> getByUser(String username) throws SQLException;

    Optional<Task> getByDate(Timestamp date) throws SQLException;

    String getAllTask() throws SQLException;

    String mapper(List<Task> tasks);
}
