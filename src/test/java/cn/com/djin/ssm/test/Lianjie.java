package cn.com.djin.ssm.test;

import org.apache.logging.log4j.util.Strings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Lianjie {
    public static void main(String[]args)throws Exception{
        Class.forName("com.mysql.jdbc.Driver");  //加载驱动程序
        Connection connection=DriverManager.getConnection("jdbc:mysql://10.0.0.98/damTPLHK","sa",
                "dell@2016123456");   //简历连接
        String sql1="SELECT CARID FROM TanPuGrid WHERE CARID = 17 ORDER BY TRACKTIME DESC LIMIT 1";
        String sql2="SELECT count(distinct CARID) FROM TanPuGrid";
        String sql3= "SELECT count(*) FROM TanPuGrid  GROUP BY CellCol";
        String sql4= "SELECT count(*) FROM TanPuGrid  GROUP BY CellRow";
        Statement statement = connection.createStatement();  //创建执行sql语句的statement
        ResultSet resultSet1 = statement.executeQuery(sql1);  //处理执行结果
        ResultSet resultSet2 = statement.executeQuery(sql2);
        ResultSet resultSet3 = statement.executeQuery(sql3);
        ResultSet resultSet4 = statement.executeQuery(sql4);
        System.out.println(resultSet1);
        System.out.println(resultSet2);
        System.out.println(resultSet3);
        System.out.println(resultSet4);
        statement.close();    //释放资源
        connection.close();
    }

}
