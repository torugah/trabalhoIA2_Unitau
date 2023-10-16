package com.example.demo;

public class NoAEstrela {
    private NoAEstrela pai;
    private String estado;
    private int nivel;
    private double custo;
    private double valor1;
    private double valor2;
    private NoAEstrela anterior;
    private NoAEstrela proximo;

    public NoAEstrela(NoAEstrela pai, String estado, int nivel, double custo, double valor1, double valor2, NoAEstrela anterior, NoAEstrela proximo) {
        this.pai = pai;
        this.estado = estado;
        this.nivel = nivel;
        this.custo = custo;
        this.valor1 = valor1;
        this.valor2 = valor2;
        this.anterior = anterior;
        this.proximo = proximo;
    }

    public NoAEstrela getPai() {
        return pai;
    }

    public void setPai(NoAEstrela pai) {
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

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public double getValor1() {
        return valor1;
    }

    public void setValor1(double valor1) {
        this.valor1 = valor1;
    }

    public double getValor2() {
        return valor2;
    }

    public void setValor2(double valor2) {
        this.valor2 = valor2;
    }

    public NoAEstrela getAnterior() {
        return anterior;
    }

    public void setAnterior(NoAEstrela anterior) {
        this.anterior = anterior;
    }

    public NoAEstrela getProximo() {
        return proximo;
    }

    public void setProximo(NoAEstrela proximo) {
        this.proximo = proximo;
    }
}

