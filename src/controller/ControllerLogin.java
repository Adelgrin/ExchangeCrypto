/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.CarteiraDAO;
import DAO.PessoasDAO;
import DAO.Conexao;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import model.Carteira;
import model.Pessoa;
import view.ConsultaSaldo;
import view.Deposito;
import view.Login;
import view.Menu;
import java.sql.PreparedStatement;
import java.util.Random;
import view.ComprarCrypto;
import view.Saque;
import view.SenhaCompra;
import view.SenhaVenda;
import view.VendaCrypto;

/**
 *
 * @author unifmnalesso
 */
public class ControllerLogin {
    private Login login;
    public String logado;
    private String nometemp;
    private String cpftemp;
    private ConsultaSaldo saldo;
    private Menu menu;
    private ComprarCrypto comprar;
    private Pessoa p;
    private Deposito deposito;
    private double valor, valorUpdate, maxbtc, minbtc, maxeth, mineth, maxxrp, minxrp, newbtc, neweth, newxrp;
    private Saque saque;
    private String moeda;
    private String senhacomp;
    private SenhaCompra senha;
    private SenhaVenda senhaVenda;
    private VendaCrypto venda;

    

    public ControllerLogin(Login view) {
        this.login = view;
        saldo = new ConsultaSaldo();
    }
    public ControllerLogin(ConsultaSaldo saldo){
        this.saldo = saldo;
    }
    public ControllerLogin(Menu menu){
        this.menu = menu;
    }
    public ControllerLogin(Deposito deposito){
        this.deposito = deposito;
    }
    public ControllerLogin(Saque saque){
        this.saque = saque;
    }
    public ControllerLogin(ComprarCrypto comprar){
        this.comprar = comprar;
    }
    public ControllerLogin(SenhaCompra senha){
        this.senha = senha;
    }
    public ControllerLogin(SenhaVenda senhaVenda){
        this.senhaVenda = senhaVenda;
    }
    public ControllerLogin(VendaCrypto venda){
        this.venda = venda;
    }
    public void loginPessoa(){
        Pessoa p = new Pessoa(login.getTxtLogin().getText(),
                                      login.getTxtSenha().getText());
        
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.getConnection();
            PessoasDAO dao = new PessoasDAO(conn);
            ResultSet res = dao.consultar(p);
            if (res.next()) {
                JOptionPane.showMessageDialog(login, "Login efetuado!");
                String n = res.getString("nome");
                String c = res.getString("cpf");
                String s = res.getString("senha");
                String u = res.getString("usuario");
                Pessoa p2 = new Pessoa(n,c,s,u);
 
                System.out.println(p2);
                Menu m = new Menu(p2);
                m.setVisible(true);
                login.setVisible(false);
                logado = login.getTxtLogin().getText();
                //System.out.println("algo" + logado);
            }else{
                JOptionPane.showMessageDialog(login, "Login não efetuado!");
            }
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(login, "Erro de conexão!");
        }
        
        
    }
    
    public void saldoController(Pessoa p, ConsultaSaldo s){
        //logado = view.getTxtLogin().getText();
        //System.out.println("teste" + logado);
        
        logado = p.getUsuario();
        //System.out.println("teste" + logado);
        Carteira c = new Carteira(logado);
        Conexao conexao = new Conexao();
        try{
            
            
            Connection conn = conexao.getConnection();
            //PessoasLogadoDAO pdao = new PessoasLogadoDAO(conn);
            //ResultSet res2 = pdao.consultar(c);
            CarteiraDAO cdao = new CarteiraDAO(conn);
            ResultSet res = cdao.consultar(c);
            
            //PessoasDAO pdao = new PessoasDAO(conn);
            //ResultSet res2 = pdao.consultar(p);
            if(res.next()){
                s.getTxtBtc().setText(res.getString("btc"));
                s.getTxtEth().setText(res.getString("eth"));
                s.getTxtXrp().setText(res.getString("xrp"));
                s.getTxtReal().setText(res.getString("realval"));
                //System.out.println(res2.getString("cpf"));
                //System.out.println("pre nome");
                s.getTxtNome().setText(p.getNome());
                //s.getTxtNome().setText(" ggg");
                //System.out.println(p.getNome());
                //System.out.println("pos nome" + res2.getString("nome"));
                s.getTxtCpf().setText(p.getCpf());
                //s.getTxtCpf().setText(p.getCpf());
                
                
            }
        }catch(SQLException e){
            
        }
    }
    
    public void depositoController(Pessoa p, double d, Deposito de, Carteira c){
        if (d < 0) {
            de.getLblErro().setText("por favor insira valores positivos apenas! ");
            return;
        }
        de.getLblErro().setText("");
        Conexao conexao = new Conexao();
        try{
            
            
            Connection conn = conexao.getConnection();
            //PessoasLogadoDAO pdao = new PessoasLogadoDAO(conn);
            //ResultSet res2 = pdao.consultar(c);
            CarteiraDAO cdao = new CarteiraDAO(conn);
            ResultSet res = cdao.consultar(c);
            if(res.next()){
                valor = res.getDouble("realval");
                System.out.println(valor);
                valorUpdate = valor + Double.valueOf(de.getTxtDeposito().getText());
                System.out.println(valorUpdate);
                String sql = "UPDATE public.carteira SET realval=? WHERE usuario = ?;";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setDouble(1, valorUpdate);
                statement.setString(2, p.getUsuario());
                System.out.println(p.getUsuario());
                statement.execute();
                de.getTxtValorReais().setText(String.valueOf(valorUpdate));
                //de.getTxtValorReais().setText("a");
                
                
            }
        }catch(SQLException e){
             e.printStackTrace();
            
        }

    
}
    
    public void saqueController(Pessoa p, double d, Saque de, Carteira c){
        
        Conexao conexao = new Conexao();
        try{
            
            
            Connection conn = conexao.getConnection();
            //PessoasLogadoDAO pdao = new PessoasLogadoDAO(conn);
            //ResultSet res2 = pdao.consultar(c);
            CarteiraDAO cdao = new CarteiraDAO(conn);
            ResultSet res = cdao.consultar(c);
            if(res.next()){
                valor = res.getDouble("realval");
            
            if (d < 0) {
            de.getLblErro().setText("por favor insira valores positivos apenas! ");
            return;
            }
            if (d > valor) {
            de.getLblErro().setText("saldo não suficiente! ");
            return;
            }
            de.getLblErro().setText("");
                
                System.out.println(valor);
                valorUpdate = valor - Double.valueOf(de.getTxtDeposito().getText());
                System.out.println(valorUpdate);
                String sql = "UPDATE public.carteira SET realval=? WHERE usuario = ?;";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setDouble(1, valorUpdate);
                statement.setString(2, p.getUsuario());
                System.out.println(p.getUsuario());
                statement.execute();
                de.getTxtValorReais().setText(String.valueOf(valorUpdate));
                //de.getTxtValorReais().setText("a");
                
                
            }
        }catch(SQLException e){
             e.printStackTrace();
            
        }

    
}
    public void comprarController(Carteira c, ComprarCrypto comprar){
         Conexao conexao = new Conexao();
         Carteira c2 = new Carteira("cotacao");
         try{
         Connection conn = conexao.getConnection();
         CarteiraDAO cdao = new CarteiraDAO(conn);
         CarteiraDAO cdao2 = new CarteiraDAO(conn);
            ResultSet resc2 = cdao2.consultar(c2);
            ResultSet resc = cdao.consultar(c);
            if (resc.next() && resc2.next()) {
                //System.out.println(comprar.getBtBtc().isSelected());
                if (comprar.getBtBtc().isSelected()) {
                    valorUpdate = (resc.getDouble("realval") - Double.parseDouble(comprar.getTxtValor().getText())*resc2.getDouble("btc"))-resc.getDouble("realval")*0.02;//2 por cento de valor de compra
                    //System.out.println(valorUpdate);
                    if (valorUpdate >= 0) {
                    String sql = "UPDATE public.carteira SET realval = ? WHERE usuario = ?;";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setDouble(1, valorUpdate);
                    //System.out.println(valorUpdate);
                statement.setString(2, c.usuario);
                    //System.out.println(c.usuario);
                statement.execute();
                        //System.out.println("funcionou");
                        valorUpdate = (Double.parseDouble(comprar.getTxtValor().getText()) + resc.getDouble("btc"));
                    String sql2 = "UPDATE public.carteira SET btc = ? WHERE usuario = ?;";
                PreparedStatement statement2 = conn.prepareStatement(sql2);
                statement2.setDouble(1, valorUpdate);
                    //System.out.println(valorUpdate);
                statement2.setString(2, c.usuario);
                    //System.out.println(c.usuario);
                statement2.execute();
                
                
                    }else{
                        JOptionPane.showMessageDialog(comprar,"saldo insuficiente! ");
                        return;
                    }
                    
                }else if (comprar.getBtEth().isSelected()) {
                    valorUpdate = (resc.getDouble("realval") - Double.parseDouble(comprar.getTxtValor().getText())*resc2.getDouble("eth"))-resc.getDouble("realval")*0.01;//1 por cento de valor de compra
                    //System.out.println(valorUpdate);
                    if (valorUpdate >= 0) {
                    String sql = "UPDATE public.carteira SET realval = ? WHERE usuario = ?;";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setDouble(1, valorUpdate);
                    //System.out.println(valorUpdate);
                statement.setString(2, c.usuario);
                    //System.out.println(c.usuario);
                statement.execute();
                        //System.out.println("funcionou");
                        valorUpdate = Double.parseDouble(comprar.getTxtValor().getText()) + resc.getDouble("eth");
                    String sql2 = "UPDATE public.carteira SET eth = ? WHERE usuario = ?;";
                PreparedStatement statement2 = conn.prepareStatement(sql2);
                statement2.setDouble(1, valorUpdate);
                    //System.out.println(valorUpdate);
                statement2.setString(2, c.usuario);
                    //System.out.println(c.usuario);
                statement2.execute();
                
                
                    }else{
                        JOptionPane.showMessageDialog(comprar,"saldo insuficiente! ");
                        return;
                    }
                }else if (comprar.getBtXrp().isSelected()){
                    valorUpdate = (resc.getDouble("realval") - Double.parseDouble(comprar.getTxtValor().getText())*resc2.getDouble("xrp"))-resc.getDouble("realval")*0.01;//1 por cento de valor de compra
                    //System.out.println(valorUpdate);
                    if (valorUpdate >= 0) {
                    String sql = "UPDATE public.carteira SET realval = ? WHERE usuario = ?;";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setDouble(1, valorUpdate);
                    //System.out.println(valorUpdate);
                statement.setString(2, c.usuario);
                    //System.out.println(c.usuario);
                statement.execute();
                        //System.out.println("funcionou");
                        valorUpdate = Double.parseDouble(comprar.getTxtValor().getText()) + resc.getDouble("xrp");
                    String sql2 = "UPDATE public.carteira SET xrp = ? WHERE usuario = ?;";
                PreparedStatement statement2 = conn.prepareStatement(sql2);
                statement2.setDouble(1, valorUpdate);
                    //System.out.println(valorUpdate);
                statement2.setString(2, c.usuario);
                    //System.out.println(c.usuario);
                statement2.execute();
                
                
                    }else{
                        JOptionPane.showMessageDialog(comprar,"saldo insuficiente! ");
                        return;
                    }
                }else{
                    comprar.getLblErro().setText("favor selecionar uma opção! ");
                    return;
                }
                JOptionPane.showMessageDialog(comprar,"compra concluida ");
                comprar.getLblErro().setText("");
                //System.out.println(moeda);
                
        }else{
                //System.out.println("n funciona");
            }
         }catch(SQLException e){
             e.printStackTrace();
         }
        
    }
    public void VerificarSenhaCompra(Pessoa p, String senha, SenhaCompra janela){
        logado = p.getUsuario();
        Conexao conexao = new Conexao();
        Carteira cota = new Carteira("cotacao");
        try{
            Connection conn = conexao.getConnection();
            PessoasDAO dao = new PessoasDAO(conn);
            CarteiraDAO cdao = new CarteiraDAO(conn);
            ResultSet res2 = cdao.consultar(cota);
            ResultSet res = dao.consultar(p);
            if (res.next() && res2.next()) {
                senhacomp = res.getString("senha");
                System.out.println(senha);
                System.out.println(senhacomp);
                if (senha.equals(senhacomp)) {
                    System.out.println("senha valida");
                    JOptionPane.showMessageDialog(janela,"login Efetuado");
                    Carteira c = new Carteira(p.getUsuario());
                    janela.setVisible(false);
                    ComprarCrypto comprar = new ComprarCrypto(c);
                    comprar.setVisible(true);
                    comprar.getTxtCotaBtc().setText(String.valueOf(res2.getDouble("btc")));
                    comprar.getTxtCotaEth().setText(String.valueOf(res2.getDouble("eth")));
                    comprar.getTxtCotaXrp().setText(String.valueOf(res2.getDouble("xrp")));
                    
                }else{
                    //System.out.println("senha errada");
                    JOptionPane.showMessageDialog(janela,"Senha incorreta! ");
                }
            }else{
                JOptionPane.showMessageDialog(janela, "Login não efetuado!");
            }
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(login, "Erro de conexão!");
            e.printStackTrace();
        }
    }
    
    public void atualizarCota(Menu janela){
        Pessoa p = new Pessoa("cotacao");
        Carteira c = new Carteira(p);
        c.setUsuario("cotacao");
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.getConnection();
            CarteiraDAO cdao = new CarteiraDAO(conn);
            ResultSet res = cdao.consultar(c);
            //System.out.println(p);
            System.out.println(c);
            if (res.next()) {
                Random rand = new Random();
                maxbtc = res.getDouble("btc") * 1.05;
                minbtc = (res.getDouble("btc")-(res.getDouble("btc")*0.05));
                newbtc = rand.nextDouble((maxbtc - minbtc)+ 1)+ minbtc;
                
                maxeth = res.getDouble("eth") * 1.05;
                mineth = (res.getDouble("eth")-(res.getDouble("eth")*0.05));
                neweth = rand.nextDouble((maxeth - mineth)+ 1)+ mineth;
                
                maxxrp = res.getDouble("xrp") * 1.05;
                minxrp = (res.getDouble("xrp")-(res.getDouble("xrp")*0.05));
                newxrp = rand.nextDouble((maxxrp - minxrp)+ 1)+ minxrp;
                
                String sql = "UPDATE public.carteira SET btc = ?, eth = ?, xrp = ? WHERE usuario = 'cotacao';";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setDouble(1, newbtc);
                statement.setDouble(2, neweth);
                statement.setDouble(3, newxrp);
                statement.execute();
                
                JOptionPane.showMessageDialog(janela, "cotações atualizadas! ");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void vendaController(Carteira c, VendaCrypto venda){
         Conexao conexao = new Conexao();
         Carteira c2 = new Carteira("cotacao");
         try{
         Connection conn = conexao.getConnection();
         CarteiraDAO cdao = new CarteiraDAO(conn);
         CarteiraDAO cdao2 = new CarteiraDAO(conn);
            ResultSet resc2 = cdao2.consultar(c2);
            ResultSet resc = cdao.consultar(c);
            if (resc.next() && resc2.next()) {
                //System.out.println(comprar.getBtBtc().isSelected());
                if (venda.getBtBtc().isSelected()) {
                    //valorUpdate = (resc.getDouble("realval") - Double.parseDouble(venda.getTxtValor().getText())*resc2.getDouble("btc"))-resc.getDouble("realval")*0.02;
                    valorUpdate = resc.getDouble("btc")-Double.parseDouble(venda.getTxtValor().getText());
                    //System.out.println(valorUpdate);
                    if (valorUpdate >= 0) {
                    String sql = "UPDATE public.carteira SET btc = ? WHERE usuario = ?;";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setDouble(1, valorUpdate);
                    //System.out.println(valorUpdate);
                statement.setString(2, c.usuario);
                    //System.out.println(c.usuario);
                statement.execute();
                        //System.out.println("funcionou");
                        valorUpdate = (resc.getDouble("realval") + (Double.parseDouble(venda.getTxtValor().getText())*resc2.getDouble("btc")));
                        valorUpdate = valorUpdate - (valorUpdate * 0.03);//3 por cento de valor de venda
                    String sql2 = "UPDATE public.carteira SET realval = ? WHERE usuario = ?;";
                PreparedStatement statement2 = conn.prepareStatement(sql2);
                statement2.setDouble(1, valorUpdate);
                    //System.out.println(valorUpdate);
                statement2.setString(2, c.usuario);
                    //System.out.println(c.usuario);
                statement2.execute();
                
                
                    }else{
                        JOptionPane.showMessageDialog(venda,"saldo insuficiente! ");
                        return;
                    }
                    
                }else if (venda.getBtEth().isSelected()) {
                    valorUpdate = resc.getDouble("eth")-Double.parseDouble(venda.getTxtValor().getText());
                    //System.out.println(valorUpdate);
                    if (valorUpdate >= 0) {
                    String sql = "UPDATE public.carteira SET eth = ? WHERE usuario = ?;";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setDouble(1, valorUpdate);
                    //System.out.println(valorUpdate);
                statement.setString(2, c.usuario);
                    //System.out.println(c.usuario);
                statement.execute();
                        //System.out.println("funcionou");
                        valorUpdate = resc.getDouble("realval") + (Double.parseDouble(venda.getTxtValor().getText())*resc2.getDouble("eth"));
                        valorUpdate = valorUpdate - (valorUpdate * 0.02);//2 por cento de valor de venda
                    String sql2 = "UPDATE public.carteira SET realval = ? WHERE usuario = ?;";
                PreparedStatement statement2 = conn.prepareStatement(sql2);
                statement2.setDouble(1, valorUpdate);
                    //System.out.println(valorUpdate);
                statement2.setString(2, c.usuario);
                    //System.out.println(c.usuario);
                statement2.execute();
                
                
                    }else{
                        JOptionPane.showMessageDialog(venda,"saldo insuficiente! ");
                        return;
                    }
                }else if (venda.getBtXrp().isSelected()){
                    valorUpdate = resc.getDouble("xrp")-Double.parseDouble(venda.getTxtValor().getText());
                    //System.out.println(valorUpdate);
                    if (valorUpdate >= 0) {
                    String sql = "UPDATE public.carteira SET xrp = ? WHERE usuario = ?;";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setDouble(1, valorUpdate);
                    //System.out.println(valorUpdate);
                statement.setString(2, c.usuario);
                    //System.out.println(c.usuario);
                statement.execute();
                        //System.out.println("funcionou");
                        valorUpdate = resc.getDouble("realval") + (Double.parseDouble(venda.getTxtValor().getText())*resc2.getDouble("xrp"));
                        valorUpdate = valorUpdate - (valorUpdate * 0.01);//1 por cento de valor de venda
                    String sql2 = "UPDATE public.carteira SET realval = ? WHERE usuario = ?;";
                PreparedStatement statement2 = conn.prepareStatement(sql2);
                statement2.setDouble(1, valorUpdate);
                    //System.out.println(valorUpdate);
                statement2.setString(2, c.usuario);
                    //System.out.println(c.usuario);
                statement2.execute();
                
                
                    }else{
                        JOptionPane.showMessageDialog(venda,"saldo insuficiente! ");
                        return;
                    }
                }else{
                    venda.getLblErro().setText("favor selecionar uma opção! ");
                    return;
                }
                JOptionPane.showMessageDialog(venda,"venda concluida ");
                venda.getLblErro().setText("");
                //System.out.println(moeda);
                
        }else{
                //System.out.println("n funciona");
            }
         }catch(SQLException e){
             e.printStackTrace();
         }
        
    }
    public void VerificarSenhaVenda(Pessoa p, String senha, SenhaVenda janela){
        logado = p.getUsuario();
        Conexao conexao = new Conexao();
        Carteira cota = new Carteira("cotacao");
        try{
            Connection conn = conexao.getConnection();
            PessoasDAO dao = new PessoasDAO(conn);
            CarteiraDAO cdao = new CarteiraDAO(conn);
            ResultSet res2 = cdao.consultar(cota);
            ResultSet res = dao.consultar(p);
            if (res.next() && res2.next()) {
                senhacomp = res.getString("senha");
                System.out.println(senha);
                System.out.println(senhacomp);
                if (senha.equals(senhacomp)) {
                    System.out.println("senha valida");
                    JOptionPane.showMessageDialog(janela,"login Efetuado");
                    Carteira c = new Carteira(p.getUsuario());
                    janela.setVisible(false);
                    VendaCrypto venda = new VendaCrypto(c);
                    venda.setVisible(true);
                    venda.getTxtCotaBtc().setText(String.valueOf(res2.getDouble("btc")));
                    venda.getTxtCotaEth().setText(String.valueOf(res2.getDouble("eth")));
                    venda.getTxtCotaXrp().setText(String.valueOf(res2.getDouble("xrp")));
                    
                }else{
                    //System.out.println("senha errada");
                    JOptionPane.showMessageDialog(janela,"Senha incorreta! ");
                }
            }else{
                JOptionPane.showMessageDialog(janela, "Login não efetuado!");
            }
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(login, "Erro de conexão!");
            e.printStackTrace();
        }
    }
}