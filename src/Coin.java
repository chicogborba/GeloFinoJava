public class Coin extends ElementoBasico {

    public Coin(String id, int linInicial, int colInicial, Tabuleiro tabuleiro) {
        super(id, "money.jpeg", linInicial, colInicial, tabuleiro);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void acao(ElementoBasico outro) {
        getTabuleiro().insereElemento(new Water("water", getLin(), getCol(), getTabuleiro()));
        // Adiciona 10 ponto ao score
        App.addScore(10);
    }

}
