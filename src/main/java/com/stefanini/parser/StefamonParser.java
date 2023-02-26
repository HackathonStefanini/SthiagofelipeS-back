package com.stefanini.parser;

import com.stefanini.dto.StefamonDTO;
import com.stefanini.entity.Stefamon;

public class StefamonParser {

    public static Stefamon DtotoEntity(StefamonDTO dto) {
        	Stefamon stefamon		= new Stefamon();
        	stefamon.setId			(dto.getId()			);
        	stefamon.setNome		(dto.getNome()		);
        	stefamon.setVida		(dto.getVida()		);
        	stefamon.setAtaque		(dto.getAtaque()		);
        	stefamon.setDefesa		(dto.getDefesa()		);
        	stefamon.setInteligencia(dto.getInteligencia());
        	stefamon.setPoder		(dto.getPoder()		);
        	stefamon.setVelocidade	(dto.getVelocidade()	);
        	stefamon.setUrlFoto		(dto.getUrlFoto()	);
        	return stefamon;
    }

    public static StefamonDTO EntitytoDto(Stefamon stefamon) {
    	StefamonDTO dto = new StefamonDTO();
    	dto.setId			(stefamon.getId()				);
    	dto.setNome			(stefamon.getNome()			);  
    	dto.setVida			(stefamon.getVida()			); 
    	dto.setAtaque		(stefamon.getAtaque()			);	
    	dto.setDefesa		(stefamon.getDefesa()			);
    	dto.setInteligencia	(stefamon.getInteligencia()	); 
    	dto.setPoder		(stefamon.getPoder()			);
    	dto.setVelocidade	(stefamon.getVelocidade()		);
    	dto.setUrlFoto		(stefamon.getUrlFoto()			);
    	return dto;
    }
    
}
