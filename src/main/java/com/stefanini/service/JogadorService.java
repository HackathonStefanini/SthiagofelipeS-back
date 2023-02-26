package com.stefanini.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import com.stefanini.constants.ValuesConstants;
import com.stefanini.dto.JogadorAuthDTO;
import com.stefanini.dto.JogadorDTO;
import com.stefanini.dto.StefamonDTO;
import com.stefanini.entity.Jogador;
import com.stefanini.exceptions.RegraDeNegocioException;
import com.stefanini.parser.JogadorAuthParser;
import com.stefanini.parser.JogadorParser;
import com.stefanini.repository.JogadorRepository;
import com.stefanini.utils.PasswordUtils;

public class JogadorService {

	@Inject
    JogadorRepository jogadorRepository;

	@Inject
	StefamonService stefamonService;
	
	
    public void salvar(Jogador jogador) {
        jogadorRepository.save(jogador);
    }

    public Jogador pegarPorId(Long id) {
        var jogador = jogadorRepository.findById(id);
        if(Objects.isNull(jogador)) {
            throw new RegraDeNegocioException("Ocorreu um erro ao buscar o Jogador de id " + id, Response.Status.NOT_FOUND);
        }
        return jogador;
    }

    public void alterar(Jogador jogador) {
        jogadorRepository.update(jogador);
    }

    public void deletar(Long id) {
        jogadorRepository.delete(id);
    }

    public List<Jogador> listarTodos() {
        return jogadorRepository.listAll();
    }
    
    public JogadorDTO cadastrar(JogadorDTO jogador) {
    	if(Objects.isNull(jogador)) {
            throw new RegraDeNegocioException("Ocorreu um erro ao buscar o Jogador informado ", Response.Status.NOT_FOUND);
        }
    	
    	if(!PasswordUtils.isValidPassword(jogador.getPassword())) {
    		throw new RegraDeNegocioException("Formato de senha inválida.", Response.Status.NOT_FOUND);
    	}
    	
    	jogador.setPassword(PasswordUtils.encodeBase64(jogador.getPassword()));
    	
    	salvar(JogadorParser.DtotoEntity(jogador));
    	
    	return jogadorRepository.searchByUserAndPassword(JogadorParser.DtotoEntity(jogador));
    }
    
    public JogadorDTO logar(JogadorAuthDTO jogador) {
    	if(Objects.isNull(jogador)) {
            throw new RegraDeNegocioException("Ocorreu um erro ao buscar o Jogador informado ", Response.Status.NOT_FOUND);
        }
    	if(jogador.getPassword().isBlank()) {
    		 throw new RegraDeNegocioException("É necessário inserir a senha.", Response.Status.BAD_REQUEST);
    	}
    	
    	if(jogador.getNickname().isBlank()) {
   		 throw new RegraDeNegocioException("É necessário inserir o usuário.", Response.Status.BAD_REQUEST);
    	}
    	
    	jogador.setPassword(PasswordUtils.encodeBase64(jogador.getPassword()));
    	
    	return jogadorRepository.searchByUserAndPassword(JogadorAuthParser.DtotoEntity(jogador));
    	
    }
    
    public void comprarStefamon(JogadorDTO jogador, StefamonDTO stefamon) {
    	if(jogador.getStefamons().stream().anyMatch(s -> s.getId() == stefamon.getId())) {
    		throw new RegraDeNegocioException("Você já tem esse Stefamon.", Response.Status.NOT_MODIFIED);
    	}

    	if(jogador.getStefamons().stream().count() >= ValuesConstants.SEIS) {
    		throw new RegraDeNegocioException("Você já tem o máximo de Stefamons possíveis.", Response.Status.NOT_MODIFIED);
		}
    	
		if(jogador.getSaldo().doubleValue() < Double.valueOf(stefamon.getStefamonValor()).doubleValue()) {
			throw new RegraDeNegocioException("Você não tem saldo para comprar esse Stefamon.", Response.Status.NOT_MODIFIED);
		}
		
		jogador.addStefamon(stefamon);
		
		salvar(JogadorParser.DtotoEntity(jogador));
			
    }
    
    private void validarJogadorBatalhar(JogadorDTO jogador) {
    	if(jogador.getStefamons().isEmpty()) {
    		throw new RegraDeNegocioException("Essa batalha não pode ser realizada. Verifique se voçê ou seu adversário tem Stefamons.", Response.Status.BAD_REQUEST);
    	}
    	
    	if(Objects.isNull(jogadorRepository.findById(jogador.getId()))) {
    		throw new RegraDeNegocioException("Essa batalha não foi realizada.", Response.Status.NOT_FOUND);
    	}
    	
    }
    
    public String batalhar(JogadorDTO jogador, JogadorDTO adversario) {
    	validarJogadorBatalhar(jogador);
    	validarJogadorBatalhar(adversario);
    	
    	int valorAtaque = jogador.getStefamons().stream().mapToInt(s -> s.getStefamonValor()).sum();
    	int valorAdvers = adversario.getStefamons().stream().mapToInt(s -> s.getStefamonValor()).sum();
    	
    	
    	jogador.setSaldo(BigDecimal.valueOf(jogador.getSaldo().doubleValue()+(valorAdvers- valorAtaque)));
    	adversario.setSaldo(BigDecimal.valueOf(adversario.getSaldo().doubleValue()+(valorAdvers - valorAtaque)));
    	
    	salvar(JogadorParser.DtotoEntity(jogador));
    	salvar(JogadorParser.DtotoEntity(adversario));
    	
    	if( valorAtaque >= valorAdvers) {
    		return "Você venceu";
    	}
    	return "Você perdeu";
    }
    
}
