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
        FenetreAdresseDeLivraison.setSize(400, 300);
        FenetreAdresseDeLivraison.setLayout(new GridLayout(6, 2, 5, 5));

        JComboBox<String> ListePaysPourSelection = new JComboBox<>(ListeDesPays);
        JTextField ChampPourVille = new JTextField();
        JTextField ChampPourCodePostal = new JTextField();
        JTextField ChampPourAdresse = new JTextField();
        JTextField ChampPourDeuxiemeAdresse = new JTextField();
        JButton BoutonSuivantPourAdresse = new JButton("Suivant");

        FenetreAdresseDeLivraison.add(new JLabel("Pays :"));
        FenetreAdresseDeLivraison.add(ListePaysPourSelection);
        FenetreAdresseDeLivraison.add(new JLabel("Ville :"));
        FenetreAdresseDeLivraison.add(ChampPourVille);
        FenetreAdresseDeLivraison.add(new JLabel("Code Postal :"));
        FenetreAdresseDeLivraison.add(ChampPourCodePostal);
        FenetreAdresseDeLivraison.add(new JLabel("Adresse :"));
        FenetreAdresseDeLivraison.add(ChampPourAdresse);
        FenetreAdresseDeLivraison.add(new JLabel("Compléments d'adresse (Optionnel) :"));
        FenetreAdresseDeLivraison.add(ChampPourDeuxiemeAdresse);
        FenetreAdresseDeLivraison.add(new JLabel());
        FenetreAdresseDeLivraison.add(BoutonSuivantPourAdresse);

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

        FenetreAdresseDeLivraison.setVisible(true);
    }

    private static void AfficherFenetreMethodesDePaiement() {
        JFrame FenetreMethodesDePaiement = new JFrame("Méthode de Paiement");
        FenetreMethodesDePaiement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FenetreMethodesDePaiement.setSize(400, 300);
        FenetreMethodesDePaiement.setLayout(new GridLayout(ListeDesMethodesDePaiement.length + 1, 1));

        ButtonGroup GroupePourMethodes = new ButtonGroup();
        JRadioButton[] BoutonsPourMethodes = new JRadioButton[ListeDesMethodesDePaiement.length];

        for (int i = 0; i < ListeDesMethodesDePaiement.length; i++) {
            BoutonsPourMethodes[i] = new JRadioButton(ListeDesMethodesDePaiement[i]);
            GroupePourMethodes.add(BoutonsPourMethodes[i]);
            FenetreMethodesDePaiement.add(BoutonsPourMethodes[i]);
        }

        JButton BoutonSuivantPourMethodes = new JButton("Suivant");
        FenetreMethodesDePaiement.add(BoutonSuivantPourMethodes);

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

    private static void AfficherFenetreDetailsPaiement(String Methode) {
        JFrame FenetreDetailsDePaiement = new JFrame("Détails de Paiement");
        FenetreDetailsDePaiement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FenetreDetailsDePaiement.setSize(400, 300);
        FenetreDetailsDePaiement.setLayout(new GridLayout(6, 2, 5, 5));

        boolean EstCrypto = Methode.equals("Cryptomonnaie");

        JComboBox<String> ListeCryptomonnaiesPourSelection = new JComboBox<>(ListeDesCryptomonnaies);
        JTextField ChampPourNumeroDeCarte = CreerChampFormateAvecEspaces("#### #### #### ####");
        JTextField ChampPourNomDuProprietaire = new JTextField();
        JTextField ChampPourDateDeValidite = EstCrypto ? null : CreerChampFormate("##/##");
        JFormattedTextField ChampPourCodeCVV = CreerChampFormateCVV("###");

        if (EstCrypto) {
            FenetreDetailsDePaiement.add(new JLabel("Crypto :"));
            FenetreDetailsDePaiement.add(ListeCryptomonnaiesPourSelection);

            FenetreDetailsDePaiement.add(new JLabel("Nom du Propriétaire :"));
            FenetreDetailsDePaiement.add(ChampPourNomDuProprietaire);

            FenetreDetailsDePaiement.add(new JLabel("Numéro de Compte :"));
            FenetreDetailsDePaiement.add(ChampPourNumeroDeCarte);

            FenetreDetailsDePaiement.add(new JLabel("Code Crypto :"));
            FenetreDetailsDePaiement.add(ChampPourCodeCVV);
        } else {
            FenetreDetailsDePaiement.add(new JLabel("Nom du Propriétaire :"));
            FenetreDetailsDePaiement.add(ChampPourNomDuProprietaire);

            FenetreDetailsDePaiement.add(new JLabel("Numéro de Carte :"));
            FenetreDetailsDePaiement.add(ChampPourNumeroDeCarte);

            FenetreDetailsDePaiement.add(new JLabel("Code CVV :"));
            FenetreDetailsDePaiement.add(ChampPourCodeCVV);

            FenetreDetailsDePaiement.add(new JLabel("Date de Validité :"));
            FenetreDetailsDePaiement.add(ChampPourDateDeValidite);
        }


        JButton BoutonValiderPaiement = new JButton("Valider le Paiement");
        FenetreDetailsDePaiement.add(new JLabel());
        FenetreDetailsDePaiement.add(BoutonValiderPaiement);

        BoutonValiderPaiement.addActionListener(e -> {
            String NomDuProprietaire = ChampPourNomDuProprietaire.getText().trim().toUpperCase();
            ChampPourNomDuProprietaire.setText(NomDuProprietaire);

            String NumeroDeCarteBrut = ChampPourNumeroDeCarte.getText().replaceAll("[^0-9]", "");
            String CodeCryptoBrut = ChampPourCodeCVV.getText().trim();

            if (NomDuProprietaire.isEmpty()) {
                JOptionPane.showMessageDialog(FenetreDetailsDePaiement, "Le nom du propriétaire est requis.");
                return;
            }

            if (NumeroDeCarteBrut.length() != 16) {
                JOptionPane.showMessageDialog(FenetreDetailsDePaiement, EstCrypto ? "Le numéro de compte doit contenir 16 chiffres." : "Le numéro de carte doit contenir 16 chiffres.");
                return;
            }

            if (CodeCryptoBrut.length() != 3) {
                JOptionPane.showMessageDialog(FenetreDetailsDePaiement, EstCrypto ? "Le code crypto doit contenir exactement 3 caractères." : "Le code CVV doit contenir exactement 3 chiffres.");
                return;
            }

            if (!EstCrypto && (ChampPourDateDeValidite.getText().contains(" ") || !ChampPourDateDeValidite.getText().matches("\\d{2}/\\d{2}"))) {
                JOptionPane.showMessageDialog(FenetreDetailsDePaiement, "La date de validité est incomplète ou invalide.");
                return;
            }

            JOptionPane.showMessageDialog(FenetreDetailsDePaiement, "Paiement effectué.");
            FenetreDetailsDePaiement.dispose();
        });

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