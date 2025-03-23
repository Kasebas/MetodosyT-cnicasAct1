import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Carta extends JButton {
    private String rutaImagen;
    private boolean descubierta = false;
    private ImageIcon reverso;
    private static final int ANCHO = 200;
    private static final int ALTO = 200;

    public Carta(String rutaImagen) {
        this.rutaImagen = rutaImagen;
        reverso = escalarImagen("img/carta_oculta.png"); // Imagen del reverso
        setIcon(reverso);
        setPreferredSize(new Dimension(ANCHO, ALTO));

        addActionListener(e -> revelarCarta());
    }

    private void revelarCarta() {
        if (!descubierta) {
            setIcon(escalarImagen(rutaImagen)); // Mostrar imagen real
            descubierta = true;
        }
    }

    public void ocultar() {
        setIcon(reverso);
        descubierta = false;
    }

    public boolean estaDescubierta() {
        return descubierta;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    private ImageIcon escalarImagen(String ruta) {

        URL imgURL = getClass().getClassLoader().getResource(ruta);
        if (imgURL != null) {
            ImageIcon iconoOriginal = new ImageIcon(imgURL);
            Image imagenOriginal = iconoOriginal.getImage();
            Image imagenEscalada = imagenOriginal.getScaledInstance(ANCHO, ALTO, Image.SCALE_SMOOTH);
            return new ImageIcon(imagenEscalada);
        } else {
            System.err.println("No se pudo cargar la imagen: " + ruta);
            return null; // Retorna null si la imagen no se encuentra
        }
    }
}
