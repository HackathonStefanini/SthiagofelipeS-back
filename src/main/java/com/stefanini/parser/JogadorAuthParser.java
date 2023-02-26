package com.stefanini.parser;

import com.stefanini.dto.JogadorAuthDTO;
import com.stefanini.entity.Jogador;

public class JogadorAuthParser {
	
	   public static Jogador DtotoEntity(JogadorAuthDTO dto) {
	        Jogador jogador = new Jogador();
	        jogador.setNickname(dto.getNickname());
	    	jogador.setPassword(dto.getPassword());
	    	return jogador;
	    }

	   
	   public static JogadorAuthDTO EntitytoDto(Jogador jogador) {
	        JogadorAuthDTO dto = new JogadorAuthDTO();
	        dto.setNickname(jogador.getNickname());
	        dto.setPassword(jogador.getPassword());
	    	return dto;
	    }
}
