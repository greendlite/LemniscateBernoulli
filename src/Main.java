import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // 4 набора параметров графиков
        GraphParams g1 = new GraphParams(Color.RED, 100, 0.06, 5);
        GraphParams g2 = new GraphParams(Color.BLUE, 150, 0.05, 4);
        GraphParams g3 = new GraphParams(Color.GREEN, 200, 0.04, 3);
        GraphParams g4 = new GraphParams(Color.MAGENTA, 250, 0.03, 2);

        // 4 отдельных окна с разными графиками
        createWindow("Лемниската Бернулли (красная)", makePicture(g1));
        createWindow("Лемниската Бернулли (синяя)", makePicture(g2));
        createWindow("Лемниската Бернулли (зелёная)", makePicture(g3));
        createWindow("Лемниската Бернулли (розовая)", makePicture(g4));

        // Общее окно со всеми графиками
        Picture combined = new Picture();
        combined.graphs.add(g1);
        combined.graphs.add(g2);
        combined.graphs.add(g3);
        combined.graphs.add(g4);

        createWindow("Общее окно — все графики", combined);
    }

    // Создаёт Picture, добавляет в него один график
    private static Picture makePicture(GraphParams g) {
        Picture p = new Picture();
        p.graphs.add(g);
        return p;
    }

    // Создание окна
    private static void createWindow(String title, Picture picture) {
        JFrame frame = new JFrame(title);
        frame.setSize(600, 600);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(picture);
        frame.setVisible(true);
        picture.start();
    }
}