package ma.fstt.model;

// java bean
public class Client {
        private Long id_client ;

        private String nom ;

        private String telephone ;

    public Client() {
    }

    public Client(Long id_client, String nom, String telephone) {
        this.id_client = id_client;
        this.nom = nom;
        this.telephone = telephone;
    }

    public Long getId_client() {
        return id_client;
    }

    public void setId_client(Long id_client) {
        this.id_client = id_client;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id_client=" + id_client +
                ", nom='" + nom + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
