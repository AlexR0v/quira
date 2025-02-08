package ru.alexrov.quiraapi.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class IncorrectData {

    private String message;
    private boolean error = true;
    private int httpStatus = HttpStatus.BAD_REQUEST.value();
    private String errorText;
    private Set<String> validationErrors;
}
