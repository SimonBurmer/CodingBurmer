package Snake;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * @author CodingBurmer
 */
public class Snake implements ActionListener, KeyListener {

    public static Snake snake; // snake wird in der Main-Methode ein Objekt von Snake zugewiesen;
    public JFrame jframe;
    public RenderPanel renderPanel;
    public Timer timer = new Timer(5, this); // gibt ein delay an und was nach dieser zeit
                                             // ausgeführt werden soll
                                             // this ist ein Objekt (Spielzeit läuft in diesem
                                             // Objekt schon)
    public ArrayList<Point> snakeParts = new ArrayList<Point>();
    public ArrayList<Point> enemyParts = new ArrayList<Point>();
    Enemy enemyHead;

    public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 10;
    public int ticks = 0, direction = DOWN, score, tailLength = 10, time, different = 0;
    public Point head, cherry;
    public Random random;
    public boolean over = false, paused, hasntStart = true;
    public Dimension dim;

    public Snake() // 2
    {
        dim = Toolkit.getDefaultToolkit().getScreenSize(); // Mißt Bildschirmgröße
        jframe = new JFrame("Snake");
        jframe.setVisible(true);
        jframe.setSize(805, 700);
        jframe.setResizable(false);
        jframe.setLocation(dim.width / 2 - jframe.getWidth() / 2,
                dim.height / 2 - jframe.getHeight() / 2);
        jframe.add(renderPanel = new RenderPanel()); // fügt neues renderPanel Objekt in jframe
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.addKeyListener(this);
        startGame();
    }

    public void startGame() // 3
    {
        over = false;
        paused = false;
        time = 0;
        score = 0;
        tailLength = 14;
        ticks = 0;
        direction = DOWN;
        head = new Point(1, -1);

        enemyHead = new Enemy(40, 40);

        random = new Random();
        snakeParts.clear();
        cherry = new Point(random.nextInt(79), random.nextInt(66));
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) // wird immer aufgerufen wenn was passiert, ALso
                                                  // wenn die zeit von Timer abläuft.
    {
        renderPanel.repaint(); // ruf die Methode repaint von JPanel auf
        ticks++;

        if (ticks % 2 == 0 && head != null && !over && !paused) {
            time++;
            actionEnemy();
            snakeParts.add(new Point(head.x, head.y));

            if (direction == UP) {
                if (noTailAt(head.x, head.y - 1)) {
                    head = new Point(head.x, head.y - 1);
                } else {
                    over = true;

                }
            }
            if (head.y - 1 < -1) { // Oben
                head = new Point(head.x, 67);
            }

            if (direction == DOWN) {
                if (noTailAt(head.x, head.y + 1)) {
                    head = new Point(head.x, head.y + 1);
                } else {
                    over = true;
                }
            }
            if (head.y + 1 > 68) { // Unten
                head = new Point(head.x, 0);
            }

            if (direction == LEFT) {
                if (noTailAt(head.x - 1, head.y)) {
                    head = new Point(head.x - 1, head.y);
                } else {
                    over = true;
                }
            }
            if (head.x - 1 < -1) { // Links
                head = new Point(79, head.y);
            }

            if (direction == RIGHT) {
                // timer.setDelay(10);
                if (noTailAt(head.x + 1, head.y)) {
                    head = new Point(head.x + 1, head.y);
                } else {
                    over = true;
                }
            }

            if (head.x + 1 > 81) { // Rechts
                head = new Point(0, head.y);
            }

            if (snakeParts.size() > tailLength) { // kürzt falls zu lange
                snakeParts.remove(0);

            }

            if (head.equals(enemyHead)) {
                over = true;
            }

            if (cherry != null) { // Erkennung der Frucht. Achtung! Frucht ist größer als ein Block
                if (head.x == cherry.x || head.x == cherry.x + 1 || head.x == cherry.x + 2
                        || head.x == cherry.x + 2 || head.x == cherry.x + 3) {
                    if (head.y == cherry.y || head.y == cherry.y + 1 || head.y == cherry.y + 2
                            || head.y == cherry.y + 3 || head.y == cherry.y + 4) {

                        score += 10;
                        tailLength = tailLength + 10;
                        cherry.setLocation(random.nextInt(79), random.nextInt(66));

                    }
                }
            }

        }
    }

    public void actionEnemy() { // erzeugung des Gegners
        enemyParts.add(enemyHead);
        enemyHead = new Enemy(enemyHead.x, enemyHead.y);
        enemyHead.action();
        if (enemyParts.size() > 10) {
            enemyParts.remove(0);
        }
    }

    public boolean noTailAt(int x, int y) {
        for (Point point : snakeParts) {
            if (point.equals(new Point(x, y))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) // 1
    {
        snake = new Snake(); // erzeugt eine neues Objekt von Snake und speichert es unter snake;
    }

    @Override
    public void keyPressed(KeyEvent e) // wird immer aufgerufen, wenn eine Taste gedrückt wurde.
    {
        int i = e.getKeyCode();

        if ((i == KeyEvent.VK_A || i == KeyEvent.VK_LEFT) && direction != RIGHT) {
            direction = LEFT;
        }

        if ((i == KeyEvent.VK_D || i == KeyEvent.VK_RIGHT) && direction != LEFT) {
            direction = RIGHT;
        }

        if ((i == KeyEvent.VK_W || i == KeyEvent.VK_UP) && direction != DOWN) {
            direction = UP;
        }

        if ((i == KeyEvent.VK_S || i == KeyEvent.VK_DOWN) && direction != UP) {
            direction = DOWN;
        }

        if (i == KeyEvent.VK_SPACE) {
            if (over) {
                startGame();
            } else {
                paused = !paused;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
        for (int steps = 0; steps < 5; steps++) {
            int x = 3;
            }
        
    }

}