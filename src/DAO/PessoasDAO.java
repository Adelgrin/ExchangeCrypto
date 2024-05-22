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
import model.Pessoa;

 

/**
 * @author unifmnalesso
 */
public class PessoasDAO {
    private Connection conn;

    public PessoasDAO(Connection conn) {
        this.conn = conn;
    }
    
    public ResultSet consultar(Pessoa pessoa) throws SQLException{
        //String sql = "select * from aluno where usuario = '" 
        //             + aluno.getUsuario() + "' and senha = '" 
        //             + aluno.getSenha() + "'";
        
        String sql = "select * from pessoa where usuario = ? and senha = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, pessoa.getUsuario());
        statement.setString(2, pessoa.getSenha());
        statement.execute();
        System.out.println("comando de conexão executado");
        ResultSet resultado = statement.getResultSet();
        return resultado;
        
    }
    
}
