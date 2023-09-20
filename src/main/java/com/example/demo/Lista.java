package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lista {

    private No head;
    private No tail;

    // INSERE NO INÍCIO DA LISTA
    public void inserePrimeiro(String estado, int nivel, No pai) {
        No novoNo = new No(pai, estado, nivel, null, null);
        if (head == null) {
            tail = novoNo;
            head = novoNo;
        } else {
            novoNo.setProximo(head);
            head.setAnterior(novoNo);
            head = novoNo;
        }
    }

    // INSERE NO FIM DA LISTA
    public void insereUltimo(String estado, int nivel, No p) {
        No novoNo = new No(p, estado, nivel, null, null);
        if (head == null) {
            head = novoNo;
            tail = novoNo;
        } else {
            tail.setProximo(novoNo);
            novoNo.setAnterior(tail);
            tail = novoNo;
        }
    }

    // REMOVE NO INÍCIO DA LISTA
    public No deletaPrimeiro() {
        if (head == null) {
            return null;
        } else {
            No no = head;
            head = head.getProximo();
            if (head != null) {
                head.setAnterior(null);
            } else {
                tail = null;
            }
            return no;
        }
    }

    // REMOVE NO FIM DA LISTA
    public No deletaUltimo() {
        if (tail == null) {
            return null;
        } else {
            No no = tail;
            tail = tail.getAnterior();
            if (tail != null) {
                tail.setProximo(null);
            } else {
                head = null;
            }
            return no;
        }
    }

    // RETORNA O PRIMEIRO DA LISTA
    public No primeiro() {
        return head;
    }

    // RETORNA O ÚLTIMO DA LISTA
    public No ultimo() {
        return tail;
    }

    // VERIFICA SE A LISTA ESTÁ VAZIA
    public boolean vazio() {
        return head == null;
    }

    // EXIBE O CONTEÚDO DA LISTA
    public List<Object[]> exibeLista() {
        List<Object[]> result = new ArrayList<>();
        No aux = head;

        while (aux != null) {
            Object[] temp = {aux.getEstado(), aux.getNivel()};
            result.add(temp);
            aux = aux.getProximo();
        }

        return result;
    }

    // EXIBE O CAMINHO ENCONTRADO
    public List<String> exibeCaminho() {
        List<String> caminho = new ArrayList<>();
        No atual = tail;

        while (atual.getPai() != null) {
            caminho.add(atual.getEstado());
            atual = atual.getPai();
        }
        caminho.add(atual.getEstado());
        Collections.reverse(caminho);
        return caminho;
    }

    // EXIBE O CAMINHO ENCONTRADO (BIDIRECIONAL)
    public List<String> exibeCaminho1(Object valor) {
        No atual = head;
        while (!atual.getEstado().equals(valor)) {
            atual = atual.getProximo();
        }

        List<String> caminho = new ArrayList<>();
        atual = atual.getPai();

        while (atual.getPai() != null) {
            caminho.add(atual.getEstado());
            atual = atual.getPai();
        }
        caminho.add(atual.getEstado());
        Collections.reverse(caminho);
        return caminho;
    }
}
