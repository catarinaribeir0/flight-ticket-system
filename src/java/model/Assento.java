/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Leonardo
 */
public class Assento {

    private int idAssento;
    private int numeroAssento;
    private boolean ocupado;
    private String tipoAssento;
    private double peso;
    private int idVoo;

    public Assento(int idAssento, int numeroAssento, boolean ocupado, String tipoAssento, double peso, int idVoo) {
        this.idAssento = idAssento;
        this.numeroAssento = numeroAssento;
        this.ocupado = ocupado;
        this.tipoAssento = tipoAssento;
        this.peso = peso;
        this.idVoo = idVoo;
    }

    public int getIdAssento() {
        return idAssento;
    }

    public void setIdAssento(int idAssento) {
        this.idAssento = idAssento;
    }

    public int getIdVoo() {
        return idVoo;
    }

    public void setIdVoo(int idVoo) {
        this.idVoo = idVoo;
    }

    /**
     * @return the numeroAssento
     */
    public int getNumeroAssento() {
        return numeroAssento;
    }

    /**
     * @param numeroAssento the numeroAssento to set
     */
    public void setNumeroAssento(int numeroAssento) {
        this.numeroAssento = numeroAssento;
    }

    /**
     * @return the ocupado
     */
    public boolean isOcupado() {
        return ocupado;
    }

    /**
     * @param ocupado the ocupado to set
     */
    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public String getTipoAssento() {
        return tipoAssento;
    }

    public void setTipoAssento(String tipoAssento) {
        this.tipoAssento = tipoAssento;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

}
