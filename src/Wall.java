
public class Wall extends ElementoBasico {

    public Wall(String id, int linInicial, int colInicial, Tabuleiro tabuleiro) {
        super(id, "wall.jpeg", linInicial, colInicial, tabuleiro);
    }

    @Override
    public void acao(ElementoBasico outro) {

    }
}
