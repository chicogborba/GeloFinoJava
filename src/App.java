import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class App extends JFrame implements ActionListener {
    private Tabuleiro tabuleiro;
    private MainMenu mainMenu;
    private Personagem personagem;
    private int level = 1;
    private int score = 0;
    private int maxLevel = 3;

    public App() {
        super();
        tabuleiro = new Tabuleiro();

        // ---------------
        // LAYOUT DA TELA
        // ---------------

        // Cria os botões de movimento e adiciona o action listener
        MoveButtons moveButtons = new MoveButtons();
        moveButtons.createButtons(this);

        // Cria o painel do jogo onde ficara o tabuleiro e os botões de movimento
        JPanel painelJogo = new JPanel();
        painelJogo.setBackground(new Color(217, 241, 255));
        painelJogo.setLayout(new BoxLayout(painelJogo, BoxLayout.PAGE_AXIS));
        painelJogo.add(tabuleiro);
        painelJogo.add(moveButtons);

        // Carrega o jogo pela primeira vez com o atributo level
        loadGame(level);

        // Cria o menu principal e o deixa visível
        // enquanto o jogo fica invisível até o botão de iniciar ser clicado
        this.setLayout(new BorderLayout());
        mainMenu = new MainMenu(painelJogo);
        painelJogo.setVisible(false);
        mainMenu.setVisible(true);
        this.add(painelJogo, BorderLayout.CENTER);
        this.add(mainMenu, BorderLayout.NORTH);
        mainMenu.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Quando o botão de iniciar for clicado, o menu some e o jogo aparece
                mainMenu.setVisible(false);
                painelJogo.setVisible(true);
                tabuleiro.atualizaVisualizacao();
                // Deixa o app com o menor tamanho possível para evitar as
                // bordas brancas dos botões
                App.this.pack();
            }
        });

        // Colocando um tamanho fixo inicial para a tela para que o menu fique do
        // mesmo tamanho que o tabuleiro
        this.setSize(950, 725);
        this.setResizable(false);

        // Exibe a tela
        this.setTitle("Gelo fino");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        // Utiliza a função playSong para tocar a música de fundo
        // Em loop infinito
        playSong("src/sounds/main_music.wav");
        tabuleiro.atualizaVisualizacao();
    }

    // Limpa o tabuleiro e carrega o level passado como parâmetro
    public void loadGame(int level) {
        tabuleiro.reset();
        tabuleiro.loadLevel(level);
        personagem = tabuleiro.getPrincipal();
        personagem.setAnterior(personagem.getAnterior());

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new App();
            }
        });
    }

    // ------------------------------
    // TRATAMENTO DE EVENTOS (INPUT)
    // ------------------------------
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

        // Verifica se o level foi completado
        // Se sim, carrega o próximo level
        // Se for o último level, exibe uma mensagem de vitória
        if (tabuleiro.isLevelComplete()) {
            if (level < maxLevel) {
                level++;
                loadGame(level);
            } else {
                JOptionPane.showMessageDialog(null, "Parabéns, você venceu o jogo!");
                System.exit(0);
            }
        }
        tabuleiro.atualizaVisualizacao();
    }

    // ----------------
    // MUSICA DE FUNDO
    // ----------------
    // Função para tocar musica de fundo
    private void playSong(String filePath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            Clip clip = AudioSystem.getClip();

            // Open the audioInputStream and start playing the clip
            clip.open(audioInputStream);
            // Set the loop count to infinite
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            clip.start();

        } catch (Exception e) {
            // Handle any exceptions
            e.printStackTrace();
        }
    }
}