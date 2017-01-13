/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


/**
 *
 * @author Felipe
 */
public class Voo {

    private int id;
    private int idOrigem;
    private int idDestino;
    private double precoBase;
    private String dataVoo;
    private String horaVoo;

    public Voo(int id, int idOrigem, int idDestino, double precoBase, String dataVoo, String horaVoo) {
        this.id = id;
        this.idOrigem = idOrigem;
        this.idDestino = idDestino;
        this.precoBase = precoBase;
        this.dataVoo = dataVoo;
        this.horaVoo = horaVoo;
    }


    public Voo() {

    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the idOrigem
     */
    public int getIdOrigem() {
        return idOrigem;
    }

    /**
     * @param idOrigem the idOrigem to set
     */
    public void setOrigem(int idOrigem) {
        this.idOrigem = idOrigem;
    }

    /**
     * @return the idDestino
     */
    public int getIdDestino() {
        return idDestino;
    }

    /**
     * @param idDestino the idDestino to set
     */
    public void setDestino(int idDestino) {
        this.idDestino = idDestino;
    }

    /**
     * @return the precoBase
     */
    public double getPrecoBase() {
        return precoBase;
    }

    /**
     * @param precoBase the precoBase to set
     */
    public void setPrecoBase(double precoBase) {
        this.precoBase = precoBase;
    }

    /**
     * @return the dataVoo
     */
    public String getDataVoo() {
        return dataVoo;
    }

    /**
     * @param dataVoo the data to set
     */
    public void setDataVoo(String dataVoo) {
        this.dataVoo = dataVoo;
    }

    /**
     * @return the horaVoo
     */
    public String getHoraVoo() {
        return horaVoo;
    }

    /**
     * @param horaVoo the horaVoo to set
     */
    public void setHoraVoo(String horaVoo) {
        this.horaVoo = horaVoo;
    }

    @Override
    public String toString() {
        return "Origem: "+this.getIdOrigem()+" Destino: "+this.getIdDestino()+" precoBase: "+this.getPrecoBase()+" Data: "+ " Hora: "+this.getHoraVoo();
    }
    
    

}
