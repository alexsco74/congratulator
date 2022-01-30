package ru.sas7.congratulator.backendspringboot.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor

public class UpcomingBirthdaysParams {

    // Дата с которой получаем ближайшие дни рождения
    private String fromDate;
    private Integer categoryId;
    private Integer pageIndex;
    private Integer pageSize;
}
