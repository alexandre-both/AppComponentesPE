package br.dev.alexandreboth.mobile.componentespe.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Componente {
    //atributos
    private int id;
    private int tipo;
    private String nome;
    private int tensao;
    private int gaveta;
    private int espaco;


    //JSON
    //CONSTRUTOR - inicializa atributos de um arquivo JSon
    public Componente (JSONObject jp) {
        try {
            //Id
            Integer numero = (int) jp.get("id");
            this.setId(numero);

            //tipo do componente
            numero = (int) jp.get("tipo");
            this.setTipo(numero);

            //nome do componente
            this.setNome((String) jp.get("nome"));

            //tensao do componente
            numero = (int) jp.get("tensao");
            this.setTensao(numero);

            //gaveta do componente
            numero = (int) jp.get("gaveta");
            this.setGaveta(numero);

            //espaco do componente
            numero = (int) jp.get("espaco");
            this.setEspaco(numero);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //CONSTRUTOR - Inicializa os atributos para gerar Objeto Json
    public Componente () {
        this.setId(0);
        this.setTipo(0);
        this.setNome("");
        this.setTensao(0);
        this.setGaveta(0);
        this.setEspaco(0);

    }

    //Metodo retorna o objeto com dados no formato JSON
    public JSONObject toJsonObject() {
        JSONObject json = new JSONObject();
        try {
            json.put("id", this.id);
            json.put("tipo", this.tipo);
            json.put("nome", this.nome);
            json.put("tensao", this.tensao);
            json.put("gaveta", this.gaveta);
            json.put("espaco", this.espaco);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    //métodos


    //id
    public int getId(){return this.id;}
    public void setId(int id){this.id = id;}

    //tipo
    public void setTipo(int t){
        if (t >= 0 && t <= 9){
            this.tipo = t;
        } else {
            this.tipo = 9;
        }
    }

    public int getTipo(){
        return this.tipo;
    }


    //nome
    public void setNome(String nm){
        if (nm.length() <3) {
            this.nome = "S/N. A definir";
        } else {
            this.nome = nm;
        }
    }

    public String getNome(){
        return this.nome;
    }


    //tensao
    public void setTensao(int ten){
        if (ten >= 0 && ten <= 9){
            this.tensao = ten;
        } else {
            this.tensao = 9;
        }
    }

    public int getTensao(){
        return this.tensao;
    }

    public void setGaveta(int gav){
        if (gav >= 0 && gav <= 9){
            this.gaveta = gav;
        } else {
            this.gaveta = 0;
        }
    }

    public int getGaveta(){
        return this.gaveta;
    }

    public void setEspaco(int espa){
        if (espa >= 0 && espa <= 9){
            this.espaco = espa;
        } else {
            this.espaco = 0;
        }
    }

    public int getEspaco(){
        return this.espaco;
    }



}
