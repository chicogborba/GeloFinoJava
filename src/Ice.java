public class Ice extends ElementoBasico {

  public Ice(String id, int linInicial, int colInicial, Tabuleiro tabuleiro) {
    super(id, "ice_tile.png", linInicial, colInicial, tabuleiro);
  }

  @Override
  public void acao(ElementoBasico outro) {
    // Troca gelo por elemento water
    getTabuleiro().insereElemento(new Water("water", getLin(), getCol(), getTabuleiro()));
    // Adiciona 1 ponto ao score
    App.addScore(1);
  }

}
