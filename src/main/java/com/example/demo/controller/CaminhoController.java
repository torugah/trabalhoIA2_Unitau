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
    public List<String> retornaCaminho(@PathVariable String origem, @PathVariable String destino, @PathVariable String metodo, @RequestParam String limite){
        
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
                List<String> profundidadeLimitada = busca.profundidadeLimitada(origem, destino, trab.getCidades(), trab.getCidadesPorLinha(), Integer.parseInt(limite.replace(',', '.')));
                return profundidadeLimitada;
            case "profundidadeIterativa":
                List<String> profundidadeIterativa = busca.profundidadeItarativa(origem, destino, trab.getCidades(), trab.getCidadesPorLinha(), Integer.parseInt(limite.replace(',', '.')));
                return profundidadeIterativa;
            case "bidirecional":
                List<String> bidirecional = busca.bidirecional(origem, destino, trab.getCidades(), trab.getCidadesPorLinha());
                return bidirecional;
            case "custoUniforme":
                List<String> custoUniforme = busca.buscaCustoUniforme(origem, destino, trab.getCidades(), trab.lerCidadesPorLinhaCusteado());
                return custoUniforme;
            case "greedy":
                List<String> greedy = busca.greedy(origem, destino, trab.getCidades(), trab.lerCidadesPorLinhaCusteado());
                return greedy;
            case "aEstrela":
                List<String> aEstrela = busca.aEstrela(origem, destino, trab.getCidades(), trab.lerCidadesPorLinhaCusteado());
                return aEstrela;
            case "aiaEstrela":
                List<String> aIaEstrela = busca.aIaEstrela(origem, destino, trab.getCidades(), trab.lerCidadesPorLinhaCusteado(), Double.parseDouble(limite.replace(',', '.')));
                return aIaEstrela;
        }

        return null;
    }

    // <editor-fold defaultstate="collapsed" desc="código antigo">
    /* 
    @GetMapping(value = "/new/{origem}/{destino}/{metodo}")
    public List<String> retornaCaminhoNovo(@PathVariable String origem, @PathVariable String destino, @PathVariable String metodo){
        
        TrabalhoIaApplication trab = new TrabalhoIaApplication();
        
        Busca busca = new Busca();
       
        switch(metodo){
            case "custoUniforme":
                List<String> custoUniforme = busca.buscaCustoUniforme(origem, destino, trab.getCidades(), trab.lerCidadesPorLinhaCusteado());
                return custoUniforme;
            case "greedy":
                List<String> greedy = busca.greedy(origem, destino, trab.getCidades(), trab.lerCidadesPorLinhaCusteado());
                return greedy;
        }

        return null;
    }
    */
    // </editor-fold>

    @GetMapping("/mensagem")
    public String getMensagem(@RequestParam(name = "nome", defaultValue = "Visitante") String nome) {
        return "Olá, " + nome + "! Esta é uma mensagem de exemplo.";
    }
    
}
