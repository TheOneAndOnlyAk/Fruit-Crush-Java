package finalProject;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FruitCrush extends JPanel{
//private JFrame frame;
 private JButton[][] board;
 private int[][] fruits;
 private JLabel title;
 public boolean isPressed = false;
 public int tempR, tempC;

 public FruitCrush(){
    //gui();
    setLayout(new BorderLayout());
    
    JPanel north = new JPanel();
    north.setLayout(new FlowLayout());
    add(north, BorderLayout.NORTH);
    title = new JLabel("Fruit Crush");
    title.setFont(new Font("Serif", Font.BOLD, 65));
    title.setForeground(Color.red);
    north.add(title);
    
    JPanel center = new JPanel();
    center.setLayout(new GridLayout(7,7));
    add(center, BorderLayout.CENTER);
    board = new JButton[7][7];
    fruits = new int[7][7];
    for(int r = 0; r < 7; r++)
       for(int c = 0; c < 7; c++)
       {
          int random = (int)(Math.random() * 5 + 1);
          fruits[r][c] = random;
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
       ImageIcon bananaImage = new ImageIcon("C:\\Users\\aksha_j9bug79\\Pictures\\Banana.png");         
       Image img = bananaImage.getImage();  
       Image resizedImage = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);  
       return new ImageIcon(resizedImage);
    }
    else if(r == 2) {
       ImageIcon appleImage = new ImageIcon("C:\\Users\\aksha_j9bug79\\Pictures\\Apple.png");         
       Image img = appleImage.getImage();  
       Image resizedImage = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);  
       return new ImageIcon(resizedImage);
    }
    else if(r == 3) {
       ImageIcon grapesImage = new ImageIcon("C:\\Users\\aksha_j9bug79\\Pictures\\Grapes.jpg");         
       Image img = grapesImage.getImage();  
       Image resizedImage = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);  
       return new ImageIcon(resizedImage);
    }
    
    else if(r == 4) {
       ImageIcon orangeImage = new ImageIcon("C:\\Users\\aksha_j9bug79\\Pictures\\Orange.jpg");         
       Image img = orangeImage.getImage();  
       Image resizedImage = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);  
       return new ImageIcon(resizedImage); 
    }
    else {
       ImageIcon pearImage = new ImageIcon("C:\\Users\\aksha_j9bug79\\Pictures\\Pear.jpg");         
       Image img = pearImage.getImage();  
       Image resizedImage = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);  
       return new ImageIcon(resizedImage);
    }
 } // end of iconSet method
 
 public void swap(int r1, int c1, int r2, int c2)
 {
	 int temp = fruits[r1][c1];
	 fruits[r1][c1] = fruits [r2][c2];
	 fruits[r2][c2] = temp;
	 board[r1][c1].setIcon(iconSet(fruits[r1][c1]));
	 board[r2][c2].setIcon(iconSet(fruits[r2][c2]));
	 System.out.println(fruits[r1][c1]);
	 System.out.println(fruits[r2][c2]);
 }
 
 public boolean switchable(int r1, int r2, int c1, int c2)//checks to see if two squares are adjacent to be swapped
 {
    if(c1==c2)
    	if((r1-1==r2)||r1+1==r2)
    		return true;
    if(r1==r2)
    	if((c1-1==c2)||c1+1==c2)
    		return true;
    return false;
 }
 public void switches(int r1, int c1, int r2, int c2)//switches two elements on the board
 {
	 swap(r1,c1,r2,c2);
	 checkScore(r1,c1);
	 replace(c1);
	 checkScore(r2,c2);
	 replace(c2);
 }

 
 public void checkScore(int r, int c) 
 {
	checkVert(r, c);
	checkHoriz(r, c);
 }
public void checkHoriz(int r, int c) {
	int rightCount = 0;
	int leftCount = 0;
	int ri = c + 1; //copy of column variable for right
	int l = c - 1; //copy of column variable for left
	while((ri < fruits[r].length)&&(fruits[r][c] == fruits[r][ri]))//counts the number of same candies right
	{
		rightCount++;
		ri++;
		System.out.println(ri+" right");
	}
	while((l >= 0)&&(fruits[r][c] == fruits[r][l]))//counts the number of same candies left
	{
		leftCount++;
		l--;
		System.out.println(l+" left");
	}
	if(rightCount != 0 && leftCount != 0)//if there is at least one candy on each side
		for(int i = l+1; i<=ri-1; i++)
		{
			remove(r, i);
			replace(i);
		}
	else if(rightCount >= 2)//if there are at least 2 on the right side
		for(int k = r; k<= ri-1; k++)
		{
			remove(r, k);
			replace(k);
		}
	else if(leftCount >= 2)//if there are at least 2 on the left side
		for(int j = l+1; j <= r; j++)
		{
			remove(r, j);	
			replace(j);
		}
}

public void checkVert(int r, int c) {
	int upCount = 0;
	int downCount = 0;
	int u = r - 1; //copy of row going upwards
	int d = r + 1; //copy of row going downwards
	while((d < fruits.length) && (fruits[r][c] == fruits[d][c]))//loops downward on the array and avoids out of bounds
	{
		downCount++;
		d++;
	}
	while((u >= 0) && (fruits[r][c] == fruits[u][c]))//loops upward on the array and avoids out of bounds
	{
		upCount++;
		u--;
	}
	if(downCount != 0 && upCount != 0)//if there is at least one candy on each side
		for(int i = u+1; i<=d-1; i++)//goes from highest up to lowest down
			remove(i, c);
	else if(upCount >= 2)//if there are at least 2 on the up side
		for(int k = u+1; k <= r; k++)//goes from highest up to initial row
			remove(k, c);
	else if(downCount >= 2)//if there are at least 2 on the down side
		for(int j = r; j <= d-1; j++)//goes from lowest down to inital row
			remove(j, c);	
}
 public void remove(int r, int c)
 {
	 fruits[r][c] = 0;
 }
 public void replace(int c)
 {
	 for(int i = fruits.length-1; i >=0; i--)//starts from the bottom and looks for "0"s
		 if(fruits[i][c] == 0)
		 {
			 slide(i,c);//moves "0"s to top of column
			 i--;
		 }
	 int k = 0;
	 int random = (int)(Math.random() * 5 + 1);//generate random number
	 while((k<fruits.length)&&(fruits[k][c]==0))//replaces all "0"s with new fruits
	 {
		 random = (int)(Math.random() * 5 + 1);
		 fruits[k][c] = random;
		 board[k][c].setIcon(iconSet(random));
		 k++;
	 }
 }


public void slide(int r, int c)//slides 0 upwards
{
	for(int i = r; i>0; i--)
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
         if(!(isPressed)) {//if a button is not selected
            tempR = myRow;
            tempC = myCol;
            isPressed = true;
            board[myRow][myCol].setBackground(Color.red);
         }
         else if(isPressed)
            if(switchable(myRow, tempR, myCol, tempC)){//if a button is selected
               switches(tempR, tempC, myRow, myCol);
               board[tempR][tempC].setBackground(Color.white);
               isPressed = false;
            }
               
         
                  
      
    } // end of actionPerformed method
 
 } // end of Handler1 class
  
 public static void main(String [] args) throws Throwable {
    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    JFrame frame = new JFrame("Fruit Crush");
    frame.setSize(1000, 1000); // sizes the frame
    frame.setLocation(200, 200);
    frame.setContentPane(new FruitCrush());
    frame.setVisible(true); // shows the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exits frame if red X is clicked
    
 } // end of main method

} // end of the Fruit Crush Class