package com.stefanini.repository;


import java.util.Objects;

import javax.persistence.TypedQuery;
import javax.ws.rs.core.Response;

import com.stefanini.dao.GenericDAO;
import com.stefanini.dto.JogadorDTO;
import com.stefanini.entity.Jogador;
import com.stefanini.exceptions.RegraDeNegocioException;
import com.stefanini.parser.JogadorParser;

public class JogadorRepository extends GenericDAO<Jogador, Long> {
	
	
	public JogadorDTO searchByUserAndPassword(Jogador jogador) {
		StringBuilder sb = new StringBuilder("SELECT * from Jogador as jog ");
		sb.append(" left outer join jog.stefamons as stef ");
		sb.append(" WHERE jog.nickname = :nick ");
		sb.append(" AND  jog.password = :pass ");
		
		TypedQuery<Jogador> query = createQuery(sb.toString());
		query.setParameter("nick", jogador.getNickname());
		query.setParameter("pass", jogador.getPassword());
		
		Jogador entidade = query.getSingleResult();
		if(Objects.isNull(entidade)) {
			throw new RegraDeNegocioException("Usuário e senha incorretos.", Response.Status.NOT_FOUND);
		}
		
		return JogadorParser.EntitytoDto(entidade);
		
	}
	
}
