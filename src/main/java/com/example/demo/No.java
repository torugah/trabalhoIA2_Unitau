package com.example.demo;

public class No {
    private No pai;
    private String estado;
    private int nivel;
    private No anterior;
    private No proximo;

    public No() {
        this.pai = null;
        this.estado = null;
        this.nivel = 0;
        this.anterior = null;
        this.proximo = null;
    }

    public No(No pai, String estado, int nivel, No anterior, No proximo) {
        this.pai = pai;
        this.estado = estado;
        this.nivel = nivel;
        this.anterior = anterior;
        this.proximo = proximo;
    }

    public No getPai() {
        return pai;
    }

    public void setPai(No pai) {
        this.pai = pai;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public No getAnterior() {
        return anterior;
    }

    public void setAnterior(No anterior) {
        this.anterior = anterior;
    }

    public No getProximo() {
        return proximo;
    }

    public void setProximo(No proximo) {
        this.proximo = proximo;
    }
}
