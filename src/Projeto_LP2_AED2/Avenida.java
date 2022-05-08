package Projeto_LP2_AED2;

import java.util.ArrayList;

public class Avenida extends Ways{

    private ArrayList<Object> nodes = new ArrayList<>();

    /*---------------------------------------------------------------------------------------------------------------*/

    public Avenida(String nome, int velocidade, ArrayList<Object> nodes) {
        super(nome, velocidade);
        this.nodes = nodes;
    }

    /*---------------------------------------------------------------------------------------------------------------*/

    public ArrayList<Object> getNodes() {return nodes;}

    public void setNodes(ArrayList<Object> nodes) {this.nodes = nodes;}

    public void tostring() {
        System.out.println("Rua {\n" + "\tID=" + this.getId() + ", Nome: " + this.getNome()  + ";\n"+
                "\n\tNodes da Avenida:");
        for (Object n : this.getMyEtiqueta()) {

            System.out.println("\t\t" + n.toString() + ";");
        }
        System.out.println("\n\tEtiquetas da Avenida:");
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
