package com.stefanini.parser;

import java.util.stream.Collectors;

import com.stefanini.dto.JogadorDTO;
import com.stefanini.entity.Jogador;

public class JogadorParser {

	   public static Jogador DtotoEntity(JogadorDTO dto) {
	        Jogador jogador = new Jogador();
	    	jogador.setId(dto.getId());
	    	jogador.setNickname(dto.getNickname());
	    	jogador.setPassword(dto.getPassword());
	    	jogador.setSaldo(dto.getSaldo());
	    	jogador.setStefamons(dto.getStefamons().stream().map(s -> StefamonParser.DtotoEntity(s)).collect(Collectors.toList()));
	    	return jogador;
	    }
	   
	   public static JogadorDTO EntitytoDto(Jogador jogador) {
		   
		   JogadorDTO dto = new JogadorDTO();
	    	
		   	dto.setId		(jogador.getId()                                                                   );
	    	dto.setNickname	(jogador.getNickname()                                                             );
	    	dto.setPassword	(jogador.getPassword()                                                             );
	    	dto.setSaldo	(jogador.getSaldo()                                                                );
	    	dto.setStefamons(jogador.getStefamons().stream().map(s -> StefamonParser.EntitytoDto(s)).collect(Collectors.toList()));
	    	
	    	return dto;
	    }
	   
	    
}
