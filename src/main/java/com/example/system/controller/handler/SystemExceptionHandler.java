package com.example.system.controller.handler;

import com.example.system.controller.model.SystemErrorResponse;
import com.example.system.exception.NoDataFoundException;
import com.example.system.exception.ValidationErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * 例外ハンドラークラス<br/>
 */
@RestControllerAdvice(annotations = RestController.class) // HTMLコントローラーの例外を除外する
@Slf4j
public class SystemExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * 入力チェックエラーのハンドリング.
     *
     * @param ex      exception
     * @param request request
     * @return
     */
    @ExceptionHandler(ValidationErrorException.class)
    public ResponseEntity<Object> handleValidationErrorException(ValidationErrorException ex, WebRequest request) {

//        val fieldErrorContexts = new ArrayList<FieldErrorResource>();
//
//        if (ex instanceof ValidationErrorException) {
//            val vee = (ValidationErrorException) ex;
//
//            vee.getErrors().ifPresent(errors -> {
//                val fieldErrors = errors.getFieldErrors();
//
//                if (fieldErrors != null) {
//                    fieldErrors.forEach(fieldError -> {
//                        val fieldErrorResource = new FieldErrorResource();
//                        fieldErrorResource.setFieldName(fieldError.getField());
//                        fieldErrorResource.setErrorType(fieldError.getCode());
//                        fieldErrorResource.setErrorMessage(fieldError.getDefaultMessage());
//                        fieldErrorContexts.add(fieldErrorResource);
//                    });
//                }
//            });
//        }

//        Locale locale = request.getLocale();
//        val errorContext = new ErrorResourceImpl();
//        errorContext.setMessage(message);
//        errorContext.setFieldErrors(fieldErrorContexts);
//        List<String> errorList = retrieveErrors(ex.getErrors());

        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        SystemErrorResponse response = new SystemErrorResponse(status, ex);
        return super.handleExceptionInternal(ex, response, headers, status, request);

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
            log.info(error.getObjectName() + " - " + error.getField() + " : " + error.getDefaultMessage());
            errorList.add(error.getObjectName() + " - " + error.getField() + " : " + error.getDefaultMessage());
        });
        return errorList;
    }

    /**
     * データ不存在エラーのハンドリング.
     *
     * @param ex      exception
     * @param request request
     * @return
     */
    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Object> handleNoDataFoundException(Exception ex, WebRequest request) {

        String parameterDump = this.dumpParameterMap(request.getParameterMap());
        log.info("no data found. dump: {}", parameterDump);

        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.NOT_FOUND;
        SystemErrorResponse response = new SystemErrorResponse(status, ex);
        return super.handleExceptionInternal(ex, response, headers, status, request);
    }

    /**
     * 予期せぬ例外のハンドリング.
     * どの例外ハンドラーにもキャッチされなかった場合に処理される
     *
     * @param ex      exception
     * @param request request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnexpectedException(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        HttpHeaders headers = new HttpHeaders();
        String parameterDump = this.dumpParameterMap(request.getParameterMap());
        log.error(String.format("unexpected error has occurred. dump: %s", parameterDump), ex);
        SystemErrorResponse response = new SystemErrorResponse(status, ex);

        return super.handleExceptionInternal(ex, response, headers, status, request);
    }

    /**
     * パラメータをダンプする.
     *
     * @param parameterMap parametersMap
     * @return
     */
    protected String dumpParameterMap(Map<String, String[]> parameterMap) {
        StringBuilder sb = new StringBuilder(256);
        parameterMap.forEach((key, values) -> {
            sb.append(key).append("=").append("[");
            for (String value : values) {
                sb.append(value).append(",");
            }
            sb.delete(sb.length() - 1, sb.length()).append("], ");
        });
        int length = sb.length();
        if (2 <= length) {
            sb.delete(length - 2, length);
        }

        return sb.toString();
    }
}
