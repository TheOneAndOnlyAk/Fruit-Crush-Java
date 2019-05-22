package finalProject;

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
	 if(r1+1 == r2 || r1-1 == r2)
		 return true;
	 else if(c1+1 == c2 || c1-1 == c2)
		 return true;
	 else
		 return false;
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
	checkVert(r, c);
	checkHoriz(r, c);
	checkMajDiag(r,c);
	checkMinDiag(r,c);
	
 }
 private void checkMinDiag(int r, int c) {
	// TODO Auto-generated method stub
	
}

private void checkMajDiag(int r, int c) {
	// TODO Auto-generated method stub
	
}

private void checkHoriz(int r, int c) {
	int rightCount = 0;
	int leftCount = 0;
	int ri = r + 1; //copy of column variable for right
	int l = r - 1; //copy of column variable for left
	while(matrix[r][c] == matrix[ri][c])//counts the number of same candies right
	{
		rightCount++;
		ri++;
	}
	while(matrix[r][c] == matrix[l][c])//counts the number of same candies left
	{
		leftCount++;
		l--;
	}
	if(rightCount != 0 && leftCount != 0)//if there is at least one candy on each side
		for(int i = l; i<=ri; i++)
			remove(i, c);
	else if(rightCount >= 2)//if there are at least 2 on the right side
		for(int k = r; k<= ri; k++)
			remove(k, c);
	else if(leftCount >= 2)//if there are at least 2 on the left side
		for(int j = r; j >= l; j--)
			remove(j, c);	
}

private void checkVert(int r, int c) {
	int upCount = 0;
	int downCount = 0;
	int d = c - 1; //copy of column variable for down
	int u = c + 1; //copy of column variable for up
	while(matrix[r][c] == matrix[r][u])//counts the number of same candies up
	{
		upCount++;
		u++;
	}
	while(matrix[r][c] == matrix[r][d])//counts the number of same candies down
	{
		downCount++;
		d--;
	}
	if(downCount != 0 && upCount != 0)//if there is at least one candy on each side
		for(int i = d; i<=c; i++)
			remove(r, i);
	else if(upCount >= 2)//if there are at least 2 on the up side
		for(int k = c; k<= u; k++)
			remove(r, k);
	else if(downCount >= 2)//if there are at least 2 on the down side
		for(int j = c; j >= d; j--)
			remove(r, j);	
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
    public Handler1(int r, int c)
    {
       myRow = r;
       myCol = c;
    } // end of Hander1 constructor 
    
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

} // end of the Candy Crush Class
