package NoWarPolis;

public class Etiqueta {
    private static int count = 0;
    private int Id;

    private String Nome;

    private String nomeCriador;

    private String Descricao;

    private Point GPS;


    /*---------------------------------------------------------------------------------------------------------------*/

    public Etiqueta(String nome, String nomeCriador, String descricao, float latitude, float longitude) {
        Id = count++;
        Nome = nome;
        this.nomeCriador = nomeCriador;
        Descricao = descricao;
        this.GPS = new Point(latitude, longitude);
    }

    public Etiqueta(String nome, String nomeCriador, String descricao) {
        Id = count++;
        Nome = nome;
        this.nomeCriador = nomeCriador;
        Descricao = descricao;
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

    public void tostring() {
        System.out.println("Etiqueta {\n" + "\tID=" + this.getId() + ", Nome: " + this.getNome()  + ", Nome do Criador: " + nomeCriador +";\n"+
                "\tDescricao: " + Descricao +
                "\n\tGPS: " + GPS.getLatitude() + " | " + GPS.getLongitude());

        System.out.println("\n}\n");
    }

    /*---------------------------------------------------------------------------------------------------------------*/


}