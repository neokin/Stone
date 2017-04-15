package by.pvt.mazanov.stone.servlets;


import dao.JDBCConnectionPool;
import dao.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Connection;


@WebServlet(urlPatterns = "/necklace", loadOnStartup = 1)
public class NecklaceServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Connection conn = JDBCUtils.getConnectionPool().checkOut();

        request.setAttribute("necklace", JDBCUtils.getStones(conn));
        request.getRequestDispatcher("/necklace.jsp").forward(request, response);
        JDBCUtils.getConnectionPool().expire(conn);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String range = request.getParameter("range");
        String min = request.getParameter("min");
        String max = request.getParameter("max");


        Connection conn = JDBCUtils.getConnectionPool().checkOut();

        request.setAttribute("necklace", JDBCUtils.findStones(conn, range, Integer.parseInt(min), Integer.parseInt(max)));
        request.getRequestDispatcher("/necklace.jsp").forward(request, response);
        JDBCUtils.getConnectionPool().expire(conn);
      
    }
}
