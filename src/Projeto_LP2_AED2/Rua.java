package Projeto_LP2_AED2;

import java.util.ArrayList;

public class Rua extends Ways{

    private Object node1;

    private Object node2;

    /*---------------------------------------------------------------------------------------------------------------*/

    public Rua(String nome, int velocidade, Object node1, Object node2) {
        super(nome, velocidade);
        this.node1 = node1;
        this.node2 = node2;
    }

    /*---------------------------------------------------------------------------------------------------------------*/

    public Object getNode1() {return node1;}

    public void setNode1(Object node1) {this.node1 = node1;}

    public Object getNode2() {return node2;}

    public void setNode2(Object node2) {this.node2 = node2;}

    public void tostring() {
        System.out.println("Rua {\n" + "\tID=" + this.getId() + ", Nome: " + this.getNome()  + ", Node1: " + node1.toString() + ", Node2: " + node2.toString() +";\n"+
                "\n\tEtiquetas da Rua:");
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
