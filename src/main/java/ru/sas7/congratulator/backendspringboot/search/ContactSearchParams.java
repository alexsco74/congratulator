package ru.sas7.congratulator.backendspringboot.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor

public class ContactSearchParams {
    private String firstName;
    private String middleName;
    private String lastName;
    private Integer categoryId;
}
