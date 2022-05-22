package NoWarPolis;

import java.io.Serializable;
import java.util.ArrayList;

public class Ways implements Serializable {

    private static int count = 0;

    private int Id;

    private String Tipo;

    private String Nome;

    private float Velocidade;

    private ArrayList<Etiqueta> myEtiqueta = new ArrayList<>();


    /*---------------------------------------------------------------------------------------------------------------*/

    public Ways(String tipo, String nome, float velocidade) {
        Id = ++count;
        this.Tipo = tipo;
        Nome = nome;
        Velocidade = velocidade;
    }

    /*---------------------------------------------------------------------------------------------------------------*/

    public float getVelocidade() {return Velocidade;}

    public void setVelocidade(float velocidade) {Velocidade = velocidade;}

    public int getId() {return Id;}

    public void setId(int id) {Id = id;}

    public String getTipo() {return Tipo;}

    public void setTipo(String tipo) {Tipo = tipo;}

    public String getNome() {return Nome;}

    public void setNome(String tipo) {Nome = tipo;}

    public ArrayList<Etiqueta> getMyEtiqueta() {return myEtiqueta;}

    public void setMyEtiqueta(ArrayList<Etiqueta> myEtiqueta) {this.myEtiqueta = myEtiqueta;}

    /*---------------------------------------------------------------------------------------------------------------*/

}