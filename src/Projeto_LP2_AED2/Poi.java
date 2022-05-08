package Projeto_LP2_AED2;

import algs4.RedBlackBST;

import java.sql.Date;

public class Poi extends Node {


  private String Tipo;

  private Point GPS;

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

  @Override
  public String toString() {
    return "Poi{" +
            "Id=" + this.getTipo() +
            ", Nome='" + this.getNome() + '\'' +
            ", Tipo='" + Tipo + '\'' +
            ", GPS=" + GPS +
            ", Veiculo='" + Veiculo + '\'' +
            '}';
  }

  /*---------------------------------------------------------------------------------------------------------------*/

}