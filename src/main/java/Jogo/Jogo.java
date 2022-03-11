/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Jogo;

/**
 *
 * @author guita
 */
public class Jogo {
    private Peca[][] Tabuleiro;
    private Boolean Ganhou;
    private Peca[][] EstadoObjetivo;
   
    
    public Jogo(int X, int Y) {
        this.Tabuleiro = new Peca[X][Y];
        this.Ganhou = false; 
        initEstadoObjetivo(X,Y);       
    }

    public Peca[][] getTabuleiro() {
        return Tabuleiro;
    }

    public Boolean getGanhou() {
        return Ganhou;
    }

    public Peca[][] getEstadoObjetivo() {
        return EstadoObjetivo;
    }

    public void setTabuleiro(Peca[][] tabuleiro) {
        this.Tabuleiro = tabuleiro;
    }


    /*
        Embaralha o tabuleiro e retorna a posição em que se encotra o espaço vazio
    */
    public Posicao novoJogo() {
        try {
            this.Ganhou = false;

            this.Tabuleiro[0][0] = new Peca(1, 0, 0);
            this.Tabuleiro[0][1] = new Peca(2, 0, 1);
            this.Tabuleiro[0][2] = new Peca(3, 0, 2);
            this.Tabuleiro[1][0] = new Peca(4, 1, 0);
            this.Tabuleiro[1][1] = new Peca(8, 1, 1);
            this.Tabuleiro[1][2] = new Peca(6, 1, 2);
            this.Tabuleiro[2][0] = new Peca(7, 2, 0);
            this.Tabuleiro[2][1] = new Peca(5, 2, 1);
            this.Tabuleiro[2][2] = new Peca(0, 2, 2);
            
//              Descomentar para testar a função eGanhador()
//            this.tabuleiro[0][0] = new Peca(1, 0, 0);
//            this.tabuleiro[0][1] = new Peca(2, 0, 1);
//            this.tabuleiro[0][2] = new Peca(3, 0, 2);
//            this.tabuleiro[1][0] = new Peca(4, 1, 0);
//            this.tabuleiro[1][1] = new Peca(5, 1, 1);
//            this.tabuleiro[1][2] = new Peca(6, 1, 2);
//            this.tabuleiro[2][0] = new Peca(7, 2, 0);
//            this.tabuleiro[2][1] = new Peca(8, 2, 1);
//            this.tabuleiro[2][2] = new Peca(0, 2, 2);
            
            shuffle();

            return encontrarPosicao(0);

        } catch (Exception er) {
            throw er;
        }
    }

    /*
        Move a Peca no tabuleiro para o espaço vazio
    */
    public Posicao mover(Posicao vazio, int NumTabuleiro) {
        try {
            Posicao novo = encontrarPosicao(NumTabuleiro);
            
            boolean t = vazio.equals(novo);

            if (!t && eTrocavel(vazio, novo)) {

                int ox = vazio.getX();
                int oy = vazio.getY();

                int nx = novo.getX();
                int ny = novo.getY();

                this.Tabuleiro[ox][oy].setNumero(this.Tabuleiro[nx][ny].getNumero());
                this.Tabuleiro[nx][ny].setNumero(0);
                
                this.Ganhou = eGanhador();

                return this.Tabuleiro[nx][ny].getPosicao();
            }
            
            this.Ganhou = eGanhador();

            return vazio;

        } catch (Exception er) {
            throw er;
        }
    }
    
    /*
        Embaralha aleatoriamente o tabuleiro
    */
    private void shuffle() {
        for (Peca[] tabuleiro1 : this.Tabuleiro) {
            for (Peca tabuleiro11 : tabuleiro1) {
                int i1 = (int)(Math.random() * this.Tabuleiro.length);
                int j1 = (int) (Math.random() * tabuleiro1.length);
                int aux = tabuleiro11.getNumero();
                tabuleiro11.setNumero(this.Tabuleiro[i1][j1].getNumero());
                this.Tabuleiro[i1][j1].setNumero(aux);
            }
        }
  }

    /*
        Retorna um objeto Posição em que se encontra a Peca com o número informado caso o encontre 
        ou retorna uma instacia vazia do objeto Posicao
    */
    private Posicao encontrarPosicao(int NumTabuleiro) {
        try {
            Posicao p = new Posicao();

            for (Peca[] i : this.Tabuleiro) {
                for (Peca j : i) {
                    if (j.getNumero() == NumTabuleiro) {
                        p = j.getPosicao();
                    }
                }
            }
            return p;
        } catch (Exception er) {
            throw er;
        }
    }

    /*
        Confere se o espaço vazio se encontra paralelo a Peca
        Retorna verdadeiro case sim e falso caso não se encontre
    */
    private Boolean eTrocavel(Posicao vazio, Posicao novo) {
        try {
            int ox = vazio.getX();
            int oy = vazio.getY();

            int nx = novo.getX();
            int ny = novo.getY();

            int tamanhoX = this.Tabuleiro.length - 1;
            int tamanhoY = this.Tabuleiro[0].length - 1;

            //region X
            nx++;
            if (nx < 0 || nx > tamanhoX) {

            } else {
                if (vazio.equals(new Posicao(nx, ny))) {
                    return true;
                }
            }
            
            nx = novo.getX();

            nx--;
            if (nx < 0 || nx > tamanhoX) {

            } else {
                if (vazio.equals(new Posicao(nx, ny))) {
                    return true;
                }
            }
            //endregion
            
            nx = novo.getX();

            //region Y
            ny--;
            if (ny < 0 || ny > tamanhoY) {

            } else {
                if (vazio.equals(new Posicao(nx, ny))) {
                    return true;
                }
            }

            ny = novo.getY();

            ny++;
            if (ny < 0 || ny > tamanhoY) {

            } else {
                if (vazio.equals(new Posicao(nx, ny))) {
                    return true;
                }
            }
            //endregion
            
            return false;
        } catch (Exception er) {
            throw er;
        }
    }

    /*
        Monta como deve estar o tabuleiro para se caracterizar uma vitoria no jogo
    */
    private void initEstadoObjetivo(int X, int Y) {
        try{
            this.EstadoObjetivo = new Peca[X][Y];
            
            int n = (X * Y);
            
            for(int x = X-1; x >= 0 ; x--){
                for(int y = Y-1; y >= 0; y--){
                    this.EstadoObjetivo[x][y] = new Peca(n, x, y);
                    n--;
                    
                }
            }
            
            this.EstadoObjetivo[X-1][Y-1].setNumero(0);
            
//            Descomentar para exibir o EstadoObjetivo do Jogo;                    
//            for(Peca[] i : this.Modelo){
//                for(Peca j : i){
//                    System.out.println(j.getNumero() + " " + j.getPosicaoX() + ";" + j.getPosicaoY());
//                }
//            }
            
        }catch(Exception er){
            throw er;
        }
    }

    /*
        Retorna verdadeiro se o estado do tabuleiro corresponder com o do EstadoObjetivo e falso caso contrario
    */
    private Boolean eGanhador() {
        try{          
                int x = -1, y;
                
                for(Peca[] i : this.EstadoObjetivo){
                    x++;
                    y = -1;
                    for(Peca j : i){
                        y++;
                        if(j.getNumero() != this.Tabuleiro[x][y].getNumero()){
                            return false;
                        }
                    }
                }
           
            return true;
        }catch(Exception er){
            throw er;
        }
    }
}



