import java.util.function.Function;

public class Personagem extends ElementoBasico {
    private ElementoBasico anterior;
    private Boolean hasTheKey;

    public Personagem(String id, String iconPath, int linInicial, int colInicial, Tabuleiro tabuleiro) {
        super(id, iconPath, linInicial, colInicial, tabuleiro);
        this.hasTheKey = false;
    }

    public void setAnterior(ElementoBasico anterior) {
        this.anterior = anterior;
    }

    public ElementoBasico getAnterior() {
        return anterior;
    }

    public void moveDireita() {
        // Remove o Personagem da posicao atual e avança
        getTabuleiro().insereElemento(anterior);
        this.incCol();
        // Verifica se tem algum elemento de interesse na nova posicao
        // e interage de acordo
        movePlayer((Void) -> {
            this.decCol();
            return null;
        });
    }

    public void moveEsquerda() {
        // Remove o Personagem da posicao atual e avança
        getTabuleiro().insereElemento(anterior);
        this.decCol();
        // Verifica se tem algum elemento de interesse na nova posicao
        // e interage de acordo
        movePlayer((Void) -> {
            this.incCol();
            return null;
        });
    }

    public void moveCima() {
        // Remove o Personagem da posicao atual e avança
        getTabuleiro().insereElemento(anterior);
        this.decLin();
        // Verifica se tem algum elemento de interesse na nova posicao
        // e interage de acordo
        movePlayer((Void) -> {
            this.incLin();
            return null;
        });
    }

    public void moveBaixo() {
        // Remove o Personagem da posicao atual e avança
        getTabuleiro().insereElemento(anterior);
        this.incLin();
        // Verifica se tem algum elemento de interesse na nova posicao
        // e interage de acordo
        movePlayer((Void) -> {
            this.decLin();
            return null;
        });
    }

    // make method recive another method
    private void movePlayer(Function<Void, Void> moveFunction) {
        // Verifica se tem algum elemento de interesse na nova posicao
        // e interage de acordo
        ElementoBasico elemento = getTabuleiro().getElementoNaPosicao(this.getLin(), this.getCol());
        if (!(elemento instanceof Fundo) && !(elemento instanceof Ice) && !(elemento instanceof HardIce)
                && !(elemento instanceof Coin) && !(elemento instanceof Key) && !(elemento instanceof Padlock)
                && !(elemento instanceof Teleport)) {
            elemento.acao(this);
            moveFunction.apply(null);
            this.anterior = getTabuleiro().insereElemento(this);
        } else if (elemento instanceof Ice) {
            elemento.acao(this);
            this.anterior = getTabuleiro().insereElemento(this);
        } else if (elemento instanceof HardIce) {
            elemento.acao(this);
            this.anterior = getTabuleiro().insereElemento(this);
        } else if (elemento instanceof Coin) {
            elemento.acao(this);
            this.anterior = getTabuleiro().insereElemento(this);
        } else if (elemento instanceof Key) {
            hasTheKey = true;
            elemento.acao(this);
            this.anterior = getTabuleiro().insereElemento(this);
        } else if (elemento instanceof Teleport) {
            elemento.acao(this);
            this.anterior = getTabuleiro().insereElemento(this);

        } else if (elemento instanceof Padlock) {
            if (hasTheKey == true) {
                elemento.acao(this);
                this.anterior = getTabuleiro().insereElemento(this);
            } else {
                moveFunction.apply(null);
                this.anterior = getTabuleiro().insereElemento(this);
            }
        } else {
            this.anterior = getTabuleiro().insereElemento(this);
        }
    }

    public boolean isMovable() {
        // Verifica se o jogador consegue se mover para cima pra baixo ou pros lados e
        // caso estaja preso retorna false
        ElementoBasico elementoCima = getTabuleiro().getElementoNaPosicao(this.getLin() - 1, this.getCol());
        ElementoBasico elementoBaixo = getTabuleiro().getElementoNaPosicao(this.getLin() + 1, this.getCol());
        ElementoBasico elementoEsquerda = getTabuleiro().getElementoNaPosicao(this.getLin(), this.getCol() - 1);
        ElementoBasico elementoDireita = getTabuleiro().getElementoNaPosicao(this.getLin(), this.getCol() + 1);

        if (elementoCima instanceof Wall || elementoCima instanceof Water) {
            if (elementoBaixo instanceof Wall || elementoBaixo instanceof Water) {
                if (elementoEsquerda instanceof Wall || elementoEsquerda instanceof Water) {
                    if (elementoDireita instanceof Wall || elementoDireita instanceof Water) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void acao(ElementoBasico outro) {
        throw new UnsupportedOperationException("Unimplemented method 'acao'");
    }

}
