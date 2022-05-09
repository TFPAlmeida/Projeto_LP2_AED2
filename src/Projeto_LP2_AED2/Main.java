package Projeto_LP2_AED2;


import algs4.In;
import algs4.RedBlackBST;
import algs4.SeparateChainingHashST;
import javax.xml.crypto.Data;
import java.awt.dnd.DropTargetEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Main {

    //Path
    private static String inputNodestxt = ("C:\\Users\\tiago\\IdeaProjects\\Projeto_LP2_AED2\\src\\data\\inputNodes.txt");
    private static String inputWaystxt = ("C:\\Users\\tiago\\IdeaProjects\\Projeto_LP2_AED2\\src\\data\\inputWays.txt");
    private static String utilizadorestxt = ("C:\\Users\\tiago\\IdeaProjects\\Projeto_LP2_AED2\\src\\data\\utilizadores.txt");
    private static String nodestxt = ("C:\\Users\\tiago\\IdeaProjects\\Projeto_LP2_AED2\\src\\data\\nodes.txt");
    private static String etiquetastxt = ("C:\\Users\\tiago\\IdeaProjects\\Projeto_LP2_AED2\\src\\data\\etiquetas.txt");
    private static String waystxt = ("C:\\Users\\tiago\\IdeaProjects\\Projeto_LP2_AED2\\src\\data\\ways.txt");

    private static String utilizadoresRemovidostxt = ("C:\\Users\\tiago\\IdeaProjects\\Projeto_LP2_AED2\\src\\data\\utilizadoresRemovidos.txt");
    private static String nodesRemovidastxt = ("C:\\Users\\tiago\\IdeaProjects\\Projeto_LP2_AED2\\src\\data\\nodesRemovidas.txt");
    private static String waysRemovidastxt = ("C:\\Users\\tiago\\IdeaProjects\\Projeto_LP2_AED2\\src\\data\\waysRemovidas.txt");
    private static String etiquetasRemovidostxt = ("C:\\Users\\tiago\\IdeaProjects\\Projeto_LP2_AED2\\src\\data\\etiquetasRemovidos.txt");

    public static SeparateChainingHashST<Integer, Basic> basics = new SeparateChainingHashST<>();
    public static SeparateChainingHashST<Integer, Admin> admins = new SeparateChainingHashST<>();

    public static SeparateChainingHashST<String, Poi> pois = new SeparateChainingHashST<>();
    public static SeparateChainingHashST<String, Curva> curvas = new SeparateChainingHashST<>();
    public static SeparateChainingHashST<String, Cruzamento> cruzamentos = new SeparateChainingHashST<>();
    public static SeparateChainingHashST<String, Entroncamento> entroncamentos = new SeparateChainingHashST<>();
    public static SeparateChainingHashST<String, Rua> ruas = new SeparateChainingHashST<>();
    public static SeparateChainingHashST<String, Avenida> avenidas = new SeparateChainingHashST<>();

    public static RedBlackBST<String, Etiqueta> etiquetas = new RedBlackBST<>();

    /**
     * Pesquisa por um objeto do tipo Etiqueta no Arraylist de myEtiqueta dos Nodes recebida por parametro,
     * por nome ou descrição;
     *
     * @param Node   Objeto do tipo Node;
     * @param string Nome/descrição da Etiqueta a procurar;
     * @return Retorna o Objeto do tipo Etiqueta caso seja encontrado nos Nodes;
     */
    public static Etiqueta findEtiquetaInNode(Object Node, String string) {

        if (Node instanceof Poi) {
            for (Etiqueta e : ((Poi) Node).getMyEtiqueta()) {
                if (e.getDescricao().equals(string) || e.getNome().equals(string)) {
                    return e;
                }
            }
        }
        System.out.println("ERRO! { \n\tNenhuma etiqueta com a descrição: '" + string +
                "', encontrado nas etiquetas dos nodes!\n}\n");
        return null;
    }

    /**
     * Pesquisa por um objeto do tipo Etiqueta no Arraylist de Etiquetas do User recebido por parametro,
     * por nome ou descrição;
     *
     * @param User   Objeto do tipo User;
     * @param string Nome/descrição da Etiqueta a procurar;
     * @return Retorna o Objeto do tipo Etiqueta caso seja encontrado no User;
     */
    public static Etiqueta findEtiquetaInUsers(Object User, String string) {
        if (User instanceof Basic) {
            System.out.println("Erro! \nOs Utilizadores Basic não podem ter etiquetas!\n");
        }
        if (User instanceof Admin) {
            for (Etiqueta e : ((Admin) User).getEtiquetas()) {
                if (e.getDescricao().equals(string) || e.getNome().equals(string)) {
                    return e;
                }
            }
            System.out.println("ERRO! { \n\tNenhuma etiqueta com a descrição: '" + string +
                    "', encontrado nas etiquetas dos nodes!\n}\n");
        }
        return null;
    }

    /*---------------------------------------------------------------------------------------------------------------*/

    /**
     * ADD / EDITAR / REMOVER / LISTAR --Nodes / LOGS--
     */


    /**
     * Adicionar objetos do tipo Nodes na ST de Node global;
     *
     * @param Node Objeto do tipo Nodes a add;
     */
    public static void addNode(Object Node) {
        if (Node instanceof Poi) {
            if (pois.contains(((Poi) Node).getNome())) {
                System.out.println("Erro, Poi ja existe na DB!");
                return;
            }
            pois.put(((Poi) Node).getNome(), ((Poi) Node));
        }
        else if(Node instanceof Cruzamento){
            if(cruzamentos.contains(((Cruzamento) Node).getNome())){
                System.out.println("Erro, Cruzamento ja existe na DB!");
                return;
            }
            cruzamentos.put(((Cruzamento) Node).getNome(), ((Cruzamento) Node));
        }
        else if(Node instanceof Entroncamento){
            if(entroncamentos.contains(((Entroncamento) Node).getNome())){
                System.out.println("Erro, Entroncamento ja existe na DB!");
                return;
            }
            entroncamentos.put(((Entroncamento) Node).getNome(), ((Entroncamento) Node));
        }
        else if(Node instanceof Curva){
            if(curvas.contains(((Curva) Node).getNome())){
                System.out.println("Erro, Entroncamento ja existe na DB!");
                return;
            }
            curvas.put(((Curva) Node).getNome(), ((Curva) Node));
        }

    }


    private static void removeNode(Object Node) throws IOException {
        if (Node instanceof Poi) {
            if (pois.contains(((Poi) Node).getNome())) {
                FileWriter wr = new FileWriter(nodesRemovidastxt, true);
                wr.write(((Poi) Node).getId() + ", " + ((Poi) Node).getNome() + ", " + ((Poi) Node).getTipo() + ";\n");
                wr.write(((Poi) Node).getMyEtiqueta().size() + " etiquetas :\n");
                for (Etiqueta e : ((Poi) Node).getMyEtiqueta()) {
                    wr.write(e.getId() + ", " + e.getDescricao());
                }
                wr.write("\n" + ((Poi) Node).getLogs().size() + " Logs :\n");
                for (int i : ((Poi) Node).getLogs().keys()) {
                    wr.write(((Poi) Node).getLogs().get(i).toString());
                }
                wr.close();
                pois.delete(((Poi) Node).getNome());
            } else System.out.println("Erro, o node nao existe nao DB!");
        }
        else if(Node instanceof Cruzamento){
            FileWriter wr = new FileWriter(nodesRemovidastxt, true);
            wr.write(((Cruzamento) Node).getId() + ", " + ((Cruzamento) Node).getNome() + ";\n");
            wr.write(((Cruzamento) Node).getMyEtiqueta().size() + " etiquetas :\n");
            for (Etiqueta e : ((Cruzamento) Node).getMyEtiqueta()) {
                wr.write(e.getId() + ", " + e.getDescricao());
            }
            wr.write("\n" + ((Cruzamento) Node).getLogs().size() + " Logs :\n");
            for (int i : ((Cruzamento) Node).getLogs().keys()) {
                wr.write(((Cruzamento) Node).getLogs().get(i).toString());
            }
            wr.close();
            cruzamentos.delete(((Cruzamento) Node).getNome());
        }
        else if(Node instanceof Entroncamento){
            FileWriter wr = new FileWriter(nodesRemovidastxt, true);
            wr.write(((Entroncamento) Node).getId() + ", " + ((Entroncamento) Node).getNome() + ";\n");
            wr.write(((Entroncamento) Node).getMyEtiqueta().size() + " etiquetas :\n");
            for (Etiqueta e : ((Entroncamento) Node).getMyEtiqueta()) {
                wr.write(e.getId() + ", " + e.getDescricao());
            }
            wr.write("\n" + ((Entroncamento) Node).getLogs().size() + " Logs :\n");
            for (int i : ((Entroncamento) Node).getLogs().keys()) {
                wr.write(((Entroncamento) Node).getLogs().get(i).toString());
            }
            wr.close();
            entroncamentos.delete(((Entroncamento) Node).getNome());
        }
        else if(Node instanceof Curva){
            FileWriter wr = new FileWriter(nodesRemovidastxt, true);
            wr.write(((Curva) Node).getId() + ", " + ((Curva) Node).getNome() + ";\n");
            wr.write(((Curva) Node).getMyEtiqueta().size() + " etiquetas :\n");
            for (Etiqueta e : ((Curva) Node).getMyEtiqueta()) {
                wr.write(e.getId() + ", " + e.getDescricao());
            }
            wr.write("\n" + ((Curva) Node).getLogs().size() + " Logs :\n");
            for (int i : ((Curva) Node).getLogs().keys()) {
                wr.write(((Curva) Node).getLogs().get(i).toString());
            }
            wr.close();
            curvas.delete(((Curva) Node).getNome());
        }
    }

    /**
     * Editar objetos do tipo Node que estão ST de Nodes global;
     * Caso o objeto nao esteja inserido na ST, uma mensagem
     * de erro é enviada para o terminal;
     *
     * @param Node      Objeto do tipo Node a editar;
     * @param nome      Novo nome do Node (String)
     * @param latitude  Nova latitude do Node (Float)
     * @param longitude Nova longitude do Node (Float)
     */
    public static void editarNode(Object Node, String nome, float latitude, float longitude, String veiculo) {

        if (Node instanceof Poi) {
            String name = ((Poi) Node).getNome();
            if (!pois.contains(name)) {
                System.out.println("Erro Node nao registado na DB!");
                return;
            }
            pois.get(name).setNome(nome);
            pois.get(name).getGPS().setLatitude(latitude);
            pois.get(name).getGPS().setLatitude(longitude);
            pois.get(name).setVeiculo(veiculo);
        }
        else if(Node instanceof Cruzamento){
            String name = ((Cruzamento) Node).getNome();
            if (!cruzamentos.contains(name)) {
                System.out.println("Erro Node nao registado na DB!");
                return;
            }
            cruzamentos.get(name).setNome(nome);
            cruzamentos.get(name).getGPS().setLatitude(latitude);
            cruzamentos.get(name).getGPS().setLatitude(longitude);
        }
        else if(Node instanceof Entroncamento){
            String name = ((Entroncamento) Node).getNome();
            if (!entroncamentos.contains(name)) {
                System.out.println("Erro Node nao registado na DB!");
                return;
            }
            entroncamentos.get(name).setNome(nome);
            entroncamentos.get(name).getGPS().setLatitude(latitude);
            entroncamentos.get(name).getGPS().setLatitude(longitude);
        }
        else if(Node instanceof Curva){
            String name = ((Curva) Node).getNome();
            if (!curvas.contains(name)) {
                System.out.println("Erro Node nao registado na DB!");
                return;
            }
            curvas.get(name).setNome(nome);
        }
    }

    public static void listarPois() {
        System.out.println("Nodes: \n");
        for (String poi : pois.keys()) {
            pois.get(poi).tostring();
        }
    }

    public static void listarEntroncamentos() {
        System.out.println("Nodes: \n");
        for (String entrocamento : entroncamentos.keys()) {
            entroncamentos.get(entrocamento).tostring();
        }
    }

    public static void listarCruzamentos() {
        System.out.println("Nodes: \n");
        for (String cruzamento : cruzamentos.keys()) {
            cruzamentos.get(cruzamento).tostring();
        }
    }

    public static void listarCurvas() {
        System.out.println("Nodes: \n");
        for (String curva : curvas.keys()) {
            curvas.get(curva).tostring();
        }
    }

    /*---------------------------------------------------------------------------------------------------------------*/

    /**
     * ADD / EDITAR / REMOVER / LISTAR --Ways / LOGS--
     */


    /**
     * Adicionar objetos do tipo Ways na ST de Node global;
     *
     * @param Ways Objeto do tipo Ways a add;
     */
    public static void addWays(Object Ways) {
        if (Ways instanceof Rua) {
            if (ruas.contains(((Rua) Ways).getNome())) {
                System.out.println("Erro, cache ja existe na DB!");
                return;
            }
            ruas.put(((Rua) Ways).getNome(), ((Rua) Ways));
        } else if (Ways instanceof Avenida) {
            if (avenidas.contains(((Avenida) Ways).getNome())) {
                System.out.println("Erro, cache ja existe na DB!");
                return;
            }
            avenidas.put(((Avenida) Ways).getNome(), ((Avenida) Ways));
        }

    }


    private static void removeWays(Object Ways) throws IOException {
        if (Ways instanceof Rua) {
            if (ruas.contains(((Rua) Ways).getNome())) {
                FileWriter wr = new FileWriter(waysRemovidastxt, true);
                wr.write(((Rua) Ways).getId() + ", " + ((Rua) Ways).getNome() + ", " + ";\n");
                wr.write(((Rua) Ways).getMyEtiqueta().size() + " etiquetas :\n");
                for (Etiqueta e : ((Rua) Ways).getMyEtiqueta()) {
                    wr.write(e.getId() + ", " + e.getDescricao());
                }

                wr.close();
                ruas.delete(((Rua) Ways).getNome());
            } else System.out.println("Erro, o Way nao existe nao DB!");

        } else if (Ways instanceof Avenida) {
            if (avenidas.contains(((Avenida) Ways).getNome())) {
                FileWriter wr = new FileWriter(waysRemovidastxt, true);
                wr.write(((Avenida) Ways).getId() + ", " + ((Avenida) Ways).getNome() + ", " + ";\n");
                wr.write(((Avenida) Ways).getMyEtiqueta().size() + " etiquetas :\n");
                for (Etiqueta e : ((Avenida) Ways).getMyEtiqueta()) {
                    wr.write(e.getId() + ", " + e.getDescricao());
                }

                wr.close();
                avenidas.delete(((Avenida) Ways).getNome());
            } else System.out.println("Erro, o Way nao existe nao DB!");
        }
    }

    /**
     * Editar objetos do tipo Ways que estão ST de Ways global;
     * Caso o objeto nao esteja inserido na ST, uma mensagem
     * de erro é enviada para o terminal;
     *
     * @param Ways       Objeto do tipo Ways a editar;
     * @param nome       Novo nome do Ways (String)
     * @param velocidade Nova velocidade do Ways (int)
     */
    public static void editarWays(Object Ways, String nome, int velocidade) {

        if (Ways instanceof Rua) {
            String name = ((Rua) Ways).getNome();
            if (!ruas.contains(name)) {
                System.out.println("Erro Way nao registado na DB!");
                return;
            }
            ruas.get(name).setNome(nome);
            ruas.get(name).setVelocidade(velocidade);

        } else if (Ways instanceof Avenida) {
            String name = ((Avenida) Ways).getNome();
            if (!avenidas.contains(name)) {
                System.out.println("Erro way nao registado na DB!");
                return;
            }
            avenidas.get(name).setNome(nome);
            avenidas.get(name).setVelocidade(velocidade);
        }
    }


    public static void listarRuas() {
        System.out.println("Ruas: \n");
        for (String rua : ruas.keys()) {
            ruas.get(rua).tostring();
        }
    }

    public static void listarAvenidas() {
        System.out.println("Avenidas: \n");
        for (String avenida : avenidas.keys()) {
            pois.get(avenida).tostring();
        }
    }

    /*---------------------------------------------------------------------------------------------------------------*/

    /*  ADD / EDITAR / REMOVER / LISTAR --USERS--  */

    /**
     * Adicionar objetos do tipo User na ST basics ou Admins;
     *
     * @param User Objeto do tipo User a add;
     */
    public static void addUser(Object User) {
        if (User instanceof Basic) {
            int id = ((Basic) User).getId();
            if (basics.contains(id)) {
                System.out.println("Erro, Utilizador Basic com ID:" + id + " já existe na DB!");
                return;
            }
            basics.put(id, ((Basic) User));
        } else if (User instanceof Admin) {
            int id = ((Admin) User).getId();
            if (admins.contains(id)) {
                System.out.println("Erro, Utilizador Admin com ID:" + id + " já existe na DB!");
                return;
            }
            admins.put(id, ((Admin) User));
        }
    }

    /**
     * Editar objetos do tipo User que estão ST basics;
     * Caso o objeto nao esteja inserido na ST, uma mensagem
     * de erro é enviada para o terminal;
     *
     * @param User Objeto do tipo User a editar;
     * @param nome Novo nome do Admin ou Basic(String);
     */
    public static void editarUser(Object User, String nome) {
        if (User instanceof Basic) {
            int id = ((Basic) User).getId();
            if (!basics.contains(id)) {
                System.out.println("Erro, Utilizador Basic com ID:" + id + " já existe na DB!");
                return;
            }
            basics.get(id).setNome(nome);
        } else if (User instanceof Admin) {
            int id = ((Admin) User).getId();
            if (admins.contains(id)) {
                System.out.println("Erro, Utilizador Admin com ID:" + id + " já existe na DB!");
                return;
            }
            basics.get(id).setNome(nome);
        }
    }

    /**
     * Remover objetos do tipo User na ST basics;
     *
     * @param User Objeto do tipo User a remover;
     */
    public static void removerUser(Object User) throws IOException {
        if (User instanceof Basic) {
            int id = ((Basic) User).getId();
            if (!basics.contains(id)) {
                System.out.println("Erro Utilizador nao registado na DB!");
                return;
            }
            FileWriter wr = new FileWriter(utilizadoresRemovidostxt, true);
            wr.write(((Basic) User).getId() + ", " + ((Basic) User).getNome() + "\n");
            wr.write(((Basic) User).getEtiquetas().size() + " etiqueta");
            for (Etiqueta e : ((Basic) User).getEtiquetas()) {
                wr.write(e.getId() + ", " + e.getDescricao());
            }
            wr.close();
            basics.delete(id);
        } else if (User instanceof Admin) {
            int id = ((Admin) User).getId();
            if (!admins.contains(id)) {
                System.out.println("Erro Utilizador nao registado na DB!");
                return;
            }
            FileWriter wr = new FileWriter(utilizadoresRemovidostxt, true);
            wr.write(((Admin) User).getId() + ", " + ((Admin) User).getNome() + "\n");
            wr.write(((Admin) User).getEtiquetas().size() + " etiqueta");
            for (Etiqueta e : ((Admin) User).getEtiquetas()) {
                wr.write(e.getId() + ", " + e.getDescricao());
            }
            wr.close();
            admins.delete(id);
        }
    }

    /**
     * Lista todas os Utilizadores do tipo Basic que foram inseridas na ST basics;
     */
    public static void listarBasicUsers() {
        System.out.println("Basic Users: \n");
        for (Integer i : basics.keys()) {
            basics.get(i).tostring();
        }
    }

    /**
     * Lista todas os Utilizadores do tipo Admin que foram inseridas na ST admins;
     */
    public static void listarAdminUsers() {
        System.out.println("Admin Users: \n");
        for (Integer i : admins.keys()) {
            admins.get(i).tostring();
        }
    }

    /*---------------------------------------------------------------------------------------------------------------*/

    /**
     * Encontrar de todas as ST's, o objeto que tem o nome igual ao que é passado por parametros;
     *
     * @param nome nome a procurar nas ST's;
     */
    public static Object findUser(String nome) {
        for (int i : basics.keys()) {
            if (basics.get(i).getNome().equals(nome))
                return basics.get(i);
        }
        for (int i : admins.keys()) {
            if (admins.get(i).getNome().equals(nome))
                return admins.get(i);
        }
        System.out.println("User não está registado na DB!");
        return null;
    }

    /**
     * Escrita de toda a informação presente nas ST's Basics e Admins;
     */
    public static void escritaFicheiroTxtUtilizadores() throws IOException {
        FileWriter wr = new FileWriter(utilizadorestxt, true);
        int nUtilizadores = basics.size() + admins.size();
        wr.write(String.valueOf(nUtilizadores) + "\n");
        for (Integer i : basics.keys()) {
            wr.write(basics.get(i).getId() + ", " + basics.get(i).getNome() + ", " +
                    basics.get(i).getEtiquetas().size());
            for (Etiqueta e : basics.get(i).getEtiquetas()) {
                wr.write(e.getId() + ", " + e.getDescricao());
            }
            wr.write("\n");
        }
        for (Integer i : admins.keys()) {
            wr.write(admins.get(i).getId() + ", " + admins.get(i).getNome() + ", " +
                    admins.get(i).getEtiquetas().size());
            for (Etiqueta e : admins.get(i).getEtiquetas()) {
                wr.write(e.getId() + ", " + e.getDescricao());
            }
            wr.write("\n");
        }
        wr.close();
    }

    /**
     * Escrita de toda a informação presente nas ST Pois;
     */
    public static void escritaFicheiroTxtPois() throws IOException {

        FileWriter wr = new FileWriter(nodestxt, true);
        int nNodes = pois.size();
        wr.write(nNodes + "\n");
        for (String s : pois.keys()) {
            wr.write(pois.get(s).getId() + ", " + pois.get(s).getNome() + ", " + pois.get(s).getTipo() + ", " +
                    pois.get(s).getGPS().getLatitude() + ", " +
                    pois.get(s).getGPS().getLongitude() + ", " + String.valueOf(pois.get(s).getMyEtiqueta().size()));
            for (Etiqueta e : pois.get(s).getMyEtiqueta()) {
                wr.write(e.getId() + ", " + e.getDescricao());
            }
            wr.write("\n" + pois.get(s).getLogs().size() + "\n");
            for (int i : pois.get(s).getLogs().keys()) {
                wr.write(pois.get(s).getLogs().get(i).toString());
            }
        }
        wr.close();
    }

    /**
     * Escrita de toda a informação presente nas ST Pois;
     */
    public static void escritaFicheiroTxtCurvas() throws IOException {

        FileWriter wr = new FileWriter(nodestxt, true);
        int nNodes = curvas.size();
        wr.write(nNodes + "\n");
        for (String s : curvas.keys()) {
            wr.write(curvas.get(s).getId() + ", " + curvas.get(s).getNome() + ", "
                  + String.valueOf(curvas.get(s).getGPS().size()));
            for (Point p : curvas.get(s).getGPS()) {
                wr.write("Gps: " + p.getLatitude() + " | " + p.getLongitude() + "\n");
            }
            wr.write("\n" + curvas.get(s).getMyEtiqueta().size() + "\n");
            for (Etiqueta e : curvas.get(s).getMyEtiqueta()) {
                wr.write(e.getId() + ", " + e.getDescricao());
            }
            wr.write("\n" + curvas.get(s).getLogs().size() + "\n");
            for (int i : curvas.get(s).getLogs().keys()) {
                wr.write(curvas.get(s).getLogs().get(i).toString());
            }
        }
        wr.close();
    }

    /**
     * Escrita de toda a informação presente nas ST Pois;
     */
    public static void escritaFicheiroTxtCruzamentos() throws IOException {

        FileWriter wr = new FileWriter(nodestxt, true);
        int nNodes = cruzamentos.size();
        wr.write(nNodes + "\n");
        for (String s : cruzamentos.keys()) {
            wr.write(cruzamentos.get(s).getId() + ", " + cruzamentos.get(s).getNome() + ", "+
                    cruzamentos.get(s).getGPS().getLatitude() + ", " +
                    cruzamentos.get(s).getGPS().getLongitude() + ", " + String.valueOf(cruzamentos.get(s).getMyEtiqueta().size()));
            for (Etiqueta e : cruzamentos.get(s).getMyEtiqueta()) {
                wr.write(e.getId() + ", " + e.getDescricao());
            }
            wr.write("\n" + cruzamentos.get(s).getLogs().size() + "\n");
            for (int i : cruzamentos.get(s).getLogs().keys()) {
                wr.write(cruzamentos.get(s).getLogs().get(i).toString());
            }
        }
        wr.close();
    }

    /**
     * Escrita de toda a informação presente nas ST Pois;
     */
    public static void escritaFicheiroTxtEntroncamentos() throws IOException {

        FileWriter wr = new FileWriter(nodestxt, true);
        int nNodes = entroncamentos.size();
        wr.write(nNodes + "\n");
        for (String s : entroncamentos.keys()) {
            wr.write(entroncamentos.get(s).getId() + ", " + entroncamentos.get(s).getNome() + ", " +
                    entroncamentos.get(s).getGPS().getLatitude() + ", " +
                    entroncamentos.get(s).getGPS().getLongitude() + ", " + String.valueOf(entroncamentos.get(s).getMyEtiqueta().size()));
            for (Etiqueta e : entroncamentos.get(s).getMyEtiqueta()) {
                wr.write(e.getId() + ", " + e.getDescricao());
            }
            wr.write("\n" + entroncamentos.get(s).getLogs().size() + "\n");
            for (int i : entroncamentos.get(s).getLogs().keys()) {
                wr.write(entroncamentos.get(s).getLogs().get(i).toString());
            }
        }
        wr.close();
    }

    public static void escritaFicheiroTxtRuas() throws IOException {

        FileWriter wr = new FileWriter(waystxt, true);
        int nNodes = ruas.size();
        wr.write(nNodes + "\n");
        for (String s : ruas.keys()) {
            wr.write(ruas.get(s).getId() + ", " + ruas.get(s).getNome() + ", "  +
                    ruas.get(s).getNode1().toString() + ", " +
                    ruas.get(s).getNode2().toString() + ", " + String.valueOf(ruas.get(s).getMyEtiqueta().size()));
            for (Etiqueta e : ruas.get(s).getMyEtiqueta()) {
                wr.write(e.getId() + ", " + e.getDescricao());
            }
        }
        wr.close();
    }

    public static void escritaFicheiroTxtAvenidas() throws IOException {

        FileWriter wr = new FileWriter(waystxt, true);
        int nNodes = avenidas.size();
        wr.write(nNodes + "\n");
        for (String s : avenidas.keys()) {
            wr.write(avenidas.get(s).getId() + ", " + avenidas.get(s).getNome());
            wr.write("\n" + avenidas.get(s).getNodes().size() + "\n");
            for (Object o : avenidas.get(s).getNodes()) {
                if(o instanceof Poi){
                    int nNodes1 = pois.size();
                    wr.write(nNodes1 + "\n");
                    for (String s1 : pois.keys()) {
                        wr.write(pois.get(s1).getId() + ", " + pois.get(s1).getNome() + ", " + pois.get(s1).getTipo() + ", " +
                                pois.get(s1).getGPS().getLatitude() + ", " +
                                pois.get(s1).getGPS().getLongitude() + ", " + String.valueOf(pois.get(s1).getMyEtiqueta().size()));
                        for (Etiqueta e : pois.get(s1).getMyEtiqueta()) {
                            wr.write(e.getId() + ", " + e.getDescricao());
                        }
                        wr.write("\n" + pois.get(s1).getLogs().size() + "\n");
                        for (int i : pois.get(s1).getLogs().keys()) {
                            wr.write(pois.get(s1).getLogs().get(i).toString());
                        }
                    }
                }
                else if(o instanceof Curva){
                    int nNodes2 = curvas.size();
                    wr.write(nNodes2 + "\n");
                    for (String s2 : curvas.keys()) {
                        wr.write(curvas.get(s2).getId() + ", " + curvas.get(s2).getNome() + ", "
                                + String.valueOf(curvas.get(s2).getGPS().size()));
                        for (Point p : curvas.get(s2).getGPS()) {
                            wr.write("Gps: " + p.getLatitude() + " | " + p.getLongitude() + "\n");
                        }
                        wr.write("\n" + curvas.get(s2).getMyEtiqueta().size() + "\n");
                        for (Etiqueta e : curvas.get(s2).getMyEtiqueta()) {
                            wr.write(e.getId() + ", " + e.getDescricao());
                        }
                        wr.write("\n" + curvas.get(s2).getLogs().size() + "\n");
                        for (int i : curvas.get(s2).getLogs().keys()) {
                            wr.write(curvas.get(s2).getLogs().get(i).toString());
                        }
                    }
                }
                else if(o instanceof Entroncamento){
                    int nNodes3 = entroncamentos.size();
                    wr.write(nNodes3 + "\n");
                    for (String s3 : entroncamentos.keys()) {
                        wr.write(entroncamentos.get(s3).getId() + ", " + entroncamentos.get(s3).getNome() + ", " +
                                entroncamentos.get(s3).getGPS().getLatitude() + ", " +
                                entroncamentos.get(s3).getGPS().getLongitude() + ", " + String.valueOf(entroncamentos.get(s3).getMyEtiqueta().size()));
                        for (Etiqueta e : entroncamentos.get(s3).getMyEtiqueta()) {
                            wr.write(e.getId() + ", " + e.getDescricao());
                        }
                        wr.write("\n" + entroncamentos.get(s3).getLogs().size() + "\n");
                        for (int i : entroncamentos.get(s3).getLogs().keys()) {
                            wr.write(entroncamentos.get(s3).getLogs().get(i).toString());
                        }
                    }
                }
                else if(o instanceof Cruzamento){
                    int nNodes4 = cruzamentos.size();
                    wr.write(nNodes4 + "\n");
                    for (String s4 : cruzamentos.keys()) {
                        wr.write(cruzamentos.get(s4).getId() + ", " + cruzamentos.get(s4).getNome() + ", "+
                                cruzamentos.get(s4).getGPS().getLatitude() + ", " +
                                cruzamentos.get(s4).getGPS().getLongitude() + ", " + String.valueOf(cruzamentos.get(s4).getMyEtiqueta().size()));
                        for (Etiqueta e : cruzamentos.get(s4).getMyEtiqueta()) {
                            wr.write(e.getId() + ", " + e.getDescricao());
                        }
                        wr.write("\n" + cruzamentos.get(s4).getLogs().size() + "\n");
                        for (int i : cruzamentos.get(s4).getLogs().keys()) {
                            wr.write(cruzamentos.get(s4).getLogs().get(i).toString());
                        }
                    }
                }

            }
            wr.write("\n" + avenidas.get(s).getMyEtiqueta().size() + "\n");
            for (Etiqueta e : avenidas.get(s).getMyEtiqueta()) {
                wr.write(e.getId() + ", " + e.getDescricao());
            }
        }
        wr.close();
    }

    /**
     * Escrita de toda a informação presente nas RedBlack Etiquetas
     */
    public static void escritaFicheiroTxtEtiquetas() throws IOException {
        FileWriter wr = new FileWriter(etiquetastxt, true);
        wr.write(etiquetas.size() + "\n");
        for (String nome : etiquetas.keys()) {
            wr.write(etiquetas.get(nome).getId() + ", " + etiquetas.get(nome).getNome() + ", " +
                    etiquetas.get(nome).getNomeCriador() + ", " + etiquetas.get(nome).getDescricao() + ", " +
                    etiquetas.get(nome).getGPS().getLatitude() + ", " + etiquetas.get(nome).getGPS().getLongitude() + "\n");
        }
        wr.close();
    }


    /**
     * Função percorre todas os objetos do tipo Nodes presentes na ST de Nodes, encontrando as
     * nodes que têm a mesma data que a pedida por parametro, guarda-a no ArrayList de Nodes
     * retornando-o no final;
     *
     * @param data data condicional para obter os Nodes presentes na mesma;
     * @return ArrayList com os Objetos Node presentes na data;
     */
    public static ArrayList<Poi> getNodesInData(Date data) {
        ArrayList<Poi> poisInData = new ArrayList<>();
        for (String s : pois.keys()) {
            for (Integer log : pois.get(s).getLogs().keys()) {
                if (pois.get(s).getLogs().get(log).getDate().equals(data)) {
                    System.out.println(pois.get(s).getId() + ", " + pois.get(s).getTipo() + ", " + pois.get(s).getGPS().getLatitude() + ", " +
                            pois.get(s).getGPS().getLongitude());
                }
            }
        }
        return poisInData;
    }

    /*---------------------------------------------------------------------------------------------------------------*/

    /*  --- PESQUISAS ---  */

    /**
     * Lista todas os Utilizadores que visitaram uma determinado Poi;
     *
     * @param poiVisitado Objeto do tipo Poi;
     */
    public static void listarUtilizadoresPoi(Poi poiVisitado) {
        int k = 0;
        System.out.println("Utilizadores que visitaram o Poi ( " + poiVisitado.getNome() + " ):");
        for (int i : basics.keys()) {
            if (basics.get(i).getPoisVisitados().contains(poiVisitado)) {
                basics.get(i).tostring();
                k++;
            }
        }
        for (int i : admins.keys()) {
            if (admins.get(i).getPoisVisitados().contains(poiVisitado)) {
                admins.get(i).tostring();
                k++;
            }
        }
        if (k == 0) System.out.println("Poi ainda não foi visitada por nenhum Utilizador!");
    }

    /**
     * Listar o Top 5 de utilizadores que visitaram mais Nodes num determinada intervalo de tempo;
     *
     * @param dataInicio Objeto do tipo Date;
     * @param dataFim    Objeto do tipo Date;
     */
    public static void topUtilizadores(Date dataInicio, Date dataFim) {
        int count = 0;
        Object[] UserId = new Object[5];
        Integer[] UserCount = new Integer[5];
        System.out.println("Top Utilizadores com mais Caches Visitadas de '" +
                dataInicio.getYear() + "/" + dataInicio.getMonth() + "/" + dataInicio.getDay() + " - " +
                dataInicio.getHours() + ":" + dataInicio.getMinutes() + ":" + dataInicio.getSeconds() +
                "' a '" + dataFim.getYear() + "/" + dataFim.getMonth() + "/" + dataFim.getDay() + " - " +
                dataFim.getHours() + ":" + dataFim.getMinutes() + ":" + dataFim.getSeconds() + "' : ");
        for (int i : basics.keys()) {
            for (Poi j : basics.get(i).getPoisVisitados()) {
                for (Integer log : j.getLogs().keys()) {
                    if (basics.get(i).getId() == j.getLogs().get(log).getUserId()
                            && j.getLogs().get(log).getDate().compareTo(dataInicio) > 0
                            && j.getLogs().get(log).getDate().compareTo(dataFim) < 0) {
                        count++;
                    }
                }
            }
            int pos = 0;
            for (int k = 0; k < UserCount.length; k++) {
                if (UserCount[k] == null && UserId[k] == null) {
                    UserId[k] = basics.get(i);
                    UserCount[k] = count;
                    break;
                }
                if (UserCount[k] < count && UserCount[k] != null) {
                    pos = k;
                } else if ((k + 1 == UserCount.length)) {
                    UserCount[pos] = count;
                    UserId[pos] = basics.get(i);
                    break;
                }
            }
            count = 0;
        }
        for (int i : admins.keys()) {
            for (Poi j : admins.get(i).getPoisVisitados()) {
                for (Integer log : j.getLogs().keys()) {
                    if (admins.get(i).getId() == j.getLogs().get(log).getUserId()
                            && j.getLogs().get(log).getDate().compareTo(dataInicio) > 0
                            && j.getLogs().get(log).getDate().compareTo(dataFim) < 0) {
                        count++;
                    }
                }
            }
            int pos = 0;
            for (int k = 0; k < UserCount.length; k++) {
                if (UserCount[k] == null && UserId[k] == null) {
                    UserId[k] = admins.get(i);
                    UserCount[k] = count;
                    break;
                }
                if (UserCount[k] < count && UserCount[k] != null) pos = k;
                else if ((k + 1 == UserCount.length)) {
                    UserCount[pos] = count;
                    UserId[pos] = admins.get(i);
                    break;
                }
            }
            count = 0;
        }
        for (Object ob : UserId) {
            if (ob instanceof Admin) {
                System.out.println(((Admin) ob).getId() + ", " + ((Admin) ob).getNome());
            }
            if (ob instanceof Basic) {
                System.out.println(((Basic) ob).getId() + ", " + ((Basic) ob).getNome());
            }
        }
    }


    /*---------------------------------------------------------------------------------------------------------------*/


    /*  ADD / EDITAR / REMOVER / LISTAR --Etiquetas--  */

    /**
     * Remover um Etiquetas da DB
     */
    public static void removerEtiquetas(Etiqueta et) throws IOException {
        if (etiquetas.contains(et.getNome())) {
            FileWriter wr = new FileWriter(etiquetasRemovidostxt, true);
            wr.write(et.getId() + ", " + et.getNome() + ", " + et.getNomeCriador() + ", " +
                    et.getDescricao() + ", " + et.getGPS().getLatitude() + ", " + et.getGPS().getLongitude() + "\n");

            wr.close();
            etiquetas.delete(et.getNome());
        } else {
            System.out.println("Erro TravelBug nao registado na DB!");
        }
    }

    public static void listarEtiquetas() {
        System.out.println("Etiquetas: \n");
        for (String s : etiquetas.keys()) {
            etiquetas.get(s).tostring();
        }
    }

    /*---------------------------------------------------------------------------------------------------------------*/

    public static void main(String[] args) throws IOException, ParseException {

        /*------------------------------------------------------------------------------------------------------------*/
        /** USERS */
        //Manuel
        Basic b1 = new Basic("Manuel", 41.1740298f, -8.6132806f, "CarroEletrico");
        addUser(b1);
        //Pedro
        Basic b2 = new Basic("Pedro", 0, 0, "CarroEletrico");
        addUser(b2);
        //Filomena
        Basic b3 = new Basic("Filomena", 0, 0, "CarroEletrico");
        addUser(b3);
        //Fernando
        Admin a1 = new Admin("Fernando", 41.1740298f, -8.6132806f, "CarroEletrico");
        addUser(a1);
        //Joana
        Admin a2 = new Admin("Joana", 0, 0, "CarroEletrico");
        addUser(a2);
        //Maria
        Admin a3 = new Admin("Maria", 0, 0, "CarroEletrico");
        addUser(a3);

        /*------------------------------------------------------------------------------------------------------------*/
        /** Nodes */

        Poi p1 = new Poi("p1", "EstacaoEletrica", 41.1740298f, -8.6132806f, "CarroEletrico");
        addNode(p1);
        Poi p2 = new Poi("p2", "EstacaoEletrica", 41.1722618f, -8.6126820f, "BicicletaEletrica");
        addNode(p2);
        Poi p3 = new Poi("p3", "Escola", 41.1710753f, -8.6128402f, "Autocarro");
        addNode(p3);
        Poi p4 = new Poi("p4", "Cinema", 41.1703690f, -8.6129333f, "Autocarro");
        addNode(p4);
        Poi p5 = new Poi("p5", "EstacaoEletrica", 41.1699072f, -8.6129174f, "BicicletaEletrica");
        addNode(p5);
        Poi p6 = new Poi("p6", "Museu", 41.1696617f, -8.6128633f, "Autocarro");
        addNode(p6);
        Poi p7 = new Poi("p7", "EstacaoEletrica", 41.1670140f, -8.6117522f, "BicicletaEletrica");
        addNode(p7);
        Poi p8 = new Poi("p8", "Cinema", 41.1727151f, -8.6127041f, "CarroEletrico");
        addNode(p8);
        Poi p9 = new Poi("p9", "EstacaoEletrica", 41.1726118f, -8.6111237f, "BicicletaEletrica");
        addNode(p9);
        Poi p10 = new Poi("p10", "Museu", 41.1709836f, -8.6113112f, "CarroEletrico");
        addNode(p10);
        Poi p11 = new Poi("p11", "Escola", 41.1711021f, -8.6128045f, "CarroEletrico");
        addNode(p11);
        Poi p12 = new Poi("p12", "EstacaoEletrica", 41.1722967f, -8.6126472f, "BicicletaEletrica");
        addNode(p12);
        Poi p13 = new Poi("p13", "EstacaoEletrica", 41.1718292f, -8.6127436f, "CarroEletrico");
        addNode(p13);
        Poi p14 = new Poi("p14", "Escola", 41.1739403f, -8.6103814f, "CarroEletrico");
        addNode(p14);
        Poi p15 = new Poi("p15", "Museu", 41.1727672f, -8.6103785f, "BicicletaEletrica");
        addNode(p15);
        Poi p16 = new Poi("p16", "Museu", 41.1677569f, -8.6107657f, "CarroEletrico");
        addNode(p16);
        Poi p17 = new Poi("p17", "Escola", 41.1695191f, -8.6084611f, "BicicletaEletrica");
        addNode(p17);
        Poi p18 = new Poi("p18", "Cinema", 41.1709494f, -8.6112781f, "Autocarro");
        addNode(p18);
        Poi p19 = new Poi("p19", "EstacaoEletrica", 41.1712961f, -8.6128154f, "CarroEletrico");
        addNode(p19);
        Poi p20 = new Poi("p20", "Cinema", 41.1738924f, -8.6132242f, "BicicletaEletrica");
        addNode(p20);
        Poi p21 = new Poi("p21", "EstacaoEletrica", 41.1736241f, -8.6131209f, "BicicletaEletrica");
        addNode(p21);
        Poi p22 = new Poi("p22", "Escola", 41.1728369f, -8.6128570f, "BicicletaEletrica");
        addNode(p22);
        Poi p23 = new Poi("p23", "EstacaoEletrica", 41.1727692f, -8.6128188f, "CarroEletrico");
        addNode(p23);
        Poi p24 = new Poi("p24", "Cinema", 41.1729352f, -8.6095116f, "CarroEletrico");
        addNode(p24);
        Poi p25 = new Poi("p25", "Escola", 41.1729427f, -8.6094669f, "BicicletaEletrica");
        addNode(p25);
        Poi p26 = new Poi("p26", "EstacaoEletrica", 41.1729674f, -8.6093197f, "CarroEletrico");
        addNode(p26);
        Poi p27 = new Poi("p27", "Museu", 41.1717464f, -8.6127544f, "BicicletaEletrica");
        addNode(p27);
        Poi p28 = new Poi("p28", "EstacaoEletrica", 41.1736583f, -8.6103807f, "BicicletaEletrica");
        addNode(p28);
        Poi p29 = new Poi("p29", "EstacaoEletrica", 41.1728373f, -8.6103787f, "CarroEletrico");
        addNode(p29);
        Poi p30 = new Poi("p30", "Escola", 41.1710618f, -8.6126606f, "Autocarro");
        addNode(p30);
        /*------------------------------------------------------------------------------------------------------------*/
        //b1.createEtiquetas(etiquetas,p1,"Transito", "e1");
        a1.createEtiquetasNodes(etiquetas, p5, "Transito", "e2");
        System.out.println(a1.getEtiquetas().size());
        /*------------------------------------------------------------------------------------------------------------*/
        //b1.visitarPoi(p1, null);
        //.visitarPoi(p8, null);
        //String date_string = "08-05-2022";
        //SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        //Date date = formatter.parse(date_string);
        //System.out.println("Date value: "+date);
        //b1.listarPoisVisitados(date);
        //listarAdminUsers();
        //listarPois();
        //listarEtiquetas();
    }

}
