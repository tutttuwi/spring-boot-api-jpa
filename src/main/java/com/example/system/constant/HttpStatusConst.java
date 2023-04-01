package com.example.system.constant;

/**
 * 定数定義.
 * HTTPステータスコードをアノテーション内で使うために定数管理する<br/>
 * 以下を参照して必要なコード値を適宜追加
 *
 * @see <https://developer.mozilla.org/ja/docs/Web/HTTP/Status>
 * @see <org.springframework.http.HttpStatus>
 */
public interface HttpStatusConst {

  String OK = "200";
  String CREATED = "201";
  String NO_CONTENT = "204";
  String BAD_REQUEST = "400";
  String NOT_FOUND = "404";
  String INTERNAL_SERVER_ERROR = "500";
}
