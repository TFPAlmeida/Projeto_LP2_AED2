package Projeto_LP2_AED2;


import algs4.In;
import algs4.RedBlackBST;
import algs4.SeparateChainingHashST;

import javax.xml.crypto.Data;
import java.awt.dnd.DropTargetEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Main {

    //Path
    private static String inputxt = (".//data//input.txt");
    private static String utilizadorestxt = (".//data//utilizadores.txt");
    private static String nodestxt = (".//data//nodes.txt");
    private static String etiquetastxt = (".//data//etiquetas.txt");
    private static String ligacoesnodestxt = (".//data//ligacoescaches.txt");

    private static String utilizadoresRemovidostxt = (".//data//utilizadoresRemovidos.txt");
    private static String nodesRemovidastxt = (".//data//nodesRemovidas.txt");
    private static String etiquetasRemovidostxt = (".//data//etiquetasRemovidos.txt");

    public static SeparateChainingHashST<Integer, Basic> basics = new SeparateChainingHashST<>();
    public static SeparateChainingHashST<Integer, Admin> admins = new SeparateChainingHashST<>();

    public static SeparateChainingHashST<String, Poi> pois = new SeparateChainingHashST<>();
    private static ArrayList<ligacoesNodes> ligacoes = new ArrayList<>();

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
                System.out.println("Erro, cache ja existe na DB!");
                return;
            }
            pois.put(((Poi) Node).getNome(), ((Poi) Node));
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
    }

    public static void listarPois() {
        System.out.println("Nodes: \n");
        for (String poi : pois.keys()) {
            pois.get(poi).toString();
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

    /*  LEITURA E ESCRITA DE FICHEIROS INFORMAÇÃO -- INPUT --  */

    /**
     * Leitura de dados de um ficheiro de INPUT, populando assim as ST's de basics, premiums e admins,
     * assim como da ST de Nodes, seus ArrayLists de Etiquetas, as ligacoes das caches e os travelbugs;
     *
     * @param path Caminho para o ficheiro a ler;
     */
    public static void leituraFicheiroTxt(String path) {
        In in = new In(path);

        while (!in.isEmpty()) {
            int l = Integer.parseInt(in.readLine());
            for (int i = 0; i < l; i++) {
                String line = in.readLine();
                String[] fields = line.split(", ");
                switch (fields[4]) {
                    case "basic":
                        Basic b = new Basic(fields[1], Float.parseFloat(fields[2]),Float.parseFloat(fields[3]), fields[4]);
                        addUser(b);
                    case "admin":
                        Admin a = new Admin(fields[1], Float.parseFloat(fields[2]),Float.parseFloat(fields[3]), fields[4]);
                        addUser(a);
                        break;
                }
            }
            int m = Integer.parseInt(in.readLine());
            for (int i = 0; i < m; i++) {
                String line = in.readLine();
                String[] aux = line.split(", ");
                for (int j = 0; j < Integer.parseInt(aux[1]); j++) {
                    String line2 = in.readLine();
                    String[] field = line2.split(", ");
                    Poi p = new Poi(aux[0], field[1], Float.parseFloat(field[3]),
                            Float.parseFloat(field[4]), field[5]);
                    addNode(p);
                    for (int k = 0; k < Integer.parseInt(field[4]); k++) {
                        Etiqueta e = new Etiqueta(Integer.parseInt(field[6 + k]),field[7 + k],field[8 + k], field[9 + k],
                                Float.parseFloat(field[10 + k]), Float.parseFloat(field[11 + k]) );
                        p.getMyEtiqueta().add(e);
                    }
                }
            }
            int p = Integer.parseInt(in.readLine());
            for(int i = 0; i < p; i++){
                String[]aux = in.readLine().split(", ");
                ligacoesNodes ligacoesNode = new ligacoesNodes(aux[0], aux[1],
                        Float.parseFloat(aux[2]), Integer.parseInt(aux[3]));
                ligacoes.add(ligacoesNode);
            }
            int k = Integer.parseInt(in.readLine());
            for (int i= 0; i < k; i++){
                String[]aux = in.readLine().split(", ");
                Etiqueta et = new Etiqueta(Integer.parseInt(aux[0]), aux[1], aux[2], aux[3], Float.parseFloat(aux[4]),
                        Float.parseFloat(aux[5]));
                etiquetas.put(et.getNome(), et);
            }
        }
    }

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
     *   Escrita de toda a informação presente nas RedBlack Etiquetas
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

    public static void escritaFicheiroTxtLigacoesNodes() throws IOException {
        FileWriter wr = new FileWriter(ligacoesnodestxt, false);
        for (ligacoesNodes l : ligacoes) {
            wr.write(l.Inicial + ", " + l.Final + ", " + l.distancia + ", " + l.min + "\n");
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
    public static ArrayList<Poi> getCachesInData(Date data) {
        ArrayList<Poi> poisInData = new ArrayList<>();
        for (String s: pois.keys()) {
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


    /**
     * Class auxiliar para melhor manipulação de dados sobre distancias entre Nodes;
     */
    public static class ligacoesNodes {
        String Inicial;
        String Final;
        float distancia;
        int min;

        public ligacoesNodes(String Inicial, String Final, float distancia, int min) {
            this.Inicial = Inicial;
            this.Final = Final;
            this.distancia = distancia;
            this.min = min;
        }

        public String getGeocacheInicial() {
            return Inicial;
        }

        public String getGeocacheFinal() {
            return Final;
        }

        public float getDistancia() {
            return distancia;
        }

        public int getMin() {
            return min;
        }
    }


    /**
     * Remover um Etiquetas da DB
     */
    public static void removerEtiquetas(Etiqueta et) throws IOException {
        if(etiquetas.contains(et.getNome())) {
            FileWriter wr = new FileWriter(etiquetasRemovidostxt, true);
            wr.write(et.getId() + ", " +et.getNome() + ", " + et.getNomeCriador() + ", " +
                    et.getDescricao() + ", " + et.getGPS().getLatitude() + ", " + et.getGPS().getLongitude() + "\n");

            wr.close();
            etiquetas.delete(et.getNome());
        }
        else {
            System.out.println("Erro TravelBug nao registado na DB!");
        }
    }

    public static void main(String[] args) throws IOException {


        //Manuel
        Basic b1 = new Basic("Manuel",0,0,"CarroEletrico");
        addUser(b1);
        //Pedro
        Basic b2 = new Basic("Pedro",0,0,"CarroEletrico");
        addUser(b2);
        //Filomena
        Basic b3 = new Basic("Filomena",0,0,"CarroEletrico");
        addUser(b3);
        //Fernando
        Admin a1 = new Admin("Fernando",0,0,"CarroEletrico");
        addUser(a1);
        //Joana
        Admin a2 = new Admin("Joana",0,0,"CarroEletrico");
        addUser(a2);
        //Maria
        Admin a3 = new Admin("Maria",0,0,"CarroEletrico");
        addUser(a3);

        listarAdminUsers();
    }

}