import javax.swing.*;
public class App {
    public static void main(String[] args) {
        int boardWidth = 360;
        int boardHeight = 640 ;

        JFrame frame = new JFrame("Flappy Bird");
        frame.setVisible(true);
        frame.setSize(boardWidth,boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FlappyBird flappyBird1 = new FlappyBird();
        frame.add(flappyBird1);
        frame.pack();
        flappyBird1.requestFocus();
        frame.setVisible(true);
    }
}
