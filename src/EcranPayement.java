import javax.swing.*;

import java.awt.*;

import javax.swing.text.*;

import java.text.ParseException;

public class EcranPayement {
    private static final String[] ListeDesPays = {
            "Afghanistan", "Afrique du Sud", "Albanie", "Algérie", "Allemagne", "Andorre", "Angola", "Antigua-et-Barbuda", "Arabie Saoudite", "Argentine", "Arménie", "Australie", "Autriche", "Azerbaïdjan", "Bahamas", "Bahreïn", "Bangladesh", "Barbade", "Belgique", "Belize", "Bénin", "Bhoutan", "Biélorussie", "Birmanie", "Bolivie", "Bosnie-Herzégovine", "Botswana", "Brésil", "Brunei", "Bulgarie", "Burkina Faso", "Burundi", "Cambodge", "Cameroun", "Canada", "Cap-Vert", "République centrafricaine", "Chili", "Chine", "Chypre", "Colombie", "Comores", "République du Congo", "République démocratique du Congo", "Corée du Nord", "Corée du Sud", "Costa Rica", "Côte d’Ivoire", "Croatie", "Cuba", "Danemark", "Djibouti", "Dominique", "Égypte", "Émirats arabes unis", "Équateur", "Érythrée", "Espagne", "Estonie", "Eswatini", "États-Unis", "Éthiopie", "Fidji", "Finlande", "France", "Gabon", "Gambie", "Géorgie", "Ghana", "Grèce", "Grenade", "Guatemala", "Guinée", "Guinée équatoriale", "Guinée-Bissau", "Guyana", "Haïti", "Honduras", "Hongrie", "Inde", "Indonésie", "Irak", "Iran", "Irlande", "Islande", "Israël", "Italie", "Jamaïque", "Japon", "Jordanie", "Kazakhstan", "Kenya", "Kirghizistan", "Kiribati", "Koweït", "Laos", "Lesotho", "Lettonie", "Liban", "Liberia", "Libye", "Liechtenstein", "Lituanie", "Luxembourg", "Macédoine du Nord", "Madagascar", "Malaisie", "Malawi", "Maldives", "Mali", "Malte", "Maroc", "Îles Marshall", "Maurice", "Mauritanie", "Mexique", "Micronésie", "Moldavie", "Monaco", "Mongolie", "Monténégro", "Mozambique", "Namibie", "Nauru", "Népal", "Nicaragua", "Niger", "Nigeria", "Norvège", "Nouvelle-Zélande", "Oman", "Ouganda", "Ouzbékistan", "Pakistan", "Palaos", "Palestine", "Panama", "Papouasie-Nouvelle-Guinée", "Paraguay", "Pays-Bas", "Pérou", "Philippines", "Pologne", "Portugal", "Qatar", "Roumanie", "Royaume-Uni", "Russie", "Rwanda", "Saint-Kitts-et-Nevis", "Saint-Marin", "Saint-Vincent-et-les-Grenadines", "Sainte-Lucie", "Salvador", "Samoa", "Sao Tomé-et-Principe", "Sénégal", "Serbie", "Seychelles", "Sierra Leone", "Singapour", "Slovaquie", "Slovénie", "Somalie", "Soudan", "Soudan du Sud", "Sri Lanka", "Suède", "Suisse", "Suriname", "Syrie", "Tadjikistan", "Tanzanie", "Tchad", "République tchèque", "Thaïlande", "Timor oriental", "Togo", "Tonga", "Trinité-et-Tobago", "Tunisie", "Turkménistan", "Turquie", "Tuvalu", "Ukraine", "Uruguay", "Vanuatu", "Vatican", "Venezuela", "Vietnam", "Yémen", "Zambie", "Zimbabwe"
    };

