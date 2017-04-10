package dao;

import by.pvt.mazanov.stone.beans.Stone;
import by.pvt.mazanov.stone.beans.StoneSelector;
import by.pvt.mazanov.stone.enums.StoneType;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;


public class JDBCUtils {

    private static JDBCConnectionPool connectionPool;

    public static dao.JDBCConnectionPool getConnectionPool() {
        if (connectionPool == null) {
            Properties props = readProperties("jdbc.properties");
            connectionPool = new dao.JDBCConnectionPool(
                    props.getProperty("jdbc.Driver"),
                    props.getProperty("jdbc.url"),
                    props.getProperty("jdbc.user"),
                    props.getProperty("jdbc.password")
            );
        }

        return connectionPool;
    }

    private static Properties readProperties(String fileResourceName) {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = JDBCUtils.class.getClassLoader().getResourceAsStream(fileResourceName);
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return prop;
    }

    public static Collection<Stone> getStones(Connection conn) {
        List<Stone> list = new ArrayList<>();
        String sql = "select * from stones";
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                String name = rs.getString("name");
                String type = rs.getString("type");
                String weight = rs.getString("weight");
                String cost = rs.getString("cost");


                StoneSelector stoneSelector = new StoneSelector();

                Stone stone = stoneSelector.getStone(StoneType.valueOf(type), name, Double.parseDouble(weight), Double.parseDouble(cost));

                list.add(stone);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return list;
    }

    public static void insertStone(Connection conn, Stone stone) {
        String sql = "insert into stones(name,type,weight,cost) values (?,?,?,?)";

        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(sql);
            //pstm.setString(1, stone.getName());
            pstm.setString(2, String.valueOf(stone.getType()));
            //pstm.setString(2, "text");
            pstm.setDouble(3, stone.getWeight());
            pstm.setDouble(4, stone.getCost());
/*            pstm.setString(2, employee.getPosition());
            pstm.setString(3, employee.getOffice());
            pstm.setString(4, employee.getAge());
            pstm.setString(5, employee.getStartDate());
            pstm.setString(6, employee.getSalary());*/

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static Collection<Stone> findStones(Connection conn, String column, int min, int max){
        List<Stone> list = new ArrayList<>();
        String sql = "select * from stones where "+column+" >= " + min +" and "+ column + "<= " + max;

        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                String name = rs.getString("name");
                String type = rs.getString("type");
                String weight = rs.getString("weight");
                String cost = rs.getString("cost");


                StoneSelector stoneSelector = new StoneSelector();

                Stone stone = stoneSelector.getStone(StoneType.valueOf(type), name, Double.parseDouble(weight), Double.parseDouble(cost));

                list.add(stone);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return list;
    }
}
