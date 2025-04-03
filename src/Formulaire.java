import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Pattern;

public class Formulaire {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistrationWindow());
    }
}

class RegistrationWindow extends JFrame {
    private JTextField usernameField, emailField;
    private JPasswordField passwordField;
    private JLabel errorLabel;
    private JButton showPasswordButton, registerButton, nextButton;

    public RegistrationWindow() {
        setTitle("Formulaire d'Inscription");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 2)); // Augmenter le nombre de lignes pour le bouton suivant

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        showPasswordButton = new JButton("👁");
        add(showPasswordButton);

        registerButton = new JButton("S'inscrire");
        add(registerButton);

        errorLabel = new JLabel("", SwingConstants.CENTER);
        errorLabel.setForeground(Color.RED);
        add(errorLabel);

        nextButton = new JButton("Nouvelle inscription ");
        nextButton.setVisible(false); // Cacher le bouton au début
        add(nextButton);

        // Action pour le bouton d'inscription
        registerButton.addActionListener(e -> validateForm());

        // Action pour afficher/masquer le mot de passe
        showPasswordButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                passwordField.setEchoChar((char) 0); // Afficher le mot de passe
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                passwordField.setEchoChar('*'); // Masquer le mot de passe
            }
        });

        // Action pour le bouton "Nouvelle inscription"
        nextButton.addActionListener(e -> resetForm());

        setVisible(true);
    }

    private void validateForm() {
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        // Validation du username
        if (!username.matches("^[^\\s]{2,}$")) {
            errorLabel.setText("Username invalide (min. 2 caractères, sans espace)");
            return;
        }

        // Validation de l'email
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (!Pattern.matches(emailRegex, email)) {
            errorLabel.setText("Email invalide");
            return;
        }

        // Validation du mot de passe
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
        if (!Pattern.compile(passwordRegex).matcher(password).matches()) {
            errorLabel.setText("Mot de passe invalide (8+ caractères, 1 maj, 1 min, 1 chiffre)");
            return;
        }

        // Si tout est valide
        errorLabel.setForeground(Color.GREEN);
        errorLabel.setText("Inscription réussie !");
        nextButton.setVisible(true); // Afficher le bouton "Nouvelle inscription"

        // Désactiver tous les champs et le bouton d'inscription
        usernameField.setEnabled(false);
        emailField.setEnabled(false);
        passwordField.setEnabled(false);
        registerButton.setEnabled(false);
        showPasswordButton.setEnabled(false);
    }

    private void resetForm() {
        // Réinitialisation des champs
        usernameField.setText("");
        emailField.setText("");
        passwordField.setText("");
        errorLabel.setText("");
        errorLabel.setForeground(Color.RED);
        nextButton.setVisible(false); // Cacher le bouton "Nouvelle inscription"

        // Réactiver tous les champs et le bouton d'inscription
        usernameField.setEnabled(true);
        emailField.setEnabled(true);
        passwordField.setEnabled(true);
        registerButton.setEnabled(true);
        showPasswordButton.setEnabled(true);
    }
}
