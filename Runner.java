import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;
import java.util.ArrayList;

public class Runner 
{
  public static void main(String[] args)
  {
    JFrame window = new JFrame();
    CustomPanel container = new CustomPanel();
    container.setSize(1500, 1500);
    window.setSize(container.getSize());
    window.add(container);
    window.addKeyListener(container);
    window.addMouseListener(container);
    window.setVisible(true);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }
}

class CustomPanel extends JPanel implements ActionListener, MouseListener, KeyListener
{
  private Timer time;
  private int direction;
  private int currentXPos, currentYPos;
  private int speedMultiplier;
  private ArrayList<Rocket> rockets;
  private Image spaceship;

  public CustomPanel()
  {
    time = new Timer(15, this);
    setSize(1500, 1500);
    setVisible(true);
    time.start();
    direction = 0;
    currentXPos = 750;
    currentYPos = 800;
    speedMultiplier = 3;
    rockets = new ArrayList<Rocket>();
    try 
    {
      spaceship = ImageIO.read(new File("spaceship.png"));
    }
    catch (Exception e) {}
  }

  public void paintComponent(Graphics g)
  {
    g.drawImage(spaceship, currentXPos - (spaceship.getWidth(null)/2), currentYPos, null);
    paintRockets(g); 

  }

  public void paintRockets(Graphics g)
  {
    for (Rocket rocket : rockets) {
      g.fillOval(rocket.getX(), currentYPos - rocket.getY(), 10, 10);
    }
  }

  // Main Event Loop

  public void actionPerformed(ActionEvent e) 
  {
    currentXPos += direction * speedMultiplier;

    for (int i = 0; i < rockets.size(); i++) {
      if (rockets.get(i).getY() > 700) {
        rockets.remove(i);
        i--;
      }
      else {
        rockets.get(i).increaseY();
      }
    }


    repaint(); 
  }

  // Key Events

  public void keyPressed(KeyEvent e) 
  {
    int code = e.getKeyCode(); 

    switch (code) 
    {
      case KeyEvent.VK_A: direction = -1; break; 
      case KeyEvent.VK_D: direction = 1; break;
      case KeyEvent.VK_RIGHT: direction = 1; break;
      case KeyEvent.VK_LEFT: direction = -1; break;
    }
  }

  public void keyReleased(KeyEvent e)
  {
    direction = 0;
  }

  public void keyTyped(KeyEvent e) {}

  // Mouse Event Handling

  public void mouseClicked(MouseEvent e) 
  {
    Rocket nRocket = new Rocket(currentXPos, 7);
    rockets.add(nRocket); 
  }

  public void mousePressed(MouseEvent e){}
  public void mouseReleased(MouseEvent e){}
  public void mouseEntered(MouseEvent e){}
  public void mouseExited(MouseEvent e){}

}

class Rocket 
{
  private int x, y;
  private Timer time;
  private int speed;

  public Rocket(int x, int s)
  {
    this.x = x;
    this.speed = s;
    y = 0;
  }

  public void increaseY() 
  {
    y+=speed;
  }

  public int getY()
  {
    return y;
  } 

  public int getX()
  {
    return x;
  }
}
