package Projeto_LP2_AED2;

import algs4.RedBlackBST;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Node implements Serializable{

    private static int countUser = 0;
    private int Id;

    private String Nome;

    private ArrayList<Etiqueta> myEtiqueta = new ArrayList<>();

    private ArrayList<Ways> myWays = new ArrayList<>();

    private RedBlackBST<Integer, Log> logs = new RedBlackBST<>();

    /*---------------------------------------------------------------------------------------------------------------*/

    public Node(String nome) {
        Id = ++countUser;
        Nome = nome;
    }

    /*---------------------------------------------------------------------------------------------------------------*/

    public int getId() {return Id;}

    public void setId(int id) {Id = id;}

    public String getNome() {return Nome;
    }

    public void setNome(String nome) {Nome = nome;
    }

    public ArrayList<Etiqueta> getMyEtiqueta() {return myEtiqueta;}

    public void setMyEtiqueta(ArrayList<Etiqueta> myEtiqueta) {this.myEtiqueta = myEtiqueta;}

    public ArrayList<Ways> getMyWays() {return myWays;}

    public void setMyWays(ArrayList<Ways> myWays) {this.myWays = myWays;}

    public RedBlackBST<Integer, Log> getLogs() {return logs;}

    public void setLogs(RedBlackBST<Integer, Log> logs) {this.logs = logs;}

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
     * Lista todos os items no ecra.
     */

    public void listaritems() {
        System.out.print("Items:  ");
        for (Etiqueta i : this.getMyEtiqueta()) {
            System.out.print(i.getDescricao() + "; ");
        }
        System.out.println("\n");
    }
}

    /*---------------------------------------------------------------------------------------------------------------*/

