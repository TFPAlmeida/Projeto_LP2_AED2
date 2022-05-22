package JavaFX;

import NoWarPolis.*;

import algs4.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

import static NoWarPolis.Base_Dados.*;


public class Controller {
    private static final int radius = 30;
    private String basicUsersFXtxt = (".//data//basicUsersFX.txt");
    private String adminUsersFXtxt = (".//data//adminUsersFX.txt");
    private String nodesFXtxt = (".//data//nodesFX.txt");
    private String nodesBin = (".//data//nodesBin.bin");
    private String waysFXtxt = (".//data//waysFX.txt");
    private String waysBin = (".//data//ways.bin");
    private String basicUsersBin = (".//data//usersBin.bin");
    private String adminUsersBin = (".//data//usersBin.bin");
    private String etiquetasBin = (".//data//etiquetasBin.bin");
    private String etiquetasFXtxt = (".//data//etiquetasFX.txt");
    private String grafostxt = (".//data//grafosFXtxt.txt");
    private String grafosbin = (".//data//grafosFXbin.bin");
    private String pesquisasTxT = (".//data//pesquisasFx.txt");

    private JavaFX.Nowarpolis Nowarpolis;

    public MenuItem infoTxtMenu;
    public MenuItem infoBinMenu;
    public MenuItem graphTxtMenu;
    public MenuItem graphBinMenu;
    public MenuItem infoSaveTxtMenu;
    public MenuItem infoSaveBinMenu;
    public MenuItem graphTxtSaveMenu;
    public MenuItem graphBinSaveMenu;

    public Pane graphPane;
    public ComboBox<Node> partidaCombo;
    public ComboBox<Node> destinoCombo;
    public Button ResetButton;

    public TextField VTnomeTextField;
    public TextField VTxTextField;
    public TextField VTyTextField;
    public TextField VTveiculoTextField;
    public TextField VTtipoTextField;
    public TextField VTtipopoiTextField;
    public Button VTadd1Button;

    public ComboBox<Node> VTvNodes;
    public ComboBox<Node> VTwNodes;
    public Button VTadd2Button;

    public ComboBox<Node> VTremoveComboBox;
    public Button VTremoveButton;

    public ComboBox<EdgeDoubleWeighted> VTremove2ComboBox;
    public Button VTremove2Button;

    public ComboBox<Node> VTedit1ComboBox;
    public Button VTeditButton;

    public TextField VTedit3TF;
    public TextField VTedit4TF;
    public Button VT1confirmButton;

    public ComboBox<EdgeDoubleWeighted> VTedit2ComboBox;
    public Button VTligacaoEditButton;

    public TextField VT3edgeEditTF;
    public TextField VT4edgeEditTF;
    public Button VT2confirmButton;

    public TableColumn<User, Integer> idUtilizadores;
    public TableColumn<User, String> nomeUtilizadores;
    public TableColumn<User, String> tipoUtilizadores;
    public TableColumn<User, String> veiculoUtilizadores;
    public TextField nomeUtilizadoresTextField;
    public TextField tipoUtilizadoresTextField;
    public TextField veiculoUtilizadoresTextField;
    public Button adicionarUtilizadores;
    public Button removerUtilizadores;


    public TableColumn<Nodes, String> nomeNodes;
    public TableColumn<Nodes, String> tipoNodes;
    public TableColumn<Nodes, String> tipopoiNodes;
    public TableColumn<Nodes, Float> xNodes;
    public TableColumn<Nodes, Float> yNodes;
    public TableColumn<Nodes, String> veiculoNodes;
    public TableColumn<Nodes, Integer> nEtiquetasNodes;
    public TableColumn<Nodes, String> etiquetasNodes;
    public TextField nomeNodesTextField;
    public TextField tipoNodesTextField;
    public TextField tipopoiNodesTextField;
    public TextField xNodesTextField;
    public TextField yNodesTextField;
    public TextField veiculoNodesTextField;
    public Button adicionarNodes;
    public Button removerNodes;

    public TableColumn<Log, String> nodeLog;
    public TableColumn<Log, String> dataLogs;
    public TableColumn<Log, String> infoLogs;
    public TableColumn<Log, String> mensagemLogs;
    public TextField dataLogsTextField;
    public TextField infoLogsTextField;
    public TextField mensagemLogsTextField;
    public TextField nodeLogsTextField;
    public Button addLogs;
    public Button removeLogs;

    public TableColumn<Etiqueta, String> nomeEtiquetas;
    public TableColumn<Etiqueta, String> userEtiquetas;
    public TableColumn<Etiqueta, Float> xEtiquetas;
    public TableColumn<Etiqueta, Float> yEtiquetas;
    public TableColumn<Etiqueta, String> descricaoEtiquetas;
    public TextField nomeEtiquetasTextField;
    public TextField userEtiquetasTextField;
    public TextField xEtiquetasTextField;
    public TextField yEtiquetasTextField;
    public TextField descricaoEtiquetasTextField;
    public Button addTravelBugs;
    public Button removeTravelBugs;


    public TableView<Etiqueta> etiquetasTable;
    public TableView<Log> logsTable;
    public TableView<Object> nodesTable;
    public TableView<User> usersTable;

    public Controller(Nowarpolis<Node> nowarpolis) {
        this.Nowarpolis = nowarpolis;
    }

    @FXML
    public void initialize() {
        //Tornar as Tables editáveis
        usersTable.setEditable(true);
        nodesTable.setEditable(true);
        logsTable.setEditable(true);
        etiquetasTable.setEditable(true);


        //Tornar as Colunas não editáveis
        nEtiquetasNodes.setEditable(false);
        dataLogs.setEditable(false);

        //Users
        idUtilizadores.setCellValueFactory(new PropertyValueFactory<>("ID"));
        nomeUtilizadores.setCellValueFactory(new PropertyValueFactory<>("nome"));
        veiculoUtilizadores.setCellValueFactory(new PropertyValueFactory<>("veiculo"));

        //Edit Users
        idUtilizadores.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        nomeUtilizadores.setCellFactory(TextFieldTableCell.forTableColumn());
        veiculoUtilizadores.setCellFactory(TextFieldTableCell.forTableColumn());

        //Nodes
        nomeNodes.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        tipoNodes.setCellValueFactory(new PropertyValueFactory<>("Tipo"));
        xNodes.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        yNodes.setCellValueFactory(new PropertyValueFactory<>("longitude"));
        nEtiquetasNodes.setCellValueFactory(new PropertyValueFactory<>("n_etiquetas"));
        etiquetasNodes.setCellValueFactory(new PropertyValueFactory<>("descricaoEtiquetas"));

        //Editar Nodes
        nomeNodes.setCellFactory(TextFieldTableCell.forTableColumn());
        tipoNodes.setCellFactory(TextFieldTableCell.forTableColumn());
        xNodes.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        yNodes.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        nEtiquetasNodes.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        etiquetasNodes.setCellFactory(TextFieldTableCell.forTableColumn());

        //Logs
        nodeLog.setCellValueFactory(new PropertyValueFactory<>("cacheTravelBug"));
        dataLogs.setCellValueFactory(new PropertyValueFactory<>("Data"));
        infoLogs.setCellValueFactory(new PropertyValueFactory<>("Info"));
        mensagemLogs.setCellValueFactory(new PropertyValueFactory<>("Mensagem"));

        //Editar Logs
        nodeLog.setCellFactory(TextFieldTableCell.forTableColumn());
        dataLogs.setCellFactory(TextFieldTableCell.forTableColumn());
        infoLogs.setCellFactory(TextFieldTableCell.forTableColumn());
        mensagemLogs.setCellFactory(TextFieldTableCell.forTableColumn());

        //Etiquetas
        nomeEtiquetas.setCellValueFactory(new PropertyValueFactory<>("nome"));
        userEtiquetas.setCellValueFactory(new PropertyValueFactory<>("nomeCriador"));
        xEtiquetas.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        yEtiquetas.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        descricaoEtiquetas.setCellValueFactory(new PropertyValueFactory<>("descricaoEtiquetas"));

        //Editar Etiquetas
        nomeEtiquetas.setCellFactory(TextFieldTableCell.forTableColumn());
        userEtiquetas.setCellFactory(TextFieldTableCell.forTableColumn());
        xNodes.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        yNodes.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        descricaoEtiquetas.setCellFactory(TextFieldTableCell.forTableColumn());

        //createGraphGroup(this.Geocaching.getAllNodes(), null, null);
        //handleGerarGrafoCache();
        //handleGerarEvitarGrafoCache("norte", null, null);


        showEdgesComboBox();
        showAddNodeComboBox();
        showNodesComboBox(Nowarpolis.getAllNodes());
    }

