import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginApp extends JFrame {
    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_HEIGHT = 260;
    private static final int DASHBOARD_WIDTH = 500;
    private static final int DASHBOARD_HEIGHT = 300;
    private static final String DEFAULT_USERNAME = "admin";
    private static final String DEFAULT_PASSWORD = "1234";
    
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginApp() {
        initializeLoginWindow();
    }

    private void initializeLoginWindow() {
        setTitle("Login System");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = createLoginPanel();
        add(panel);
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 240));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title Label
        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        // Username Label & Field
        JLabel userLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(userLabel, gbc);

        usernameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(usernameField, gbc);

        // Password Label & Field
        JLabel passLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(passLabel, gbc);

        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(passwordField, gbc);

        // Login Button
        JButton loginButton = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(loginButton, gbc);

        loginButton.addActionListener(e -> handleLogin());

        return panel;
    }

    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (isValidCredentials(username, password)) {
            JOptionPane.showMessageDialog(this, "Login berhasil!", "Sukses", 
                    JOptionPane.INFORMATION_MESSAGE);
            openDashboard(username);
        } else {
            JOptionPane.showMessageDialog(this, "Username atau password salah!", 
                    "Login Gagal", JOptionPane.ERROR_MESSAGE);
            clearLoginFields();
        }
    }

    private boolean isValidCredentials(String username, String password) {
        return username.equals(DEFAULT_USERNAME) && password.equals(DEFAULT_PASSWORD);
    }

    private void clearLoginFields() {
        usernameField.setText("");
        passwordField.setText("");
    }

    private void openDashboard(String username) {
        JFrame dashboardFrame = new JFrame("Dashboard");
        dashboardFrame.setSize(DASHBOARD_WIDTH, DASHBOARD_HEIGHT);
        dashboardFrame.setLocationRelativeTo(this);
        dashboardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel dashboardPanel = createDashboardPanel(username, dashboardFrame);
        dashboardFrame.add(dashboardPanel);
        dashboardFrame.setVisible(true);

        this.dispose();
    }

    private JPanel createDashboardPanel(String username, JFrame dashboardFrame) {
        JPanel dashboardPanel = new JPanel(new BorderLayout(10, 10));
        dashboardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        dashboardPanel.setBackground(new Color(250, 250, 250));

        // Welcome Label
        JLabel welcomeLabel = new JLabel("Selamat datang, " + username + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dashboardPanel.add(welcomeLabel, BorderLayout.CENTER);

        // Logout Button
        JButton logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(new Dimension(100, 40));
        logoutButton.addActionListener(e -> handleLogout(dashboardFrame));
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(logoutButton);
        dashboardPanel.add(buttonPanel, BorderLayout.SOUTH);

        return dashboardPanel;
    }

    private void handleLogout(JFrame dashboardFrame) {
        dashboardFrame.dispose();
        clearLoginFields();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginApp().setVisible(true));
    }
}
