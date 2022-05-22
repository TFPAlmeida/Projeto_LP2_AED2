package NoWarPolis;

public class Poi extends Nodes {


  private String Tipo1;

  private Point GPS = new Point();

  private String Veiculo;


  /*---------------------------------------------------------------------------------------------------------------*/

  public Poi(String tipo, String nome, String tipo1, float latitude, float longitude, String veiculo) {
    super(tipo, nome);
    Tipo1 = tipo1;
    this.GPS.setLatitude(latitude);
    this.GPS.setLongitude(longitude);
    Veiculo = veiculo;
  }

  /*---------------------------------------------------------------------------------------------------------------*/

  public String getTipo1() {return Tipo1;}

  public void setTipo1(String tipo) {Tipo1 = tipo;}

  public Point getGPS() {return GPS;}

  public void setGPS(Point gps) {GPS = gps;}

  public String getVeiculo() {return Veiculo;}

  public void setVeiculo(String veiculo) {Veiculo = veiculo;}


  public void tostring() {
    System.out.println("POI {\n" + "\tID=" + this.getId() + ", Nome: " + this.getNome()  + ", Tipo: " + Tipo1 +";\n"+
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