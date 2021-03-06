package stuba.fei.gono.javablocking.errors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.*;
import java.util.stream.Collectors;

/***
 * Class that implements custom error handling
 */
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(ReportedOverlimitTransactionException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public List<String> springHandleNotFound(Exception ex)  {
        return new ArrayList<>(Collections.singleton(ex.getMessage()));
    }

    @ExceptionHandler(CreateReportedOverlimitTransactionException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String postException(Exception ex)  {
        return ex.getMessage();
    }

    /***
     * Transforms validation errors into JSON array
     * @param ex caught validation exception
     * @return List of validation error messages
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<String> handleValidationExceptions(MethodArgumentNotValidException ex)
    {
        return ex.getBindingResult()
                .getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
    }

    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMessageNotReadableException(org.springframework.http.converter.HttpMessageNotReadableException ex)
    {
        return ex.getMessage();
    }

}
