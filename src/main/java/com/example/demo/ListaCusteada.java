package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class ListaCusteada {

    private NoCusteado head;
    private NoCusteado tail;

    // INSERE NO INÍCIO DA LISTA
    public void inserePrimeiro(NoCusteado novoNo) {
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
    public void insereUltimo(NoCusteado novoNo) {
        if (head == null) {
            head = novoNo;
            tail = novoNo;
        } else {
            tail.setProximo(novoNo);
            novoNo.setAnterior(tail);
            tail = novoNo;
        }
    }

    // REMOVE O NÓ COM MENOR CUSTO DA LISTA
    public NoCusteado deletaMenorCusto() {
        if (head == null) {
            return null;
        } else {
            NoCusteado noMenorCusto = encontrarNoMenorCusto();
            if (noMenorCusto == head) {
                head = head.getProximo();
                if (head != null) {
                    head.setAnterior(null);
                } else {
                    tail = null;
                }
            } else if (noMenorCusto == tail) {
                tail = tail.getAnterior();
                if (tail != null) {
                    tail.setProximo(null);
                } else {
                    head = null;
                }
            } else {
                NoCusteado anterior = noMenorCusto.getAnterior();
                NoCusteado proximo = noMenorCusto.getProximo();
                anterior.setProximo(proximo);
                proximo.setAnterior(anterior);
            }
            return noMenorCusto;
        }
    }

    // ENCONTRA O NÓ COM MENOR CUSTO NA LISTA
    private NoCusteado encontrarNoMenorCusto() {
        NoCusteado atual = head;
        NoCusteado menorCusto = head;

        while (atual != null) {
            if (atual.getCusto() < menorCusto.getCusto()) {
                menorCusto = atual;
            }
            atual = atual.getProximo();
        }

        return menorCusto;
    }

    // RETORNA O PRIMEIRO DA LISTA
    public NoCusteado primeiro() {
        return head;
    }

    // RETORNA O ÚLTIMO DA LISTA
    public NoCusteado ultimo() {
        return tail;
    }

    // VERIFICA SE A LISTA ESTÁ VAZIA
    public boolean vazio() {
        return head == null;
    }

    // EXIBE O CONTEÚDO DA LISTA
    public List<NoCusteado> exibeLista() {
        List<NoCusteado> result = new ArrayList<>();
        NoCusteado aux = head;

        while (aux != null) {
            result.add(aux);
            aux = aux.getProximo();
        }

        return result;
    }
}
