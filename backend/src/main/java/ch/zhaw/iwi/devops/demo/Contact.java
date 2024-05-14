package ch.zhaw.iwi.devops.demo;

public class Contact {

    private int id;
    private String vorname;
    private String nachname;

    public Contact() {
    }
    
    public Contact(int id, String vorname, String nachname) {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
    }

    public int getId() {
        return id;
    }    

    public void setId(int id) {
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }
    
}