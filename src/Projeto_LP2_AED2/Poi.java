package Projeto_LP2_AED2;

import algs4.RedBlackBST;
import java.sql.Date;

public class Poi extends Node {


  private String Tipo;

  private Point GPS = new Point();

  private String Veiculo;


  /*---------------------------------------------------------------------------------------------------------------*/

  public Poi(String nome, String tipo, float latitude, float longitude, String veiculo) {
    super(nome);
    Tipo = tipo;
    this.GPS.setLatitude(latitude);
    this.GPS.setLongitude(longitude);
    Veiculo = veiculo;
  }

  /*---------------------------------------------------------------------------------------------------------------*/

  public String getTipo() {return Tipo;}

  public void setTipo(String tipo) {Tipo = tipo;}

  public Point getGPS() {return GPS;}

  public void setGPS(Point gps) {GPS = gps;}

  public String getVeiculo() {return Veiculo;}

  public void setVeiculo(String veiculo) {Veiculo = veiculo;}


  public void tostring() {
    System.out.println("POI {\n" + "\tID=" + this.getId() + ", Nome: " + this.getNome()  + ", Tipo: " + Tipo +";\n"+
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
  /*---------------------------------------------------------------------------------------------------------------*/

}