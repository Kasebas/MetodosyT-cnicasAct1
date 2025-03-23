import javax.swing.*;
import java.awt.*;

public class Juego extends JFrame {
    private Tablero tablero;
    private static Juego instancia; //

    private JComboBox<Integer> filasSelector;
    private JComboBox<Integer> columnasSelector;
    private JButton iniciarBtn;
    private JLabel tiempoLabel;
    private Timer timer;
    private int tiempoRestante = 60;

    public Juego() {
        instancia = this; // Guardar la referencia de la instancia
        setTitle("Juego de Parejas");
        setSize(600, 600);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel de configuración
        JPanel configPanel = new JPanel();
        configPanel.add(new JLabel("Filas:"));
        filasSelector = new JComboBox<>(new Integer[]{2, 4, 6});
        configPanel.add(filasSelector);

        configPanel.add(new JLabel("Columnas:"));
        columnasSelector = new JComboBox<>(new Integer[]{2, 4, 6});
        configPanel.add(columnasSelector);

        iniciarBtn = new JButton("Iniciar Juego");
        configPanel.add(iniciarBtn);
        tiempoLabel = new JLabel("Tiempo: " + tiempoRestante);
        configPanel.add(tiempoLabel);

        add(configPanel, BorderLayout.NORTH);

        iniciarBtn.addActionListener(e -> iniciarJuego());
        setVisible(true);
    }

    public static Juego getInstance() {
        return instancia;
    }

    public void habilitarBotonIniciar() {
        if (timer != null) timer.stop();
        iniciarBtn.setEnabled(true);
    }

    private void iniciarJuego() {
        int filas = (int) filasSelector.getSelectedItem();
        int columnas = (int) columnasSelector.getSelectedItem();

        iniciarBtn.setEnabled(false); // Deshabilita el botón mientras se juega

        if (tablero != null) remove(tablero);
        tablero = new Tablero(filas, columnas);
        add(tablero, BorderLayout.CENTER);
        revalidate();
        repaint();

        // Reiniciar el temporizador
        if (timer != null) timer.stop();
        tiempoRestante = 60; // Reinicia el contador a 60 segundos
        tiempoLabel.setText("Tiempo: " + tiempoRestante);

        timer = new Timer(1000, e -> {
            tiempoRestante--;
            tiempoLabel.setText("Tiempo: " + tiempoRestante);
            if (tiempoRestante == 0) {
                timer.stop();
                JOptionPane.showMessageDialog(this, "Tiempo agotado. Reinicia el juego.");
                tablero.deshabilitarCartas();
                habilitarBotonIniciar();
            }
        });
        timer.start();
    }

}
