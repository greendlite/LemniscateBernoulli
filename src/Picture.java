import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

// Наследуется JPanel для того, чтобы рисовать;
// Реализуется Runnable для того, чтобы создавать потоки.
public class Picture extends JPanel implements Runnable {

    public Color color; // Поле, которое будет держать в себе цвет карандаша
    public int a; // Масштаб лемнискаты — чем больше число, тем больше рисунок
    public double step; // Шаг изменения параметра t. Чем меньше шаг, тем больше точек и более гладкая кривая
    public int pointSize; // Размер точки для отрисовки
    public List<Point> points = new CopyOnWriteArrayList<>(); // Поле списка, которое будет хранить в себе координаты x и y

    public Picture(Color color, int a, double step, int pointSize) {
        this.color = color;
        this.a = a;
        this.step = step;
        this.pointSize = pointSize;
    }

    // Метод внутри JPanel, который вызывается автоматически, когда панель нужно нарисовать
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Вызов родительского метода
        drawAxes(g); // Отрисовка осей
        g.setColor(color); // Установка цвета карандаша
        for (Point p : points) {
            int x = p.x;
            int y = p.y;
            g.fillOval(x, y, pointSize, pointSize);
        }
    }

    // Метод Runnable, который вызывается, когда создается новый поток
    @Override
    public void run() {
        System.out.println("Поток запущен: " + Thread.currentThread().getName());

        // Центр панели, используется как центр лемнискаты
        int cx = getWidth() / 2;
        int cy = getHeight() / 2;

        // Параметр t — параметризация лемнискаты Бернулли
        // Проходим полный круг от 0 до 2π
        for (double t = 0; t < 2 * Math.PI; t += step) {

            // Параметрические формулы лемнискаты Бернулли:
            // x(t) = cos(t) / (1 + sin^2(t))
            // y(t) = sin(t) * cos(t) / (1 + sin^2(t))
            double x = Math.cos(t) / (1 + Math.pow(Math.sin(t), 2));
            double y = Math.sin(t) * Math.cos(t) / (1 + Math.pow(Math.sin(t), 2));

            // Преобразование координат к координатам экрана:
            // Умножение на масштаб a и перенос центр кривой в (cx, cy)
            int X = (int) (cx + a * x);
            int Y = (int) (cy - a * y);

            // Добавление точки в список для дальнейшего рисования
            points.add(new Point(X, Y));

            // Вызов repaint(), чтобы Swing перерисовал панель, paintComponent рисует новую точку
            repaint();

            // Задержка для анимации
            try {
                Thread.sleep(15);
            } catch (InterruptedException ignored) {
            }
        }
    }

    // Метод для запуска потоков
    public void start() {
        new Thread(this).start();
    }

    // Метод для отрисовки осей
    private void drawAxes(Graphics g) {
        int w = getWidth();
        int h = getHeight();
        int xc = w / 2;
        int yc = h / 2;

        g.setColor(Color.GRAY);

        // Ось X
        g.drawLine(0, yc, w, yc);

        // Ось Y
        g.drawLine(xc, 0, xc, h);

        // Стрелки на осях
        g.drawLine(w - 10, yc - 5, w, yc);
        g.drawLine(w - 10, yc + 5, w, yc);
        g.drawLine(xc - 5, 10, xc, 0);
        g.drawLine(xc + 5, 10, xc, 0);

        // Шаги и подписи (каждые 50 px)
        int step = 50;
        g.setFont(new Font("Arial", Font.PLAIN, 12));

        // Деления по X
        for (int x = xc + step; x < w; x += step) {
            g.drawLine(x, yc - 4, x, yc + 4);
            g.drawString(Integer.toString(x - xc), x - 10, yc + 20);
        }
        for (int x = xc - step; x > 0; x -= step) {
            g.drawLine(x, yc - 4, x, yc + 4);
            g.drawString(Integer.toString(x - xc), x - 15, yc + 20);
        }

        // Деления по Y
        for (int y = yc - step; y > 0; y -= step) {
            g.drawLine(xc - 4, y, xc + 4, y);
            g.drawString(Integer.toString(yc - y), xc + 8, y + 5);
        }
        for (int y = yc + step; y < h; y += step) {
            g.drawLine(xc - 4, y, xc + 4, y);
            g.drawString(Integer.toString(yc - y), xc + 8, y + 5);
        }

        // Надписи X и Y
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("X", w - 20, yc - 10);
        g.drawString("Y", xc + 10, 20);
    }

}
