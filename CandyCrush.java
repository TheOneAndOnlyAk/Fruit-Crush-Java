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
            matrix[r][c] = 0;
            board[r][c] = new JButton();
            board[r][c].setIcon(refresh());
            board[r][c].setBackground(Color.WHITE);
            board[r][c].addActionListener( new Handler1(r, c) );
            center.add(board[r][c]);
         } // end of for loop
      
   }
   
   public ImageIcon refresh(){
      int random = (int)(Math.random() * 5 + 1);
      if(random == 1){
         ImageIcon bananaImage = new ImageIcon("/Users/Jacob/Downloads/Banana.png");         
         Image img = bananaImage.getImage();  
         Image resizedImage = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);  
         return new ImageIcon(resizedImage);
      }
      else if(random == 2) {
         ImageIcon appleImage = new ImageIcon("/Users/Jacob/Downloads/Apple.png");         
         Image img = appleImage.getImage();  
         Image resizedImage = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);  
         return new ImageIcon(resizedImage);
      }
      else if(random == 3) {
         ImageIcon grapesImage = new ImageIcon("/Users/Jacob/Downloads/Grapes.jpg");         
         Image img = grapesImage.getImage();  
         Image resizedImage = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);  
         return new ImageIcon(resizedImage);
      }
      
      else if(random == 4) {
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
   }
   
   private class Handler1 implements ActionListener {
      
      private int myRow, myCol;
      public Handler1(int r, int c)
      {
         myRow = r;
         myCol = c;
      }
      
      public void actionPerformed(ActionEvent e) {
      
      
      
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
   
   
   
   //public int[][] get(){};

} // end of the Candy Crush Class
