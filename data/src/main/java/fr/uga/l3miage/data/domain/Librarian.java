package fr.uga.l3miage.data.domain;

public class Librarian extends Person{

    private Librarian manager;

    public Librarian getManager() {
        return manager;
    }

    public void setManager(Librarian manager) {
        this.manager = manager;
    }
}
