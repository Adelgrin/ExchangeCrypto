/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Adelgrin
 */
public class Pessoa {
    public String nome, cpf, senha, usuario;

    public Pessoa(String nome, String cpf, String senha, String usuario) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.usuario = usuario;
    }

    public Pessoa(String usuario, String senha) {
        this.senha = senha;
        this.usuario = usuario;
    }

    public Pessoa(String usuario) {
        this.usuario = usuario;
    }

    public Pessoa(String nome, String cpf, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "nome=" + nome + ", cpf=" + cpf + ", senha=" + senha + ", usuario=" + usuario + '}';
    }
    
    
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    
    
}
