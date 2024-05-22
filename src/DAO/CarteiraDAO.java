/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import model.Carteira;
/**
 *
 * @author Adelgrin
 */
public class CarteiraDAO {
    private Connection conn;
    
    public CarteiraDAO(Connection conn) {
        this.conn = conn;
    }
    
    public ResultSet consultar(Carteira carteira) throws SQLException{
        //String sql = "select * from aluno where usuario = '" 
        //             + aluno.getUsuario() + "' and senha = '" 
        //             + aluno.getSenha() + "'";
        
        String sql = "select * from carteira where usuario = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, carteira.getUsuario());
        statement.execute();
        ResultSet resultado = statement.getResultSet();
        return resultado;
        
    }
}
