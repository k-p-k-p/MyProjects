import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
public class MineSweeper {

    private class MineTile extends JButton {
        int r;
        int c;
    
        public MineTile(int r,int c){
            this.r=r;
            this.c=c;
        }
        
    }

    int tileSize = 70;
    int numRows = 8;
    int numCols = 8;
    int boardWidth = numCols * tileSize;
    int boardHeight = numRows * tileSize;
    Random random = new Random();
    int minesCount = random.nextInt(19 - 7 + 1) + 7;

    JFrame frame = new JFrame("MineSweeper");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    MineTile [][] board = new MineTile[numRows][numCols];
    ArrayList<MineTile> mineList;
    

    int tilesClicked = 0;
    boolean gameOver = false;

    MineSweeper(){
        // frame.setVisible(true);  We need to add this after all the tiles get loaded ,else all the tiles wont get visible on the frame.
        frame.setSize(boardWidth,boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setFont(new Font("Arial",Font.BOLD,25));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("MineSweeper : " + Integer.toString(minesCount));
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel,BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(numRows,numCols));//8x8
        // boardPanel.setBackground(Color.GREEN);
        frame.add(boardPanel);

        int r=0,c=0;
        for (r = 0; r < numRows; r++) {
            for (c= 0; c < numCols; c++) {
                MineTile tile = new MineTile(r, c);
                board[r][c] = tile;

                tile.setFocusable(false);
                tile.setMargin(new Insets(0,0,0,0));
                tile.setFont(new Font("Arial Unicode MS",Font.PLAIN,45));
                // tile.setText("💣");
                tile.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {         
                        if(gameOver){
                            return;
                        }
                        MineTile tile = (MineTile)e.getSource();        
                        
                        //left click
                        if(e.getButton() == MouseEvent.BUTTON1){
                            if(tile.getText() == ""){
                                if(mineList.contains(tile)){
                                    revealMines();
                                }
                                else{
                                    checkMine(tile.r,tile.c);
                                }
                            }
                        }
                        //right click
                        else if (e.getButton() == MouseEvent.BUTTON3){
                            if (tile.getText() == "" && tile.isEnabled()){
                                tile.setText("🚩");
                            }
                            else if (tile.getText() == "🚩"){
                                tile.setText("");
                            }
                        }
                    }
                   
                });
                boardPanel.add(tile);
            }
        }
        frame.setVisible(true);
        setMines();
    }

    void setMines() {
        mineList = new ArrayList<MineTile>();
        // mineList.add(board[2][2]);
        // mineList.add(board[2][3]);
        // mineList.add(board[5][6]);
        // mineList.add(board[3][4]);
        // mineList.add(board[1][1]);
        int r = 0;
        int c = 0;
        int minesLeft = minesCount;
        while (minesLeft > 0) {
            r = random.nextInt(numRows);
            c = random.nextInt(numCols);

            MineTile tile = board[r][c];
            if (!mineList.contains(tile)) {
                mineList.add(tile);
                minesLeft--;
            }
        }

    }
    void revealMines() {
       for (int i = 0; i < mineList.size(); i++) {
            MineTile tile = mineList.get(i);
            tile.setText("💣");
       }
       gameOver = true;
       textLabel.setText("GAME OVER");
    }
    void checkMine(int r, int c) {
        if(r < 0 || r> numRows || c < 0 || c>numCols){
            return;
        }
        MineTile tile = board[r][c];
        if(!tile.isEnabled() || tile.getText() == "🚩"){
            return;
        }
        tile.setEnabled(false);
        tilesClicked +=1;
        System.out.println("Tiles Clicked : "+tilesClicked);
        int minesFound = 0;
        //top 3
        minesFound += countMine(r-1 , c-1);
        minesFound += countMine(r-1 , c);
        minesFound += countMine(r-1 , c+1);
        //both side ,left and right
        minesFound += countMine(r , c-1);
        minesFound += countMine(r , c+1);
        //bottom 3
        minesFound += countMine(r+1 , c-1);
        minesFound += countMine(r+1 , c);
        minesFound += countMine(r+1 , c+1);

        if(minesFound > 0){
            tile.setText(Integer.toString(minesFound));
        }
        else{
            tile.setText("");

            //for automatically opening the empty tiles

            //top 3
            checkMine(r-1 , c-1);
            checkMine(r-1 , c);
            checkMine(r-1 , c+1);
            //both side ,left and right
            checkMine(r , c-1);
            checkMine(r , c+1);
            //bottom 3
            checkMine(r+1 , c-1);
            checkMine(r+1 , c);
            checkMine(r+1 , c+1);
        }

        if (tilesClicked == numRows * numCols - mineList.size()){
            gameOver = true;
            textLabel.setText("Mines Cleared !");
        }
    }

    int countMine(int r , int c) {
       if(r < 0 || r> numRows || c < 0 || c>numCols){
            return 0;
       }
       if(mineList.contains(board[r][c])) {
            return 1;
       }
       return 0;
    }
}
