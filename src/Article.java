public class Article {
    private int id;
    private String nom;
    private double prixUnitaire;
    private double prixVrac;
    private int quantiteVrac;

    public Article(int id, String nom, double prixUnitaire, double prixVrac, int quantiteVrac) {
        this.id = id;
        this.nom = nom;
        this.prixUnitaire = prixUnitaire;
        this.prixVrac = prixVrac;
        this.quantiteVrac = quantiteVrac;
    }

    // Getters et setters
    public int getId() { return id; }
    public String getNom() { return nom; }
    public double getPrixUnitaire() { return prixUnitaire; }
    public double getPrixVrac() { return prixVrac; }
    public int getQuantiteVrac() { return quantiteVrac; }
}gi