    /*-----------------------------------------LEITURA TXT P/TABELAS-------------------------------------------------*/

    /**
     * Carrega toda a informação (Utilizadores, Nodes, Etiquetas e Logs
     * para as tabelas da interface.
     *
     * @param actionEvent
     * @throws IOException
     */

    public void handleCarregarInfoTXT(ActionEvent actionEvent) throws IOException {
        ArrayList<Basic> basicUsers = readBasicUsersFromFile(basicUsersFXtxt);
        ArrayList<Admin> adminUsers = readAdminUsersFromFile(adminUsersFXtxt);
        ArrayList<Etiqueta> etiquetasArrayList = new ArrayList<>();
        ArrayList<Object> nodesArrayList = new ArrayList<>();
        ArrayList<Log> LogsNode = new ArrayList<>();

        for (String s : pois.keys()) {
            nodesArrayList.add(pois.get(s));
        }

        for (String s : curvas.keys()) {
            nodesArrayList.add(curvas.get(s));
        }

        for (String s : cruzamentos.keys()) {
            nodesArrayList.add(cruzamentos.get(s));
        }

        for (String s : entroncamentos.keys()) {
            nodesArrayList.add(entroncamentos.get(s));
        }

        for (String s : etiquetas.keys()) {
            etiquetasArrayList.add(etiquetas.get(s));
        }
        for (Object n : nodesArrayList) {
            if (n instanceof Poi) {
                if (!((Poi) n).getLogs().isEmpty()) {
                    for (int i : ((Poi) n).getLogs().keys()) {
                        LogsNode.add(((Poi) n).getLogs().get(i));
                    }
                }
            }
            if (n instanceof Curva) {
                if (!((Curva) n).getLogs().isEmpty()) {
                    for (int i : ((Curva) n).getLogs().keys()) {
                        LogsNode.add(((Curva) n).getLogs().get(i));
                    }
                }
            }
            if (n instanceof Cruzamento) {
                if (!((Cruzamento) n).getLogs().isEmpty()) {
                    for (int i : ((Cruzamento) n).getLogs().keys()) {
                        LogsNode.add(((Cruzamento) n).getLogs().get(i));
                    }
                }
            }
            if (n instanceof Entroncamento) {
                if (!((Entroncamento) n).getLogs().isEmpty()) {
                    for (int i : ((Entroncamento) n).getLogs().keys()) {
                        LogsNode.add(((Entroncamento) n).getLogs().get(i));
                    }
                }
            }
        }
        usersTable.getItems().addAll(basicUsers);
        usersTable.getItems().addAll(adminUsers);
        nodesTable.getItems().addAll(nodesArrayList);
        etiquetasTable.getItems().addAll(etiquetasArrayList);
        logsTable.getItems().addAll(LogsNode);
    }

    /**
     * Leitura de dados de um ficheiro de INPUT
     *
     * @param path Caminho para o ficheiro ler
     * @return retorna o arraylist com as utilizadores basics
     */
    private static ArrayList<Basic> readBasicUsersFromFile(String path) {
        ArrayList<Basic> basicUsers = new ArrayList<>();
        In in = new In(path);

        while (!in.isEmpty()) {
            int m = Integer.parseInt(in.readLine());
            for (int i = 0; i < m; i++) {
                String line = in.readLine();
                String[] fields = line.split("; ");
                Basic b = new Basic(fields[0], fields[1], fields[2]);

                basicUsers.add(b);
                basics.put(b.getId(), b);
            }
        }
        return basicUsers;
    }


    /**
     * Leitura de dados de um ficheiro de INPUT
     *
     * @param path Caminho para o ficheiro ler
     * @return retorna o arraylist com as utilizadores Admin
     */
    private static ArrayList<Admin> readAdminUsersFromFile(String path) {
        ArrayList<Admin> adminUsers = new ArrayList<>();
        In in = new In(path);

        while (!in.isEmpty()) {
            int m = Integer.parseInt(in.readLine());
            for (int i = 0; i < m; i++) {
                String line = in.readLine();
                String[] fields = line.split("; ");
                Admin a = new Admin(fields[0], fields[1], fields[2]);

                adminUsers.add(a);
                admins.put(a.getId(), a);
            }

        }
        return adminUsers;
    }


    /**
     * Leitura de dados de um ficheiro de INPUT
     *
     * @param path Caminho para o ficheiro ler
     */
    static void readNodesFromFile(String path) {
        In in = new In(path);

        while (!in.isEmpty()) {
            int l = Integer.parseInt(in.readLine());
            for (int i = 0; i < l; i++) {
                String line = in.readLine();
                String[] fields = line.split(";");
                switch (fields[0]) {
                    case "Poi":
                        Poi p = new Poi(fields[0], fields[1], fields[2], Float.parseFloat(fields[3]), Float.parseFloat(fields[4]), fields[5]);
                        addNode(p);
                        break;
                    case "Curva":
                        ArrayList<Point> GPS = new ArrayList<>();
                        Point p1 = new Point(Float.parseFloat(fields[2]), Float.parseFloat(fields[3]));
                        GPS.add(p1);
                        Point p2 = new Point(Float.parseFloat(fields[4]), Float.parseFloat(fields[5]));
                        GPS.add(p2);
                        Point p3 = new Point(Float.parseFloat(fields[6]), Float.parseFloat(fields[7]));
                        GPS.add(p3);
                        Point p4 = new Point(Float.parseFloat(fields[8]), Float.parseFloat(fields[9]));
                        GPS.add(p4);
                        Curva cu = new Curva(fields[0], fields[1], GPS);
                        addNode(cu);
                        break;
                    case "Cruzamento":
                        Cruzamento cr = new Cruzamento(fields[0], fields[1], Float.parseFloat(fields[2]), Float.parseFloat(fields[3]));
                        addNode(cr);
                        break;
                    case "Entroncamento":
                        Entroncamento e = new Entroncamento(fields[0], fields[1], Float.parseFloat(fields[2]), Float.parseFloat(fields[3]));
                        addNode(e);
                        break;
                }
            }
        }
    }



