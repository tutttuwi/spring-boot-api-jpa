package com.example.system.utils;

import java.util.Locale;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * メッセージ取得ユーティリティ.<br/>
 * {@link i18n} 直下の messages.properties に定義された情報を取得します。
 * キー情報は定数/Enumを用意せず、IDE(Eclipse/Intellij等)の補完機能で利用されていること、参照元先を判断します。
 * デフォルトで提供されている MessageSource では扱いづらいためユーティリティとしてラッピングしています。
 */
@Slf4j
@Component
public class MessageUtils {

  private static MessageSource messageSource;

  /**
   * Static変数にDIするためのメソッド定義
   *
   * @param messageSource
   */
  @Autowired
  public void setMessageSource(MessageSource messageSource) {
    MessageUtils.messageSource = messageSource;
  }

  /**
   * メッセージを取得します.
   *
   * @param key String
   * @return
   */
  public static String getMsg(String key) {
    Locale locale = LocaleContextHolder.getLocale();
    return MessageUtils.messageSource.getMessage(key, null, locale);
  }

  /**
   * メッセージを取得します.
   *
   * @param key  String
   * @param args Object...
   * @return
   */
  public static String getMsg(String key, Object... args) {
    Locale locale = LocaleContextHolder.getLocale();
    return MessageUtils.messageSource.getMessage(key, args, locale);
  }

  /**
   * ロケールを指定してメッセージを取得します.
   *
   * @param key    String
   * @param locale Locale
   * @param args   Object...
   * @return
   */
  public static String getMsg(String key, Locale locale, Object... args) {
    return MessageUtils.messageSource.getMessage(key, args, locale);
  }

  /**
   * メッセージを取得します.
   *
   * @param resolvable MessageSourceResolvable
   * @return
   */
  public static String getMsg(MessageSourceResolvable resolvable) {
    Locale locale = LocaleContextHolder.getLocale();
    return MessageUtils.messageSource.getMessage(resolvable, locale);
  }
}