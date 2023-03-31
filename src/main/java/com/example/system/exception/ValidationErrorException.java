package com.example.system.exception;

import lombok.Getter;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

/**
 * バリデーションエラー.
 */
@Getter
public class ValidationErrorException extends RuntimeException {

    private List<FieldError> errors;
    private String message;

    public ValidationErrorException(List<FieldError> errors) {
        super();
        this.errors = errors;
        this.message = retrieveErrors(errors).toString();
    }

    /**
     * エラーオブジェクト収集.<br/>
     * バリデーションエラー項目を収集して、必要な項目のみ抽出し返却します。
     *
     * @param fieldErrors
     * @return errorList
     */
    protected List<String> retrieveErrors(List<FieldError> fieldErrors) {
        List<String> errorList = new ArrayList<>();
        fieldErrors.forEach(error -> {
            errorList.add(error.getObjectName() + " - " + error.getField() + " : " + error.getDefaultMessage());
        });
        return errorList;
    }
}
