package game.application;

import game.entities.Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardAdapter extends KeyAdapter {

    private Level level;

    public KeyboardAdapter(Level level) {
        this.level = level;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();
        if (!this.level.isInGame() && !this.level.isStarted()){
            if (code == KeyEvent.VK_B){
                this.level.setAvatar("res//bike_boy.gif");
                this.level.initializeGame();
            }
            else if (code == KeyEvent.VK_G){
                this.level.setAvatar("res//bike_girl.gif");
                this.level.initializeGame();
            }

        }
        else if(!this.level.isInGame()){
            if(code == KeyEvent.VK_ENTER){

                if (this.level.isWon()) {
                    this.level.setCurrentLevel(this.level.getCurrentLevel() + 1);
                } else {
                    this.level.setCurrentLevel(1);
                }

                this.level.initializeTrash();
                this.level.initializeObstacles();
                this.level.setStarted(true);
                this.level.setInGame(true);
                this.level.setPlayer(new Player(this.level.getAvatar()));
            }
        }


        this.level.getPlayer().keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e){
        this.level.getPlayer().keyReleased(e);
    }
}
