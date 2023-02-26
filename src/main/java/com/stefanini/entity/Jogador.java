package com.stefanini.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;

import com.stefanini.constants.AlertsConstants;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Table(schema = "tb_jogador")
public class Jogador {

    @Id
    @Column(name = "id_jogador")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotNull(message = AlertsConstants.NOT_NULL)
    @NotEmpty(message = AlertsConstants.NOT_EMPTY)
    private String nickname;

    @Column(nullable = false)
    @NotNull(message = AlertsConstants.NOT_NULL)
    @NotEmpty(message = AlertsConstants.NOT_EMPTY)
    @Size(min = 4 , max = 10, message = AlertsConstants.TAMANHO_MIN_4_MAX_10)
    private String password;

    @Column(nullable = false)
    @ColumnDefault("0")
    @NotNull(message = AlertsConstants.NOT_NULL)
    @NotEmpty(message = AlertsConstants.NOT_EMPTY)
    private BigDecimal saldo;


    @ManyToMany
    @JoinTable(name = "Jogador_Stefamon",
            joinColumns = {@JoinColumn(name = "id_jogador")},
            inverseJoinColumns = {@JoinColumn(name = "id")})
    private List<Stefamon> stefamons = new ArrayList<>();

    public Jogador() {
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

	public List<Stefamon> getStefamons() {
		return stefamons;
	}

	public void setStefamons(List<Stefamon> stefamons) {
		this.stefamons = stefamons;
	}
	
	public void addStefamon(Stefamon stefamon) {
		this.stefamons.add(stefamon);
	}
    
    
}
