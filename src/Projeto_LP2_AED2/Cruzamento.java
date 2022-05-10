package Projeto_LP2_AED2;

public class Cruzamento extends Node{

    private Point GPS = new Point();

    /*---------------------------------------------------------------------------------------------------------------*/

    public Cruzamento(String nome, float latitude, float longitude) {
        super(nome);
        this.GPS.setLatitude(latitude);
        this.GPS.setLongitude(longitude);
    }

    /*---------------------------------------------------------------------------------------------------------------*/

    public Point getGPS() {return GPS;}

    public void setGPS(Point GPS) {this.GPS = GPS;}

    public void tostring() {
        System.out.println("Cruzamento {\n" + "\tID=" + this.getId() + ", Nome: " + this.getNome() +";\n"+
                "\tGPS: " + GPS.getLatitude() + " | " + GPS.getLongitude() + "\n\tEtiquetas do Poi:");
        for (Etiqueta e : this.getMyEtiqueta()) {

            System.out.println("\t\t" + e.getId() + ", " +e.getDescricao() + ";");
        }
        if (this.getMyEtiqueta().isEmpty()) {
            System.out.println("\t\tPoi nao tem Etiquetas publicadas!");
            System.out.println("\n}\n");
            return;
        }
        System.out.println("\n}\n");
    }

}
