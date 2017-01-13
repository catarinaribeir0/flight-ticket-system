/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Felipe
 */
public class Passagem {

    private int idPassagem;
    private Usuario usuario;
    private Voo voo;
    private Aeroporto aeroportoOrigem;
    private Aeroporto aeroportoDestino;
    private Assento assento;
    private double preco;

    private String nome;
    private String dataNasc;
    private String nacionalidade;
    private String rg;
    private boolean deficiencia;
    private String passaporte;
    
    private boolean checkin = false;
    private String telefone;

    public Passagem(Usuario usuario, Voo voo, Aeroporto aeroportoOrigem, Aeroporto aeroportoDestino,
            Assento assento, double preco, String nome, String dataNasc, String nacionalidade, String rg,
            boolean deficiencia, String passaporte, boolean checkin) {
        this.usuario = usuario;
        this.voo = voo;
        this.aeroportoOrigem = aeroportoOrigem;
        this.aeroportoDestino = aeroportoDestino;
        this.assento = assento;
        this.preco = preco;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.nacionalidade = nacionalidade;
        this.rg = rg;
        this.deficiencia = deficiencia;
        this.passaporte = passaporte;
        telefone = "";
    }

    public Passagem(int idPassagem, Usuario usuario, Voo voo, Aeroporto aeroportoOrigem, Aeroporto aeroportoDestino, 
            Assento assento, double preco, String nome, String dataNasc, String nacionalidade, String rg,
            boolean deficiencia, String passaporte, boolean checkin, String telefone) {
        this.idPassagem = idPassagem;
        this.usuario = usuario;
        this.voo = voo;
        this.aeroportoOrigem = aeroportoOrigem;
        this.aeroportoDestino = aeroportoDestino;
        this.assento = assento;
        this.preco = preco;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.nacionalidade = nacionalidade;
        this.rg = rg;
        this.deficiencia = deficiencia;
        this.passaporte = passaporte;
        this.checkin = checkin;
        this.telefone = telefone;
    }

    public int getIdPassagem() {
        return idPassagem;
    }

    public void setIdPassagem(int idPassagem) {
        this.idPassagem = idPassagem;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Voo getVoo() {
        return voo;
    }

    public void setVoo(Voo voo) {
        this.voo = voo;
    }

    public Aeroporto getAeroportoOrigem() {
        return aeroportoOrigem;
    }

    public void setAeroportoOrigem(Aeroporto aeroportoOrigem) {
        this.aeroportoOrigem = aeroportoOrigem;
    }

    public Aeroporto getAeroportoDestino() {
        return aeroportoDestino;
    }

    public void setAeroportoDestino(Aeroporto aeroportoDestino) {
        this.aeroportoDestino = aeroportoDestino;
    }

    public Assento getAssento() {
        return assento;
    }

    public void setAssento(Assento assento) {
        this.assento = assento;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public boolean isDeficiencia() {
        return deficiencia;
    }

    public void setDeficiencia(boolean deficiencia) {
        this.deficiencia = deficiencia;
    }

    public String getPassaporte() {
        return passaporte;
    }

    public void setPassaporte(String passaporte) {
        this.passaporte = passaporte;
    }

    public boolean isCheckin() {
        return checkin;
    }

    public void setCheckin(boolean checkin) {
        this.checkin = checkin;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    
    
    @Override
    public String toString() {
        return "";
    }
}
