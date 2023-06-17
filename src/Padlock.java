public class Padlock extends ElementoBasico {

    public Padlock(String id, int linInicial, int colInicial, Tabuleiro tabuleiro) {
        super(id, "Padlock.jpeg",  linInicial, colInicial, tabuleiro);
        
    }

    @Override
    public void acao(ElementoBasico outro) {
       
     getTabuleiro().insereElemento(new Ice("ice", getLin(), getCol(), getTabuleiro()));
    }
    
}
