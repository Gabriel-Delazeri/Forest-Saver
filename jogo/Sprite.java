package jogo;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Rectangle;

public abstract class Sprite {
    protected Image imagem;
    protected int x, y;
    protected int largura, altura;
    protected boolean visivel;
    protected int velocidade;

    public Sprite(int x, int y, int velocidade){
        this.x = x;
        this.y = y;
        this.velocidade = velocidade;
        visivel = true;
    }

    public void mexer(){

        if(this.x < 0){
            this.x = Fase.LARGURA_TELA + 200;
        } else {
            this.x -= velocidade;
        }
    }

    public void gerarImagem(String path){
        ImageIcon referencia = new ImageIcon(path); //instancia a referencia da imagem
		this.imagem = referencia.getImage(); //atribui a imagem referenciada
        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);      
    }

     // tratar colisão vida etc
     public Rectangle getBounds(){
        return new Rectangle(x, y, largura, altura);
    }

    // Getters e setters
    public boolean isVisivel() {
        return visivel;
    }
    public void setVisivel(boolean isVisivel){
        this.visivel = isVisivel;
    }
    public Image getImagem() {
        return imagem;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
  
}
