package com.zaxxer.hikari.yang;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author: CY.Ma
 * @date: 2024/5/28 10:32
 * @description:
 */
public class HikariCPTest {

   @Test
   public void test() throws SQLException{
      HikariConfig hikariConfig = new HikariConfig();
      hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
      hikariConfig.setUsername("root");
      hikariConfig.setConnectionTimeout(10000);
      hikariConfig.setPassword("mcycxc19740203");
      hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/demo_ds_0?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8&allowPublicKeyRetrieval=true&useServerPrepStmts=true");
      hikariConfig.addDataSourceProperty("useServerPrepStmts", true);
      try (HikariDataSource ds = new HikariDataSource(hikariConfig);
         Connection connection = ds.getConnection()) {

         for (int i = 0; i < 10; i++) {
            try (var statement = connection.createStatement()) {
               // connection was created a few milliseconds before, so set query timeout is omitted (we assume it will succeed)
               boolean execute = statement.execute("select 1");
               System.out.println(execute);
            }
         }

      }
   }
}
