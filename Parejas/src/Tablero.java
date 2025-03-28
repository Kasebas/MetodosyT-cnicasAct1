import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class Tablero extends JPanel {
    private Carta primeraCarta = null;
    private Carta segundaCarta = null;
    private Timer tiempoEspera;

    public Tablero(int filas, int columnas) {
        setLayout(new GridLayout(filas, columnas));
        List<String> imagenes = generarImagenes(filas * columnas);

        for (String imagen : imagenes) {
            Carta carta = new Carta(imagen);
            carta.addActionListener(e -> manejarClickCarta(carta));
            add(carta);
        }
    }

    private List<String> generarImagenes(int total) {
        List<String> imagenes = new ArrayList<>();
        int numParejas = total / 2;

        for (int i = 1; i <= numParejas; i++) {
            imagenes.add("img/" + i + ".png");
            imagenes.add("img/" + i + ".png");
        }

        Collections.shuffle(imagenes);
        return imagenes;
    }

    private void manejarClickCarta(Carta carta) {
        if (primeraCarta == null) {
            primeraCarta = carta;
        } else if (segundaCarta == null && carta != primeraCarta) {
            segundaCarta = carta;
            verificarPareja();
        }
    }

    private void verificarPareja() {
        if (primeraCarta.getRutaImagen().equals(segundaCarta.getRutaImagen())) {
            primeraCarta = null;
            segundaCarta = null;


            SwingUtilities.invokeLater(this::verificarVictoria);
        } else {
            tiempoEspera = new Timer(1000, e -> {
                primeraCarta.ocultar();
                segundaCarta.ocultar();
                primeraCarta = null;
                segundaCarta = null;
            });
            tiempoEspera.setRepeats(false);
            tiempoEspera.start();
        }
    }



    public void deshabilitarCartas() {
        for (Component comp : getComponents()) {
            if (comp instanceof Carta) {
                comp.setEnabled(false);
            }
        }
    }

    public void verificarVictoria() {
        for (Component comp : getComponents()) {
            if (comp instanceof Carta) {
                Carta carta = (Carta) comp;
                if (!carta.estaDescubierta()) {
                    return;
                }
            }
        }


        JOptionPane.showMessageDialog(this, "¡Has ganado! Reinicia el juego.");
        deshabilitarCartas();
        Juego.getInstance().habilitarBotonIniciar();
    }


}
