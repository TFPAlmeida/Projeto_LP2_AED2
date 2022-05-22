package NoWarPolis;

import algs4.RedBlackBST;
import algs4.SeparateChainingHashST;
import algs4.RedBlackBST;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;


public abstract class User {

    private static int countUser = 0;

    private static int countEtiqueta = 0;

    private int Id;

    private String Tipo;

    private String Nome;

    private String veiculo;

    private ArrayList<Poi> poisVisitados = new ArrayList<>();

    private ArrayList<Etiqueta> Etiquetas = new ArrayList<>();


    /*---------------------------------------------------------------------------------------------------------------*/

    public User(String tipo,String nome, String veiculo) {
        this.Id = ++countUser;
        this.Tipo = tipo;
        Nome = nome;
        this.veiculo = veiculo;
    }

    /*---------------------------------------------------------------------------------------------------------------*/

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTipo() {return Tipo;}

    public void setTipo(String tipo) {Tipo = tipo;}

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
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
                 "\tEtiquetas do Utilizador:");
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

    public void createEtiquetasNodes(RedBlackBST<String, Etiqueta> etiquetas, Object Node, String descricao, String nome) {
        int id = ++countEtiqueta;
        if(Node instanceof Poi){
            Etiqueta ET = new Etiqueta(nome, this.getNome(), descricao, ((Poi) Node).getGPS().getLatitude(),
                    ((Poi) Node).getGPS().getLongitude());
            if (etiquetas.contains(nome) || this.getEtiquetas().contains(ET)) {
                System.out.println("Erro, Etiqueta ja existe na DB!");
                return;
            }
            ((Poi) Node).getMyEtiqueta().add(ET);
            etiquetas.put(nome, ET);
        }
    }

    public void createEtiquetasWays(RedBlackBST<String, Etiqueta> etiquetas, Object Ways, String descricao, String nome) {
        int id = ++countEtiqueta;
        if(Ways instanceof Rua){
            Etiqueta ET = new Etiqueta(nome, this.getNome(), descricao);
            if (etiquetas.contains(nome) || this.getEtiquetas().contains(ET)) {
                System.out.println("Erro, Etiqueta ja existe na DB!");
                return;
            }
            ((Rua) Ways).getMyEtiqueta().add(ET);
            etiquetas.put(nome, ET);
        }
        else if(Ways instanceof Avenida){
            Etiqueta ET = new Etiqueta(nome, this.getNome(), descricao);
            if (etiquetas.contains(nome) || this.getEtiquetas().contains(ET)) {
                System.out.println("Erro, Etiqueta ja existe na DB!");
                return;
            }
            ((Avenida) Ways).getMyEtiqueta().add(ET);
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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        if (this.veiculo.equals(p.getVeiculo())) {
            this.getPoisVisitados().add(p);
            LocalDateTime date = LocalDateTime.now();
            Date dateAtual = new Date(dtf.format(date));
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