    /*---------------------------------------------------------------------------------------------------------------*/

    /*------------------------------------------- POPULAR COMBOBOXS -------------------------------------------------*/


    public void showPartidaComboBox(ArrayList<Node> NodeAL) {
        partidaCombo.getItems().clear();
        for (Node n : NodeAL) {
            partidaCombo.getItems().add(n);
        }
    }

    public void showDestinoComboBox(ArrayList<Node> NodeAL) {
        destinoCombo.getItems().clear();
        for (Node node : NodeAL) {
            destinoCombo.getItems().add(node);
        }
    }

    public void showNodesComboBox(ArrayList<Node> NodeAL) {
        for (Node n : NodeAL) {
            VTremoveComboBox.getItems().add(n);
            VTedit1ComboBox.getItems().add(n);
        }
    }

    public void showEdgesComboBox() {
        VTremove2ComboBox.getItems().clear();
        VTedit2ComboBox.getItems().clear();
        ArrayList<Node> NodeAL = this.Nowarpolis.getAllNodes();
        for (Node n : NodeAL) {
            Iterable<EdgeDoubleWeighted> allEdgesFromNode = Nowarpolis.getAllEdgesFromNode(n);
            for (EdgeDoubleWeighted e : allEdgesFromNode) {
                VTremove2ComboBox.getItems().add(e);
                VTedit2ComboBox.getItems().add(e);
            }
        }
    }

    public void showAddNodeComboBox() {
        ArrayList<Node> NodeAL = this.Nowarpolis.getAllNodes();
        VTvNodes.getItems().clear();
        VTwNodes.getItems().clear();
        for (Node n : NodeAL) {
            VTvNodes.getItems().add(n);
            VTwNodes.getItems().add(n);
        }
    }


    /*---------------------------------------------------------------------------------------------------------------*/

    /*-------------------------------------- REMOVER ITEMS DAS TABELAS ----------------------------------------------*/

    public void handleRemoveUserAction(ActionEvent actionEvent) throws IOException {
        User user = usersTable.getSelectionModel().getSelectedItem();
        usersTable.getItems().remove(usersTable.getSelectionModel().getSelectedItem());
        removerUser(user);
        usersTable.refresh();
    }

    public void handleRemoveNodeAction(ActionEvent actionEvent) throws IOException {
        Nodes node = (Nodes) nodesTable.getSelectionModel().getSelectedItem();
        nodesTable.getItems().remove(nodesTable.getSelectionModel().getSelectedItem());
        if (node.getTipo().equals("Poi")) {
            pois.delete(node.getNome());
        } else if (node.getTipo().equals("Curva")) {
            curvas.delete(node.getNome());
        } else if (node.getTipo().equals("Cruzamento")) {
            cruzamentos.delete(node.getNome());
        } else if (node.getTipo().equals("Entroncamento")) {
            entroncamentos.delete(node.getNome());
        }
        nodesTable.refresh();
        //atualizarLogs();
    }

    public void handleRemoveEtiquetaAction(ActionEvent actionEvent) throws IOException {
        Etiqueta tb = etiquetasTable.getSelectionModel().getSelectedItem();
        etiquetasTable.getItems().remove(etiquetasTable.getSelectionModel().getSelectedItem());
        etiquetas.delete(tb.getNome());
        etiquetasTable.refresh();
    }

    public void handleRemoveLogNodeAction(ActionEvent actionEvent) throws IOException {
        Log log = logsTable.getSelectionModel().getSelectedItem();
        logsTable.getItems().remove(logsTable.getSelectionModel().getSelectedItem());
        logsTable.refresh();
    }
    /*---------------------------------------------------------------------------------------------------------------*/

    /*----------------------------------- EDITAR ITEMS DA TABELA USERS ----------------------------------------------*/

    public void handleEditarUserId(TableColumn.CellEditEvent editedcell) {
        User user = usersTable.getSelectionModel().getSelectedItem();
        if (editedcell.getNewValue().toString().equals("")) return;
        int aux = Integer.parseInt(editedcell.getNewValue().toString());
        if (basics.get(aux) == null && admins.get(aux) == null) {
            if (user.getTipo().equals("admin")) {
                admins.delete(user.getId());
                user.setId(aux);
                admins.put(user.getId(), (Admin) user);
            } else if (user.getTipo().equals("basic")) {
                basics.delete(user.getId());
                user.setId(aux);
                basics.put(user.getId(), (Basic) user);
            }
            listarBasicUsers();
            listarAdminUsers();
        } else {
            Alert("Utilizador", null, "ID do user ja consta na DB!");
        }
    }

    public void handleEditarUserNome(TableColumn.CellEditEvent editedcell) {
        User user = usersTable.getSelectionModel().getSelectedItem();
        if (editedcell.getNewValue().toString().equals("")) return;
        user.setNome(editedcell.getNewValue().toString());
        int aux = Integer.parseInt(editedcell.getNewValue().toString());

    }

    public void handleEditarUserTipo(TableColumn.CellEditEvent editedcell) {
        User user = usersTable.getSelectionModel().getSelectedItem();
        if (editedcell.getNewValue().toString().equals("")) return;
        if (editedcell.getNewValue().toString().equals("admin") ||
                editedcell.getNewValue().toString().equals("basic")) {
            user.setNome(editedcell.getNewValue().toString());
        } else {
            Alert("Utilizador",
                    "(basic / admin)", "Tipo de utilizador introduzido é inválido!");
        }
    }

    /*---------------------------------------------------------------------------------------------------------------*/

    /*----------------------------------- EDITAR ITEMS DA TABELA Nodes ---------------------------------------------*/

    public void handleEditarNodeNome(TableColumn.CellEditEvent editedcell) {
        Object node = nodesTable.getSelectionModel().getSelectedItem();
        if (node instanceof Poi) {
            if ((editedcell.getNewValue().toString().equals("")) || pois.contains(editedcell.getNewValue().toString())) {
                Alert("Poi", null, "Nome do Poi introduzida já consta na DB!");
                return;
            }
            pois.delete(((Poi) node).getNome());
            ((Poi) node).setNome(editedcell.getNewValue().toString());
            pois.put(((Poi) node).getNome(), ((Poi) node));
            atualizarLogs();
        } else if (node instanceof Curva) {
            if ((editedcell.getNewValue().toString().equals("")) || curvas.contains(editedcell.getNewValue().toString())) {
                Alert("Cache", null, "Nome da Curva introduzida já consta na DB!");
                return;
            }
            curvas.delete(((Curva) node).getNome());
            ((Curva) node).setNome(editedcell.getNewValue().toString());
            curvas.put(((Curva) node).getNome(), ((Curva) node));
            atualizarLogs();
        } else if (node instanceof Cruzamento) {
            if ((editedcell.getNewValue().toString().equals("")) || pois.contains(editedcell.getNewValue().toString())) {
                Alert("Cruzamento", null, "Nome do Cruzamento introduzida já consta na DB!");
                return;
            }
            cruzamentos.delete(((Cruzamento) node).getNome());
            ((Cruzamento) node).setNome(editedcell.getNewValue().toString());
            cruzamentos.put(((Cruzamento) node).getNome(), ((Cruzamento) node));
        } else if (node instanceof Entroncamento) {
            if ((editedcell.getNewValue().toString().equals("")) || entroncamentos.contains(editedcell.getNewValue().toString())) {
                Alert("Entroncamento", null, "Nome do Entroncamento introduzida já consta na DB!");
                return;
            }
            entroncamentos.delete(((Entroncamento) node).getNome());
            ((Entroncamento) node).setNome(editedcell.getNewValue().toString());
            entroncamentos.put(((Entroncamento) node).getNome(), ((Entroncamento) node));
            atualizarLogs();
        }

    }

