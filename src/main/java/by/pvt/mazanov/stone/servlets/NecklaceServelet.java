package by.pvt.mazanov.stone.servlets;


import by.pvt.mazanov.stone.beans.Necklace;
import by.pvt.mazanov.stone.beans.Stone;
import dao.JDBCConnectionPool;
import dao.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Connection;
import java.util.List;


@WebServlet(urlPatterns = "/necklace", loadOnStartup = 1)
public class NecklaceServelet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Connection conn = JDBCUtils.getConnectionPool().checkOut();

        request.setAttribute("stones", JDBCUtils.getStones(conn));
        Necklace necklace = JDBCUtils.getNecklace(conn);
        necklace.setStonesList(JDBCUtils.getNecklaceStones(conn, necklace.getId()));
        request.setAttribute("necklace", necklace);
        request.getRequestDispatcher("/necklace.jsp").forward(request, response);
        JDBCUtils.getConnectionPool().expire(conn);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = JDBCUtils.getConnectionPool().checkOut();

        String method = request.getParameter("method");
        switch(method){
            case "search": {
                String range = request.getParameter("range");
                String min = request.getParameter("min");
                String max = request.getParameter("max");
                request.setAttribute("stones", JDBCUtils.findStones(conn, range, Integer.parseInt(min), Integer.parseInt(max)));
                Necklace necklace = JDBCUtils.getNecklace(conn);
                necklace.setStonesList(JDBCUtils.getNecklaceStones(conn, necklace.getId()));
                request.setAttribute("necklace", necklace);

                request.getRequestDispatcher("/necklace.jsp").forward(request, response);
                JDBCUtils.getConnectionPool().expire(conn);
                break;
            }
            case "add": {
                String necklaceId = request.getParameter("necklace");
                String stoneId = request.getParameter("stone");
                Boolean result =JDBCUtils.insertNecklaceStone(conn, Integer.parseInt(necklaceId), Integer.parseInt(stoneId));
                if(!result){
                    response.sendError(500);
                }
                JDBCUtils.getConnectionPool().expire(conn);
                break;
            }
            case "delete": {
                String necklaceId = request.getParameter("necklace");
                String stoneId = request.getParameter("stone");
                Boolean result =JDBCUtils.deleteNecklaceStone(conn, Integer.parseInt(necklaceId), Integer.parseInt(stoneId));
                if(!result){
                    response.sendError(500);
                }
                JDBCUtils.getConnectionPool().expire(conn);
                break;
            }
            case "updateCost": {

                Necklace necklace = JDBCUtils.getNecklace(conn);
                necklace.setStonesList(JDBCUtils.getNecklaceStones(conn, necklace.getId()));
                double cost = necklace.getCost();
                response.getWriter().write(String.valueOf(cost));
                response.getWriter().close();
                request.getRequestDispatcher("/necklace.jsp").forward(request, response);
                JDBCUtils.getConnectionPool().expire(conn);
                break;
            }

            case "updateWeight": {

                Necklace necklace = JDBCUtils.getNecklace(conn);
                necklace.setStonesList(JDBCUtils.getNecklaceStones(conn, necklace.getId()));
                double weight = necklace.getWeight();
                response.getWriter().write(String.valueOf(weight));
                response.getWriter().close();
                request.getRequestDispatcher("/necklace.jsp").forward(request, response);
                JDBCUtils.getConnectionPool().expire(conn);
                break;
            }
        }

        JDBCUtils.getConnectionPool().expire(conn);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = JDBCUtils.getConnectionPool().checkOut();

        String necklace = request.getParameter("necklace");
        String stone = request.getParameter("stone");
        JDBCUtils.deleteNecklaceStone(conn, Integer.parseInt(necklace), Integer.parseInt(stone));
        request.setAttribute("necklace", JDBCUtils.getNecklaceStones(conn, Integer.parseInt(necklace)));
        request.getRequestDispatcher("/necklace.jsp").forward(request, response);
        JDBCUtils.getConnectionPool().expire(conn);
    }
}
