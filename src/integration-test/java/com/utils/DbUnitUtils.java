package com.utils;


import jakarta.validation.constraints.NotNull;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.SortedTable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

/**
 * DbUnitユーティリティクラス.<br/>
 */
public class DbUnitUtils {

  /**
   * XMLからDBUnitのデータセットを取得
   * @param xmlPathStream
   * return 変換後のDataSet
   * @throws SQLException
   * @throws DatabaseUnitException
   */
  public static IDataSet convertXmlToData(InputStream xmlPathStream)
      throws SQLException, DatabaseUnitException {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
    DateTimeFormatter dtfc = DateTimeFormatter.ofPattern("yyyyMMdd");
    IDataSet xmlDataSet = new FlatXmlDataSetBuilder().build(xmlPathStream);
    ReplacementDataSet dataSet = new ReplacementDataSet(xmlDataSet);
    dataSet.addReplacementObject("[null]", null);
    dataSet.addReplacementSubstring("[now]", LocalDateTime.now().format(dtf));
    dataSet.addReplacementSubstring("[now_yyyyMMdd]", LocalDateTime.now().format(dtfc));
    dataSet.addReplacementSubstring("[now+1d]", LocalDateTime.now().plusDays(1).format(dtf));
    dataSet.addReplacementSubstring("[now+2d]", LocalDateTime.now().plusDays(2).format(dtf));
    dataSet.addReplacementSubstring("[now+3d]", LocalDateTime.now().plusDays(3).format(dtf));
    dataSet.addReplacementSubstring("[now+4d]", LocalDateTime.now().plusDays(4).format(dtf));
    dataSet.addReplacementSubstring("[now+5d]", LocalDateTime.now().plusDays(5).format(dtf));
    dataSet.addReplacementSubstring("[now-1d]", LocalDateTime.now().minusDays(1).format(dtf));
    dataSet.addReplacementSubstring("[now-2d]", LocalDateTime.now().minusDays(2).format(dtf));
    dataSet.addReplacementSubstring("[now-3d]", LocalDateTime.now().minusDays(3).format(dtf));
    dataSet.addReplacementSubstring("[now-4d]", LocalDateTime.now().minusDays(4).format(dtf));
    dataSet.addReplacementSubstring("[now-5d]", LocalDateTime.now().minusDays(5).format(dtf));
    return xmlDataSet;
  }

  /**
   * カラムデータ取得
   *
   * @param iDataSet
   * @param tableName
   * @param columnName
   * @param index
   * @return
   * @throws DataSetException
   */
  public static Object getColumn(IDataSet iDataSet, String tableName, String columnName, int index)
      throws DataSetException {
    ITable actualTable = iDataSet.getTable(tableName);
    System.out.println("getColumn");
    System.out.println(actualTable.getValue(index, columnName));
    return actualTable.getValue(index, columnName);
  }

  /**
   * カラムがNullかどうかをチェック<br/>
   *
   * @param iDataSet   DBUnitのデータセット
   * @param tableName  テーブル名
   * @param columnName カラム名
   * @param index      インデックス
   * @return 指定されたカラムがNullでない場合true、Nullならfalse
   * @throws DataSetException
   */
  public static boolean isColumnNotNull(
      IDataSet iDataSet, String tableName, String columnName,
      int index) throws DataSetException {
    ITable actualTable = iDataSet.getTable(tableName);
    return Objects.nonNull(actualTable.getValue(index, columnName));
  }


  /**
   * 更新日時が更新されているかどうかチェック.<br/>
   *
   * @param iDataSet    データセット
   * @param tableName   テーブル名
   * @param insertDate  作成日時
   * @param updatedDate 更新日時
   * @param index       インデックス
   * @return 更新日時が作成日時よりも後ならtrue, 前ならfalse
   * @throws DataSetException
   */
  public static boolean isUpdatedRecord(IDataSet iDataSet, String tableName, String insertDate,
                                        String updatedDate, int index) throws DataSetException {
    ITable actualTable = iDataSet.getTable(tableName);
    Timestamp dateTime = (Timestamp) actualTable.getValue(index, insertDate);
    LocalDateTime createDate = dateTime.toLocalDateTime();
    dateTime = (Timestamp) actualTable.getValue(index, updatedDate);
    LocalDateTime updateDate = dateTime.toLocalDateTime();
    return updateDate.isAfter(createDate);
  }


  /**
   * テーブルソート処理.<br/>
   *
   * @param iTable ソート対象テーブル
   * @param sort   ソートキー
   * @return
   * @throws DataSetException
   */
  public static ITable sortTable(ITable iTable, String[] sort) throws DataSetException {
    if (Objects.isNull(sort)) {
      return iTable;
    }
    if (iTable.getRowCount() > 0) {
      SortedTable sortedTable = new SortedTable(iTable, sort);
      sortedTable.setUseComparable(true);
      return sortedTable;
    } else {
      return iTable;
    }
  }

  /**
   * テーブルソートした上で比較対象カラムを除外.<br/>
   *
   * @param iTable         対象テーブル
   * @param sortColumns    ソートキー
   * @param excludeColumns 除外カラム
   * @return ソートして比較対象カラム除外後テーブル
   * @throws DataSetException
   */
  public static @NotNull ITable sortedAndExcludedColumnsTable(
      ITable iTable, String[] sortColumns,
      String[] excludeColumns
  ) throws DataSetException {
    return DefaultColumnFilter.excludedColumnsTable(sortTable(iTable, sortColumns), excludeColumns);
  }
}
