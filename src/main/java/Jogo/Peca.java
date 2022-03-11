/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Jogo;

/**
 *
 * @author guita
 */

/*
    A classe Peca representa uma peça no tabuleiro do jogo dos 8
    Contém seu número e posição(x,y) no tabuleiro
*/
public class Peca {
    private int Numero;
    private Posicao posicao;

    public int getNumero() {
        return Numero;
    }

    public void setNumero(int Numero) {
        this.Numero = Numero;
    }
    
    public void setPosicao(Posicao p){
        this.posicao = p;
    }
    
    public Posicao getPosicao(){
        return this.posicao;
    }

    public int getPosicaoX() {
        return posicao.getX();
    }
    
    public int getPosicaoY() {
        return posicao.getY();
    }

    public void setPosicaoX(int X) {
        this.posicao.setX(X);
    }
    
    public void setPosicaoY(int Y){
        this.posicao.setY(Y);
    }

    public Peca(int Numero, int x, int y) {
        this.Numero = Numero;
        this.posicao = new Posicao(x,y);
    }

    
    
}


