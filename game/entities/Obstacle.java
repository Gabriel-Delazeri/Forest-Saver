package game.entities;

import java.util.Random;

public class Obstacle extends Sprite {

    private int dano;

    public Obstacle(int x, int y, int velocidade){
        super(x, y, velocidade);
        Random random = new Random();
        switch(random.nextInt(6)){
            case 2:       
                dano = 3;           
                gerarImagem("res//tree2.png");
                break;
            case 5: 
                dano = -1;          
                gerarImagem("res//heart.png");
                break;
            default:                
                dano = 1;
                gerarImagem("res//pedra.png");
        }
     
    }

    public int getDano(){
        return dano;
    }
        
}
