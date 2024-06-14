package ar.edu.ubp.pdc.listadocomprasjdbc.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/remove.jsp")
public class RemoveServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS:54106;databaseName=pdc;encrypt=false;integratedSecurity=false", "test", "test");
            connection.setAutoCommit(false);

            try {
                PreparedStatement preparedStatement = connection.prepareStatement("delete\n" +
                        "  from dbo.lista_compras\n" +
                        " where nro_item = ?");
                preparedStatement.setInt(1, Integer.parseInt(req.getParameter("removedItem")));
                preparedStatement.executeUpdate();
                connection.commit();
                preparedStatement.close();
            }
            catch (SQLException e) {
                connection.rollback();
                throw e;
            }
            finally {
                connection.close();
            }
        }
        catch (ClassNotFoundException | SQLException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("error-modal.jsp").forward(req, resp);
        }
    }
}
