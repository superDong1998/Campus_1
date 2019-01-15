import java.io.Serializable;

public class person implements Serializable {
    private String name;
    private String password;
    private int ID;

    public person(){

    }
    public String getName(){
        return name;
    }

    public void setName(String name){
       this.name = name;
    }
}
