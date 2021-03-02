package com.cg.tasktracker.exceptions;

import java.sql.SQLException;
// import javax.servlet.http.HttpServletRequest;
// import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomErrorAdvice {
    // @ResponseBody
    // @ResponseStatus(value=HttpStatus.NOT_FOUND)
    // @ExceptionHandler(value = {Exception.class})
    // protected ErrorInfo handleConflict(Exception ex) {
    //     System.out.println("..............handleConflict------------...");
    //     String bodyOfResponse = ex.getMessage();
    //     return new ErrorInfo(uri,bodyOfResponse); 
    // }

    @ExceptionHandler({ CustomException.class, SQLException.class, NullPointerException.class })
    public ResponseEntity<ErrorInfo> handle1(Exception ce) {
        ErrorInfo error = new ErrorInfo(ce.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}