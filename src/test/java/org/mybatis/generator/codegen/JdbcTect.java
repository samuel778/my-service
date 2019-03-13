package org.mybatis.generator.codegen;

import com.alibaba.fastjson.JSON;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.XMLParserException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 通过JDBC去连接数据库，可以获得所有表明细
 * 通过表明细，就可以做很多事了
 *
 * @author saml
 * @version 1.0
 * @date 2019-02
 */
public class JdbcTect {
    private static JDBCConnectionConfiguration configuration;
//    private static String pkname = "com.mysql.jdbc.Driver";
//    private static String url = "jdbc:mysql://cd-cdb-cq6l724v.sql.tencentcdb.com:63980/smart_parking20190222?zeroDateTimeBehavior=convertToNull";
//    private static String username = "haitunpark";
//    private static String passwd = "park9*pHT";

    public static void main(String[] args) throws IOException, XMLParserException {
        List<String> warnings = new ArrayList<String>();
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(Generator.class.getResourceAsStream("/mybatis/generator/generatorConfig.xml"));
        Context contexts = config.getContexts().get(0);
        JdbcTect.configuration = contexts.getJdbcConnectionConfiguration();

        List<Object[]> list = column_type_remarks();
        System.out.println(JSON.toJSONString(list));
    }

    /**
     * 搜索全部表，并转化成mybatis generator配置
     *
     * @param tableName
     */
    public static void tableNameUtils(String tableName) {
        String camelCaseName = camelCaseName(tableName);
        camelCaseName = camelCaseName.substring(0, 1).toUpperCase() + camelCaseName.substring(1, camelCaseName.length());
        System.out.println("<table tableName=\"" + tableName + "\" mapperName=\"" + camelCaseName + "Repository\"  domainObjectName=\"" + camelCaseName + "\" enableCountByExample=\"false\" enableUpdateByExample=\"false\" enableDeleteByExample=\"false\" enableSelectByExample=\"false\" selectByExampleQueryId=\"false\"/>");
    }

    /**
     * 转换为驼峰
     *
     * @param underscoreName
     * @return
     */
    public static String camelCaseName(String underscoreName) {
        StringBuilder result = new StringBuilder();
        if (underscoreName != null && underscoreName.length() > 0) {
            boolean flag = false;
            for (int i = 0; i < underscoreName.length(); i++) {
                char ch = underscoreName.charAt(i);
                if ("_".charAt(0) == ch) {
                    flag = true;
                } else {
                    if (flag) {
                        result.append(Character.toUpperCase(ch));
                        flag = false;
                    } else {
                        result.append(ch);
                    }
                }
            }
        }
        return result.toString();
    }

    public static List<Object[]> column_type_remarks() {
        List<Object[]> list = new ArrayList<>();
        Connection conn = null;
        DatabaseMetaData metaData = null;
        ResultSet rs = null;
        ResultSet crs = null;
        try {
            Class.forName(configuration.getDriverClass());
            conn = DriverManager.getConnection(configuration.getConnectionURL(), configuration.getUserId(), configuration.getPassword());
            metaData = conn.getMetaData();
            // 获取表
            rs = metaData.getTables(null, "%", "", new String[]{"TABLE"});
            while (rs.next()) {
                String tablename = rs.getString("TABLE_NAME");
                tableNameUtils(tablename);
                // 获取当前表的列
                crs = metaData.getColumns(null, "%", tablename, "%");
                while (crs.next()) {
                    String columnname = crs.getString("COLUMN_NAME");
                    String columntype = crs.getString("TYPE_NAME");
                    String remarks = crs.getString("REMARKS");
                    boolean is_pk = false;
                    if (columnname.equals("id")) {
                        is_pk = true;
                    }
                    list.add(new Object[]{columntype, columnname, remarks, is_pk});
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                if (null != rs) {
                    rs.close();
                }
                if (null != conn) {
                    conn.close();
                }
            } catch (Exception e2) {
            }
        }
        return list;
    }


}