package com.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dbunit.database.IDatabaseConnection;

/**
 * DbUnit入力情報クラス.<br/>
 */
@AllArgsConstructor
@Getter
public class DbUnitInputData {
  private final IDatabaseConnection con;
  private final String xml;
}
