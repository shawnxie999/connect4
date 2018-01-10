
//import libraries
import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;

public class Connect4 implements ActionListener
{
  // #variable
  /**********************************************************************
  * Variable Dictionary
  * JFrame mainFrame - frame of the game
  * CardLayout cl - the cardLayout that contains panels
  * GridBagConstraints grid1 - layout that arranges the mode1Panel
  * GridBagConstraints grid2 - layout that arranges the mode2Panel
  * JPanel mainPanel - panel that contains cardLayout
  * JPanel menuPanel - panel that contains the menu
  * JPanel mode1Panel - panel that contains Player vs Player
  * JPanel mode2Panel - panel that contains Player vs Computer
  * JButton mode1Btn - button that leads to Player vs Player
  * JButton mode2Btn - button that leads to Player vs Computer
  * JButton menu1Btn - button in Player vs Player that returns to menu
  * JButton menu2Btn - button in Player vs Computer that returns to menu
  * JButton again1Btn - button in Player vs Player that restarts
  * JButton again2Btn - button in Player vs Computer that restarts
  * JLabel printInstru - the label that contains intructions
  * JLabel name - contains the title
  * JButton [] drop1Btn - the array of drop button used for Player vs Player
  * JButton [] drop2Btn - the array of drop button used for Player vs Computer
  * private ImageIcon whiteDisk - the image of white disk
  * private ImageIcon redDisk - the image of red disk
  * private ImageIcon blackDisk - the image of black disk 
  * JLabel [][] labels1 - the array of JLabels that stores disks in Player vs Player
  * JLabel [][] labels2 - the array of JLabels that stores disks in Player vs Computer
  * int [][] colour1 - the arrary that stores the values of disks (Player vs Player)
  * int [][] colour2 - the arrary that stores the values of disks (Player vs Computer)
  * int turn1 - stores the turn of the Players in Player vs Player
  * int turn2 - stores the turn of the players in Player vs Computer
  * int theWinner1 - storest the winner in Player vs Player 
  * int theWinner2 - storest the winner in Player vs Computer
  * JLabel winner1 - label that prints the winner in Player vs Player
  * Jlabel winner2 - label that prints the winner in Player vs Computer
  * int count - count the first time the random number is generated
  * int random - generates a random number between 0 and 6
  * JLabel cheat - prints the column that computer will drop its disk
  * *********************************************************************/
  JFrame mainFrame = new JFrame("Connect 4");
  int turn1 = 1;
  int turn2 = 1;
  int theWinner1 = 0;
  int theWinner2 = 0;
  
  int count = 0;
  JLabel winner1 = new JLabel();
  JLabel winner2 = new JLabel();
  
  int random = 0;
  JLabel cheat = new JLabel();
  
  JPanel menuPanel = new JPanel();
  JPanel mode1Panel = new JPanel();
  JPanel mode2Panel = new JPanel();
  JPanel mainPanel = new JPanel(new CardLayout());
  
  private ImageIcon whiteDisk;
  private ImageIcon redDisk;
  private ImageIcon blackDisk;
  

  // #error
  // #array
  JLabel [][] labels1 = new JLabel[6][7];
  JLabel [][] labels2 = new JLabel[6][7];
  
  // #error
  // #array
  int [][] colour1 = new int [6][7];
  int [][] colour2 = new int [6][7];
 
  JButton mode1Btn = new JButton("Player Vs Player");    

  JButton mode2Btn = new JButton("Player Vs Computer");
  JButton menu1Btn = new JButton("Menu");
  JButton menu2Btn = new JButton("Menu");
  JButton again1Btn = new JButton("Restart/Again");
  JButton again2Btn = new JButton("Restart/Again");

  // #array
  JButton [] drop1Btn = new JButton[7];
  JButton [] drop2Btn = new JButton[7];

  
  CardLayout cl = new CardLayout();


  JLabel name = new JLabel("CONNECT 4",  SwingConstants.CENTER);


  JLabel printInstru = new JLabel ("<html>INTRUCTIONS: "
                      + "<p>You need to right click to drop your coloured disk.</p>"
                      + "<p>Red disk always gets first turn. You need to try to align</p>"
                      + "<p>four of your disks vertically, horizontally, or diagonally</p>"
                      + "<p>to win. (Player vs Computer mode includes God Mode and tells</p>"
                      + "<p>you computer's next move)</p></html>", SwingConstants.CENTER);
  GridBagConstraints grid1 = new GridBagConstraints();
  GridBagConstraints grid2= new GridBagConstraints();


