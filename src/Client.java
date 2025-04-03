public class Client {
    private int id;
    private String nom;
    private String email;
    private boolean ancienClient; // True si ancien client

    public Client(int id, String nom, String email, boolean ancienClient) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.ancienClient = ancienClient;
    }

    // Test commit Bat 

    // Getters et setters
    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getEmail() { return email; }
    public boolean isAncienClient() { return ancienClient; }
}