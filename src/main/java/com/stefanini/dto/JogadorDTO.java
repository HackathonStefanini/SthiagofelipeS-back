package com.stefanini.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.stefanini.entity.Jogador;

public class JogadorDTO{
	
	private Long id;
    private String nickname;
    private String password;
    private BigDecimal saldo;
    private List<StefamonDTO> stefamons = new ArrayList<>();
	
    public JogadorDTO() {
    	super();
    }
    
    public JogadorDTO(Long id, String nickname, String password, BigDecimal saldo,  List<StefamonDTO> stefamons) {
    	this();
    	this.id = id;
    	this.nickname = nickname;
    	this.password = password;
    	this.saldo = saldo;
    	this.stefamons = stefamons;
    }
    
    public JogadorDTO(Jogador jogador) {
    	this();
    	this.id = jogador.getId();
    	this.nickname = jogador.getNickname();
    	this.password = jogador.getPassword();
    	this.saldo = jogador.getSaldo();
    	this.stefamons = jogador.getStefamons().stream().map(StefamonDTO::new).collect(Collectors.toList());
    }
    
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	public List<StefamonDTO> getStefamons() {
		return stefamons;
	}
	public void setStefamons(List<StefamonDTO> stefamons) {
		this.stefamons = stefamons;
	}
	
	public void addStefamon(StefamonDTO stefamon) {
		if(Objects.nonNull(this.stefamons)) {
			this.stefamons.add(stefamon);
		}
	}
    
    public Jogador toEntity() {
    	Jogador jogador = new Jogador();
    	jogador.setId(id);
    	jogador.setNickname(nickname);
    	jogador.setPassword(password);
    	jogador.setSaldo(saldo);
    	jogador.setStefamons(this.getStefamons().stream().map(s -> s.toEntity()).collect(Collectors.toList()));
    	return jogador;
    }
    
}
