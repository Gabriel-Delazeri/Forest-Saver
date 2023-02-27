package game.application;

import javax.swing.JFrame;

public class Window extends JFrame{
	
	public Window() {
        add(new Level());
		setTitle("Forest Saver");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280,720);
		setLocationRelativeTo(null);
        setResizable(false);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Window();
	}	
}
