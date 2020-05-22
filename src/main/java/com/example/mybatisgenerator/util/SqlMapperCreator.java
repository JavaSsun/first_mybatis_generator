package com.example.mybatisgenerator.util;

import com.alibaba.druid.util.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SqlMapperCreator {

    public static void main(String[] args)throws Exception {
        createTableMapping();
    }

    private static void createTableMapping() throws Exception {
        String URL = "jdbc:mysql://192.168.121.100:3306/world?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false&zeroDateTimeBehavior=convertToNull&rewriteBatchedStatement=true";
        String USER = "root";
        String PASSWORD = "123456";

        Class.forName("com.mysql.jdbc.Driver");

        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select table_name from information_schema.tables where table_schema='world';");

        while (rs.next()) {
            String tableName = rs.getString("table_name");
            String xml = formatTableNameToXml(tableName);
            System.out.println(xml);
        }

        rs.close();
        st.close();
        conn.close();
    }
    private static String formatTableNameToXml(String tableName){
        StringBuilder tableNameFmt = formantCamelName(tableName);
        StringBuilder sb = new StringBuilder("<table tableName=\"");
        sb.append(tableName).append("\" domainObjectName=\"").append(tableNameFmt).append("\"><table>");
        return sb.toString();
    }

    private static StringBuilder formantCamelName(String tableNmae){
        StringBuilder result = new StringBuilder();

        // 非法字符串过滤
        if(StringUtils.isEmpty(tableNmae)){
            return result;
        }

        // 不含下划线，仅将首字母小写
        if(!tableNmae.contains("_")){
            return result.append(tableNmae.substring(0,1).toLowerCase()).append(tableNmae.substring(1));
        }

        // 用下划线将原始字符串分割
        String camels[] = tableNmae.split("_");
        for (String camel : camels) {
            if(StringUtils.isEmpty(camel)){
                continue;
            }

            if(result.length()!=0){
                result.append(camel.toLowerCase());
                continue;
            }

            result.append(camel.substring(0,1).toUpperCase());
            result.append(camel.substring(1).toLowerCase());
        }
        return result;
    }
}
