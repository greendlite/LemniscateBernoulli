import java.awt.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GraphParams {
    public Color color; // Поле, которое будет держать в себе цвет карандаша
    public int a; // Масштаб лемнискаты — чем больше число, тем больше рисунок
    public double step; // Шаг изменения параметра t. Чем меньше шаг, тем больше точек и более гладкая кривая
    public int pointSize; // Размер точки для отрисовки
    public List<Point> points = new CopyOnWriteArrayList<>(); // Поле списка, которое будет хранить в себе координаты x и y

    public GraphParams(Color color, int a, double step, int pointSize) {
        this.color = color;
        this.a = a;
        this.step = step;
        this.pointSize = pointSize;
    }
}
