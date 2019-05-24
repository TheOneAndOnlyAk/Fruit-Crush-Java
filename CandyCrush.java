//package finalProject;

//package finalProject;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CandyCrush extends JPanel{
 //private JFrame frame;
 private JButton[][] board;
 private int[][] matrix;
 private JLabel title;
 public boolean isPressed = false;
 public int tempR, tempC;

 public CandyCrush(){
    //gui();
    setLayout(new BorderLayout());
    
    JPanel north = new JPanel();
    north.setLayout(new FlowLayout());
    add(north, BorderLayout.NORTH);
    title = new JLabel("Candy Crush");
    title.setFont(new Font("Serif", Font.BOLD, 65));
    title.setForeground(Color.red);
    north.add(title);
    
    JPanel center = new JPanel();
    center.setLayout(new GridLayout(7,7));
    add(center, BorderLayout.CENTER);
    board = new JButton[7][7];
    matrix = new int[7][7];
    for(int r = 0; r < 7; r++)
       for(int c = 0; c < 7; c++)
       {
          int random = (int)(Math.random() * 5 + 1);
          matrix[r][c] = random;
          board[r][c] = new JButton();
          board[r][c].setIcon(iconSet(random));
          board[r][c].setBackground(Color.WHITE);
          board[r][c].addActionListener( new Handler1(r, c) );
          center.add(board[r][c]);
       } // end of for loop
             
 }
 
 public ImageIcon iconSet(int r){
    //int random = (int)(Math.random() * 5 + 1);
    if(r == 1){
       ImageIcon bananaImage = new ImageIcon("/Users/Jacob/Downloads/Banana.png");         
       Image img = bananaImage.getImage();  
       Image resizedImage = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);  
       return new ImageIcon(resizedImage);
    }
    else if(r == 2) {
       ImageIcon appleImage = new ImageIcon("/Users/Jacob/Downloads/Apple.png");         
       Image img = appleImage.getImage();  
       Image resizedImage = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);  
       return new ImageIcon(resizedImage);
    }
    else if(r == 3) {
       ImageIcon grapesImage = new ImageIcon("/Users/Jacob/Downloads/Grapes.jpg");         
       Image img = grapesImage.getImage();  
       Image resizedImage = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);  
       return new ImageIcon(resizedImage);
    }
    
    else if(r == 4) {
       ImageIcon orangeImage = new ImageIcon("/Users/Jacob/Downloads/Orange.jpg");         
       Image img = orangeImage.getImage();  
       Image resizedImage = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);  
       return new ImageIcon(resizedImage); 
    }
    else {
       ImageIcon pearImage = new ImageIcon("/Users/Jacob/Downloads/Pear.jpg");         
       Image img = pearImage.getImage();  
       Image resizedImage = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);  
       return new ImageIcon(resizedImage);
    }
 } // end of iconSet method
 
 public void swap(int r1, int c1, int r2, int c2)
 {
	 int temp = matrix[r1][c1];
	 matrix[r1][c1] = matrix [r2][c2];
	 matrix[r2][c2] = temp;
	 board[r1][c1].setIcon(iconSet(matrix[r1][c1]));
	 board[r2][c2].setIcon(iconSet(matrix[r2][c2]));	 
 }
 
 public boolean switchable(int r1, int r2, int c1, int c2)
 {
    return (((r1+1==r2)||(r1-1==r2))||((c1+1==c2)||((c1-1==c2))));
 }
 public void switches(int r1, int c1, int r2, int c2)
 {
	 swap(r1,c1,r2,c2);
	 checkScore(r1,c1);
	 checkScore(r2,c2);
	 replace(c1);
	 replace(c2);
 }

 
 public void checkScore(int r, int c) 
 {
	 
 }
 public int checkScoreHelper(int r, int c)
 {
	 return 0;
 }
 public void remove(int r, int c)
 {
	 matrix[r][c] = 0;
 }
 public void replace(int c)
 {
	 for(int i = matrix.length-1; i >=0; i--)
		 if(matrix[i][c] == 0)
		 {
			 slide(i,c);
			 i--;
		 }
	 int k = 0;
	 int random = (int)(Math.random() * 5 + 1);
	 while(matrix[k][c]==0)
	 {
		 random = (int)(Math.random() * 5 + 1);
		 matrix[k][c] = random;
		 board[k][c].setIcon(iconSet(random));
	 }
 }


public void slide(int r, int c) 
{
	for(int i = r; i>0; i++)
		swap(i,c,i-1,c);		
}


private class Handler1 implements ActionListener {
    
    private int myRow, myCol;
    private int oneMyRow, oneMyCol;
    private int count;
    public Handler1(int r, int c)
    {
       myRow = r;
       myCol = c;
    } // end of Hander1 constructor 
    
    public void actionPerformed(ActionEvent e) {      
         if(!(isPressed)) {
            tempR = myRow;
            tempC = myCol;
            isPressed = true;
            board[myRow][myCol].setBackground(Color.red);
         }
         else if(isPressed)
            if(switchable(myRow, tempR, myCol, tempC)){
               switches(tempR, tempC, myRow, myCol);
               board[tempR][tempC].setBackground(Color.white);
               isPressed = false;
            }
               
         
                  
      
    } // end of actionPerformed method
 
 } // end of Handler1 class
  
 public static void main(String [] args) throws Throwable {
    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    JFrame frame = new JFrame("Candy Crush");
    frame.setSize(1000, 1000); // sizes the frame
    frame.setLocation(200, 200);
    frame.setContentPane(new CandyCrush());
    frame.setVisible(true); // shows the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exits frame if red X is clicked
    
 } // end of main method

} // end of the Candy Crush Class