    public void handleEditarNodeX(TableColumn.CellEditEvent editedcell) {
        Object node = nodesTable.getSelectionModel().getSelectedItem();
        if (node instanceof Poi) {
            if (editedcell.getNewValue().toString().equals("")) {
                Alert("Poi", null, "Coordenada intoduzida inválida!");
                return;
            }
            ((Poi) node).getGPS().setLatitude(Float.parseFloat(editedcell.getNewValue().toString()));
        } else if (node instanceof Cruzamento) {
            if (editedcell.getNewValue().toString().equals("")) {
                Alert("Cruzamento", null, "Coordenada intoduzida inválida!");
                return;
            }
            ((Cruzamento) node).getGPS().setLatitude(Float.parseFloat(editedcell.getNewValue().toString()));
        } else if (node instanceof Entroncamento) {
            if (editedcell.getNewValue().toString().equals("")) {
                Alert("Entroncamento", null, "Coordenada intoduzida inválida!");
                return;
            }
            ((Entroncamento) node).getGPS().setLatitude(Float.parseFloat(editedcell.getNewValue().toString()));
        }
    }

    public void handleEditarNodeY(TableColumn.CellEditEvent editedcell) {
        Object node = nodesTable.getSelectionModel().getSelectedItem();
        if (node instanceof Poi) {
            if (editedcell.getNewValue().toString().equals("")) {
                Alert("Poi", null, "Coordenada intoduzida inválida!");
                return;
            }
            ((Poi) node).getGPS().setLongitude(Float.parseFloat(editedcell.getNewValue().toString()));
        } else if (node instanceof Cruzamento) {
            if (editedcell.getNewValue().toString().equals("")) {
                Alert("Cruzamento", null, "Coordenada intoduzida inválida!");
                return;
            }
            ((Cruzamento) node).getGPS().setLongitude(Float.parseFloat(editedcell.getNewValue().toString()));
        } else if (node instanceof Entroncamento) {
            if (editedcell.getNewValue().toString().equals("")) {
                Alert("Entroncamento", null, "Coordenada intoduzida inválida!");
                return;
            }
            ((Entroncamento) node).getGPS().setLongitude(Float.parseFloat(editedcell.getNewValue().toString()));
        }
    }

    /*---------------------------------------------------------------------------------------------------------------*/

    /*----------------------------------- EDITAR TABELA Etiquetas -----------------------------------------*/

    public void handleEditarETNome(TableColumn.CellEditEvent editedcell) {
        Etiqueta et = etiquetasTable.getSelectionModel().getSelectedItem();
        if ((editedcell.getNewValue().toString().equals("")) ||
                etiquetas.contains(editedcell.getNewValue().toString())) {
            Alert("Etiqueta", null, "Nome da Etiqueta introduzida já consta na DB!");
            return;
        }
        etiquetas.delete(et.getNome());
        et.setNome(editedcell.getNewValue().toString());
        etiquetas.put(et.getNome(), et);
    }

    public void handleEditarEtiquetaUser(TableColumn.CellEditEvent editedcell) {
        int flag = 0;
        Etiqueta et = etiquetasTable.getSelectionModel().getSelectedItem();
        for (int i : basics.keys())
            if (basics.get(i).getNome().equals(editedcell.getNewValue().toString())) {
                Alert("Etiqueta", null, "Utilizador introduzido não pode criar TravelBugs!");
                return;
            }
        for (int i : admins.keys())
            if (admins.get(i).getNome().equals(editedcell.getNewValue().toString())) flag++;
        if ((editedcell.getNewValue().toString().equals("")) || flag == 0) {
            Alert("Etiqueta", null, "Nome de Utilizador introduzido inválido!");
            return;
        }
        et.setNomeCriador(editedcell.getNewValue().toString());
    }

    public void handleEditarETDescricao(TableColumn.CellEditEvent editedcell) {
        Etiqueta et = etiquetasTable.getSelectionModel().getSelectedItem();
        if ((editedcell.getNewValue().toString().equals(""))) {
            Alert("Etiqueta", null, "Objetivo introduzido inválido!");
            return;
        }
        et.setDescricao(editedcell.getNewValue().toString());
    }

    public void handleEditarETX(TableColumn.CellEditEvent editedcell) {
        Etiqueta et = etiquetasTable.getSelectionModel().getSelectedItem();
        if ((editedcell.getNewValue().toString().equals("")) ||
                etiquetas.contains(editedcell.getNewValue().toString())) {
            Alert("Etiqueta", null, "Nome da Etiqueta introduzida já consta na DB!");
            return;
        }
        et.getGPS().setLatitude(Float.parseFloat(editedcell.getNewValue().toString()));
    }

    public void handleEditarETY(TableColumn.CellEditEvent editedcell) {
        Etiqueta et = etiquetasTable.getSelectionModel().getSelectedItem();
        if ((editedcell.getNewValue().toString().equals("")) ||
                etiquetas.contains(editedcell.getNewValue().toString())) {
            Alert("Etiqueta", null, "Nome da Etiqueta introduzida já consta na DB!");
            return;
        }
        et.getGPS().setLongitude(Float.parseFloat(editedcell.getNewValue().toString()));
    }

    /*---------------------------------------------------------------------------------------------------------------*/

    /*---------------------------------------- ADICIONAR ITEMS NAS TABELAS ------------------------------------------*/

    public void handleAdicionarUtilizador(ActionEvent actionEvent) {
        if (nomeUtilizadoresTextField.getText().isEmpty() || tipoUtilizadoresTextField.getText().isEmpty() ||
                veiculoUtilizadoresTextField.getText().isEmpty()) {
            Alert("Utilizador", null, "Por favor preencha o campo que se encontra vazio!");
        } else {
            if (tipoUtilizadoresTextField.getText().equals("basic")) {
                Basic b = new Basic(tipoUtilizadoresTextField.getText(), nomeUtilizadoresTextField.getText(), veiculoUtilizadoresTextField.getText());
                basics.put(b.getId(), b);
                usersTable.getItems().add(b);
            } else if (tipoUtilizadoresTextField.getText().equals("admin")) {
                Admin a = new Admin(tipoUtilizadoresTextField.getText(), nomeUtilizadoresTextField.getText(), veiculoUtilizadoresTextField.getText());
                admins.put(a.getId(), a);
                usersTable.getItems().add(a);
            } else
                Alert("Utilizador", "(basic / admin)", "Tipo de Utilizador inválido");
            nomeUtilizadoresTextField.clear();
            tipoUtilizadoresTextField.clear();
            veiculoUtilizadoresTextField.clear();
        }
    }

