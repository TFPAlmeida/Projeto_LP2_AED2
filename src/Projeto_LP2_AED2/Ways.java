package Projeto_LP2_AED2;

import algs4.RedBlackBST;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

public class Ways implements Serializable {

    private static int count = 0;

    private int Id;

    private String Nome;

    private int Velocidade;

    private ArrayList<Etiqueta> myEtiqueta = new ArrayList<>();


    /*---------------------------------------------------------------------------------------------------------------*/

    public Ways(String nome, int velocidade) {
        Id = ++count;
        Nome = nome;
        Velocidade = velocidade;
    }

    /*---------------------------------------------------------------------------------------------------------------*/

    public int getVelocidade() {return Velocidade;}

    public void setVelocidade(int velocidade) {Velocidade = velocidade;}

    public int getId() {return Id;}

    public void setId(int id) {Id = id;}

    public String getNome() {return Nome;}

    public void setNome(String tipo) {Nome = tipo;}

    public ArrayList<Etiqueta> getMyEtiqueta() {return myEtiqueta;}

    public void setMyEtiqueta(ArrayList<Etiqueta> myEtiqueta) {this.myEtiqueta = myEtiqueta;}

    /*---------------------------------------------------------------------------------------------------------------*/

}