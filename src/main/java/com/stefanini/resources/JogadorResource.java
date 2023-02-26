package com.stefanini.resources;

import com.stefanini.dto.JogadorAuthDTO;
import com.stefanini.dto.JogadorDTO;
import com.stefanini.dto.StefamonDTO;
import com.stefanini.entity.Jogador;
import com.stefanini.service.JogadorService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@ApplicationPath("/jogador")
public class JogadorResource {

    @Inject
    JogadorService jogadorService;

    @GET
    @Path("/{id}")
    public Response pegarPorId(@PathParam("id") Long id){
        return Response.status(Response.Status.OK).entity(jogadorService.pegarPorId(id)).build();
    }

    @GET
    @Path("/todos")
    public Response listarTodos(){
        return Response.status(Response.Status.OK).entity(jogadorService.listarTodos()).build();
    }

    @POST
    public Response salvar(@Valid Jogador jogador) {
        jogadorService.salvar(jogador);
        return Response.status(Response.Status.CREATED).build();
    }

    @POST
    public Response alterar(@Valid Jogador jogador) {
        jogadorService.alterar(jogador);
        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        jogadorService.deletar(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @POST
    @Path("/cadastro")
    public Response cadastrarJogador(@Valid JogadorDTO jogador) {
    	 jogadorService.cadastrar(jogador);
         return Response.status(Response.Status.CREATED).build();
    }

    @POST
    @Path("/login")
    public Response logarJogador(@Valid JogadorAuthDTO jogador) {
    	 JogadorDTO dto = jogadorService.logar(jogador);
         return Response.status(Response.Status.CREATED).entity(dto).build();
    }
    
    @POST
    @Path("/comprar")
    public Response comprarStefamons(@Valid JogadorDTO jogador,  @Valid StefamonDTO stefamon) {
    	jogadorService.comprarStefamon(jogador, stefamon);
    	return Response.status(Response.Status.OK).entity(jogador).build();
    }
    
}
