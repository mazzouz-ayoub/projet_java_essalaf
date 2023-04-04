package ma.fstt.model;

// java bean
public class Produit {
    private Long id_produit ;

    private String nom_produit ;

    private String prix ;
    private String description ;

    public Produit() {
    }

    public Produit(Long id_produit, String nom_produit, String prix , String description) {
        this.id_produit = id_produit;
        this.nom_produit = nom_produit;
        this.prix = prix;
        this.description = description;
    }

    public Long getId_produit() {
        return id_produit;
    }

    public void setId_produit(Long id_produit) {
        this.id_produit = id_produit;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




    @Override
    public String toString() {
        return "Produit{" +
                "id_livreur=" + id_produit +
                ", nom='" + nom_produit + '\'' +
                ", telephone='" + prix + '\'' +
                ", telephone='" + description + '\'' +
                '}';
    }
}
