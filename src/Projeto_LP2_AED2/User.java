package Projeto_LP2_AED2;

import algs4.RedBlackBST;
import algs4.SeparateChainingHashST;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;


public abstract class User {

    private static int countUser = 0;

    private static int countEtiqueta = 0;

    private int Id;

    private String Nome;

    private Point GPS = new Point();

    private String veiculo;

    private ArrayList<Ways> myWays = new ArrayList<>();

    private ArrayList<Poi> poisVisitados = new ArrayList<>();
    private ArrayList<Etiqueta> Etiquetas = new ArrayList<>();


    /*---------------------------------------------------------------------------------------------------------------*/

    public User(String nome, float latitude, float longitude, String veiculo) {
        this.Id = ++countUser;
        Nome = nome;
        this.GPS.setLatitude(latitude);
        this.GPS.setLongitude(longitude);
        this.veiculo = veiculo;
    }

    /*---------------------------------------------------------------------------------------------------------------*/

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public Point getGPS() {
        return GPS;
    }

    public void setGPS(Point GPS) {
        this.GPS = GPS;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    public ArrayList<Poi> getPoisVisitados() {
        return poisVisitados;
    }

    public void setPoisVisitados(ArrayList<Poi> poisVisitados) {
        this.poisVisitados = poisVisitados;
    }

    public ArrayList<Etiqueta> getEtiquetas() {
        return Etiquetas;
    }

    public void setEtiquetas(ArrayList<Etiqueta> etiquetas) {Etiquetas = etiquetas;}


    public void tostring() {
        System.out.println("Utilizador {\n" + "\tID=" + Id + ", Nome: " + Nome  + ";\n" +
                 "GPS: " + GPS.getLatitude() + " | " + GPS.getLongitude() + "\tEtiquetas do Utilizador:");
        for (Etiqueta e : this.getEtiquetas()) {

                System.out.println("\t\t" + e.getId() + ", " +e.getDescricao() + ";");
        }
        if (this.getEtiquetas().isEmpty()) {
            System.out.println("\t\tUtilizador nao tem Etiquetas publicadas!");
            System.out.println("\n}\n");
            return;
        }
        System.out.println("\n}\n");
    }

    /*---------------------------------------------------------------------------------------------------------------*/

    /**
     * -- MANIPULAÇAO REDBLACK ETIQUETAS --
     */

    public void createEtiquetas(RedBlackBST<String, Etiqueta> etiquetas, Object Node, String descricao, String nome) {
        int id = ++countEtiqueta;
        if(Node instanceof Poi){
            Etiqueta ET = new Etiqueta(id, nome, this.getNome(), descricao, ((Poi) Node).getGPS().getLatitude(),
                    ((Poi) Node).getGPS().getLongitude());
            if (etiquetas.contains(nome) || this.getEtiquetas().contains(ET)) {
                System.out.println("Erro, Etiqueta ja existe na DB!");
                return;
            }
            ((Poi) Node).getMyEtiqueta().add(ET);
            etiquetas.put(nome, ET);
        }
    }

    /*---------------------------------------------------------------------------------------------------------------*/


    /**
     * -- METODOS ESPECIFICOS --
     */

    /**
     * Simula a Visita de uma Cache por parte de um utilizador, como tal é possivel a troca
     * de Items com a Cache, a escrita de uma mensagem no Log criado ao ser visitada a Cache
     *
     * @param p          Objeto do tipo Poi a visitar/interagir pelo Utilizador;
     * @param Mensagem   Mensagem que será escrita no log (null se não quiser deixar mensagem);
     *
     */

    public void visitarPoi(Poi p, String Mensagem) {
        if (this.veiculo.equals(p.getVeiculo())) {
            this.getPoisVisitados().add(p);
            LocalDateTime date = LocalDateTime.now();
            Date dateAtual = new Date(date.getYear() + 1900, date.getMonthValue(), date.getDayOfMonth());
            String info = this.Nome;
            Log log = new Log(dateAtual, info, Mensagem);
            p.getLogs().put(log.getId(), log);
        } else {
            System.out.println("ERRO! { \n\tCom este Veiculo não podem aceder a este Node!\n\t" +
                    this.getId() + ", " + this.getNome());
        }
    }

    /*---------------------------------------------------------------------------------------------------------------*/

    /**
     * -- PESQUISAS --
     */

    /**
     * Listar todas as Caches visitadas por um determinado utilizador;
     *
     * @param Data Data restringe a os Pois a serem imprimidas
     *
     */

    public void listarPoisVisitados(Date Data) {
        if (this.getPoisVisitados().isEmpty()) {
            System.out.println("Utilizador nao visitou um Poi!");
        }
        if (Data != null) {
            for (Poi p : this.getPoisVisitados()) {
                for (Integer log : p.getLogs().keys()) {
                    if (p.getLogs().get(log).getDate().equals(Data)) {
                        System.out.println(p.getId() + ", " + p.getTipo() + ", " + p.getGPS().getLatitude() + ", " +
                                p.getGPS().getLongitude());
                    }
                }
            }
        } else {
            for (Poi p : this.getPoisVisitados()) {
                System.out.println(p.getId() + ", " + p.getTipo() + ", " + p.getGPS().getLatitude() + ", " +
                        p.getGPS().getLongitude());
            }
        }
    }

    /**
     * Listar todas as Caches nao visitadas por um determinado utilizador;
     *
     * @param pois DB onde estao armazenadas todas os Pois;
     * @param data Data restringe a os Pois a serem imprimidas
     *
     */

    public void listarPoisNaoVisitados(Date data, SeparateChainingHashST<String, Poi> pois) {
        int i = 0;
        for (String s : pois.keys()) {
            if (!this.getPoisVisitados().contains(pois.get(s))) {
                if (data != null) {
                    for (Integer log : pois.get(s).getLogs().keys()) {
                        if (pois.get(s).getLogs().get(log).getDate().equals(data)) {
                            i++;
                            System.out.println(pois.get(s).getId() + ", " + pois.get(s).getTipo() + ", " +
                                    pois.get(s).getGPS().getLatitude() + ", " + pois.get(s).getGPS().getLongitude());
                        }
                    }
                }else{
                    System.out.println(pois.get(s).getId() + ", " + pois.get(s).getTipo() + ", " +
                            pois.get(s).getGPS().getLatitude() + ", " + pois.get(s).getGPS().getLongitude());
                }
            }
        }
        if(i == 0){
            System.out.println("Utilizador ja visitou o Poi na data inserida!");
        }
    }
}