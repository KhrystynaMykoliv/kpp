package lab4;

import lab4.controllers.MainController;
import lab4.seeds.urls;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Application extends JFrame {
    private MainController threadsController;
    private JPanel appPanel;
    private JButton startButton;
    private JTextArea urlsTextArea;
    private JPanel resultPanel;
    private JLabel resultLabel;

    public Application() {
        setTitle("Web Scrapping");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(appPanel);
        setSize(800, 600);
        setLocationRelativeTo(null);

        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));

        urlsTextArea.setText(String.join("\n", urls.urls));

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String urlsText = urlsTextArea.getText();

                List<String> urlsList = List.of(urlsText.split("\\n"));

                resultPanel.removeAll();
                resultLabel.setText("Status: Running...");

                threadsController = new MainController(urlsList, resultPanel, resultLabel);
                threadsController.startScraping();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Application app = new Application();
            app.setVisible(true);
        });
    }
}