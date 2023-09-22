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

    public List<String> buscaCustoUniforme(String inicio, String fim, List<String> nos, List<Map<String,Integer>> grafo) {
        // Usamos uma fila de prioridade para ordenar os nós pelo custo
        PriorityQueue<NoCusteado> fila = new PriorityQueue<>(Comparator.comparingInt(NoCusteado::getCusto));
        // Mapa para rastrear os custos
        // Inicializa o custo do nó inicial como 0
        NoCusteado noInicial = new NoCusteado(null, inicio, 0, 0, null, null);
        fila.add(noInicial);

        while (!fila.isEmpty()) {
            // Remove o nó de menor custo da fila
            NoCusteado atual = fila.poll();

            if (atual.getEstado().equals(fim)) {
                // Encontramos o objetivo, construímos o caminho
                return construirCaminho(atual);
            }

            int ind = nos.indexOf(atual.getEstado());
            for (Map.Entry<String, Integer> entrada : grafo.get(ind).entrySet()) {
                String novo = entrada.getKey();
                int pesoAresta = entrada.getValue();
                int custoParaNodo = atual.getCusto() + 1; // Custo uniforme, assumindo que todos têm custo igual a 1

                // Verifica se o nó já foi visitado ou tem um custo menor
                if (!filaContemEstado(fila, novo) || custoParaNodo < getCustoPorEstado(fila, novo)) {
                    NoCusteado novoNo = new NoCusteado(atual, novo, atual.getNivel() + 1, custoParaNodo, null, null);
                    fila.add(novoNo);
                }
            }
        }

        return Collections.singletonList("Caminho não encontrado"); // Caminho não encontrado
    }

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

    private int getCustoPorEstado(PriorityQueue<NoCusteado> fila, String estado) {
        return fila.stream()
                .filter(no -> no.getEstado().equals(estado))
                .findFirst()
                .map(NoCusteado::getCusto)
                .orElse(Integer.MAX_VALUE);
    }

}
