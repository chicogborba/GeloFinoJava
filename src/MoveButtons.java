import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class MoveButtons extends JPanel {

  public MoveButtons() {
    super();
  }

  public void createButtons(ActionListener actionListener) {

    this.setOpaque(false);
    this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

    // Criação de multiplos paineis para organizar os botões em formato de cruz
    JPanel botoesDirecaoVerticalFirst = new JPanel();
    botoesDirecaoVerticalFirst.setOpaque(false);
    JPanel botoesDirecaoVerticalLast = new JPanel();
    botoesDirecaoVerticalLast.setOpaque(false);
    botoesDirecaoVerticalFirst.setLayout(new BoxLayout(botoesDirecaoVerticalFirst, BoxLayout.Y_AXIS));
    botoesDirecaoVerticalLast.setLayout(new BoxLayout(botoesDirecaoVerticalLast, BoxLayout.Y_AXIS));
    JPanel botoesDirecaoHorizontal = new JPanel(new FlowLayout());
    botoesDirecaoHorizontal.setOpaque(false);

    // Tirando a borda dos botões de moimentação
    botoesDirecaoVerticalFirst.setBorder(null);
    botoesDirecaoVerticalLast.setBorder(null);
    botoesDirecaoHorizontal.setBorder(null);

    // Botões de direção esquerda e direita
    JButton butDir = new JButton("→");

    butDir.addActionListener(actionListener);
    JButton butEsq = new JButton("←");
    butEsq.addActionListener(actionListener);

    // Botão para cima e para baixo com alinhamento centralizado
    JButton butCima = new JButton("↑");
    butCima.setAlignmentX(JButton.CENTER_ALIGNMENT);
    butCima.addActionListener(actionListener);
    JButton butBaixo = new JButton("↓");
    butBaixo.setAlignmentX(JButton.CENTER_ALIGNMENT);
    butBaixo.addActionListener(actionListener);

    // Adicionando os botões na sua respectiva ordem no painel
    botoesDirecaoVerticalFirst.add(butCima);
    botoesDirecaoHorizontal.add(butEsq);
    botoesDirecaoHorizontal.add(butBaixo);
    botoesDirecaoHorizontal.add(butDir);

    // Deixando os botões transparentes sem bg
    butCima.setContentAreaFilled(false);
    butCima.setBorderPainted(false);
    butCima.setFocusPainted(false);
    butCima.setMargin(new Insets(0, 10, -20, 0));
    butCima.setForeground(new Color(0, 0, 0, 0));

    butBaixo.setContentAreaFilled(false);
    butBaixo.setBorderPainted(false);
    butBaixo.setFocusPainted(false);
    butBaixo.setMargin(new Insets(0, 0, -15, 0));
    butBaixo.setForeground(new Color(0, 0, 0, 0));

    butEsq.setContentAreaFilled(false);
    butEsq.setBorderPainted(false);
    butEsq.setFocusPainted(false);
    butEsq.setMargin(new Insets(0, 10, 20, 80));
    butEsq.setForeground(new Color(0, 0, 0, 0));

    butDir.setContentAreaFilled(false);
    butDir.setBorderPainted(false);
    butDir.setFocusPainted(false);
    butDir.setMargin(new Insets(0, 80, 20, 0));
    butDir.setForeground(new Color(0, 0, 0, 0));

    this.add(botoesDirecaoVerticalFirst);
    this.add(botoesDirecaoHorizontal);
    this.add(botoesDirecaoVerticalLast);
  }
}