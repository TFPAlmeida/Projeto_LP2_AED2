package NoWarPolis;

import algs4.RedBlackBST;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

public class Nodes implements Serializable{

    private static int count = 0;
    private int Id;

    private String Tipo;

    private String Nome;

    private ArrayList<Etiqueta> myEtiqueta = new ArrayList<>();

    private RedBlackBST<Integer, Log> logs = new RedBlackBST<>();

    /*---------------------------------------------------------------------------------------------------------------*/

    public Nodes(String tipo, String nome) {
        Id = ++count;
        this.Tipo = tipo;
        Nome = nome;
    }

    /*---------------------------------------------------------------------------------------------------------------*/

    public int getId() {return Id;}

    public void setId(int id) {Id = id;}

    public String getTipo() {return Tipo;}

    public void setTipo(String tipo) {Tipo = tipo;}

    public String getNome() {return Nome;}

    public void setNome(String nome) {Nome = nome;}

    public ArrayList<Etiqueta> getMyEtiqueta() {return myEtiqueta;}

    public void setMyEtiqueta(ArrayList<Etiqueta> myEtiqueta) {this.myEtiqueta = myEtiqueta;}

    public RedBlackBST<Integer, Log> getLogs() {return logs;}

    public void setLogs(RedBlackBST<Integer, Log> logs) {this.logs = logs;}

    @Override
    public String toString() {
        return "Node{" +
                "Id=" + Id +
                ", Nome='" + Nome + '\'' +
                '}';
    }

    /*---------------------------------------------------------------------------------------------------------------*/

/**
 * -- METODOS ESPECIFICOS --
 */

    /**
     * Adiciona logs à RedBlackST de logs.
     *
     * @param log log que sera adicionado ao RedblackST
     */
    public void addLogs(Log log) {
        Integer id = log.getId();
        if (logs.contains(id)) {
            System.out.println("Erro, nao existe logs na cache");
            return;
        }
        logs.put(id, log);
    }

    /**
     * Edita/altera os valores do log.
     *
     * @param log      log a ser editado;
     * @param id       novo id;
     * @param date     nova data;
     * @param mensagem nova mensagem;
     */

    public void editarLog(Log log, int id, Date date, String mensagem) {
        Integer ID = log.getId();
        if (logs.contains(ID)) {
            logs.get(ID).setId(id);
            logs.get(ID).setDate(date);
            logs.get(ID).setMensagem(mensagem);
        } else System.out.println("ERRO! Nao existe o Log com o Id inserido!");
    }

    /**
     * Remove o log desejado.
     *
     * @param log       Log a ser removido.
     */

    public void removelogs(Log log) {
        if (logs.contains(log.getId())) {
            logs.delete(log.getId());
        } else System.out.println("Erro, nao existe o log");
    }

    /**
     * Lista todos os logs, no ecra, existentes na RedBlackST.
     */

    public void listarlogs() {
        System.out.println("Logs:");
        for (Integer log : logs.keys()) {
            System.out.println("Id:" + logs.get(log).getId() + " - " + logs.get(log).toString());
        }
    }

    /**
     * Lista todos os Etiquetas no ecra.
     */

    public void listarEtiquetas() {
        System.out.print("Etiquetas:  ");
        for (Etiqueta i : this.getMyEtiqueta()) {
            System.out.print(i.getDescricao() + "; ");
        }
        System.out.println("\n");
    }
}


