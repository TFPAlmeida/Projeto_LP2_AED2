package Projeto_LP2_AED2;

import java.util.ArrayList;

public class Curva extends Node{

    private ArrayList<Point> GPS = new ArrayList<>();

    /*---------------------------------------------------------------------------------------------------------------*/

    public Curva(String nome, ArrayList<Point> GPS) {
        super(nome);
        this.GPS = GPS;
    }

    /*---------------------------------------------------------------------------------------------------------------*/

    public ArrayList<Point> getGPS() {return GPS;}

    public void setGPS(ArrayList<Point> GPS) {this.GPS = GPS;}

    public void tostring() {
        int size = GPS.size();;
        for(int x = 0; x < size; x++){
            if(x == (size) -1){
                System.out.println("POI {\n" + "\tID=" + this.getId() + ", Nome: " + this.getNome() +";\n"+
                        "\tGPS_Inicial: " + GPS.get(0).getLatitude() + " | " + GPS.get(0).getLongitude() + "\n" +
                        "\tGPS_Final: " + GPS.get(size).getLatitude() + " | " + GPS.get(size).getLongitude()+ "\n\tEtiquetas do Poi:");
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

    }
}
