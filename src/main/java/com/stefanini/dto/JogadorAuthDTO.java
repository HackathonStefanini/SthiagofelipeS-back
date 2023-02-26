package com.stefanini.dto;

import com.stefanini.entity.Jogador;

public class JogadorAuthDTO {
	    private String nickname;
	    private String password;
		
	    public JogadorAuthDTO() {
	    	super();
	    }
	    
	    public JogadorAuthDTO(Long id, String nickname, String password) {
	    	this();
	    	this.nickname = nickname;
	    	this.password = password;
	    }
	    
	    public JogadorAuthDTO(Jogador jogador) {
	    	this();
	    	this.nickname = jogador.getNickname();
	    	this.password = jogador.getPassword();
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
	    
	    public Jogador toEntity() {
	    	Jogador jogador = new Jogador();
	    	jogador.setNickname(nickname);
	    	jogador.setPassword(password);
	    	return jogador;
	    }	    
}
