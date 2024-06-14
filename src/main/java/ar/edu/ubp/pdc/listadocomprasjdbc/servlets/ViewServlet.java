package ar.edu.ubp.pdc.listadocomprasjdbc.servlets;

import ar.edu.ubp.pdc.listadocomprasjdbc.beans.ItemBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;

@WebServlet("/listadoCompras.jsp")
public class ViewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            LinkedList<ItemBean> items = new LinkedList<ItemBean>();

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS:54106;databaseName=pdc;encrypt=false;integratedSecurity=false", "test", "test");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select nro_item,\n" +
                    "       desc_item\n" +
                    "  from dbo.lista_compras\n" +
                    " order by fecha_hora_registro");

            while(rs.next()) {
                ItemBean item = new ItemBean();
                item.setNroItem(rs.getInt("nro_item"));
                item.setDescItem(rs.getString("desc_item"));
                items.add(item);
            }

            rs.close();
            statement.close();
            connection.close();

            req.setAttribute("items", items);
            req.getRequestDispatcher("listadoCompras/listado.jsp").forward(req, resp);
        }
        catch (ClassNotFoundException | SQLException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("listadoCompras/error-page.jsp").forward(req, resp);
        }
    }
}