  // #method
  public Connect4()
  {

    //mode1Panel (Player vs Player)---------------------
   
    //using the gridbaglayout
    mode1Panel.setLayout(new GridBagLayout());
    grid1.fill = GridBagConstraints.HORIZONTAL;
    
    //setting up the images of the disks
    whiteDisk = new ImageIcon (getClass().getResource("whiteDisk.png"));
    redDisk = new ImageIcon (getClass().getResource("redDisk.png"));
    blackDisk = new ImageIcon (getClass().getResource("blackDisk.png"));
  
    //setting up the board by making the colour of all the disks white
    for (int r = 0; r < labels1.length; r++)
    {
      for (int c = 0; c < labels1[r].length; c++)
      {
        labels1[r][c] = new JLabel(whiteDisk);
        grid1.gridx = c;
        grid1.gridy = r + 1;
        colour1[r][c] = 0;
        mode1Panel.add(labels1[r][c], grid1);

      }  
    }

 
    //setting up the drop buttons
    for (int x = 0; x < drop1Btn.length; x++)
    {
      drop1Btn[x] =  new JButton ("Drop");
      grid1.gridx = x;
      grid1.gridy = 0;
      mode1Panel.add(drop1Btn[x], grid1);
      drop1Btn[x].addActionListener(this);
    }

    //add return to menu button
    grid1.gridx = 0;
    grid1.gridy = 7;
    mode1Panel.add(menu1Btn, grid1);
    menu1Btn.addActionListener(this);
    
    //add restart button
    grid1.gridx = 1;
    grid1.gridy = 7;
    grid1.gridwidth = 2;
    mode1Panel.add(again1Btn, grid1);
    again1Btn.addActionListener(this);
    

    //setting up the message that will pop up when someone wins
    grid1.gridx = 3;
    grid1.gridy = 7;
    mode1Panel.add(winner1,grid1);   
    winner1.setText(null);
    
    //mode2Panel(Player vs Computer) -----------------
    
    //setting up gridbaglayout
    mode2Panel.setLayout(new GridBagLayout());
    grid2.fill = GridBagConstraints.HORIZONTAL;
    
    //making the colour of all the disks white
    for (int r = 0; r < labels2.length; r++)
    {
      for (int c = 0; c < labels2[r].length; c++)
      {
        labels2[r][c] = new JLabel(whiteDisk);
        grid2.gridx = c;
        grid2.gridy = r + 1;
        colour2[r][c] = 0;
        mode2Panel.add(labels2[r][c], grid2);

      }  //end for
    }//end for
    
    //setting up the drop buttons
    for (int x = 0; x < drop2Btn.length; x++)
    {
      drop2Btn[x] =  new JButton ("Drop");
      grid2.gridx = x;
      grid2.gridy = 0;
      mode2Panel.add(drop2Btn[x], grid2);
      drop2Btn[x].addActionListener(this);
    }
    //return to menu button
    grid2.gridx = 0;
    grid2.gridy = 7;
    mode2Panel.add(menu2Btn, grid2);
    menu2Btn.addActionListener(this);
    
    //restart button
    grid2.gridx = 1;
    grid2.gridy = 7;
    grid2.gridwidth = 2;
    mode2Panel.add(again2Btn, grid2);
    again2Btn.addActionListener(this);
    
    //winning message
    grid2.gridx = 3;
    grid2.gridy = 7;
    mode2Panel.add(winner2,grid2);   
    winner2.setText(null);  

    //menu and mainpanel--------------------------------------
    
    //center all the elements
    name.setAlignmentX(Component.CENTER_ALIGNMENT);
    mode1Btn.setAlignmentX(Component.CENTER_ALIGNMENT);
    mode2Btn.setAlignmentX(Component.CENTER_ALIGNMENT);
    printInstru.setAlignmentX(Component.CENTER_ALIGNMENT);
    menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

    //changing the font of the name
    name.setFont(new Font("Calibri", Font.PLAIN, 70));
    
    //creating spaces and adding name
    menuPanel.add(Box.createVerticalStrut(50));
    menuPanel.add(name);
    
    //creating spaces and adding mode 1
    menuPanel.add(Box.createVerticalStrut(50));
    menuPanel.add(mode1Btn);
    
    menuPanel.add(Box.createVerticalStrut(50));
    mode1Btn.addActionListener(this);
     

    //creating spaces and adding mode 2
    menuPanel.add(mode2Btn);
    mode2Btn.addActionListener(this);
    menuPanel.add(Box.createVerticalStrut(75));
    
    //prints instructions
    menuPanel.add(printInstru);
   
    //setting up cardlayout
    mainPanel.setLayout(cl);
    
    //background colour
    menuPanel.setBackground(Color.YELLOW);
    mode1Panel.setBackground(Color.YELLOW);
    mode2Panel.setBackground(Color.YELLOW);
    
    //adds all the panels to cardlayout
    mainPanel.add(menuPanel,"0");
    mainPanel.add(mode1Panel,"1");
    mainPanel.add(mode2Panel,"2");
    cl.show(mainPanel, "0");
  

    //frame settings
    mainFrame.add(mainPanel);
    mainFrame.pack();
    mainFrame.setVisible(true);
  }//end constructor
  
