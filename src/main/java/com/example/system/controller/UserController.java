package com.example.system.controller;

import com.example.system.constant.HttpStatusConst;
import com.example.system.controller.model.SystemAbstractResponse;
import com.example.system.controller.model.SystemErrorResponse;
import com.example.system.controller.model.SystemSuccessResponse;
import com.example.system.controller.model.users.GetUsersRequest;
import com.example.system.controller.model.users.PostUsersRequest;
import com.example.system.controller.model.users.PutUsersRequest;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//TODO: システム個別設定 Swagger設定
@ApiResponses(
    {
        @ApiResponse(
            responseCode = HttpStatusConst.INTERNAL_SERVER_ERROR,
            description = "Internal server error",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = SystemErrorResponse.class))),
        @ApiResponse(
            responseCode = HttpStatusConst.BAD_REQUEST,
            description = "Bad request",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = SystemErrorResponse.class))),
        @ApiResponse(
            responseCode = HttpStatusConst.NOT_FOUND,
            description = "Not Found",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = SystemErrorResponse.class)))
    }
)
@RestController
@RequestMapping("/users")
@Tag(name = "User", description = "User Path", externalDocs = @ExternalDocumentation(description = "OtherDocument", url = "https://example.com"))
/**
 * ユーザ情報コントローラ.<br/>
 */
public interface UserController {

  /**
   * ユーザー情報一覧取得.
   *
   * @param usersReq GetUsersRequest
   * @return ret {@code List<SearchUserList>}
   * @throws Throwable Any Exception
   */
  @Operation(summary = "Get Users Info",
      description = "This can Search Users, you can add optional conditions",
      responses = {
          @ApiResponse(responseCode = HttpStatusConst.OK,
              description = "Success",
              content = @Content(mediaType = "application/json",
                  schema = @Schema(implementation = SystemSuccessResponse.class)
              )
          )
      }
  )
  @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = MediaType.APPLICATION_FORM_URLENCODED_VALUE))
  @GetMapping(value = "")
  ResponseEntity<SystemAbstractResponse> getUsers(
      @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "ユーザ検索条件", required = false)
      @Validated
      @RequestBody
      @RequestParam(required = false)
      GetUsersRequest usersReq
  ) throws Throwable;

  /**
   * ユーザー情報取得.
   *
   * @param userId Long
   * @return ret {@code List<SearchUserList>}
   * @throws Throwable Any Exception
   */
  @Operation(summary = "Get User Info on designated Id",
      description = "This can Search Users by id",
      responses = {
          @ApiResponse(responseCode = HttpStatusConst.OK,
              description = "Success",
              content = @Content(mediaType = "application/json",
                  schema = @Schema(implementation = SystemSuccessResponse.class)
              )
          )
      }
  )
  @GetMapping("/{id}")
  @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = MediaType.APPLICATION_FORM_URLENCODED_VALUE))
  ResponseEntity<SystemAbstractResponse> getUser(
      @Parameter(description = "userId", required = true) @PathVariable(value = "id") Long userId)
      throws Throwable;

  /**
   * ユーザー情報登録.
   *
   * @param usersReq PostUsersRequest
   * @return ret {@code List<SearchUserList>}
   * @throws Throwable Any Exception
   */
  @Operation(summary = "Create User",
      description = "This can Create User Info",
      responses = {
          @ApiResponse(responseCode = HttpStatusConst.CREATED, description = "register info")
      }
  )
  @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
  @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<SystemAbstractResponse> postUser(@RequestBody @Validated PostUsersRequest usersReq,
                                                  BindingResult result)
      throws Throwable;

  /**
   * ユーザー情報更新.
   *
   * @param usersReq PutUsersRequest
   * @return ret {@code List<SearchUserList>}
   * @throws Throwable Any Exception
   */
  @Operation(summary = "Update User",
      description = "This can Update User Info",
      responses = {
          @ApiResponse(responseCode = HttpStatusConst.OK, description = "Updated info")
      }
  )
  @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
  @PutMapping("/{id}")
  ResponseEntity<SystemAbstractResponse> putUser(@Parameter(description = "userId", required = true)
                                                 @PathVariable(value = "id")
                                                 @NotNull
                                                 Long userId,
                                                 @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "ユーザ検索条件", required = true)
                                                 @Validated
                                                 PutUsersRequest usersReq,
                                                 BindingResult result)
      throws Throwable;

  /**
   * ユーザー情報削除.
   *
   * @param userId Long
   * @return ret {@code List<SearchUserList>}
   * @throws Throwable Any Exception
   */
  @Operation(summary = "Delete User",
      description = "This can Delete User Info",
      responses = {
          @ApiResponse(responseCode = HttpStatusConst.NO_CONTENT, description = "Updated info")
      }
  )
  @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = MediaType.APPLICATION_FORM_URLENCODED_VALUE))
  @DeleteMapping("/{id}")
  ResponseEntity<SystemAbstractResponse> deleteUser(
      @Parameter(description = "userId", required = true) @PathVariable(value = "id")
      @NotNull Long userId)
      throws Throwable;
}
