import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Picture picture_1 = new Picture(Color.RED, 100, 0.06, 5);
        createWindow("Лемниската Бернулли (красная)", picture_1);

        Picture picture_2 = new Picture(Color.BLUE, 150, 0.05, 4);
        createWindow("Лемниската Бернулли (синяя)", picture_2);

        Picture picture_3 = new Picture(Color.GREEN, 200, 0.04, 3);
        createWindow("Лемниската Бернулли (зеленая)", picture_3);

        Picture picture_4 = new Picture(Color.MAGENTA, 250, 0.03, 2);
        createWindow("Лемниската Бернулли (розовая)", picture_4);

        /*Picture picture_5 = new Picture(Color.RED, 100, 0.06, 5);
        Picture picture_6 = new Picture(Color.BLUE, 150, 0.05, 4);
        Picture picture_7 = new Picture(Color.GREEN, 200, 0.04, 3);
        Picture picture_8 = new Picture(Color.MAGENTA, 250, 0.03, 2);

        JFrame frameAll = new JFrame("Лемнискаты Бернулли вместе");
        frameAll.setSize(600, 600);
        frameAll.setResizable(false);
        frameAll.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frameAll.add(picture_5);
        frameAll.add(picture_6);
        frameAll.add(picture_7);
        frameAll.add(picture_8);

        frameAll.setVisible(true);

        picture_5.start();
        picture_6.start();
        picture_7.start();
        picture_8.start();*/
    }

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
