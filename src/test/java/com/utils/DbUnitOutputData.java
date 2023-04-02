package com.utils;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dbunit.dataset.IDataSet;

/**
 * DbUnit出力情報クラス.<br/>
 */
@AllArgsConstructor
@Getter
public class DbUnitOutputData {
  private final IDataSet dataSet;
  private final List<String> tables;
  private final String xml;
}
