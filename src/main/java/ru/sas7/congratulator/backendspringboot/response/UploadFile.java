package ru.sas7.congratulator.backendspringboot.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter

public class UploadFile {

    private String path;

    public UploadFile(String path) {
        this.path = path;
    }
}
