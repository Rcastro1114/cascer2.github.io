package Operaciones;
import Entidad.Login;
import java.sql.*;
import java.util.*;


public class OperacionesSQL {
    private Connection conexion;
    private void conectar(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(""
                    +"jdbc:mysql://localhost/cascer?autoReconnect=true&useSSL=false",
                    "root","admin");
        }catch(Exception e){  
        }
    }
    
    public int validar(Login lo){
        try {
            int r=0;
            this.conectar();
            String sql = "select * from login where usuario=? and clave=?;";
            PreparedStatement ps = this.conexion.prepareStatement(sql);
                       
            ps.setString(1,lo.getUsuario());
            ps.setString(2,lo.getClave());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                r=r+1;
                lo.setUsuario(rs.getString("usuario"));
                lo.setClave(rs.getString("clave"));
            }
            if(r==1){
                return 1;
            }else{
                return 0;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }finally{
            this.desconectar();
        }
    }
    
    private void desconectar(){
        try {
            conexion.close();
        } catch (Exception e) {
        }
    }
}
