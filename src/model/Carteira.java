/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Adelgrin
 */
public class Carteira{
    public String usuario;
    public double real, btc, eth, xrp;
    public Pessoa p;

    public Carteira(String usuario, double real, double btc, double eth, double xrp) {
        this.usuario = usuario;
        this.real = real;
        this.btc = btc;
        this.eth = eth;
        this.xrp = xrp;
    }

    public Carteira(String usuario) {
        this.usuario = usuario;
    }
    public Carteira(Pessoa p){
        this.p = p;
    }
    

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    

    public double getReal() {
        return real;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public double getBtc() {
        return btc;
    }

    public void setBtc(double btc) {
        this.btc = btc;
    }

    public double getEth() {
        return eth;
    }

    public void setEth(double eth) {
        this.eth = eth;
    }

    public double getXrp() {
        return xrp;
    }

    public void setXrp(double xrp) {
        this.xrp = xrp;
    }

    @Override
    public String toString() {
        return "Carteira{" + "usuario=" + usuario + ", real=" + real + ", btc=" + btc + ", eth=" + eth + ", xrp=" + xrp + ", p=" + p + '}';
    }
    
    
}
