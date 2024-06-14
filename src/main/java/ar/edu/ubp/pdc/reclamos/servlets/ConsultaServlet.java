package ar.edu.ubp.pdc.reclamos.servlets;

import ar.edu.ubp.pdc.listadocomprasjdbc.beans.ItemBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;

@WebServlet("/consulta.jsp")
public class ConsultaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connection connection = Conexion.getConnection();

            String sql = "SELECT * FROM dbo.vehiculos WHERE nro_chasis = ?";

            PreparedStatement preparedStatement;

            if (req.getParameter("patente").isEmpty()) {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, req.getParameter("chasis"));
            } else {
                sql += " AND dominio = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, req.getParameter("chasis"));
                preparedStatement.setString(2, req.getParameter("patente"));
            }

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                req.getRequestDispatcher("reclamos/success.jsp").forward(req, resp);
            } else {
                resp.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
                req.setAttribute("error", "Vehiculo no encontrado");
                req.getRequestDispatcher("reclamos/error.jsp").forward(req, resp);
            }

            rs.close();
            preparedStatement.close();
            connection.close();
        }
        catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("reclamos/error-page.jsp").forward(req, resp);
        }
    }
}
