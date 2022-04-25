package datos;

import domain.PersonaDTO;
import static datos.Conexion.*;
import java.sql.*;
import java.util.*;

public class PersonaDaoJDBC implements PersonaDAO{
    
    private Connection conexionTransaccional;
    
    private static final String SQL_SELECT = "SELECT * FROM persona";
    private static final String SQL_INSERT = "INSERT INTO persona(nombre, apellido, email, telefono)VALUES(?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE persona SET nombre = ?, apellido = ?, email = ?, telefono = ? WHERE idPersona = ?";
    private static final String SQL_DELETE = "DELETE FROM persona WHERE idPersona = ?";

    public PersonaDaoJDBC() {
    }

    public PersonaDaoJDBC(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    @Override
    public List<PersonaDTO> seleccionar() throws SQLException{
        Connection conn = null;
        PreparedStatement instruccion = null;
        ResultSet rs = null;
        PersonaDTO persona = null;
        List<PersonaDTO> personas = new ArrayList<>();
        
        try {
            
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            instruccion = conn.prepareStatement(SQL_SELECT);
            rs = instruccion.executeQuery();
            while(rs.next()){
                int idPersona = rs.getInt("idPersona");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                
                persona = new PersonaDTO(idPersona, nombre, apellido, email, telefono);
                
                personas.add(persona);
            }
            
        } finally{
            
            try {
                
                close(rs);
                close(instruccion);
                if (this.conexionTransaccional == null) {
                    close(conn);
                }
                
                
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        
        return personas;
    }

    @Override
    public int insertar(PersonaDTO persona) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());
            
            registros = stmt.executeUpdate();
            
        } finally{
            try {
                
                close(stmt);
                if (this.conexionTransaccional == null) {
                    close(conn);
                }
                
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        
        return registros;
    }

    @Override
    public int actualizar(PersonaDTO persona) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());
            stmt.setInt(5, persona.getIdPersona());
            
            registros = stmt.executeUpdate();
            
        } finally{
            try {
                
                close(stmt);
                if (this.conexionTransaccional == null) {
                    close(conn);
                }
                
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        
        return registros;
    }

    @Override
    public int eliminar(PersonaDTO persona) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            
            stmt.setInt(1, persona.getIdPersona());
            
            registros = stmt.executeUpdate();
            
        } finally{
            try {
                
                close(stmt);
                if (this.conexionTransaccional == null) {
                    close(conn);
                }
                
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        
        return registros;
    }

}
