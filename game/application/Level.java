package game.application;

import game.entities.Obstacle;
import game.entities.Player;
import game.entities.Trash;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Font;

public class Level extends JPanel implements ActionListener{
    private int currentLevel;
    private Image background;
    private Player player;
    private Timer timer;
    private String avatar;
    public static final int SCREEN_WIDTH = 1265;
    public static final int SCREEN_HEIGHT = 659;
    private boolean won, inGame, started;
    private List<Trash> trashes = new ArrayList<>();
    private List<Obstacle> obstacles = new ArrayList<>();

    KeyboardAdapter keyboardAdapter = new KeyboardAdapter(this);

    private int[][] obstacleCoordinates = {
            {20, 450},
            {300, 480},
            {320, 1000},
            {500, 564},
            {600, 350},
            {750, 900},
            {900, 1},
            {950, 500},
            {1100, 320},
            {950, 100},
            {750, 200},
            {600, 30},
            {100, 200},
            {500, 256}
    };

    private int[][] trashCoordinates = {
            {20, 300},
            {400, 200},
            {320, 550},
            {200, 290},
            {300, 1},
            {750, 390},
            {900, 564},
            {1100, 600},
            {1300, 100},
            {1300, 400},
    };

    public Level(){
        this.currentLevel = 1;
        this.started = false;
        this.inGame = false;

        setFocusable(true);
        setDoubleBuffered(true);
        addKeyListener(this.keyboardAdapter);

        background = new ImageIcon("res//fundo.png").getImage();

        initializeTrash();
        initializeObstacles();

        player = new Player("");
        timer = new Timer(5, this);
        timer.start();
    }

    public void initializeTrash(){
        trashes = new ArrayList<Trash>();
        for (int [] coordinate: trashCoordinates){
            trashes.add(new Trash(coordinate[0], coordinate[1], currentLevel));
        }
    }

    public void initializeObstacles(){
        obstacles = new ArrayList<Obstacle>();
        for(int [] coordinates: obstacleCoordinates ){
            obstacles.add(new Obstacle(coordinates[0], coordinates[1], currentLevel));
        }

    }
    public void initializeGame(){
        player = new Player(avatar);
        initializeTrash();
        initializeObstacles();
        this.started = true;
        this.inGame = true;
    }
 

    public void paint(Graphics g){
        Graphics2D graphics = (Graphics2D) g;
        graphics.drawImage(background, 0, 0, null);
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Arial Black", Font.PLAIN, 18));;

        if(inGame){

            won = false;

            graphics.drawImage(player.getImagem(),player.getX(),player.getY(), this);

            for(Trash trash : trashes){
                graphics.drawImage(trash.getImagem(), trash.getX(), trash.getY(), this);
            }

            for(Obstacle obst: obstacles){
                graphics.drawImage(obst.getImagem(), obst.getX(), obst.getY(), this);
            }

            graphics.drawString("LIXOS NA ESTRADA: " + trashes.size(), 5, 20);
            graphics.drawString("LIFE: " + player.getLife(), 5, 40);
            graphics.drawString("level: " + currentLevel, 1160, 20);

        } else {
            if(won){
               graphics.drawString("VOCÊ VENCEU!! APERTE ENTER E VÁ PARA PRÓXIMA FASE!", 5, 20);
            }
            else if (started == false){
              graphics.drawImage((Image) new ImageIcon("res//start.jpg").getImage(),0, 0, null);
              graphics.drawString("DESVIE DAS PEDRAS E ARVORES ENQUANTO RECOLHE O LIXO NA FLORESTA!", 300, 100);
              graphics.drawString("Arvores causam dano total e pedras retiram 1 ponto de vida!", 370, 150);
              graphics.drawString("ESCOLHA SEU AVATAR", 520, 290);
              graphics.drawImage((Image) new ImageIcon("res//bike1.gif").getImage(),800, 350, null);
              graphics.drawString("APERTE B", 840, 600);
              graphics.drawImage((Image) new ImageIcon("res//bike2.gif").getImage(),300, 350, null);
              graphics.drawString("APERTE G", 320, 600);
            }
            else {
                ImageIcon fimJogo = new ImageIcon("res//game_over.jpg");
                graphics.drawImage(fimJogo.getImage(), -5,-50,null);
                graphics.drawString("Pressione Enter para jogar denovo", 480, 500);
                currentLevel = 1;
            }
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

        if(player.getLife() > 0 && trashes.size() == 0){
           won = true;
           inGame = false;
        }

        for(int i = 0; i < trashes.size() ; i++){

            Trash in = (Trash) trashes.get(i);

            if(in.isVisivel()){
                in.mexer();
            } else {
                trashes.remove(i);
            }
        }

        for(int i = 0; i < obstacles.size() ; i++){

            Obstacle in = (Obstacle) obstacles.get(i);

            if(in.isVisivel()){
                in.mexer();
            } else {
                obstacles.remove(i);
            }
        }

        player.mexer();
        checarColisoes();
        repaint();
    }

    public void checarColisoes(){

        Rectangle formaPlayer = player.getBounds();
        Rectangle formaLixo;
        Rectangle formaObstaculo;

        for(Trash trash : trashes){
            formaLixo = trash.getBounds();
            if(formaPlayer.intersects(formaLixo)){
                trash.setVisivel(false);
            }
        }

        for(Obstacle obst: obstacles){
            
            formaObstaculo = obst.getBounds();

            if(formaPlayer.intersects(formaObstaculo)){
                player.setLife(player.getLife()- obst.getDano());
                if(player.getLife() < 1){
                    player.setVisivel(false);
                    inGame = false;
                }
                obst.setVisivel(false);

            }
        }
    }

    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}