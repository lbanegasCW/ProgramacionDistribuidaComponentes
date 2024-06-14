package ar.edu.ubp.pdc.listadocomprasjdbc.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;

@WebServlet("/save.jsp")
public class SaveServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS:54106;databaseName=pdc;encrypt=false;integratedSecurity=false", "test", "test");
            connection.setAutoCommit(false);

            try{
                CallableStatement callableStatement = connection.prepareCall("{call dbo.ins_item_lista_compras(?,?)}");
                callableStatement.setString(1, req.getParameter("item"));
                callableStatement.registerOutParameter(2, Types.INTEGER);
                callableStatement.execute();

                req.setAttribute("nroItem", callableStatement.getInt("nro_item"));
                connection.commit();
                callableStatement.close();

                req.getRequestDispatcher("listadoCompras/item.jsp").forward(req, resp);
            }
            catch(SQLException ex) {
                connection.rollback();
                throw ex;
            }
            finally {
                connection.close();
            }
        }
        catch (ClassNotFoundException | SQLException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("listadoCompras/error-modal.jsp").forward(req, resp);
        }
    }
}
