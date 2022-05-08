package Projeto_LP2_AED2;

public class Etiqueta {

    private int Id;

    private String Nome;

    private String nomeCriador;

    private String Descricao;

    private Point GPS;


    /*---------------------------------------------------------------------------------------------------------------*/

    public Etiqueta(int id, String nome, String nomeCriador, String descricao, float latitude, float longitude) {
        Id = id;
        Nome = nome;
        this.nomeCriador = nomeCriador;
        Descricao = descricao;
        this.GPS = new Point(latitude, longitude);
    }

    /*---------------------------------------------------------------------------------------------------------------*/

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public Point getGPS() {
        return GPS;
    }

    public void setGPS(Point GPS) {
        this.GPS = GPS;
    }

    public String getNomeCriador() {
        return nomeCriador;
    }

    public void setNomeCriador(String nomeCriador) {
        this.nomeCriador = nomeCriador;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    /*---------------------------------------------------------------------------------------------------------------*/


}