  // #method
  public void actionPerformed(ActionEvent e)
  {  
          
    //goes to mode1 when button is clicked
    if ( mode1Btn == e.getSource())
      cl.show(mainPanel, "1");
    
    //goes to mode2 when button is clicked
    if (mode2Btn == e.getSource())
      cl.show(mainPanel, "2");
    
    //When the player returns to menu in mode 1-------------------
    if (menu1Btn == e.getSource())
    {
      //goes to menu
      cl.show(mainPanel, "0");
      
      //resets the disks to colour white in mode 1
      for (int i = 0; i < labels1.length; i++)
      {
        for (int c = 0; c < labels1[i].length; c++)
        {
         labels1[i][c].setIcon(whiteDisk);
        }//end for 
      }//end for
      
      //resets the values of colour in mode 1
      // #loop
      for (int i = 0; i < colour1.length; i++)
      {
        for (int c = 0; c < colour1[i].length; c++)
        {
          colour1[i][c] = 0;
        }//end for 
      }//end for
      
      //resets the winner in mode 1
      theWinner1 = 0;
      
      //red gets first turn in mode 1
      turn1 = 1;
      
      //erases the message which tells the winner in mode 1
      winner1.setText(null);
    }//end if
    
    //When the player returns to menu in mode 2-------------------
    if (menu2Btn == e.getSource())
    {
      
      //shows menu
      cl.show(mainPanel, "0");
      
      //resets the disks to colour white in mode 2
      for (int i = 0; i < labels2.length; i++)
      {
        for (int c = 0; c < labels2[i].length; c++)
        {
         labels2[i][c].setIcon(whiteDisk);
        }//end for 
       }//end for
      
      //resets the values of colour in mode 2
       for (int i = 0; i < colour2.length; i++)
       {
         for (int c = 0; c < colour2[i].length; c++)
         {
           colour2[i][c] = 0;
         }//end for 
       }//end for
       
       //resets the winner in mode 2
       theWinner2 = 0;
       
       //red gets first turn in mode 2
       turn2 = 1;
       
       //erases the message which tells the winner in mode 2
       winner2.setText(null);
    }//end if
    
   // When the player restarts in mode 1------------------------
    
    //resets the colour of the disks to white in mode 1
    if (again1Btn == e.getSource())
    {
      for (int i = 0; i < labels1.length; i++)
      {
        for (int c = 0; c < labels1[i].length; c++)
        {
         labels1[i][c].setIcon(whiteDisk);
        }//end for 
      }//end for
     
      //resets the values of colour in mode 1
      for (int i = 0; i < colour1.length; i++)
      {
        for (int c = 0; c < colour1[i].length; c++)
        {
          colour1[i][c] = 0;
        }//end for 
      }//end for
      
      //resets the winner in mode 1
      theWinner1 = 0;
      
      //red gets first turn in mode 1
      turn1 = 1;
      
      //erases the message which tells the winner in mode 1
      winner1.setText(null);
            
    }//end if
    
    // When the player restarts in mode 2------------------------
    // #condition
    if (again2Btn == e.getSource())
    {
      
      //resets the colour of the disks to white in mode 2
      for (int i = 0; i < labels2.length; i++)
      {
        for (int c = 0; c < labels2[i].length; c++)
        {
         labels2[i][c].setIcon(whiteDisk);
        }//end for 
       }//end for
      
      //resets the values of the colour to white in mode 2
       for (int i = 0; i < colour2.length; i++)
       {
         for (int c = 0; c < colour2[i].length; c++)
         {
           colour2[i][c] = 0;
         }//end for 
       }//end for
       
       //resets the winner in mode 2
       theWinner2 = 0;
       
       //resets the turn to red in mode 2
       turn2 = 1;
       
      //erases the message which tells the winner in mode 2
       winner2.setText(null);
    }//end if
    
    //mode1Panel-------------------------------
    //Goes through the drop buttons
    for (int x = 0; x < drop1Btn.length; x++)
    {
      for (int r = 5; r >= 0; r--)         
      {
        //goes through the value of all the disks
        if (colour1[r][x] == 0)
        {             
          //if the drop button is pressed
          if (drop1Btn[x] == e.getSource())
          {  

            //checks horziantal disks---------------------
            for (int s = 0; s < 6; s++)
            {
              for (int y = 0; y < 4; y++)
              {
                // #condition
                if (colour1[s][y] != 0 && colour1[s][y] == colour1[s][y+1] 
                      && colour1[s][y] == colour1[s][y+2] && colour1[s][y] == colour1[s][y+3] )
                {
                  theWinner1 = colour1[s][y];
                }//end if
              }//end for
            }//end for
            
            
            //checks vertical disks---------------------
            for (int s = 0; s < 3; s++)
            {
              for (int y = 0; y < 7; y++)
              {
                if (colour1[s][y] != 0 && colour1[s][y] == colour1[s+1][y] 
                      && colour1[s][y] == colour1[s+2][y] && colour1[s][y] == colour1[s+3][y] )
                {
                  theWinner1 = colour1[s][y];
                }//end if
              }//end for
            }//end for
            
            //checks diagonal disks----------------------
            for (int s = 0; s < 3; s++) 
            {
              for (int y = 0; y < 4; y++) 
              {
                if (colour1[s][y] != 0  && colour1[s][y] == colour1[s+1][y+1] 
                      && colour1[s][y] == colour1[s+2][y+2] && colour1[s][y] == colour1[s+3][y+3]) 
                {
                  theWinner1 = colour1[s][y];
                  
                }//end if
              }//end for
            }//end for
            
            //checks diagonal disks------------------------
            // #loop
            for (int s = 3; s < 6; s++)
            {
              for (int y = 0; y < 4; y++) 
              {
                if (colour1[s][y] != 0  && colour1[s][y] == colour1[s-1][y+1] &&
                    colour1[s][y] == colour1[s-2][y+2] && colour1[s][y] == colour1[s-3][y+3]) 
                {
                   theWinner1 = colour1[s][y];
                }//end if
              }//end for
            }//end for
  
 
            // Declares the winner1-------------------- 
            if (theWinner1 != 0)
            {
              //the red player wins
               if (theWinner1 == 1)
                winner1.setText("Red disk player won!!!");
             //the black player wins
              else 
                winner1.setText("Black disk player won!!!");

              //adds the message of the winner in the mode1Panel
              grid1.gridx = 3;
              grid1.gridy = 7;
              mode1Panel.add(winner1,grid1);

              //resets the winner
              theWinner1 = 0;
              
              //resets the turn to red
              turn1 = 1;
               
              break;
            }//end if
             
             
            //changes the colour of the disks--------------------------
            //if the turn belongs to red player
            if(turn1 == 1)
            {             
              //image icon change to red
              labels1[r][x].setIcon(redDisk);
              
              //value of the disk changed
              colour1[r][x] = 1;
              
              //black disk player's turn
              turn1 = 2;
              break;
            }//end if
            
            //if the turn belongs to black player
            else
            {
              //changes the image icon to black
              labels1[r][x].setIcon(blackDisk);
              
              //value of the disk changed
              colour1[r][x] = 2;
              
              //red disk player's turn
              turn1 = 1;
              break;
             } //end else
            }//end if
          }//end if
        }//end for
       }//end for
   
    
    //mode2Panel--------------------------------

    //prints computer's next move
    // #cheat
    if (count == 0)
    {
      //a random number is generated between 0 and 6
      random = (int)(Math.random()*7) ;
      
      //prints the move on the screen
      cheat.setText("Next move: col " + (random+1));
      grid2.gridx = 5;
      grid2.gridy = 7;
      mode2Panel.add(cheat,grid2);
      
      //records the first time
      count = 1;
    }

    //goes through all the drop buttons in mode 2      
    for (int x = 0; x < drop2Btn.length; x++)
    {
      for (int r = 5; r >= 0; r--)         
      {
        //goes through the value of all the disks in mode 2
        if (colour2[r][x] == 0)
        {             
          if (drop2Btn[x] == e.getSource())
          {  
            //checks horziantal disks---------------------
            for (int s = 0; s < 6; s++)
            {
              for (int y = 0; y < 4; y++)
              {
                if (colour2[s][y] != 0 && colour2[s][y] == colour2[s][y+1] 
                      && colour2[s][y] == colour2[s][y+2] && colour2[s][y] == colour2[s][y+3] )
                {
                  theWinner2 = colour2[s][y];
                }//end if
              }//end for
            }//end for
            
            
            //checks vertical disks---------------------
            for (int s = 0; s < 3; s++)
            {
              for (int y = 0; y < 7; y++)
              {
                if (colour2[s][y] != 0 && colour2[s][y] == colour2[s+1][y] 
                      && colour2[s][y] == colour2[s+2][y] && colour2[s][y] == colour2[s+3][y] )
                {
                  theWinner2 = colour2[s][y];
                }//end if
              }//end for
            }//end for
            
            //checks diagonal disks----------------------
            for (int s = 0; s < 3; s++) 
            {
              for (int y = 0; y < 4; y++) 
              {
                if (colour2[s][y] != 0  && colour2[s][y] == colour2[s+1][y+1] 
                      && colour2[s][y] == colour2[s+2][y+2] && colour2[s][y] == colour2[s+3][y+3]) 
                {
                  theWinner2 = colour2[s][y];
                  
                }//end if
              }//end for
            }//end for
            
            //checks diagonal disks------------------------
            for (int s = 3; s < 6; s++)
            {
              for (int y = 0; y < 4; y++) 
              {
                if (colour2[s][y] != 0  && colour2[s][y] == colour2[s-1][y+1] &&
                    colour2[s][y] == colour2[s-2][y+2] && colour2[s][y] == colour2[s-3][y+3]) 
                {
                   theWinner2 = colour2[s][y];
                }//end if
              }//end for
            }//end for
  
            //Declares winner2
            if (theWinner2 != 0)
            {
              //if you win the game
              if (theWinner2 == 1)
                // #string
                winner2.setText("Congrats! You won!!!");
              
              //if the computer won the game
              else 
                // #string
                winner2.setText("Computer won!!!");

              //prints the message on the screen
              grid2.gridx = 3;
              grid2.gridy = 7;
              mode2Panel.add(winner2,grid2);

              //resets the winner
              theWinner2 = 0;
              
              //resets the turn 
              turn2 = 1;    
              break;
            }//end if
           
            //if its player's turn
            if (turn2 == 1)
            {
              //the colour of the disk change to red
              labels2[r][x].setIcon(redDisk);

              //change the value of the disk
              colour2[r][x] = 1;   
              
              //changes the turn to computer
              turn2 = 2;  
              break;  
            }//end if  
          }  //end if  
        } //end for
      }//end for
    }//end for

  
    //goes through all the rows
    for (int r = 5; r >= 0; r--)         
    {
      //checks if the current disk is white
      // #condition
      if (colour2[r][random] == 0)
      {   
        //checks for computer's turn
        // #condition
        if (turn2 == 2)
        {
          //changes the colour of the disk from white to black based on the random number
          labels2[r][random].setIcon(blackDisk);
          
          //changes the value of the colour
          colour2[r][random] = 2;
          
          //changes the turn to player
          turn2 = 1;
          break;
        }//end if        
      }//end if 
    }//end for
    
    //another random is generated
    // #random
    random = (int)(Math.random()*7) ;
      
    //if the column is full, it generates another number
    // #loop
    while (colour2[0][random] != 0 )
      random = (int)(Math.random()*7) ;
    
    //prints the next move of the computer
    // #string
    cheat.setText("Next move: col " + (random+1));
    grid2.gridx = 5;
    grid2.gridy = 7;
    mode2Panel.add(cheat,grid2);
     
  }//end actionperformed
  
  // #main
  public static void main(String[] args) 
  { 
    new Connect4();

  }//end main
     
}//end class Connect4





