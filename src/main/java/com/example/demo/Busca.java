package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Busca {

    /**
     * @param inicio
     * @param fim
     * @param nos
     * @param grafo
     * @return Object
     */

    public List<String> amplitude(String inicio, String fim, List<String> nos, List<List<String>> grafo) {
        // Manipular a FILA para a busca
        Lista lista1 = new Lista();

        // Cópia para apresentar o caminho (somente inserção)
        Lista lista2 = new Lista();

        // Insere ponto inicial como nó raiz da árvore
        lista1.insereUltimo(inicio, 0, null);
        lista2.insereUltimo(inicio, 0, null);

        // Controle de nós visitados
        List<List<Object>> visitado = new ArrayList<>();
        List<Object> linha = new ArrayList<>();
        linha.add(inicio);
        linha.add(0);
        visitado.add(linha);

        while (!lista1.vazio()) {

            // Remove o primeiro da fila
            No atual = lista1.deletaUltimo();

            // Direciona para o conjunto de Strings de nos com o mesmo index em grafos.
            int ind = nos.indexOf(atual.getEstado());

            // System.out.println("Índice da cidade: " + nos.indexOf(atual.getEstado()));

            // Varre todas as conexões dentro do grafo a partir de atual
            for (String novo : grafo.get(ind)) {

                // System.out.println("Nó / Cidade: " + novo);

                // Pressuponho que não foi visitado
                boolean flag = true;

                // Controle de nós repetidos
                for (int j = 0; j < visitado.size(); j++) {
                    List<Object> visitadoItem = visitado.get(j);
                    if (visitadoItem.get(0).equals(novo)) {
                        int nivel = (int) visitadoItem.get(1);
                        if (nivel <= (atual.getNivel() + 1)) {
                            flag = false;
                        } else {
                            visitadoItem.set(1, atual.getNivel() + 1);
                        }
                        break;
                    }
                }

                // Se não foi visitado, inclui na fila
                if (flag) {
                    lista1.insereUltimo(novo, atual.getNivel() + 1, atual);
                    lista2.insereUltimo(novo, atual.getNivel() + 1, atual);

                    // Marca como visitado
                    linha = new ArrayList<>();
                    linha.add(novo);
                    linha.add(atual.getNivel() + 1);
                    visitado.add(linha);

                    // Verifica se é o objetivo
                    if (novo.equals(fim)) {
                        List<String> caminho = new ArrayList<>();
                        caminho.addAll(lista2.exibeCaminho());
                        System.out.println("\nFila:\n" + lista1.exibeLista());
                        System.out.println("\nÁrvore de busca:\n" + lista2.exibeLista());
                        return caminho;
                    }
                }
            }
        }

        return Collections.singletonList("Caminho não encontrado"); // Caminho não encontrado
    }// fim da amplitude

    public List<String> profundidade(String inicio, String fim, List<String> nos, List<List<String>> grafo) {
        // Manipular a PILHA para a busca
        Lista l1 = new Lista();

        // Cópia para apresentar o caminho (somente inserção)
        Lista l2 = new Lista();

        // Insere ponto inicial como nó raiz da árvore
        l1.insereUltimo(inicio, 0, null);
        l2.insereUltimo(inicio, 0, null);

        // Controle de nós visitados
        List<List<Object>> visitado = new ArrayList<>();
        List<Object> linha = new ArrayList<>();
        linha.add(inicio);
        linha.add(0);
        visitado.add(linha);

        while (!l1.vazio()) {
            // Remove o último da pilha
            No atual = l1.deletaUltimo();

            int ind = nos.indexOf(atual.getEstado());

            // Varre todos as conexões dentro do grafo a partir de atual (inverso)
            for (int i = grafo.get(ind).size() - 1; i >= 0; i--) {
                String novo = grafo.get(ind).get(i);

                // Pressuponho que não foi visitado
                boolean flag = true;

                // Controle de nós repetidos
                for (int j = 0; j < visitado.size(); j++) {
                    List<Object> visitadoItem = visitado.get(j);
                    if (visitadoItem.get(0).equals(novo)) {
                        int nivel = (int) visitadoItem.get(1);
                        if (nivel <= (atual.getNivel() + 1)) {
                            flag = false;
                        } else {
                            visitadoItem.set(1, atual.getNivel() + 1);
                        }
                        break;
                    }
                }

                // Se não foi visitado, inclui na pilha
                if (flag) {
                    l1.insereUltimo(novo, atual.getNivel() + 1, atual);
                    l2.insereUltimo(novo, atual.getNivel() + 1, atual);

                    // Marca como visitado
                    linha = new ArrayList<>();
                    linha.add(novo);
                    linha.add(atual.getNivel() + 1);
                    visitado.add(linha);

                    // Verifica se é o objetivo
                    if (novo.equals(fim)) {
                        List<String> caminho = new ArrayList<>();
                        caminho.addAll(l2.exibeCaminho());
                        // System.out.println("\nPilha:\n" + l1.exibeLista());
                        // System.out.println("\nÁrvore de busca:\n" + l2.exibeLista());
                        return caminho;
                    }
                }
            }
        }

        return Collections.singletonList("Caminho não encontrado");
    }// fim da profundidade

    public List<String> profundidadeLimitada(String inicio, String fim, List<String> nos, List<List<String>> grafo,
            int limite) {
        // Manipular a PILHA para a busca
        Lista l1 = new Lista();

        // Cópia para apresentar o caminho (somente inserção)
        Lista l2 = new Lista();

        // Insere ponto inicial como nó raiz da árvore
        l1.insereUltimo(inicio, 0, null);
        l2.insereUltimo(inicio, 0, null);

        // Controle de nós visitados
        List<List<Object>> visitado = new ArrayList<>();
        List<Object> linha = new ArrayList<>();
        linha.add(inicio);
        linha.add(0);
        visitado.add(linha);

        while (!l1.vazio()) {
            // Remove o último da pilha
            No atual = l1.deletaUltimo();

            if (atual.getNivel() < limite) {
                int ind = nos.indexOf(atual.getEstado());

                // Varre todos as conexões dentro do grafo a partir de atual (inverso)
                for (int i = grafo.get(ind).size() - 1; i >= 0; i--) {
                    String novo = grafo.get(ind).get(i);

                    // Pressuponho que não foi visitado
                    boolean flag = true;

                    // Controle de nós repetidos
                    for (int j = 0; j < visitado.size(); j++) {
                        List<Object> visitadoItem = visitado.get(j);
                        if (visitadoItem.get(0).equals(novo)) {
                            int nivel = (int) visitadoItem.get(1);
                            if (nivel <= (atual.getNivel() + 1)) {
                                flag = false;
                            } else {
                                visitadoItem.set(1, atual.getNivel() + 1);
                            }
                            break;
                        }
                    }

                    // Se não foi visitado, inclui na pilha
                    if (flag) {
                        l1.insereUltimo(novo, atual.getNivel() + 1, atual);
                        l2.insereUltimo(novo, atual.getNivel() + 1, atual);

                        // Marca como visitado
                        linha = new ArrayList<>();
                        linha.add(novo);
                        linha.add(atual.getNivel() + 1);
                        visitado.add(linha);

                        // Verifica se é o objetivo
                        if (novo.equals(fim)) {
                            List<String> caminho = new ArrayList<>();
                            caminho.addAll(l2.exibeCaminho());
                            // System.out.println("\nPilha:\n" + l1.exibeLista());
                            // System.out.println("\nÁrvore de busca:\n" + l2.exibeLista());
                            return caminho;
                        }
                    }
                }
            }
        }

        return Collections.singletonList("Caminho não encontrado");
    }// fim da profundidade limitada

    public List<String> profundidadeItarativa(String inicio, String fim, List<String> nos, List<List<String>> grafo,
            int limiteMax) {

        for (int limite = 1; limite <= limiteMax; limite++) {

            // Manipular a PILHA para a busca
            Lista l1 = new Lista();

            // Cópia para apresentar o caminho (somente inserção)
            Lista l2 = new Lista();

            // Insere ponto inicial como nó raiz da árvore
            l1.insereUltimo(inicio, 0, null);
            l2.insereUltimo(inicio, 0, null);

            // Controle de nós visitados
            List<List<Object>> visitado = new ArrayList<>();
            List<Object> linha = new ArrayList<>();
            linha.add(inicio);
            linha.add(0);
            visitado.add(linha);

            while (!l1.vazio()) {
                // Remove o último da pilha
                No atual = l1.deletaUltimo();

                if (atual.getNivel() < limite) {
                    int ind = nos.indexOf(atual.getEstado());

                    // Varre todos as conexões dentro do grafo a partir de atual (inverso)
                    for (int i = grafo.get(ind).size() - 1; i >= 0; i--) {
                        String novo = grafo.get(ind).get(i);

                        // Pressuponho que não foi visitado
                        boolean flag = true;

                        // Controle de nós repetidos
                        for (int j = 0; j < visitado.size(); j++) {
                            List<Object> visitadoItem = visitado.get(j);
                            if (visitadoItem.get(0).equals(novo)) {
                                int nivel = (int) visitadoItem.get(1);
                                if (nivel <= (atual.getNivel() + 1)) {
                                    flag = false;
                                } else {
                                    visitadoItem.set(1, atual.getNivel() + 1);
                                }
                                break;
                            }
                        }

                        // Se não foi visitado, inclui na pilha
                        if (flag) {
                            l1.insereUltimo(novo, atual.getNivel() + 1, atual);
                            l2.insereUltimo(novo, atual.getNivel() + 1, atual);

                            // Marca como visitado
                            linha = new ArrayList<>();
                            linha.add(novo);
                            linha.add(atual.getNivel() + 1);
                            visitado.add(linha);

                            // Verifica se é o objetivo
                            if (novo.equals(fim)) {
                                List<String> caminho = new ArrayList<>();
                                caminho.addAll(l2.exibeCaminho());
                                // System.out.println("\nPilha:\n" + l1.exibeLista());
                                // System.out.println("\nÁrvore de busca:\n" + l2.exibeLista());
                                return caminho;
                            }
                        }
                    }
                }
            }

        }

        return Collections.singletonList("Caminho não encontrado");

    }// fim da profundidade limitada

    public List<String> bidirecional(String inicio, String fim, List<String> nos, List<List<String>> grafo) {
        // Primeira Amplitude
        // Manipular a FILA para a busca
        Lista l1 = new Lista();
        // Cópia para apresentar o caminho (somente inserção)
        Lista l2 = new Lista();

        // Segunda Amplitude
        // Manipular a FILA para a busca
        Lista l3 = new Lista();
        // Cópia para apresentar o caminho (somente inserção)
        Lista l4 = new Lista();

        // Insere ponto inicial como nó raiz da árvore
        l1.insereUltimo(inicio, 0, null);
        l2.insereUltimo(inicio, 0, null);

        l3.insereUltimo(fim, 0, null);
        l4.insereUltimo(fim, 0, null);

        // Controle de nós visitados
        List<List<Object>> visitado1 = new ArrayList<>();
        List<Object> linha = new ArrayList<>();
        linha.add(inicio);
        linha.add(0);
        visitado1.add(linha);

        List<List<Object>> visitado2 = new ArrayList<>();
        linha = new ArrayList<>();
        linha.add(fim);
        linha.add(0);
        visitado2.add(linha);

        int ni = 0;
        while (!l1.vazio() || !l3.vazio()) {
            while (!l1.vazio()) {
                // Para ir para o próximo amplitude
                if (ni != l1.primeiro().getNivel()) {
                    break;
                }
                // Remove o primeiro da fila
                No atual = l1.deletaPrimeiro();

                int ind = nos.indexOf(atual.getEstado());

                // Varre todos as conexões dentro do grafo a partir de atual
                for (String novo : grafo.get(ind)) {
                    // Pressuponho que não foi visitado
                    boolean flag = true;

                    // Controle de nós repetidos
                    for (int j = 0; j < visitado1.size(); j++) {
                        List<Object> visitado1Item = visitado1.get(j);
                        if (visitado1Item.get(0).equals(novo)) {
                            int nivel = (int) visitado1Item.get(1);
                            if (nivel <= (atual.getNivel() + 1)) {
                                flag = false;
                            } else {
                                visitado1Item.set(1, atual.getNivel() + 1);
                            }
                            break;
                        }
                    }

                    // Se não foi visitado, inclui na fila
                    if (flag) {
                        l1.insereUltimo(novo, atual.getNivel() + 1, atual);
                        l2.insereUltimo(novo, atual.getNivel() + 1, atual);

                        // Marca como visitado
                        linha = new ArrayList<>();
                        linha.add(novo);
                        linha.add(atual.getNivel() + 1);
                        visitado1.add(linha);

                        // Verifica se é o objetivo
                        flag = false;
                        for (int j = 0; j < visitado2.size(); j++) {
                            List<Object> visitado2Item = visitado2.get(j);
                            if (visitado2Item.get(0).equals(novo)) {
                                flag = true;
                                break;
                            }
                        }

                        if (flag) {
                            List<String> caminho = new ArrayList<>();
                            caminho.addAll(l2.exibeCaminho());
                            caminho.addAll(l4.exibeCaminho1(novo));
                            return caminho;
                        }
                    }
                }
            }

            while (!l3.vazio()) {
                // Para ir para o próximo amplitude
                if (ni != l3.primeiro().getNivel()) {
                    break;
                }

                // Remove o primeiro da fila
                No atual = l3.deletaPrimeiro();

                int ind = nos.indexOf(atual.getEstado());

                // Varre todos as conexões dentro do grafo a partir de atual
                for (String novo : grafo.get(ind)) {
                    // Pressuponho que não foi visitado
                    boolean flag = true;

                    // Controle de nós repetidos
                    for (int j = 0; j < visitado2.size(); j++) {
                        List<Object> visitado2Item = visitado2.get(j);
                        if (visitado2Item.get(0).equals(novo)) {
                            int nivel = (int) visitado2Item.get(1);
                            if (nivel <= (atual.getNivel() + 1)) {
                                flag = false;
                            } else {
                                visitado2Item.set(1, atual.getNivel() + 1);
                            }
                            break;
                        }
                    }

                    // Se não foi visitado, inclui na fila
                    if (flag) {
                        l3.insereUltimo(novo, atual.getNivel() + 1, atual);
                        l4.insereUltimo(novo, atual.getNivel() + 1, atual);

                        // Marca como visitado
                        linha = new ArrayList<>();
                        linha.add(novo);
                        linha.add(atual.getNivel() + 1);
                        visitado2.add(linha);

                        // Verifica se é o objetivo
                        flag = false;
                        for (int j = 0; j < visitado1.size(); j++) {
                            List<Object> visitado1Item = visitado1.get(j);
                            if (visitado1Item.get(0).equals(novo)) {
                                flag = true;
                                break;
                            }
                        }

                        if (flag) {
                            List<String> caminho = new ArrayList<>();
                            caminho.addAll(l4.exibeCaminho());
                            caminho.addAll(l2.exibeCaminho1(novo));
                            Collections.reverse(caminho);
                            return caminho;
                        }
                    }
                }
            }
            ni++;
        }
        return Collections.singletonList("Caminho não encontrado");
    }

    public List<String> buscaCustoUniforme(String inicio, String fim, List<String> nos, List<Map<String, Double>> grafo) {
        // Usamos uma fila de prioridade para ordenar os nós pelo custo
        PriorityQueue<NoCusteado> fila = new PriorityQueue<>(Comparator.comparingDouble(NoCusteado::getCusto));
        // Mapa para rastrear os custos
        // Inicializa o custo do nó inicial como 0
        NoCusteado noInicial = new NoCusteado(null, inicio, 0, 0.0, null, null);
        fila.add(noInicial);

        while (!fila.isEmpty()) {
            // Remove o nó de menor custo da fila
            NoCusteado atual = fila.poll();

            if (atual.getEstado().equals(fim)) {
                // Encontramos o objetivo, construímos o caminho
                return construirCaminho(atual);
            }

            int ind = nos.indexOf(atual.getEstado());
            for (Map.Entry<String, Double> entrada : grafo.get(ind).entrySet()) {
                String novo = entrada.getKey();
                Double pesoAresta = entrada.getValue();
                Double custoParaNodo = atual.getCusto() + pesoAresta;

                // Verifica se o nó já foi visitado ou tem um custo menor
                if (!filaContemEstado(fila, novo) || custoParaNodo < getCustoPorEstado(fila, novo)) {
                    NoCusteado novoNo = new NoCusteado(atual, novo, atual.getNivel() + 1, custoParaNodo, null, null);
                    fila.add(novoNo);
                }
            }
        }

        return Collections.singletonList("Caminho não encontrado"); // Caminho não encontrado
    }

    public List<String> greedy(String inicio, String fim, List<String> nos, List<Map<String, Double>> grafo) {
        int ind_f = nos.indexOf(fim);
        ListaGreedy l1 = new ListaGreedy();
        ListaGreedy l2 = new ListaGreedy();
        List<List<Object>> visitado = new ArrayList<>();

        l1.insereUltimo(inicio, 0, 0, null);
        l2.insereUltimo(inicio, 0, 0, null);
        List<Object> linha = new ArrayList<>();
        linha.add(inicio);
        linha.add(0.0);
        visitado.add(linha);

        while (!l1.vazio()) {
            NoGreedy atual = l1.deletaPrimeiro();

            if (atual.getEstado().equals(fim)) {
                List<String> caminho = l2.exibeArvore2(atual.getEstado(), atual.getValor1());
                return caminho;
            }

            int ind = nos.indexOf(atual.getEstado());
            for (Map.Entry<String, Double> novo : grafo.get(ind).entrySet()) {
                String estado = novo.getKey();
                Double custo = novo.getValue();

                int ind1 = nos.indexOf(estado);

                double v2 = atual.getValor2() + custo; // custo do caminho
                double v1 = buscarHeuristica(ind_f, ind1); 

                boolean flag1 = true;
                boolean flag2 = true;
                for (List<Object> visit : visitado) {
                    if (visit.get(0).equals(estado)) {
                        if ((double) visit.get(1) <= v2) {
                            flag1 = false;
                        } else {
                            visit.set(1, v2);
                            flag2 = false;
                        }
                        break;
                    }
                }

                if (flag1) {
                    l1.inserePos_X(estado, v1, v2, atual);
                    l2.inserePos_X(estado, v1, v2, atual);
                    if (flag2) {
                        List<Object> newLine = new ArrayList<>();
                        newLine.add(estado);
                        newLine.add(v2);
                        visitado.add(newLine);
                    }
                }
            }
        }

        return Collections.singletonList("Caminho não encontrado");
    }

    public List<String> aEstrela(String inicio, String fim, List<String> nos, List<Map<String, Double>> grafo) {
        int indF = nos.indexOf(fim);
        List<String> caminho = new ArrayList<>();
        PriorityQueue<NoAEstrela> fila = new PriorityQueue<>(Comparator.comparingDouble(NoAEstrela::getCusto));
        //List<List<Object>> visitado = new ArrayList<>();

        NoAEstrela noInicial = new NoAEstrela(null, inicio, 0, 0.0, 0.0, 0.0, null, null);
        fila.add(noInicial);

        while (!fila.isEmpty()) {
            NoAEstrela atual = fila.poll();

            if (atual.getEstado().equals(fim)) {
                caminho = construirCaminhoAStar(atual);
                return caminho;
            }

            int ind = nos.indexOf(atual.getEstado());
            for (Map.Entry<String, Double> entrada : grafo.get(ind).entrySet()) {
                String novo = entrada.getKey();
                Double pesoAresta = entrada.getValue();
                Double custoParaNodo = atual.getCusto() + pesoAresta;
                Double v1 = custoParaNodo + buscarHeuristica(indF, nos.indexOf(novo));

                if (!filaContemEstadoAStar(fila, novo) || custoParaNodo < getCustoPorEstadoAStar(fila, novo)) {
                    NoAEstrela novoNo = new NoAEstrela(atual, novo, atual.getNivel() + 1, custoParaNodo, v1, 0.0, null, null);
                    fila.add(novoNo);
                }
            }
        }

        return Collections.singletonList("Caminho não encontrado");
    }

    public List<String> aIaEstrela(String inicio, String fim, List<String> nos, List<Map<String, Double>> grafo, Double limite) {
        int indF = nos.indexOf(fim);
        while (true) {
            List<Double> limExc = new ArrayList<>();
            PriorityQueue<NoAEstrela> fila = new PriorityQueue<>(Comparator.comparingDouble(NoAEstrela::getCusto));
            List<List<Object>> visitado = new ArrayList<>();

            NoAEstrela noInicial = new NoAEstrela(null, inicio, 0, 0.0, 0.0, 0.0, null, null);
            fila.add(noInicial);

            while (!fila.isEmpty()) {
                NoAEstrela atual = fila.poll();

                if (atual.getEstado().equals(fim)) {
                    List<String> caminho = construirCaminhoAStar(atual);
                    return caminho;
                }

                int ind = nos.indexOf(atual.getEstado());
                for (Map.Entry<String, Double> entrada : grafo.get(ind).entrySet()) {
                    String novo = entrada.getKey();
                    Double pesoAresta = entrada.getValue();
                    Double custoParaNodo = atual.getCusto() + pesoAresta;
                    Double v1 = custoParaNodo + buscarHeuristica(indF, nos.indexOf(novo));

                    if (v1 <= limite) {
                        boolean flag1 = true;
                        //boolean flag2 = true;
                        for (List<Object> visit : visitado) {
                            if (visit.get(0).equals(novo)) {
                                if ((double) visit.get(1) <= custoParaNodo) {
                                    flag1 = false;
                                } else {
                                    visit.set(1, custoParaNodo);
                                    //flag2 = false;
                                }
                                break;
                            }
                        }

                        if (flag1) {
                            NoAEstrela novoNo = new NoAEstrela(atual, novo, atual.getNivel() + 1, custoParaNodo, v1, 0.0, null, null);
                            fila.add(novoNo);
                        }
                    } else {
                        limExc.add(v1);
                    }
                }
            }

            if (limExc.isEmpty()) {
                break; // Não há caminho possível dentro do limite
            } else {
                limite = calcularNovoLimite(limExc);
            }
        }

        return Collections.singletonList("Caminho não encontrado");
    }

    // MÉTODOS AUXILIARES

    private List<String> construirCaminho(NoCusteado objetivo) {
        List<String> caminho = new ArrayList<>();
        while (objetivo != null) {
            caminho.add(objetivo.getEstado());
            objetivo = objetivo.getPai();
        }
        Collections.reverse(caminho);
        return caminho;
    }

    private boolean filaContemEstado(PriorityQueue<NoCusteado> fila, String estado) {
        return fila.stream().anyMatch(no -> no.getEstado().equals(estado));
    }

    private Double getCustoPorEstado(PriorityQueue<NoCusteado> fila, String estado) {
        return fila.stream()
                .filter(no -> no.getEstado().equals(estado))
                .findFirst()
                .map(NoCusteado::getCusto)
                .orElse((double) Integer.MAX_VALUE);
    }

    private List<String> construirCaminhoAStar(NoAEstrela objetivo) {
        List<String> caminho = new ArrayList<>();
        while (objetivo != null) {
            caminho.add(objetivo.getEstado());
            objetivo = objetivo.getPai();
        }
        Collections.reverse(caminho);
        return caminho;
    }

    private boolean filaContemEstadoAStar(PriorityQueue<NoAEstrela> fila, String estado) {
        return fila.stream().anyMatch(no -> no.getEstado().equals(estado));
    }

    private Double getCustoPorEstadoAStar(PriorityQueue<NoAEstrela> fila, String estado) {
        return fila.stream()
                .filter(no -> no.getEstado().equals(estado))
                .findFirst()
                .map(NoAEstrela::getCusto)
                .orElse((double) Integer.MAX_VALUE);
    }

    private double buscarHeuristica(int destino, int meuLocal) {

        double[][] distancias = {
            {0,42.92,39.78,23.5,34.25,32.47,63.47,14.64,13.13,57.31,20.56,29.31,45.71,37.69,76.16,48.52,49.36},
            {42.92,0,6.06,65.22,33.21,21.31,67.94,40.17,31.98,47.09,26.73,49.96,20.40,42.65,78.64,17.99,73.41},
            {39.78,6.06,0,61.15,27.10,23.34,71.21,35.26,29.95,51.51,21.84,50.30,25.26,36.57,82.29,14.12,67.45},
            {23.5,65.22,61.15,0,47.07,55.88,79.56,27.17,36.53,78.23,39.72,45.21,69.09,45.65,91.84,67.29,39.52},
            {34.25,33.21,27.10,47.07,0,42.13,88.15,21.09,33.91,73.11,16.66,58.69,49.86,9.51,100.36,24.18,41.37},
            {32.47,21.31,23.34,55.88,42.13,0,48.15,37.49,19.41,30.99,27.57,29.35,13.28,50.78,59.66,37.44,75.62},
            {63.47,67.94,71.21,79.56,88.15,48.15,0,76.04,55.68,24.66,71.74,34.71,49.16,95.51,12.69,85.35,112.81},
            {14.64,40.17,35.26,27.17,21.09,37.49,76.04,0,20.87,66.36,13.44,43.02,49.29,23.11,88.73,40.10,38.47},
            {13.13,31.98,29.95,36.53,33.91,19.41,55.68,20.87,0,45.76,17.33,24.80,32.58,40.22,68.30,41.26,59.10},
            {57.31,47.09,51.51,78.23,73.11,30.99,24.66,66.36,45.76,0,58.29,35.59,26.93,81.73,32.61,65.11,104.81},
            {20.56,26.73,21.84,39.72,16.66,27.57,71.74,13.44,17.33,58.29,0,42.07,37.79,23.96,84.10,28.15,48.87},
            {29.31,49.96,50.30,45.21,58.69,29.35,34.71,43.02,24.80,35.59,42.07,0,39.50,64.58,47.33,63.56,78.36},
            {45.71,20.40,25.26,69.09,49.86,13.28,49.16,49.29,32.58,26.93,37.79,39.50,0,59.17,59.01,38.46,86.66},
            {37.69,42.65,36.57,45.65,9.51,50.78,95.51,23.11,40.22,81.73,23.96,64.58,59.17,0,108.00,32.44,32.79},
            {76.16,78.64,82.29,91.84,100.36,59.66,12.69,88.73,68.30,32.61,84.10,47.33,59.01,108.00,0,96.33,125.49},
            {48.52,17.99,14.12,67.29,24.18,37.44,85.35,40.10,41.26,65.11,28.15,63.56,38.46,32.44,96.33,0,65.19},
            {49.36,73.41,67.45,39.52,41.37,75.62,112.81,38.47,59.10,104.81,48.87,78.36,86.66,32.79,125.49,65.19,0}
        };

		return distancias[destino][meuLocal];
	}

    private double calcularNovoLimite(List<Double> limExc) {
        // Verifique se a lista de valores excluídos está vazia
        if (limExc.isEmpty()) {
            Double infinitoPositivo = Double.POSITIVE_INFINITY;
            System.out.println("Estava vazio >>> " + infinitoPositivo);           
            return infinitoPositivo;
            //return Double.POSITIVE_INFINITY; // Sem limite
        } else {
             System.out.println("Não estava vazio >>> " + limExc);
        }
        
        // Calcule a média dos valores excluídos
        double soma = 0.0;
        for (Double valor : limExc) {
            soma += valor;
        }
        double media = soma / limExc.size();
        
        // Ajuste o novo limite para uma fração (por exemplo, 1.2) da média
        double novoLimite = 1.2 * media;
        
        System.out.println("New limit >>> " + novoLimite);
        return novoLimite;
    }
    

}
