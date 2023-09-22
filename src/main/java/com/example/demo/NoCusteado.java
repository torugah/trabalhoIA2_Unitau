package com.example.demo;

public class NoCusteado {
    private NoCusteado pai;
    private String estado;
    private int nivel;
    private int custo; // Adicionado campo de custo
    private NoCusteado anterior;
    private NoCusteado proximo;

    public NoCusteado() {
        this.pai = null;
        this.estado = null;
        this.nivel = 0;
        this.custo = 0; // Inicialmente, o custo pode ser definido como 0
        this.anterior = null;
        this.proximo = null;
    }

    public NoCusteado(NoCusteado pai, String estado, int nivel, int custo, NoCusteado anterior, NoCusteado proximo) {
        this.pai = pai;
        this.estado = estado;
        this.nivel = nivel;
        this.custo = custo;
        this.anterior = anterior;
        this.proximo = proximo;
    }

    public NoCusteado getPai() {
        return pai;
    }

    public void setPai(NoCusteado pai) {
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

    public int getCusto() {
        return custo;
    }

    public void setCusto(int custo) {
        this.custo = custo;
    }

    public NoCusteado getAnterior() {
        return anterior;
    }

    public void setAnterior(NoCusteado anterior) {
        this.anterior = anterior;
    }

    public NoCusteado getProximo() {
        return proximo;
    }

    public void setProximo(NoCusteado proximo) {
        this.proximo = proximo;
    }
}
