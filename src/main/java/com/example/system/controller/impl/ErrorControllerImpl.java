package com.example.system.controller.impl;

import com.example.system.controller.model.SystemErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.webjars.NotFoundException;

@Slf4j
@Controller
@RequestMapping("/error")
public class ErrorControllerImpl extends AbstractBaseController implements ErrorController {

  /**
   * コンストラクター
   */
  ErrorControllerImpl() {
  }

  /**
   * JSON レスポンス用の ResponseEntity オブジェクトを返す。
   *
   * @return JSON レスポンス用の ResponseEntity オブジェクト
   */
  @RequestMapping
  public ResponseEntity<Object> error() {
    // Create Response
    SystemErrorResponse response =
        new SystemErrorResponse(HttpStatus.NOT_FOUND, new NotFoundException("Not Found Resouces"));
    // Return Json
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

}
