package dao;

import by.pvt.mazanov.stone.beans.Necklace;
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
                String id = rs.getString("id");
                String name = rs.getString("name");
                String type = rs.getString("type");
                String weight = rs.getString("weight");
                String cost = rs.getString("cost");


                StoneSelector stoneSelector = new StoneSelector();

                Stone stone = stoneSelector.getStone(Integer.parseInt(id), StoneType.valueOf(type), name, Double.parseDouble(weight), Double.parseDouble(cost));

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
            pstm.setDouble(3, stone.getWeight());
            pstm.setDouble(4, stone.getCost());

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
                String id = rs.getString("id");
                String name = rs.getString("name");
                String type = rs.getString("type");
                String weight = rs.getString("weight");
                String cost = rs.getString("cost");


                StoneSelector stoneSelector = new StoneSelector();

                Stone stone = stoneSelector.getStone(Integer.parseInt(id), StoneType.valueOf(type), name, Double.parseDouble(weight), Double.parseDouble(cost));

                list.add(stone);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return list;
    }

    public static Necklace getNecklace(Connection conn) {
        List<Stone> list = new ArrayList<>();
        String sql = "SELECT * FROM necklace";

        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                return new Necklace(id, name);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Stone> getNecklaceStones(Connection conn, int necklaceId) {
        List<Stone> list = new ArrayList<>();
        String sql = "SELECT stones.id, name, type, weight,cost FROM necklace_stones INNER JOIN stones ON necklace_stones.stone_id = stones.id WHERE necklace_stones.id = " + necklaceId;

        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String type = rs.getString("type");
                String weight = rs.getString("weight");
                String cost = rs.getString("cost");


                StoneSelector stoneSelector = new StoneSelector();

                Stone stone = stoneSelector.getStone(Integer.parseInt(id), StoneType.valueOf(type), name, Double.parseDouble(weight), Double.parseDouble(cost));

                list.add(stone);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return list;
    }

    public static Boolean insertNecklaceStone(Connection conn, int necklaceId, int stoneId) {
        String sql = "insert into necklace_stones(id, stone_id) values (?,?)";

        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, necklaceId);
            pstm.setInt(2, stoneId);

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public static Boolean deleteNecklaceStone(Connection conn, int necklaceId, int stoneId) {
        String sql = "delete from necklace_stones where id = ? AND stone_id = ?";

        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, necklaceId);
            pstm.setInt(2, stoneId);

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static Boolean updateTotal(Connection conn, int costId, int weightId) {
        //String sql = "delete from necklace_stones where id = ? AND stone_id = ?";

        PreparedStatement pstm = null;
        try {
            //pstm = conn.prepareStatement(sql);
            //pstm.setDouble(getNecklace(conn).getCost(), costId);
            ;
            pstm.setInt((int)getNecklace(conn).getCost(), costId);
            pstm.setInt((int)getNecklace(conn).getCost(), costId);
            //pstm.setInt(2, weightId);

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
