package com.example.demo;

public class NoGreedy {
    private NoGreedy pai;
    private String estado;
    private double valor1;
    private double valor2;
    private NoGreedy anterior;
    private NoGreedy proximo;

    public NoGreedy(NoGreedy pai, String estado, double valor1, double valor2, NoGreedy anterior, NoGreedy proximo) {
        this.pai = pai;
        this.estado = estado;
        this.valor1 = valor1;
        this.valor2 = valor2;
        this.anterior = anterior;
        this.proximo = proximo;
    }

    // Getters e setters para os campos privados, se necessário
    public NoGreedy getPai() {
        return pai;
    }

    public void setPai(NoGreedy pai) {
        this.pai = pai;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public NoGreedy getAnterior() {
        return anterior;
    }

    public void setAnterior(NoGreedy anterior) {
        this.anterior = anterior;
    }

    public NoGreedy getProximo() {
        return proximo;
    }

    public void setProximo(NoGreedy proximo) {
        this.proximo = proximo;
    }
}
