package Projeto_LP2_AED2;

public class Point {

  private Float Latitude;

  private Float Longitude;

  /*---------------------------------------------------------------------------------------------------------------*/

  public Point(Float latitude, Float longitude) {
    this.Latitude = latitude;
    this.Longitude = longitude;
  }

  public Point() {

  }


  /*---------------------------------------------------------------------------------------------------------------*/

  public float distanciaEntrePontos(Point p1, Point p2) {

    float dx = distLatitude(p1, p2);

    if(dx < 0){
      dx*=-1;
    }

    float dy = distLongitude(p1, p2);

    if(dy < 0){
      dy *= -1;
    }

    return (float)Math.sqrt(Math.pow(dx,2)+Math.pow(dy,2));
  }

  public float distLatitude(Point p1, Point p2){
    return p1.Latitude - p2.Latitude;
  }

  public float distLongitude(Point p1, Point p2){
    return p1.Longitude - p2.Longitude;
  }

  /*---------------------------------------------------------------------------------------------------------------*/

  public Float getLatitude() {
    return Latitude;
  }

  public void setLatitude(Float latitude) {
    Latitude = latitude;
  }

  public Float getLongitude() {
    return Longitude;
  }

  public void setLongitude(Float longitude) {
    Longitude = longitude;
  }

}