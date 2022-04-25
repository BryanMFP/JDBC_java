package test;

import java.sql.*;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestMysqlJDBC {
    public static void main(String[] args) /*throws ClassNotFoundException, SQLException*/ {
        String url = "jdbc:mysql://localhost:3306/testjdbc?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(url, "root", "root");
            Statement instruccion = conexion.createStatement();
            var sql = "SELECT * FROM persona";
            ResultSet resultado = instruccion.executeQuery(sql);
            while (resultado.next()) {
                System.out.print("Id Persona: " + resultado.getInt("idPersona"));
                System.out.print(" ");
                System.out.print("Nombre: " + resultado.getString("nombre"));
                System.out.print(" ");
                System.out.print("Apellido: " + resultado.getString("apellido"));
                System.out.print(" ");
                System.out.print("Email: " + resultado.getString("email"));
                System.out.print(" ");
                System.out.print("Telefono: " + resultado.getString("telefono"));
                System.out.println("");
            }
            resultado.close();
            instruccion.close();
            conexion.close();
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace(System.out);
//            Logger.getLogger(TestMysqlJDBC.class.getName()).log(Level.SEVERE, null, ex);
//        }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
//            Logger.getLogger(TestMysqlJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
