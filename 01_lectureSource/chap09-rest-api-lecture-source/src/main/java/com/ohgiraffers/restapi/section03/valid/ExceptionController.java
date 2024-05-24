package com.ohgiraffers.restapi.section03.valid;

import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//요청을 가로챔
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(UserNotFoundException e) {

        String code = "Error_Code_0001";
        String description = "회원 정보 조회 실패";
        String detail = e.getMessage();

        return new ResponseEntity<>(new ErrorResponse(code, description, detail), HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodValidException(MethodArgumentNotValidException e) {
        String code = "";
        String description = "";
        String detail = "";

        /*에러가 있다면*/
        if (e.getBindingResult().hasErrors()) {

            detail = e.getBindingResult().getFieldError().getDefaultMessage(); //e.getMessage()
            String bindResultCode = e.getBindingResult().getFieldError().getCode(); // 어떤에러가 발생했는지 NotNull, Size, NotBlank...

            switch (bindResultCode) {
                case "NotBlank":
                    code = "Error_Code_0002";
                    description = "필수 값이 누락되었습니다.";
                    break;
                case "Size":
                    code = "Error_Code_0003";
                    description = "글자수를 확인해야 합니다.";
                    break;
            }
        }
            return new ResponseEntity<>(new ErrorResponse(code, description, detail), HttpStatus.BAD_REQUEST);
    }
}
