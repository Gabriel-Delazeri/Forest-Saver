package game.entities;

import game.application.Level;

import java.awt.event.KeyEvent;

public class Player extends Sprite {

	private int dx, dy; 
	private int life; 
		
	public Player(String path){ 
		super(100, 100, 2);
		gerarImagem(path);
		life = 3;		
	}

	@Override
	public void mexer()
	{ 
		x += (x + dx < 0 | x + dx > Level.SCREEN_WIDTH - this.largura)? 0: dx;
		y += (y + dy < 0 | y + dy > Level.SCREEN_HEIGHT - this.altura)? 0: dy;
	}


	public int getLife(){
		return life;
	}
	
	public void setLife(int life){
        this.life = life;
		if(this.life > 3){
			this.life = 3;
		}
		setImagem();
	}

	private void setImagem() {
		int area = 60 - ((3 - this.life) * 5);
		imagem = imagem.getScaledInstance(area, area, imagem.SCALE_DEFAULT);
		altura = largura = area;

	}
	
	
	public void keyPressed(KeyEvent tecla){

		switch(tecla.getKeyCode()){
			case KeyEvent.VK_UP:
				dy = velocidade * -1;
				break;
			case KeyEvent.VK_DOWN:
				dy = velocidade;
				break;
			case KeyEvent.VK_LEFT:
				dx = velocidade *-1;
				break;
			case KeyEvent.VK_RIGHT:
				dx = velocidade;
				break;
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
