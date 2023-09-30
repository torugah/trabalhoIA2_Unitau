package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListaGreedy {
    private NoGreedy head = null;
    private NoGreedy tail = null;

    // INSERE NoGreedy INÍCIO DA LISTA
    public void inserePrimeiro(String s, double v1, double v2, NoGreedy p) {
        NoGreedy novoNo = new NoGreedy(p, s, v1, v2, null, null);
        if (head == null) {
            tail = novoNo;
        } else {
            novoNo.setProximo(head);
            head.setAnterior(novoNo);
        }
        head = novoNo;
    }

    // INSERE NoGreedy FIM DA LISTA
    public void insereUltimo(String s, double v1, double v2, NoGreedy p) {
        NoGreedy novoNo = new NoGreedy(p, s, v1, v2, null, null);
        if (head == null) {
            head = novoNo;
        } else {
            tail.setProximo(novoNo);
            novoNo.setAnterior(tail);
        }
        tail = novoNo;
    }

    // INSERE NA POSIÇÃO X (DE ACORDO COM O VALOR v1)
    public void inserePos_X(String s, double v1, double v2, NoGreedy p) {
        // Se a lista estiver vazia
        if (head == null) {
            inserePrimeiro(s, v1, v2, p);
        } else {
            NoGreedy atual = head;
            while (atual.getValor1() < v1) {
                atual = atual.getProximo();
                if (atual == null)
                    break;
            }

            if (atual == head) {
                inserePrimeiro(s, v1, v2, p);
            } else {
                if (atual == null) {
                    insereUltimo(s, v1, v2, p);
                } else {
                    NoGreedy novoNo = new NoGreedy(p, s, v1, v2, null, null);
                    NoGreedy aux = atual.getAnterior();
                    aux.setProximo(novoNo);
                    novoNo.setAnterior(aux);
                    atual.setAnterior(novoNo);
                    novoNo.setProximo(atual);
                }
            }
        }
    }

    // REMOVE NoGreedy INÍCIO DA LISTA
    public NoGreedy deletaPrimeiro() {
        if (head == null) {
            return null;
        } else {
            NoGreedy NoGreedy = head;
            head = head.getProximo();
            if (head != null) {
                head.setAnterior(null);
            } else {
                tail = null;
            }
            return NoGreedy;
        }
    }

    // REMOVE NoGreedy FIM DA LISTA
    public NoGreedy deletaUltimo() {
        if (tail == null) {
            return null;
        } else {
            NoGreedy NoGreedy = tail;
            tail = tail.getAnterior();
            if (tail != null) {
                tail.setProximo(null);
            } else {
                head = null;
            }
            return NoGreedy;
        }
    }

    public boolean vazio() {
        return head == null;
    }

    public void exibeLista() {
        NoGreedy aux = head;
        while (aux != null) {
            System.out.println(aux.getEstado() + " " + aux.getValor1());
            aux = aux.getProximo();
        }
    }

    public List<String> exibeArvore() {
        List<String> caminho = new ArrayList<>();
        NoGreedy atual = tail;
        while (atual.getPai() != null) {
            caminho.add(atual.getEstado());
            atual = atual.getPai();
        }
        caminho.add(atual.getEstado());
        Collections.reverse(caminho);
        return caminho;
    }

    public List<String> exibeArvore1(String s) {
        NoGreedy atual = head;
        while (atual.getEstado() != s) {
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

    public List<String> exibeArvore2(String s, double v1) {
        NoGreedy atual = tail;
        while (atual.getEstado() != s || atual.getValor1() != v1) {
            atual = atual.getAnterior();
        }
        List<String> caminho = new ArrayList<>();
        while (atual.getPai() != null) {
            caminho.add(atual.getEstado());
            atual = atual.getPai();
        }
        caminho.add(atual.getEstado());
        Collections.reverse(caminho);
        return caminho;
    }

    public NoGreedy primeiro() {
        return head;
    }

    public NoGreedy ultimo() {
        return tail;
    }
}
