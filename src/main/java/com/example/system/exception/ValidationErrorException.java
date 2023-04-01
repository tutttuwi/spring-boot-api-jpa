package com.example.system.exception;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.springframework.validation.FieldError;

/**
 * APIバリデーションエラー.<br/>
 * バリデーションの値を集めてレスポンスのメッセージに設定します。
 */
@Getter
public class ValidationErrorException extends RuntimeException {

  private final List<FieldError> errors;
  private final String message;

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
      errorList.add(
          error.getObjectName() + " - " + error.getField() + " : " + error.getDefaultMessage());
    });
    return errorList;
  }
}
