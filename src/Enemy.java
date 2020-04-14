package Snake;

import java.awt.Point;

public class Enemy extends Point {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    int lastRandomx = 1;
    int lastRandomy = 1;

    public Enemy(int x, int y) {
        super(x, y);
    }

    public void action() {

        if (Snake.snake.time % 2 == 0) {
            if (this.x - Snake.snake.head.x > 0) { // aus der Klasse Snake wird die Variable snake
                                                   // genomme, ihr ist eine Objekt von Snake
                                                   // zugewiesen. daher die Schreibweiße.
                this.x--;
            } else {
                this.x++;
            }
        }
        if (Snake.snake.time % 3 == 0) {
            if (this.y - Snake.snake.head.y > 0) {
                this.y--;
            } else {
                this.y++;
            }
        }

    }

}

//
//public void action() {
//    if (enemyArray != null) {
//        for (Point enemy : enemyArray) {
//            if (Snake.head.equals(enemy)) {
//                Snake.over = true;
//            }
//        }
//    }
//   
//    if (tailLength > 14 && different != tailLength) {
//        enemyArray.clear();
//        int amountEnemy = tailLength / 4;
//        for (int i = 0; i <= amountEnemy; i++) {
//            enemyArray.add(new Point(random.nextInt(79), random.nextInt(66)));
//        }
//        different = tailLength;
//    }