    public void handleAdicionarNode(ActionEvent actionEvent) {
        if (nomeNodesTextField.getText().isEmpty() || tipoNodesTextField.getText().isEmpty() ||
                xNodesTextField.getText().isEmpty() || yNodesTextField.getText().isEmpty()
                || veiculoNodesTextField.getText().isEmpty()) {
            Alert("Node", null, "Por favor preencha o/s campo/s que se encontra/m vazio!");
        } else {
            if (tipoNodesTextField.getText().equals("Poi") && !tipopoiNodesTextField.getText().isEmpty()) {
                if (!pois.contains(nomeNodesTextField.getText())) {
                    Poi p = new Poi(tipopoiNodesTextField.getText(), nomeNodesTextField.getText(),
                            tipopoiNodesTextField.getText(),
                            Float.parseFloat(xNodesTextField.getText()),
                            Float.parseFloat(yNodesTextField.getText()),
                            veiculoNodesTextField.getText());
                    pois.put(p.getNome(), p);
                    nodesTable.getItems().add(p);
                } else Alert("Node", null, "Node introduzido já consta na DB!");
                nomeNodesTextField.clear();
                tipoNodesTextField.clear();
                tipopoiNodesTextField.clear();
                xNodesTextField.clear();
                yNodesTextField.clear();
                veiculoNodesTextField.clear();
            } else if (tipoNodesTextField.getText().equals("Cruzamento")) {
                if (!cruzamentos.contains(nomeNodesTextField.getText())) {
                    Cruzamento c = new Cruzamento(tipoNodesTextField.getText(), nomeNodesTextField.getText(),
                            Float.parseFloat(xNodesTextField.getText()),
                            Float.parseFloat(yNodesTextField.getText()));
                    cruzamentos.put(c.getNome(), c);
                    nodesTable.getItems().add(c);
                } else Alert("Node", null, "Node introduzido já consta na DB!");
                nomeNodesTextField.clear();
                tipoNodesTextField.clear();
                xNodesTextField.clear();
                yNodesTextField.clear();
            } else if (tipoNodesTextField.getText().equals("Entrocamento")) {
                if (!entroncamentos.contains(nomeNodesTextField.getText())) {
                    Entroncamento e = new Entroncamento(tipoNodesTextField.getText(), nomeNodesTextField.getText(),
                            Float.parseFloat(xNodesTextField.getText()),
                            Float.parseFloat(yNodesTextField.getText()));
                    entroncamentos.put(e.getNome(), e);
                    nodesTable.getItems().add(e);
                } else Alert("Node", null, "Node introduzido já consta na DB!");
                nomeNodesTextField.clear();
                tipoNodesTextField.clear();
                xNodesTextField.clear();
                yNodesTextField.clear();
            }
        }

    }


    public void handleAdicionarLog(ActionEvent actionEvent) {
        if (dataLogsTextField.getText().isEmpty() || nodeLogsTextField.getText().isEmpty()
                || infoLogsTextField.getText().isEmpty() || mensagemLogsTextField.getText().isEmpty()
                || !pois.contains(nodeLogsTextField.getText())) {
            Alert("Log", null, "Por favor preencha o/s campo/s que se encontra/m vazio!");
        } else {
            String[] s = dataLogsTextField.getText().split(", ");
            if (s.length == 6) {
                Date d = new Date(Integer.parseInt(s[0]), Integer.parseInt(s[1]),
                        Integer.parseInt(s[2]), Integer.parseInt(s[3]),
                        Integer.parseInt(s[4]), Integer.parseInt(s[5]));
                Log log = new Log(d, infoLogsTextField.getText(), mensagemLogsTextField.getText());
                pois.get(nodeLogsTextField.getText()).addLogs(log);

                logsTable.getItems().add(log);
            } else Alert("Log", "(Ano, mes, dia, hora, minutos, segundos)",
                    "Data de registo inválida!");
            dataLogsTextField.clear();
            nodeLogsTextField.clear();
            infoLogsTextField.clear();
            mensagemLogsTextField.clear();
        }
    }

    public void handleAdicionarEtiqueta(ActionEvent actionEvent) {
        if (nomeEtiquetasTextField.getText().isEmpty() || userEtiquetasTextField.getText().isEmpty()
                || xEtiquetasTextField.getText().isEmpty() || yEtiquetasTextField.getText().isEmpty()
                || descricaoEtiquetasTextField.getText().isEmpty()) {
            Alert("Etiqueta", null, "Por favor preencha o/s campo/s que se encontra/m vazio!");
        } else {
            if (!etiquetas.contains(nomeEtiquetasTextField.getText())) {
                Etiqueta et = new Etiqueta(nomeEtiquetasTextField.getText(), userEtiquetasTextField.getText(),
                        descricaoEtiquetasTextField.getText(), Float.parseFloat(xEtiquetasTextField.getText()), Float.parseFloat(yEtiquetasTextField.getText()));
                etiquetas.put(et.getNome(), et);

                etiquetasTable.getItems().add(et);
            } else Alert("TravelBug", null, "TravelBug introduzido já consta na DB!");
            nomeEtiquetasTextField.clear();
            userEtiquetasTextField.clear();
            descricaoEtiquetasTextField.clear();
            xEtiquetasTextField.clear();
            yEtiquetasTextField.clear();
        }
    }

    /*---------------------------------------------------------------------------------------------------------------*/

    /*------------------------------------------- FUNÇÔES AUXILIARES ------------------------------------------------*/

    public void Alert(String Title, String Info, String Header) {
        Alert a1 = new Alert(Alert.AlertType.ERROR);
        a1.setTitle(Title);
        a1.setContentText(Info);
        a1.setHeaderText(Header);
        a1.showAndWait();
    }

    public ArrayList<Log> atualizarLogs() {
        ArrayList<Log> LogsCache = new ArrayList<>();
        for (String s : pois.keys()) {
            if (!pois.get(s).getLogs().isEmpty())
                for (int i : pois.get(s).getLogs().keys())
                    LogsCache.add(pois.get(s).getLogs().get(i));
        }
        logsTable.getItems().clear();
        logsTable.getItems().addAll(LogsCache);
        logsTable.refresh();
        return LogsCache;
    }

    /*---------------------------------------------------------------------------------------------------------------*/

    public void handleSaveGraphBin(ActionEvent event) {
        saveBinGraph(grafosbin, Nowarpolis.getNodes());
    }

    public void handleReadBinGraph(ActionEvent event) {
        Nowarpolis.setNodes((SymbolGraphCaches) readBinGraph(grafosbin));
        createGraph(Nowarpolis.getAllNodes());
    }

    public void handleReadGrafosTxt(ActionEvent event) {
        Nowarpolis.setNodes((SymbolGraphCaches) readBinGraph(grafosbin));
        createGraph(Nowarpolis.getAllNodes());
    }

    public void handleWriteGrafosTxt(ActionEvent event) {
        writeGraphTxt(grafostxt);
    }

