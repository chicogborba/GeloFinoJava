import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class App extends JFrame implements ActionListener {
    private Tabuleiro tabuleiro;
    private MainMenu mainMenu;
    private Personagem personagem;
    private int level = 1;
    private JLabel levelLabel = new JLabel("Level: " + level);
    private JLabel scoreLabel = new JLabel("Score: 0");
    private static int score = 0;
    private int maxLevel = 10;

    public App() {
        super();
        tabuleiro = new Tabuleiro();

        // ---------------
        // LAYOUT DA TELA
        // ---------------

        // Header para mostrar o level e o score
        JPanel header = new JPanel();
        header.setBackground(new Color(217, 241, 255));
        header.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS)); // Usando BoxLayout com orientação horizontal
        header.setPreferredSize(new Dimension(855, 50));

        // Adicionando espaçamento interno nas laterais
        header.setBorder(new EmptyBorder(0, 15, 0, 15));

        levelLabel.setFont(new Font("Arial", Font.BOLD, 20));
        levelLabel.setForeground(new Color(28, 106, 169));
        header.add(levelLabel);

        header.add(Box.createHorizontalGlue());

        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setForeground(new Color(28, 106, 169));

        header.add(scoreLabel);

        // Cria os botões de movimento e adiciona o action listener
        MoveButtons moveButtons = new MoveButtons();
        moveButtons.createButtons(this);

        JPanel gamebackground = new JPanel();
        try {
            Image logo = ImageIO.read(App.class.getResource("imagens/arcade.jpeg"));
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
            logoPanel.setOpaque(false);
            logoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0)); // Alinhamento centralizado

            gamebackground.setLayout(new BorderLayout());
            gamebackground.add(logoPanel, BorderLayout.CENTER);

            // Adicione outros componentes ao painel gamebackground conforme necessário

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Cria o painel do jogo onde ficara o tabuleiro e os botões de movimento
        JPanel painelJogo = new JPanel();
        painelJogo.setPreferredSize(painelJogo.getPreferredSize());
        painelJogo.setOpaque(false);
        painelJogo.setLayout(new BoxLayout(painelJogo, BoxLayout.PAGE_AXIS));
        painelJogo.add(header);
        painelJogo.add(tabuleiro);
        painelJogo.add(moveButtons);

        // Criando camadas para que o tabuleiro fique sobrepondo do background
        JLayeredPane layers = new JLayeredPane();
        layers.setPreferredSize(new Dimension(1152, 720));

        gamebackground.setBounds(0, 0, 1152, 720);
        painelJogo.setBounds(215, 32, 700, 700);

        layers.add(gamebackground, 1);
        layers.add(painelJogo, 0);

        // Carrega o jogo pela primeira vez com o atributo level
        loadGame(level);

        // Cria o menu principal e o deixa visível
        // enquanto o jogo fica invisível até o botão de iniciar ser clicado
        this.setLayout(new BorderLayout());
        mainMenu = new MainMenu();
        layers.setVisible(false);
        mainMenu.setVisible(true);
        this.add(layers, BorderLayout.CENTER);
        this.add(mainMenu, BorderLayout.NORTH);
        mainMenu.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Quando o botão de iniciar for clicado, o menu some e o jogo aparece
                mainMenu.setVisible(false);
                layers.setVisible(true);
                tabuleiro.atualizaVisualizacao();
                // Deixa o app com o menor tamanho possível para evitar as
                // bordas brancas dos botões
                App.this.pack();
                // print app width and height
                System.out.println(App.this.getWidth());
                System.out.println(App.this.getHeight());
            }
        });

        // Colocando um tamanho fixo inicial para a tela para que o menu fique do
        // mesmo tamanho que o tabuleiro
        this.setSize(1152, 748);
        this.setResizable(false);

        // Exibe a tela
        this.setTitle("Gelo fino");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(217, 241, 255));
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
        // Verifica qual botão foi clicado
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
                this.levelLabel.setText("Level: " + level); // Atualiza o texto do nível
            } else {
                JOptionPane.showMessageDialog(null, "Parabéns, você venceu o jogo!");
                System.exit(0);
            }
        }
        // Verifica se é possivel mover o personagem
        // Se não for, reinicia o level
        if (!personagem.isMovable()) {
            JOptionPane.showMessageDialog(null, "Perdeu 😭! Reiniciando o jogo... ");
            level = 1;
            score = 0;
            loadGame(level);
            this.levelLabel.setText("Level: " + level); // Atualiza o texto do nível

        }
        tabuleiro.atualizaVisualizacao();
        // Atualiza o score
        scoreLabel.setText("Score: " + score);
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

    public static void addScore(int scoreToAdd) {
        score += scoreToAdd;
    }

}