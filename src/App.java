import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame implements ActionListener {
    private Tabuleiro tabuleiro;
    private Personagem personagem;

    public App() {
        super();
        tabuleiro = new Tabuleiro();

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
        butDir.addActionListener(this);
        JButton butEsq = new JButton("←");
        butEsq.addActionListener(this);

        // Botão para cima e para baixo com alinhamento centralizado
        JButton butCima = new JButton("↑");
        butCima.setAlignmentX(JButton.CENTER_ALIGNMENT);
        butCima.addActionListener(this);
        JButton butBaixo = new JButton("↓");
        butBaixo.setAlignmentX(JButton.CENTER_ALIGNMENT);
        butBaixo.addActionListener(this);

        // Adicionando os botões na sua respectiva ordem no painel
        botoesDirecaoVerticalFirst.add(butCima);
        botoesDirecaoHorizontal.add(butEsq);
        botoesDirecaoHorizontal.add(butDir);
        botoesDirecaoVerticalLast.add(butBaixo);

        // Definido a cor do fundo do painel onde os botões estão
        botoesDirecaoVerticalFirst.setBackground(new Color(118, 187, 254));
        botoesDirecaoVerticalLast.setBackground(new Color(118, 187, 254));
        botoesDirecaoHorizontal.setBackground(new Color(118, 187, 254));

        JPanel painelGeral = new JPanel();
        // Definindo a cor do fundo do painel geral
        painelGeral.setBackground(new Color(118, 187, 254));

        painelGeral.setLayout(new BoxLayout(painelGeral, BoxLayout.PAGE_AXIS));
        painelGeral.add(tabuleiro);
        painelGeral.add(botoesDirecaoVerticalFirst);
        painelGeral.add(botoesDirecaoHorizontal);
        painelGeral.add(botoesDirecaoVerticalLast);

        // Insere os personagens no tabuleiro
        tabuleiro.loadLevel(2);
        personagem = tabuleiro.getPrincipal();
        personagem.setAnterior(personagem.getAnterior());

        // Exibe a janela
        this.add(painelGeral);

        // Coloca a tela com o menor tamanho possivel se baseando no tamanho fixo dos
        // componentes
        this.pack();
        this.setResizable(false);

        this.setTitle("Gelo fino");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        tabuleiro.atualizaVisualizacao();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new App();
            }
        });
    }

    // Movimentação do personagem usando os botões de setas
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("→")) {
            personagem.moveDireita();
        } else if (cmd.equals("←")) {
            personagem.moveEsquerda();
        } else if (cmd.equals("↑")) {
            personagem.moveCima();
        } else if (cmd.equals("↓")) {
            personagem.moveBaixo();
        }
        tabuleiro.atualizaVisualizacao();
    }
}