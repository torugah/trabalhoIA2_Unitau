package com.example.demo.controller;

import com.example.demo.Busca;
import com.example.demo.TrabalhoIaApplication;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/caminho") 
public class CaminhoController {
    
    @GetMapping(value = "/{origem}/{destino}/{metodo}")
    public List<String> retornaCaminho(@PathVariable String origem, @PathVariable String destino, @PathVariable String metodo){
        
        TrabalhoIaApplication trab = new TrabalhoIaApplication();
        
        Busca busca = new Busca();
       
        switch(metodo){
            case "amplitude":
                List<String> amplitude = busca.amplitude(origem, destino, trab.getCidades(), trab.getCidadesPorLinha());
                return amplitude;
            case "profundidade":
                List<String> profundidade = busca.profundidade(origem, destino, trab.getCidades(), trab.getCidadesPorLinha());
                return profundidade;
            case "profundidadeLimitada":
                List<String> profundidadeLimitada = busca.profundidadeLimitada(origem, destino, trab.getCidades(), trab.getCidadesPorLinha(), 6);
                return profundidadeLimitada;
            case "profundidadeIterativa":
                List<String> profundidadeIterativa = busca.profundidadeItarativa(origem, destino, trab.getCidades(), trab.getCidadesPorLinha(), 15);
                return profundidadeIterativa;
            case "bidirecional":
                List<String> bidirecional = busca.bidirecional(origem, destino, trab.getCidades(), trab.getCidadesPorLinha());
                return bidirecional;
        }

        return null;
    }

    @GetMapping("/mensagem")
    public String getMensagem(@RequestParam(name = "nome", defaultValue = "Visitante") String nome) {
        return "Olá, " + nome + "! Esta é uma mensagem de exemplo.";
    }
    
}
