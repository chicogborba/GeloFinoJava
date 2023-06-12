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

    this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

    // Criação de multiplos paineis para organizar os botões em formato de cruz
    JPanel botoesDirecaoVerticalFirst = new JPanel();
    JPanel botoesDirecaoVerticalLast = new JPanel();
    botoesDirecaoVerticalFirst.setLayout(new BoxLayout(botoesDirecaoVerticalFirst, BoxLayout.Y_AXIS));
    botoesDirecaoVerticalLast.setLayout(new BoxLayout(botoesDirecaoVerticalLast, BoxLayout.Y_AXIS));
    JPanel botoesDirecaoHorizontal = new JPanel(new FlowLayout());

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
    botoesDirecaoHorizontal.add(butDir);
    botoesDirecaoVerticalLast.add(butBaixo);

    // Definido a cor do fundo do painel onde os botões estão
    botoesDirecaoVerticalFirst.setBackground(new Color(217, 241, 255));
    botoesDirecaoVerticalLast.setBackground(new Color(217, 241, 255));
    botoesDirecaoHorizontal.setBackground(new Color(217, 241, 255));

    this.add(botoesDirecaoVerticalFirst);
    this.add(botoesDirecaoHorizontal);
    this.add(botoesDirecaoVerticalLast);
  }
}