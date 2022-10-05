package ru.astondevs.model;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private int id;
    private Timestamp timeOfTask;
    private String username;
    private String task;
    private int reportId;
    private Report report;
}
