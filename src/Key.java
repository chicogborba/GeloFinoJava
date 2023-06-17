public class Key extends ElementoBasico {

    public Key(String id, int linInicial, int colInicial, Tabuleiro tabuleiro) {
        super(id, "Key.jpeg" , linInicial, colInicial, tabuleiro);
        
    }

    @Override
    public void acao(ElementoBasico outro) {
       
       getTabuleiro().insereElemento(new Water("water", getLin(), getCol(), getTabuleiro()));
    }
    
}