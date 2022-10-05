package ru.astondevs.model;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    int reportId;
    Timestamp timeOfReport;
}