    public void handleCarregarInfoBin(ActionEvent event) {
        pois = (SeparateChainingHashST<String, Poi>) readBinGraph(nodesBin);
        curvas = (SeparateChainingHashST<String, Curva>) readBinGraph(nodesBin);
        cruzamentos = (SeparateChainingHashST<String, Cruzamento>) readBinGraph(nodesBin);
        entroncamentos = (SeparateChainingHashST<String, Entroncamento>) readBinGraph(nodesBin);
        basics = (SeparateChainingHashST<Integer, Basic>) readBinGraph(basicUsersBin);
        admins = (SeparateChainingHashST<Integer, Admin>) readBinGraph(adminUsersBin);
        etiquetas = (RedBlackBST<String, Etiqueta>) readBinGraph(etiquetasBin);
    }

    public void handleGravarInfoBin(ActionEvent event) {
        saveBinGraph(basicUsersBin, basics);
        saveBinGraph(adminUsersBin, admins);
        saveBinGraph(etiquetasBin, etiquetas);
        saveBinGraph(nodesBin, pois);
        saveBinGraph(nodesBin, curvas);
        saveBinGraph(nodesBin, cruzamentos);
        saveBinGraph(nodesBin, entroncamentos);
    }

    public void handleGravarInfoTxt(ActionEvent event) throws IOException {
        escritaFicheiroTxtUtilizadores();
        escritaFicheiroTxtPois();
        escritaFicheiroTxtCurvas();
        escritaFicheiroTxtCruzamentos();
        escritaFicheiroTxtEntroncamentos();
        escritaFicheiroTxtEtiquetas();
    }

    /*---------------------------------------------------------------------------------------------------------------*/

