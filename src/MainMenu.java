import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainMenu extends JPanel {
  private JButton startButton = new JButton();

  public MainMenu() {
    super();
    initializeUI();
  }

  public JButton getStartButton() {
    return this.startButton;
  }

  private void initializeUI() {
    this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

    // Adicionando imagem de fundo do menu principal
    try {
      Image logo = ImageIO.read(App.class.getResource("imagens/main_menu.png"));
      JPanel logoPanel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
          super.paintComponent(g);
          // Dividindo a largura e altura por 2 para centralizar a imagem
          int x = (getWidth() - logo.getWidth(null)) / 2;
          int y = (getHeight() - logo.getHeight(null)) / 2;
          g.drawImage(logo, x, y, null);
        }
      };
      logoPanel.setPreferredSize(new Dimension(730, 667));
      this.add(logoPanel);
      logoPanel.setBackground(new Color(217, 241, 255));
      logoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0)); // Alinhamento centralizado
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Adiciona o botão de iniciar
    JPanel buttonPanel = new JPanel();
    buttonPanel.setBackground(new Color(217, 241, 255));
    try {
      Image startButtonImg = ImageIO.read(App.class.getResource("imagens/start_button.png"));
      startButton.setIcon(new javax.swing.ImageIcon(startButtonImg));
      // deixando o botão transparente
      startButton.setOpaque(false);
      startButton.setContentAreaFilled(false);
      startButton.setBorderPainted(false);
      startButton.setFocusPainted(false);

    } catch (IOException e) {
      e.printStackTrace();
    }
    buttonPanel.add(startButton);
    this.add(buttonPanel);
  }

}
