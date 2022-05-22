package NoWarPolis;


import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Log implements Serializable{

    private static int count = 0;
    private int id;

    private Date date;

    private String Info;

    private String Mensagem;

    private Integer userId;

    /*---------------------------------------------------------------------------------------------------------------*/

    public Log(Date date, String info, String mensagem) {
        id = count++;
        this.date = date;
        this.Info = info;
        Mensagem = mensagem;
    }

    /*---------------------------------------------------------------------------------------------------------------*/

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public Date getDate() {return date;}

    public void setDate(Date date) {this.date = date;}

    public String getInfo() {return Info;}

    public void setInfo(String info) {Info = info;}

    public String getMensagem() {return Mensagem;}

    public void setMensagem(String mensagem) {Mensagem = mensagem;}

    public Integer getUserId() {return userId;}

    public void setUserId(Integer userId) {this.userId = userId;}
}