package NoWarPolis;

import java.util.ArrayList;

public class Curva extends Nodes {

    private ArrayList<Point> GPS = new ArrayList<>();

    /*---------------------------------------------------------------------------------------------------------------*/

    public Curva(String tipo, String nome, ArrayList<Point> GPS) {
        super(tipo, nome);
        this.GPS = GPS;
    }

    /*---------------------------------------------------------------------------------------------------------------*/

    public ArrayList<Point> getGPS() {return GPS;}

    public void setGPS(ArrayList<Point> GPS) {this.GPS = GPS;}

    public void tostring() {
        int size = GPS.size();;
        for(int x = 0; x < size; x++){
            if(x == (size) -1){
                System.out.println("Curva {\n" + "\tID=" + this.getId() + ", Nome: " + this.getNome() +";\n"+
                        "\tGPS_Inicial: " + GPS.get(0).getLatitude() + " | " + GPS.get(0).getLongitude() + "\n" +
                        "\tGPS_Final: " + GPS.get(size-1).getLatitude() + " | " + GPS.get(size-1).getLongitude()+ "\n\tEtiquetas do Poi:");
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
