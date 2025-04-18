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
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Image de fond
        ImageIcon backgroundImage = new ImageIcon("i.png"); // Remplace par ton image
        Image image = backgroundImage.getImage();
        Image scaledImage = image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(scaledImage));
        background.setLayout(new BorderLayout());
        setContentPane(background);

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 300, 40, 300));
        background.add(panel, BorderLayout.CENTER);

        // Titre
        JLabel titre = new JLabel("Formulaire d'Inscription", SwingConstants.CENTER);
        titre.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titre.setForeground(new Color(30, 30, 30));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titre);
        panel.add(Box.createVerticalStrut(30));

        // Champs
        usernameField = new JTextField();
        styliserChamp(panel, "Nom d'utilisateur :", usernameField);

        emailField = new JTextField();
        styliserChamp(panel, "Email :", emailField);

        passwordField = new JPasswordField();
        styliserChamp(panel, "Mot de passe :", passwordField);

        // Bouton afficher mot de passe
        showPasswordButton = new JButton("Rendre visible le mot de passe");
        showPasswordButton.setBackground(new Color(70, 130, 180));
        showPasswordButton.setForeground(Color.WHITE);
        showPasswordButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        showPasswordButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        showPasswordButton.setFocusable(false);
        panel.add(showPasswordButton);

        showPasswordButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                passwordField.setEchoChar((char) 0);
            }

            public void mouseReleased(MouseEvent e) {
                passwordField.setEchoChar('*');
            }
        });

        panel.add(Box.createVerticalStrut(15));

        // Bouton s'inscrire
        registerButton = new JButton("S'inscrire");
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerButton.setFocusable(false);
        registerButton.setBackground(new Color(34, 139, 34));
        registerButton.setForeground(Color.WHITE);
        registerButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panel.add(registerButton);
        registerButton.addActionListener(e -> validateForm());

        panel.add(Box.createVerticalStrut(10));

        // Message d'erreur ou de succès
        errorLabel = new JLabel("", SwingConstants.CENTER);
        errorLabel.setForeground(Color.RED);
        errorLabel.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Gras et plus grand
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(errorLabel);

        // Nouvelle inscription
        nextButton = createButton("Nouvelle inscription");
        nextButton.setVisible(false);
        panel.add(Box.createVerticalStrut(10));
        panel.add(nextButton);
        nextButton.addActionListener(e -> resetForm());

        setVisible(true);
    }

    private void styliserChamp(JPanel panel, String labelTexte, JTextField field) {
        // Conteneur pour le label avec fond vert clair
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        labelPanel.setBackground(new Color(200, 255, 200)); // Vert clair
        labelPanel.setMaximumSize(new Dimension(300, 35));
        labelPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(150, 200, 150)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        labelPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label = new JLabel(labelTexte);
        label.setForeground(new Color(30, 30, 30));
        label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        labelPanel.add(label);

        panel.add(labelPanel);
        panel.add(Box.createVerticalStrut(5));

        // Champ de saisie en dessous, hors du rectangle vert
        field.setMaximumSize(new Dimension(300, 35));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setHorizontalAlignment(JTextField.CENTER);
        field.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
        field.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(field);
        panel.add(Box.createVerticalStrut(15));
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(60, 120, 170));
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(70, 130, 180));
            }
        });

        return button;
    }

    private void validateForm() {
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        if (!username.matches("^[^\\s]{2,}$")) {
            errorLabel.setForeground(Color.RED);
            errorLabel.setText("Nom d'utilisateur invalide (2+ caractères sans espace)");
            return;
        }

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (!Pattern.matches(emailRegex, email)) {
            errorLabel.setForeground(Color.RED);
            errorLabel.setText("Email invalide");
            return;
        }

        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
        if (!Pattern.compile(passwordRegex).matcher(password).matches()) {
            errorLabel.setForeground(Color.RED);
            errorLabel.setText("Mot de passe invalide (8+ caractères, 1 maj, 1 min, 1 chiffre)");
            return;
        }

        errorLabel.setForeground(new Color(0, 128, 0));
        errorLabel.setText("Inscription réussie !");
        nextButton.setVisible(true);

        usernameField.setEnabled(false);
        emailField.setEnabled(false);
        passwordField.setEnabled(false);
        registerButton.setEnabled(false);
        showPasswordButton.setEnabled(false);
    }

    private void resetForm() {
        usernameField.setText("");
        emailField.setText("");
        passwordField.setText("");
        errorLabel.setText("");
        errorLabel.setForeground(Color.RED);
        nextButton.setVisible(false);

        usernameField.setEnabled(true);
        emailField.setEnabled(true);
        passwordField.setEnabled(true);
        registerButton.setEnabled(true);
        showPasswordButton.setEnabled(true);
    }
}
