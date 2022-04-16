package jogo;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player extends Objeto{

	private int dx, dy; //movimentação
	private int life = 3; //variavel vida
		
	public Player(){ //constructor do jogador
		gerarImagem("res//bike3.gif"); //instancia a referencia
		
	}

	public int getLife(){ //metodo que retorna a vida
		return life;
	}

	public void setLife(int life){ //metodo que seta a vida
        this.life = life;
		if(this.life > 3){ // Teto de vida
			this.life = 3;
		}
	}
	
	@Override // Comportamente polimorfico
	public void mexer(){ //metodo da movimentação

		x += dx;
		y += dy; //dx e dy são os comandos do jogador atribuidos a variavel de sua posição 

        // System.out.println(this.x + "," + this.y); 
		// a seguir, validação se o personagem exceder o limite da tela:

		if(this.x < 1){ 
			x = 1;
		}
		
		if(this.x > 424){
			x = 424;
		}
		
		if(this.y < 1){
			y = 1;
		}

		if(this.y > 300){
			y = 300;
		}

		// ends 
		
	}
	public void redefinirImagem(int medida){
		imagem = imagem.getScaledInstance(medida, medida, Image.SCALE_DEFAULT);
	}
	

	public void setImagem(int status) {
		// classe que muda o tamanho do personagem caso ele perca vida, por enquanto ele só perde, add depois ganho
		switch(status){
			case 5:
			redefinirImagem(60);;
			break;
			case 4:
			redefinirImagem(55);
			break;
			case 3:
			redefinirImagem(50);
			break;
			case 2:
			redefinirImagem(45);
			break;
			case 1:
			redefinirImagem(40);
			break;
		}
	}


	
	
	public void keyPressed(KeyEvent tecla){
		
		int codigo = tecla.getKeyCode();
		
		if(codigo == KeyEvent.VK_UP){
			dy = velocidade * -1;
		}
		
		if(codigo == KeyEvent.VK_DOWN){
			dy = velocidade;
		}
		
		if(codigo == KeyEvent.VK_LEFT){
			dx = velocidade *-1;
		}
		
		if(codigo == KeyEvent.VK_RIGHT){
			dx = velocidade;
		}
		
	}
	
	public void keyReleased(KeyEvent tecla){
		
		int codigo = tecla.getKeyCode();
		
		if(codigo == KeyEvent.VK_UP || codigo == KeyEvent.VK_DOWN){
			dy = 0;
		}
 
		
		if(codigo == KeyEvent.VK_LEFT || codigo == KeyEvent.VK_RIGHT){
			dx = 0;
		}
		
		
	}
	
	
}
