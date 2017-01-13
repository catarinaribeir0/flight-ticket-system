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
public class Cartao {
	private Usuario usuario;
	private BandeiraCartao bandeira;
	private String numero;
	private String titular;
	private String mesVencimento;
	private String anoVencimento;
	private String cvv;

	public Cartao(Usuario usuario, BandeiraCartao bandeira, String numero, String nomeCartao, String mesVcto,
			String anoVcto, String cvv) {
		this.usuario = usuario;
		this.bandeira = bandeira;
		this.numero = numero;
		this.titular = nomeCartao;
		this.mesVencimento = mesVcto;
		this.anoVencimento = anoVcto;
		this.cvv = cvv;
	}
}