    private void saveBinGraph(String Filename, Object objeto) {
        File f = new File(Filename);
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(objeto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Object readBinGraph(String Filename) {
        File f = new File(Filename);
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * guarda o grafo num ficheiro de texto
     */
    private void writeGraphTxt(String Filename) {
        ArrayList<EdgeDoubleWeighted> edges = new ArrayList<>();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(Filename));
            for (int i = 0; i < this.Nowarpolis.getG().V(); i++) {
                for (EdgeDoubleWeighted e : this.Nowarpolis.getG().adj(i)) {
                    if (!edges.contains(e)) {
                        edges.add(e);
                        Node n = this.Nowarpolis.getNode(e.getV());
                        Node n1 = this.Nowarpolis.getNode(e.getW());
                        if (n.getN() instanceof Poi) {
                            writer.write(((Poi) n.getN()).getNome() + ";");
                        } else if (n.getN() instanceof Curva) {
                            writer.write(((Curva) n.getN()).getNome() + "; ");
                        } else if (n.getN() instanceof Cruzamento) {
                            writer.write(((Cruzamento) n.getN()).getNome() + "; ");
                        } else if (n.getN() instanceof Entroncamento) {
                            writer.write(((Entroncamento) n.getN()).getNome() + "; ");
                        }
                        if (n1.getN() instanceof Poi) {
                            writer.write(((Poi) n1.getN()).getNome() + "; " + e.stWeight());
                            writer.newLine();
                        } else if (n1.getN() instanceof Curva) {
                            writer.write(((Curva) n1.getN()).getNome() + "; " + e.stWeight());
                            writer.newLine();
                        } else if (n1.getN() instanceof Cruzamento) {
                            writer.write(((Cruzamento) n1.getN()).getNome() + "; " + e.stWeight());
                            writer.newLine();
                        } else if (n1.getN() instanceof Entroncamento) {
                            writer.write(((Entroncamento) n1.getN()).getNome() + "; " + e.stWeight());
                            writer.newLine();
                        }
                    }
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /*---------------------------------------------------------------------------------------------------------------*/

    private void createGraph(ArrayList<Node> nodes) {
        graphPane.getChildren().clear();

        for (Node n : nodes) {
            Iterable<EdgeDoubleWeighted> edges = Nowarpolis.getAllEdgesFromNode(n);

            double posX = 0;
            double posY = 0;
            if (n.getN() instanceof Poi) {
                posX = ((Poi) n.getN()).getGPS().getLatitude();
                posY = ((Poi) n.getN()).getGPS().getLongitude();
            } else if (n.getN() instanceof Curva) {
                posX = ((Curva) n.getN()).getGPS().get(0).getLatitude();
                posY = ((Curva) n.getN()).getGPS().get(0).getLongitude();
            } else if (n.getN() instanceof Cruzamento) {
                posX = ((Cruzamento) n.getN()).getGPS().getLatitude();
                posY = ((Cruzamento) n.getN()).getGPS().getLongitude();
            } else if (n.getN() instanceof Entroncamento) {
                posX = ((Entroncamento) n.getN()).getGPS().getLatitude();
                posY = ((Entroncamento) n.getN()).getGPS().getLongitude();
            }

            CoordenadasVertices coordenadas = new CoordenadasVertices(posX, posY);
            Circle c = new Circle(coordenadas.getX(), coordenadas.getY(), radius);
            Text text = new Text(((Poi) n.getN()).getNome());
            if (edges.iterator().hasNext()) {
                for (EdgeDoubleWeighted e : edges) {

                    StackPane stackPane = new StackPane();
                    stackPane.setLayoutX(coordenadas.getX() - radius);
                    stackPane.setLayoutY(coordenadas.getY() - radius);
                    c.setFill(Color.GRAY);

                    if (n.compareTo(Nowarpolis.getNode(e.getV())) != 0) {
                        StackPane stackPanenew = new StackPane();
                        CoordenadasVertices coordenadas2 = new CoordenadasVertices();
                        if (Nowarpolis.getNode(e.getV()).getN() instanceof Poi) {
                            coordenadas2 = new CoordenadasVertices(
                                    ((Poi) Nowarpolis.getNode(e.getV()).getN()).getGPS().getLatitude(),
                                    ((Poi) Nowarpolis.getNode(e.getV()).getN()).getGPS().getLongitude());
                        } else if (Nowarpolis.getNode(e.getV()).getN() instanceof Curva) {
                            coordenadas2 = new CoordenadasVertices(
                                    ((Curva) Nowarpolis.getNode(e.getV()).getN()).getGPS().get(0).getLatitude(),
                                    ((Curva) Nowarpolis.getNode(e.getV()).getN()).getGPS().get(0).getLongitude());
                        } else if (Nowarpolis.getNode(e.getV()).getN() instanceof Cruzamento) {
                            coordenadas2 = new CoordenadasVertices(
                                    ((Cruzamento) Nowarpolis.getNode(e.getV()).getN()).getGPS().getLatitude(),
                                    ((Cruzamento) Nowarpolis.getNode(e.getV()).getN()).getGPS().getLongitude());
                        } else if (Nowarpolis.getNode(e.getV()).getN() instanceof Entroncamento) {
                            coordenadas2 = new CoordenadasVertices(
                                    ((Entroncamento) Nowarpolis.getNode(e.getV()).getN()).getGPS().getLatitude(),
                                    ((Entroncamento) Nowarpolis.getNode(e.getV()).getN()).getGPS().getLongitude());
                        }
                        Line l = new Line(coordenadas.getX(), coordenadas.getY(),
                                coordenadas2.getX(), coordenadas2.getY());
                        l.setStrokeWidth(2.0);
                        graphPane.getChildren().addAll(stackPanenew, l);

                    } else {
                        StackPane stackPanenew = new StackPane();
                        CoordenadasVertices cv = new CoordenadasVertices();
                        CoordenadasVertices cw = new CoordenadasVertices();
                        if (Nowarpolis.getNode(e.getV()).getN() instanceof Poi) {
                            cv = new CoordenadasVertices(
                                    ((Poi) Nowarpolis.getNode(e.getV()).getN()).getGPS().getLatitude(),
                                    ((Poi) Nowarpolis.getNode(e.getV()).getN()).getGPS().getLongitude());
                            cw = new CoordenadasVertices(
                                    ((Poi) Nowarpolis.getNode(e.getV()).getN()).getGPS().getLatitude(),
                                    ((Poi) Nowarpolis.getNode(e.getV()).getN()).getGPS().getLongitude());
                        } else if (Nowarpolis.getNode(e.getV()).getN() instanceof Curva) {
                            cv = new CoordenadasVertices(
                                    ((Curva) Nowarpolis.getNode(e.getV()).getN()).getGPS().get(0).getLatitude(),
                                    ((Curva) Nowarpolis.getNode(e.getV()).getN()).getGPS().get(0).getLongitude());
                            cw = new CoordenadasVertices(
                                    ((Curva) Nowarpolis.getNode(e.getV()).getN()).getGPS().get(0).getLatitude(),
                                    ((Curva) Nowarpolis.getNode(e.getV()).getN()).getGPS().get(0).getLongitude());
                        } else if (Nowarpolis.getNode(e.getV()).getN() instanceof Cruzamento) {
                            cv = new CoordenadasVertices(
                                    ((Cruzamento) Nowarpolis.getNode(e.getV()).getN()).getGPS().getLatitude(),
                                    ((Cruzamento) Nowarpolis.getNode(e.getV()).getN()).getGPS().getLongitude());
                            cw = new CoordenadasVertices(
                                    ((Cruzamento) Nowarpolis.getNode(e.getV()).getN()).getGPS().getLatitude(),
                                    ((Cruzamento) Nowarpolis.getNode(e.getV()).getN()).getGPS().getLongitude());
                        } else if (Nowarpolis.getNode(e.getV()).getN() instanceof Entroncamento) {
                            cv = new CoordenadasVertices(
                                    ((Entroncamento) Nowarpolis.getNode(e.getV()).getN()).getGPS().getLatitude(),
                                    ((Entroncamento) Nowarpolis.getNode(e.getV()).getN()).getGPS().getLongitude());
                            cw = new CoordenadasVertices(
                                    ((Entroncamento) Nowarpolis.getNode(e.getV()).getN()).getGPS().getLatitude(),
                                    ((Entroncamento) Nowarpolis.getNode(e.getV()).getN()).getGPS().getLongitude());
                        }
                        Line l = new Line(cv.getX(), cv.getY(), cw.getX(), cw.getY());
                        l.setStrokeWidth(2.0);
                        graphPane.getChildren().addAll(stackPanenew, l);
                    }
                    stackPane.getChildren().addAll(c, text);
                    graphPane.getChildren().add(stackPane);
                }
            } else {
                StackPane stackPane = new StackPane();
                c.setFill(Color.GRAY);
                stackPane.setLayoutX(coordenadas.getX() - radius);
                stackPane.setLayoutY(coordenadas.getY() - radius);
                stackPane.getChildren().addAll(c, text);
                graphPane.getChildren().add(stackPane);
            }

        }

        showPartidaComboBox(nodes);

        showDestinoComboBox(nodes);

    }
    /*---------------------------------------------------------------------------------------------------------------*/


    public void handleResetGraph(ActionEvent actionEvent) {
        partidaCombo.getSelectionModel().clearSelection();
        destinoCombo.getSelectionModel().clearSelection();

        createGraph(this.Nowarpolis.getAllNodes());
    }

    public void handleSelectInicialNode(ActionEvent actionEvent) {
        Node n = partidaCombo.getValue();
        Node n2;
        if (destinoCombo.getValue() != null) {
            n2 = destinoCombo.getValue();
            findFastestPath(n, n2);
            findShortestPath(n, n2);
        }
    }

    public void handleSelectFinalNode(ActionEvent actionEvent) {
        Node n = destinoCombo.getValue();
        Node n2;
        if (partidaCombo.getValue() != null) {
            n2 = partidaCombo.getValue();
            findFastestPath(n2, n);
            findShortestPath(n2, n);
        }
    }

    /*---------------------------------------------------------------------------------------------------------------*/

    private ArrayList<EdgeDoubleWeighted> findShortestPath(Node source, Node s) {
        ArrayList<String> pesquisa = new ArrayList<>();
        ArrayList<EdgeDoubleWeighted> edge = new ArrayList<>();
        /*for (int v = 0; v < this.Nowarpolis.getG().V(); v++) {
            if (source.compareTo(this.Nowarpolis.getNodes().nameOf(v)) == 0) {
                for (int i = 0; i < this.Nowarpolis.getG().V(); i++) {
                    if (s.compareTo(this.Nowarpolis.getNodes().nameOf(i)) == 0) {
                        DijkstraUndirectedSPDoubleWeighted sp = new DijkstraUndirectedSPDoubleWeighted(
                                this.Nowarpolis.getG(), v);
                        pesquisa.add("--- Shortest Path ---\n" + s + " -> " + Nowarpolis.getNode(i) + ": "
                                + sp.distTo(i) + " km\n Path:\n");
                        System.out.println("--- Shortest Path ---\n" + s + " -> " + Nowarpolis.getNode(i) + ": "
                                + sp.distTo(i) + " km\n Path:");
                        if (sp.hasPathTo(i)) {
                            for (EdgeDoubleWeighted e : sp.pathTo(i)) {
                                pesquisa.add(e.getV() + " -> " + e.getW() + ": " + e.getStWeight() + " km\n");
                                System.out.println(e.getV() + " -> " + e.getW() + ": " + e.getStWeight() + " km");
                                CoordenadasVertices cv = new CoordenadasVertices((
                                        Nowarpolis.getNode(e.getV()).getN().getLatitude()),
                                        Nowarpolis.getNode(e.getV()).getC().getLongitude());
                                CoordenadasVertices cw = new CoordenadasVertices((
                                        Nowarpolis.getNode(e.getW()).getC().getLatitude()),
                                        Nowarpolis.getNode(e.getW()).getC().getLongitude());
                                Line l = new Line(cv.getX(), cv.getY(), cw.getX(), cw.getY());
                                l.setStrokeWidth(4.0);
                                l.setStroke(Color.GREEN);
                                graphPane.getChildren().add(l);
                                edge.add(e);
                            }
                        } else {
                            pesquisa.add(s + " -> " + Nowarpolis.getNode(i) + ": No path!\n");
                            System.out.println(s + " -> " + Nowarpolis.getNode(i) + ": No path!\n");
                        }
                    }
                }
            }
        }
        carregarPesquisasTxT(pesquisa);*/
        return edge;
    }

    private ArrayList<EdgeDoubleWeighted> findFastestPath(Node source, Node s) {
        ArrayList<String> pesquisa = new ArrayList<>();
        ArrayList<EdgeDoubleWeighted> edges = new ArrayList<>();
        /*for (int v = 0; v < this.Nowarpolis.getG().V(); v++) {
            if (source.compareTo(this.Nowarpolis.getNodes().nameOf(v)) == 0) {
                for (int i = 0; i < this.Nowarpolis.getG().V(); i++) {
                    if (s.compareTo(this.Nowarpolis.getNodes().nameOf(i)) == 0) {
                        DijkstraUndirectedSPDoubleWeighted sp = new DijkstraUndirectedSPDoubleWeighted(
                                this.Nowarpolis.getG(), v, "secondWeight");
                        pesquisa.add("--- Fastest Path ---\n" + s + " -> " + Nowarpolis.getNode(i) + ": "
                                + sp.distTo(i) + " minutos\n Path:\n");
                        System.out.println("--- Fastest Path ---\n" + s + " -> " + Nowarpolis.getNode(i) + ": "
                                + sp.distTo(i) + " minutos\n Path:");
                        if (sp.hasPathTo(i)) {
                            for (EdgeDoubleWeighted e : sp.pathTo(i)) {
                                pesquisa.add(e.getV() + " -> " + e.getW() + ": " + e.getNdWeight() + " minutos\n");
                                System.out.println(e.getV() + " -> " + e.getW() + ": " + e.getNdWeight() + " minutos");
                                CoordenadasVertices cv = new CoordenadasVertices((
                                        Nowarpolis.getNode(e.getV()).getC().getLatitude()),
                                        Nowarpolis.getNode(e.getV()).getC().getLongitude());
                                CoordenadasVertices cw = new CoordenadasVertices((
                                        Nowarpolis.getNode(e.getW()).getC().getLatitude()),
                                        Nowarpolis.getNode(e.getW()).getC().getLongitude());
                                Line l = new Line(cv.getX(), cv.getY(), cw.getX(), cw.getY());
                                l.setStrokeWidth(7.5);
                                l.setStroke(Color.BLUE);
                                graphPane.getChildren().add(l);
                                edges.add(e);
                            }
                        } else {
                            pesquisa.add(s + " -> " + Nowarpolis.getNode(i) + ": No path!\n");
                            System.out.println(s + " -> " + Nowarpolis.getNode(i) + ": No path!\n");
                        }
                    }
                }
            }
        }
        carregarPesquisasTxT(pesquisa);*/
        return edges;
    }

    public void carregarPesquisasTxT(ArrayList<String> pesquisa) {
       /* try {
            FileWriter wr = new FileWriter(pesquisasTxT, true);
            for (String s : pesquisa) {
                wr.write(s);
            }
            wr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    /*---------------------------------------------------------------------------------------------------------------*/

    public void handleCreateNewNode(ActionEvent actionEvent) {
        /*String nome = VTnomeTextField.getText();
        String tipo = VTtipoTextField.getText();
        Float latitude = Float.parseFloat(VTxTextField.getText());
        Float longitude = Float.parseFloat(VTyTextField.getText());
        if (pois.contains(nome)) {
            System.out.println("Nome inserido inválido!");
            return;
        }
        Cache c = new Cache(nome, regiao, latitude, longitude, null, null, tipo);
        caches.put(c.getNome(), c);
        geocachesTable.getItems().add(c);

        VTnomeTextField.clear();
        VTtipoTextField.clear();
        VTxTextField.clear();
        VTyTextField.clear();

        Nodes n = new Nodes(c);
        Geocaching.getNodes().getSt().put(n, Geocaching.getNodes().getSt().size());*/
        /*Bag<EdgeDoubleWeighted> list = new Bag<EdgeDoubleWeighted>();
        Geocaching.getNodes().getGraph().adj[Geocaching.getNodes().getSt().size()-1] = list;
        ArrayList<Node> allNodes = Geocaching.getAllNodes();
        allNodes.add(n);
        createGraphGroup(allNodes, null, null);*/
    }

    public void handleShowNodeInfo(ActionEvent actionEvent) {
        /*Node node = VTedit1ComboBox.getValue();
        VTedit3TF.setText(node.getC().getLatitude() + "");
        VTedit4TF.setText(node.getC().getLongitude() + "");*/
    }

    public void handleEditNode(ActionEvent actionEvent) {
        /*Node node = VTedit1ComboBox.getValue();
        Float latitude = Float.parseFloat(VTedit3TF.getText());
        Float longitude = Float.parseFloat(VTedit4TF.getText());
        node.getC().setLatitude(latitude);
        node.getC().setLongitude(longitude);
        VTedit1ComboBox.getSelectionModel().clearSelection();
        VTedit3TF.clear();
        VTedit4TF.clear();
        createGraphGroup(Geocaching.getAllNodes(), null, null);*/
    }

    public void handleCreateNewEdge(ActionEvent actionEvent) {
        /*Node n1 = VTvNodes.getValue();
        Node n2 = VTwNodes.getValue();
        Double stWeight = di;
        EdgeDoubleWeighted edw = new EdgeDoubleWeighted(Nowarpolis.getNodes().indexOf(n1),
                Nowarpolis.getNodes().indexOf(n2), stWeight);
        VTvNodes.getSelectionModel().clearSelection();
        VTwNodes.getSelectionModel().clearSelection();

        Nowarpolis.getG().addEdge(edw);
        showEdgesComboBox();
        handleResetGraph(null);*/
    }

    public void handleShowEdgeInfo(ActionEvent actionEvent) {
        EdgeDoubleWeighted edw = VTedit2ComboBox.getValue();
        VT3edgeEditTF.setText(edw.getStWeight() + "");
    }

    public void handleEditEdge(ActionEvent actionEvent) {
        EdgeDoubleWeighted edw = VTedit2ComboBox.getValue();
        Double stWeight = Double.parseDouble(VT3edgeEditTF.getText());
        Double ndWeight = Double.parseDouble(VT4edgeEditTF.getText());
        edw.setStWeight(stWeight);
        VTedit2ComboBox.getSelectionModel().clearSelection();
        VT3edgeEditTF.clear();
        VT4edgeEditTF.clear();
        showEdgesComboBox();
    }

    public void handleRemoveNode(ActionEvent actionEvent) {
    }

    public void handleRemoveEdge(ActionEvent actionEvent) {
        EdgeDoubleWeighted edw = VTremove2ComboBox.getValue();
        Bag<EdgeDoubleWeighted> list = new Bag<EdgeDoubleWeighted>();
        Nowarpolis.getNodes().getGraph().adj(edw.getW());
        for (EdgeDoubleWeighted edge : Nowarpolis.getNodes().getGraph().adj(edw.getV())) {
            if (edge != edw) {
                list.add(edge);
            }
        }
        Nowarpolis.getNodes().getGraph().adj[edw.getV()] = list;
        Bag<EdgeDoubleWeighted> list2 = new Bag<EdgeDoubleWeighted>();
        for (EdgeDoubleWeighted edge : Nowarpolis.getNodes().getGraph().adj(edw.getW())) {
            if (edge != edw) {
                list2.add(edge);
            }
        }
        Nowarpolis.getNodes().getGraph().adj[edw.getW()] = list2;
        showEdgesComboBox();
        VTremove2ComboBox.getSelectionModel().clearSelection();
        handleResetGraph(null);
    }

}