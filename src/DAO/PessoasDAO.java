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
import java.text.ParseException;
//import java.sql.Date;
import java.util.Date;
import model.Pessoa;
import java.text.SimpleDateFormat;


 

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
    
    public void updateExtrato(String usuario, String operacao) throws SQLException{
        Date d = new Date(System.currentTimeMillis());

        java.sql.Date datasql = new java.sql.Date(d.getTime());
            System.out.println(datasql);
        String sql = "insert into extrato(data, operacao, usuario) values (?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setDate(1, datasql);
        statement.setString(2, operacao);
        statement.setString(3, usuario);
        statement.execute();
        ResultSet resultado = statement.getResultSet();

        
        
        
    }
    public ResultSet consultarExtrato(Pessoa p) throws SQLException{
        //String sql = "select * from aluno where usuario = '" 
        //             + aluno.getUsuario() + "' and senha = '" 
        //             + aluno.getSenha() + "'";
        
        String sql = "select * from extrato where usuario = ? ORDER BY data DESC ";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, p.getUsuario());
        statement.execute();
        ResultSet resultado = statement.getResultSet();
        return resultado;
        
    }
    
    
}