    private static final String[] ListeDesMethodesDePaiement = {"Carte de Crédit", "Paypal", "Lydia", "Cryptomonnaie"};
    private static final String[] ListeDesCryptomonnaies = {
            "Bitcoin", "Ethereum", "Ripple", "Litecoin", "Dash", "Monero",
            "Ethereum Classic", "NEM", "Orpherum Caissierum", "Vicouillum Ottistum"
    };

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> AfficherFenetreAdresse());
    }

    private static void AfficherFenetreAdresse() {
        JFrame FenetreAdresseDeLivraison = new JFrame("Adresse de livraison");
        FenetreAdresseDeLivraison.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FenetreAdresseDeLivraison.setSize(1000, 600);
        FenetreAdresseDeLivraison.setLocationRelativeTo(null);

        // Image de fond
        ImageIcon imageDeFond = new ImageIcon("maps2.jpg");
        JLabel labelImageFond = new JLabel(imageDeFond);
        labelImageFond.setBounds(0, 0, 1000, 600);

        // Panel transparent pour le contenu
        JPanel panelContenu = new JPanel();
        panelContenu.setLayout(null);
        panelContenu.setOpaque(false);
        panelContenu.setBounds(0, 0, 1000, 600);

        // Titre
        JLabel Titre = new JLabel("Remplissez vos informations");
        Titre.setFont(new Font("SansSerif", Font.BOLD, 24));
        Titre.setForeground(Color.BLACK);
        Titre.setBounds(350, 10, 400, 30);
        panelContenu.add(Titre);

        // Panel pour les champs de formulaire
        JPanel panelFormulaire = new JPanel(new GridLayout(6, 2, 10, 10));
        panelFormulaire.setOpaque(false);
        panelFormulaire.setBounds(200, 70, 600, 300);

        JComboBox<String> ListePaysPourSelection = new JComboBox<>(ListeDesPays);
        JTextField ChampPourVille = new JTextField();
        JTextField ChampPourCodePostal = new JTextField();
        JTextField ChampPourAdresse = new JTextField();
        JTextField ChampPourDeuxiemeAdresse = new JTextField();
        JButton BoutonSuivantPourAdresse = new JButton("Suivant");

        // Couleur verte pour les labels
        Color vertLabel = new Color(0, 153, 76);

        panelFormulaire.add(creerLabelVert("Pays :", vertLabel));
        panelFormulaire.add(ListePaysPourSelection);
        panelFormulaire.add(creerLabelVert("Ville :", vertLabel));
        panelFormulaire.add(ChampPourVille);
        panelFormulaire.add(creerLabelVert("Code Postal :", vertLabel));
        panelFormulaire.add(ChampPourCodePostal);
        panelFormulaire.add(creerLabelVert("Adresse :", vertLabel));
        panelFormulaire.add(ChampPourAdresse);
        panelFormulaire.add(creerLabelVert("Compléments d'adresse (Optionnel) :", vertLabel));
        panelFormulaire.add(ChampPourDeuxiemeAdresse);
        panelFormulaire.add(new JLabel()); // vide
        panelFormulaire.add(BoutonSuivantPourAdresse);

        // Style du bouton
        BoutonSuivantPourAdresse.setBackground(new Color(0, 120, 215));
        BoutonSuivantPourAdresse.setForeground(Color.WHITE);
        BoutonSuivantPourAdresse.setFocusPainted(false);

        // Ajout dans panel principal
        panelContenu.add(panelFormulaire);

        // Ajout au layeredPane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1000, 600));
        layeredPane.add(labelImageFond, Integer.valueOf(0));
        layeredPane.add(panelContenu, Integer.valueOf(1));

        FenetreAdresseDeLivraison.setContentPane(layeredPane);

        BoutonSuivantPourAdresse.addActionListener(e -> {
            if (ChampPourVille.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(FenetreAdresseDeLivraison, "Le champ Ville est obligatoire.");
            } else if (ChampPourCodePostal.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(FenetreAdresseDeLivraison, "Le champ Code Postal est obligatoire.");
            } else if (ChampPourAdresse.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(FenetreAdresseDeLivraison, "Le champ Adresse est obligatoire.");
            } else {
                FenetreAdresseDeLivraison.dispose();
                AfficherFenetreMethodesDePaiement();
            }
        });

        FenetreAdresseDeLivraison.pack();
        FenetreAdresseDeLivraison.setVisible(true);
    }

    // Crée un label encadré en vert
    private static JLabel creerLabelVert(String texte, Color couleur) {
        JLabel label = new JLabel(texte);
        label.setOpaque(true);
        label.setBackground(couleur);
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        return label;
    }

    private static void AfficherFenetreMethodesDePaiement() {
        JFrame FenetreMethodesDePaiement = new JFrame("Méthode de Paiement");
        FenetreMethodesDePaiement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FenetreMethodesDePaiement.setSize(1000, 600);
        FenetreMethodesDePaiement.setLocationRelativeTo(null);

        ImageIcon imageDeFond = new ImageIcon("payement.png");
        JLabel labelImageFond = new JLabel(imageDeFond);
        labelImageFond.setBounds(0, 0, 1000, 600);

        JPanel panelMethode = new JPanel(new GridLayout(ListeDesMethodesDePaiement.length + 2, 1, 5, 5));
        panelMethode.setOpaque(false);
        panelMethode.setBounds(300, 100, 400, 300);

        JLabel titre = new JLabel("Choisissez un moyen de paiement");
        titre.setFont(new Font("SansSerif", Font.BOLD, 20));
        titre.setHorizontalAlignment(SwingConstants.CENTER);
        titre.setForeground(Color.BLACK);
        titre.setBounds(300, 65, 400, 30);

        ButtonGroup GroupePourMethodes = new ButtonGroup();
        JRadioButton[] BoutonsPourMethodes = new JRadioButton[ListeDesMethodesDePaiement.length];

        for (int i = 0; i < ListeDesMethodesDePaiement.length; i++) {
            BoutonsPourMethodes[i] = new JRadioButton(ListeDesMethodesDePaiement[i]);
            BoutonsPourMethodes[i].setOpaque(true);
            BoutonsPourMethodes[i].setBackground(new Color(200, 255, 200));
            GroupePourMethodes.add(BoutonsPourMethodes[i]);
            panelMethode.add(BoutonsPourMethodes[i]);
        }

        JButton BoutonSuivantPourMethodes = new JButton("Suivant");
        BoutonSuivantPourMethodes.setBackground(new Color(100, 149, 237)); // Bleu
        BoutonSuivantPourMethodes.setForeground(Color.WHITE);
        BoutonSuivantPourMethodes.setFocusPainted(false);
        panelMethode.add(BoutonSuivantPourMethodes);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1000, 600));
        layeredPane.add(labelImageFond, Integer.valueOf(0));
        layeredPane.add(titre, Integer.valueOf(1));
        layeredPane.add(panelMethode, Integer.valueOf(1));

        FenetreMethodesDePaiement.setContentPane(layeredPane);

        BoutonSuivantPourMethodes.addActionListener(e -> {
            for (JRadioButton btn : BoutonsPourMethodes) {
                if (btn.isSelected()) {
                    FenetreMethodesDePaiement.dispose();
                    AfficherFenetreDetailsPaiement(btn.getText());
                    return;
                }
            }
            JOptionPane.showMessageDialog(FenetreMethodesDePaiement, "Veuillez choisir une méthode de paiement.");
        });

        FenetreMethodesDePaiement.setVisible(true);
    }

    private static JLabel creerLabelStylise(String texte, Color couleurFond) {
        JLabel label = new JLabel(texte);
        label.setOpaque(true);
        label.setBackground(couleurFond);
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        return label;
    }

    private static void AfficherFenetreDetailsPaiement(String Methode) {
        JFrame FenetreDetailsDePaiement = new JFrame("Détails de Paiement");
        FenetreDetailsDePaiement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FenetreDetailsDePaiement.setSize(1000, 600);
        FenetreDetailsDePaiement.setLocationRelativeTo(null);

        // Fond
        ImageIcon fond = new ImageIcon("dollars.jpg");
        JLabel imageFond = new JLabel(fond);
        imageFond.setBounds(0, 0, 1000, 600);

        // Titre
        JLabel titre = new JLabel("Entrez les détails de paiement", SwingConstants.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 24));
        titre.setForeground(Color.BLACK);
        titre.setBounds(250, 40, 500, 30);

        boolean EstCrypto = Methode.equals("Cryptomonnaie");

        JComboBox<String> ListeCrypto = new JComboBox<>(ListeDesCryptomonnaies);
        JTextField ChampNom = new JTextField();
        JTextField ChampCarte = CreerChampFormateAvecEspaces("#### #### #### ####");
        JTextField ChampDate = EstCrypto ? null : CreerChampFormate("##/##");
        JTextField ChampCVV = CreerChampFormateCVV("###");

        // Panel du formulaire
        JPanel panelFormulaire = new JPanel(new GridLayout(6, 2, 10, 10));
        panelFormulaire.setOpaque(false);
        panelFormulaire.setBounds(200, 100, 600, 300);

        // Méthode utilitaire pour styliser les labels
        java.util.function.Function<String, JLabel> labelVert = text -> {
            JLabel label = new JLabel(text);
            label.setOpaque(true);
            label.setBackground(new Color(144, 238, 144)); // vert clair
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            return label;
        };

        if (EstCrypto) {
            panelFormulaire.add(labelVert.apply("Crypto :"));
            panelFormulaire.add(ListeCrypto);
            panelFormulaire.add(labelVert.apply("Nom du Propriétaire :"));
            panelFormulaire.add(ChampNom);
            panelFormulaire.add(labelVert.apply("Numéro de Compte :"));
            panelFormulaire.add(ChampCarte);
            panelFormulaire.add(labelVert.apply("Code Crypto :"));
            panelFormulaire.add(ChampCVV);
        } else {
            panelFormulaire.add(labelVert.apply("Nom du Propriétaire :"));
            panelFormulaire.add(ChampNom);
            panelFormulaire.add(labelVert.apply("Numéro de Carte :"));
            panelFormulaire.add(ChampCarte);
            panelFormulaire.add(labelVert.apply("Code CVV :"));
            panelFormulaire.add(ChampCVV);
            panelFormulaire.add(labelVert.apply("Date de Validité :"));
            panelFormulaire.add(ChampDate);
        }

        JButton boutonValider = new JButton("Valider le Paiement");
        boutonValider.setBackground(new Color(0, 120, 215));
        boutonValider.setForeground(Color.WHITE);
        boutonValider.setFont(new Font("Arial", Font.BOLD, 14));
        boutonValider.setFocusPainted(false);

        panelFormulaire.add(new JLabel());
        panelFormulaire.add(boutonValider);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1000, 600));
        layeredPane.add(imageFond, Integer.valueOf(0));
        layeredPane.add(titre, Integer.valueOf(1));
        layeredPane.add(panelFormulaire, Integer.valueOf(1));

        FenetreDetailsDePaiement.setContentPane(layeredPane);

        boutonValider.addActionListener(e -> {
            String nom = ChampNom.getText().trim().toUpperCase();
            ChampNom.setText(nom);

            String numero = ChampCarte.getText().replaceAll("[^0-9]", "");
            String cvv = ChampCVV.getText().trim();

            if (nom.isEmpty()) {
                JOptionPane.showMessageDialog(FenetreDetailsDePaiement, "Le nom du propriétaire est requis.");
                return;
            }

            if (numero.length() != 16) {
                JOptionPane.showMessageDialog(FenetreDetailsDePaiement, EstCrypto ? "Le numéro de compte doit contenir 16 chiffres." : "Le numéro de carte doit contenir 16 chiffres.");
                return;
            }

            if (cvv.length() != 3) {
                JOptionPane.showMessageDialog(FenetreDetailsDePaiement, EstCrypto ? "Le code crypto doit contenir 3 caractères." : "Le code CVV doit contenir 3 chiffres.");
                return;
            }

            if (!EstCrypto && (ChampDate.getText().contains(" ") || !ChampDate.getText().matches("\\d{2}/\\d{2}"))) {
                JOptionPane.showMessageDialog(FenetreDetailsDePaiement, "La date de validité est invalide.");
                return;
            }

            JOptionPane.showMessageDialog(FenetreDetailsDePaiement, "Paiement effectué.");
            FenetreDetailsDePaiement.dispose();
        });

        FenetreDetailsDePaiement.pack();
        FenetreDetailsDePaiement.setVisible(true);
    }




    // Fonction pour créer le champ de saisie du CVV avec un masque de 3 chiffres
    private static JFormattedTextField CreerChampFormateCVV(String Masque) {
        try {
            MaskFormatter formatteur = new MaskFormatter(Masque);
            formatteur.setPlaceholderCharacter(' ');
            return new JFormattedTextField(formatteur);
        } catch (ParseException e) {
            return new JFormattedTextField();
        }
    }


    private static JFormattedTextField CreerChampFormateAvecEspaces(String Masque) {
        try {
            MaskFormatter formatteur = new MaskFormatter(Masque);
            formatteur.setPlaceholderCharacter(' ');
            return new JFormattedTextField(formatteur);
        } catch (ParseException e) {
            return new JFormattedTextField();
        }
    }

    private static JFormattedTextField CreerChampFormate(String Masque) {
        try {
            MaskFormatter formatteur = new MaskFormatter(Masque);
            formatteur.setPlaceholderCharacter(' ');
            return new JFormattedTextField(formatteur);
        } catch (ParseException e) {
            return new JFormattedTextField();
        }
    }
}