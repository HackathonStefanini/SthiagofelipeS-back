package com.stefanini.dto;

import com.stefanini.constants.ValuesConstants;
import com.stefanini.entity.Stefamon;

public class StefamonDTO {

    private Long id;
    private String nome;
    private Integer vida;
    private Integer ataque;
    private Integer defesa;
    private Integer inteligencia;
    private Integer poder;
    private Integer velocidade;
    private String urlFoto;

    public StefamonDTO() {
    	super();
    }
    public StefamonDTO(Stefamon stefamon) {
    	this.id = stefamon.getId();
    	this.nome = stefamon.getNome();
    	this.vida = stefamon.getVida();
    	this.ataque = stefamon.getAtaque();
    	this.defesa = stefamon.getDefesa();
    	this.inteligencia = stefamon.getInteligencia();
    	this.poder = stefamon.getPoder();
    	this.velocidade = stefamon.getVelocidade();
    	this.urlFoto = stefamon.getUrlFoto();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getVida() {
		return vida;
	}

	public void setVida(Integer vida) {
		this.vida = vida;
	}

	public Integer getAtaque() {
		return ataque;
	}

	public void setAtaque(Integer ataque) {
		this.ataque = ataque;
	}

	public Integer getDefesa() {
		return defesa;
	}

	public void setDefesa(Integer defesa) {
		this.defesa = defesa;
	}

	public Integer getInteligencia() {
		return inteligencia;
	}

	public void setInteligencia(Integer inteligencia) {
		this.inteligencia = inteligencia;
	}

	public Integer getPoder() {
		return poder;
	}

	public void setPoder(Integer poder) {
		this.poder = poder;
	}

	public Integer getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(Integer velocidade) {
		this.velocidade = velocidade;
	}

	public String getUrlFoto() {
		return urlFoto;
	}

	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}
	
	public int getStefamonValor() {
		return ((this.vida.intValue() + this.ataque.intValue() + this.defesa.intValue() + this.inteligencia.intValue() 
					+ this.poder.intValue() + this.velocidade.intValue())/ValuesConstants.SEIS);
	}
    
    public Stefamon toEntity() {
    	Stefamon stefamon		= new Stefamon();
    	stefamon.setId			(id			);
    	stefamon.setNome		(nome		);
    	stefamon.setVida		(vida		);
    	stefamon.setAtaque		(ataque		);
    	stefamon.setDefesa		(defesa		);
    	stefamon.setInteligencia(inteligencia);
    	stefamon.setPoder		(poder		);
    	stefamon.setVelocidade	(velocidade	);
    	stefamon.setUrlFoto		(urlFoto	);
    	return stefamon;
    }
}
