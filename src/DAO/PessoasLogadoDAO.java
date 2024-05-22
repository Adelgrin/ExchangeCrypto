/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import controller.ControllerLogin;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import model.Carteira;
import model.Pessoa;

 

/**
 * @author unifmnalesso
 */
public class PessoasLogadoDAO {
    private Connection conn;

    public PessoasLogadoDAO(Connection conn) {
        this.conn = conn;
    }
    
    public ResultSet consultar(Carteira carteira) throws SQLException{
        //String sql = "select * from aluno where usuario = '" 
        //             + aluno.getUsuario() + "' and senha = '" 
        //             + aluno.getSenha() + "'";
        
        String sql = "select * from pessoa where usuario = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, carteira.getUsuario());
        System.out.println(carteira.getUsuario());
        statement.execute();
        ResultSet resultado = statement.getResultSet();
        return resultado;
        
    }
    
}
