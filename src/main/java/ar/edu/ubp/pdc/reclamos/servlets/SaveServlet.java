package ar.edu.ubp.pdc.reclamos.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;

@WebServlet("/saveReclamo.jsp")
public class SaveServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connection connection = Conexion.getConnection();

            try {
                String sql = "INSERT INTO dbo.reclamos (nro_chasis, dominio, km, apellido, nombre, email, telefono, contactar, reclamo) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(1, req.getParameter("chasis"));
                preparedStatement.setString(2, req.getParameter("patente"));
                preparedStatement.setInt(3, Integer.parseInt(req.getParameter("kilometros")));
                preparedStatement.setString(4, req.getParameter("apellido"));
                preparedStatement.setString(5, req.getParameter("nombre"));
                preparedStatement.setString(6, req.getParameter("email"));
                preparedStatement.setString(7, req.getParameter("telefono"));
                preparedStatement.setString(8, req.getParameter("contactar"));
                preparedStatement.setString(9, req.getParameter("reclamo"));

                preparedStatement.executeUpdate();
                connection.commit();

                preparedStatement.close();

                req.getRequestDispatcher("reclamos/reclamos.jsp").forward(req, resp);
            } catch (SQLException ex) {
                connection.rollback();
                throw ex;
            } finally {
                connection.close();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
