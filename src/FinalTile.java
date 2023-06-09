public class FinalTile extends ElementoBasico {

  public FinalTile(String id, String iconPath, int linInicial, int colInicial, Tabuleiro tabuleiro) {
    super(id, iconPath, linInicial, colInicial, tabuleiro);
  }

  @Override
  public void acao(ElementoBasico outro) {
    getTabuleiro().completeLevel();
  }

}
