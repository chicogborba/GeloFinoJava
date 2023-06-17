
public class Water extends ElementoBasico {

  public Water(String id, int linInicial, int colInicial, Tabuleiro tabuleiro) {
    super(id, "water.jpeg", linInicial, colInicial, tabuleiro);
  }

  @Override
  public void acao(ElementoBasico outro) {

  }
}
