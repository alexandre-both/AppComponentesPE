package br.dev.alexandreboth.mobile.componentespe.model;

public class Componente {
    //atributos
    private int tipo;
    private String nome;
    private int tensao;

    //métodos

    //tipo
    void setTipo(int t){
        if (t >= 0 && t <= 9){
            this.tipo = t;
        } else {
            this.tipo = 9;
        }
    }

    int getTipo(){
        return this.tipo;
    }


    //nome
    void setNome(String nm){
        if (nm.length() <3) {
            this.nome = "S/N. A definir";
        } else {
            this.nome = nm;
        }
    }

    //tensao
    void setTensao(int ten){
        if (ten >= 0 && ten <= 9){
            this.tipo = ten;
        } else {
            this.tipo = 9;
        }
    }




}
