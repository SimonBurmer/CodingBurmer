package Snake;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class ColorMix {
    public static Color colorMixM() {
        
        Random rand = new Random();
        ArrayList<Color> colorList = new ArrayList<Color>();
        colorList.add(Color.blue);
        colorList.add(Color.CYAN);
        colorList.add(Color.darkGray);
        colorList.add(Color.gray);
        colorList.add(Color.green);
        colorList.add(Color.magenta);
        colorList.add(Color.orange);
        colorList.add(Color.pink);
        colorList.add(Color.red);

        return colorList.get(rand.nextInt(7));
    }
}
