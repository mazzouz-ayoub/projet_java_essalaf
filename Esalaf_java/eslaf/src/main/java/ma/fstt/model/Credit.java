package ma.fstt.model;


import java.sql.Date;

// java bean
public class Credit {
    private Long id_credit ;

    private String nom_produit ;

    private String nom_client ;

    private String datedebut ;

    private String montant ;


    public Credit(Long id_credit, String nom_produit, String nom_client , String datedebut , String montant) {
        this.id_credit = id_credit;
        this.nom_produit = nom_produit;
        this.nom_client = nom_client;
        this.datedebut = datedebut;
        this.montant = montant;
    }

    public Credit() {

    }

    public Long getId_credit() {
        return id_credit;
    }

    public void setId_credit(Long id_credit) {
        this.id_credit = id_credit;
    }

    public String getNom_produit() {
        return nom_produit;
    }
    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public String getNom_client() {
        return nom_client;
    }

    public void setNom_client( String nom_client) {
        this.nom_client = nom_client;
    }

    public String getDatedebut() {
        return datedebut;
    }

    public void setDatedebut( String datedebut) {
        this.datedebut = datedebut;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant( String montant) {
        this.montant = montant;
    }




    @Override
    public String toString() {
        return "Commande{" +
                "id_credit=" + id_credit +
                ", nom_produit='" + nom_produit + '\'' +
                ", nom_client ='" + nom_client + '\'' +
                ", datedebut ='" + datedebut + '\'' +
                ", montant ='" + montant + '\'' +
                '}';
    }
}

