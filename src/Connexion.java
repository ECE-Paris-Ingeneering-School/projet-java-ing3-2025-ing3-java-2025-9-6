import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Connexion {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginWindow());
    }
}

class LoginWindow extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel errorLabel;
    private JButton showPasswordButton, loginButton, resetButton;

    public LoginWindow() {
        setTitle("Page de Connexion");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        showPasswordButton = new JButton("👁");
        add(showPasswordButton);

        loginButton = new JButton("Se connecter");
        add(loginButton);

        errorLabel = new JLabel("", SwingConstants.CENTER);
        errorLabel.setForeground(Color.RED);
        add(errorLabel);

        resetButton = new JButton("Réinitialiser");
        add(resetButton);

        // Action pour le bouton de connexion
        loginButton.addActionListener(e -> validateLogin());

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

        // Action pour réinitialiser le formulaire
        resetButton.addActionListener(e -> resetForm());

        setVisible(true);
    }

    private void validateLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Validation des informations de connexion
        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Veuillez remplir tous les champs.");
            return;
        }

        // Exemple simple de validation (à remplacer par une validation réelle)
        if (username.equals("admin") && password.equals("admin123")) {
            errorLabel.setForeground(Color.GREEN);
            errorLabel.setText("Connexion réussie !");
        } else {
            errorLabel.setText("Nom d'utilisateur ou mot de passe incorrect.");
        }
    }

    private void resetForm() {
        // Réinitialisation des champs
        usernameField.setText("");
        passwordField.setText("");
        errorLabel.setText("");
        errorLabel.setForeground(Color.RED);
    }
}
