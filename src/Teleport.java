import java.util.stream.Collectors;

public class Teleport extends ElementoBasico {

  public Teleport(String id, int linInicial, int colInicial, Tabuleiro tabuleiro) {
    super(id, "teleport_tile.png", linInicial, colInicial, tabuleiro);
  }

  @Override
  public void acao(ElementoBasico outro) {
    // filtra o tabuleiro para encontrar um elemento do tipo teleport diferente do
    // atual
    java.util.List<ElementoBasico> teleports = getTabuleiro().getListaElementos().stream()
        .filter(e -> e instanceof Teleport && !e.equals(this))
        .collect(Collectors.toList());

    // se houver algum teleport diferente do atual, move o personagem para ele
    if (teleports.size() > 0) {
      ElementoBasico teleport = teleports.get(0);
      Personagem personagem = (Personagem) outro;
      personagem.setAnterior(this);
      personagem.setLin(teleport.getLin());
      personagem.setCol(teleport.getCol());
      getTabuleiro().insereElemento(personagem);
      getTabuleiro().insereElemento(new Wall("parede", getLin(), getCol(), getTabuleiro()));
      getTabuleiro().insereElemento(new Wall("parede", teleport.getLin(), teleport.getCol(), getTabuleiro()));
    }

  }

}
