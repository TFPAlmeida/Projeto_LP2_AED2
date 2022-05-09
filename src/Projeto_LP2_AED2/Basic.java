package Projeto_LP2_AED2;

import algs4.RedBlackBST;

public class Basic extends Admin {


  public Basic(String nome, float latitude, float longitude, String myVeiculo) {
    super(nome, latitude, longitude, myVeiculo);
  }

  /*---------------------------------------------------------------------------------------------------------------*/

  /**
   * -- METODOS ESPECIFICOS --
   */

  /**
   * Utilizador básico não pode criar Etiquetas, então esta função vai sobrepor a mesma da superclass.
   */


  public void createEtiquetas(RedBlackBST<String, Etiqueta> Etiquetas, Object Node, String descricao, String nome){
    System.out.println("ERRO! { \n\tUtilizadores Basic não podem criar Etiquetas!\n\t" +
            this.getId() + ", " + this.getNome());
  }



}