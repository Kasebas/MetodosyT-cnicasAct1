import javax.swing.*;
import java.net.URL;

public class Carta extends JButton {
    private String imagen;
    private boolean descubierta = false;
    private ImageIcon reverso;

    public Carta(String imagen) {
        this.imagen = imagen;
        reverso = cargarImagen("img/carta_oculta.png");

        setIcon(reverso);
        addActionListener(e -> revelarCarta());
    }

    private void revelarCarta() {
        if (!descubierta) {
            setIcon(cargarImagen(imagen));
            descubierta = true;
        }
    }

    public void ocultar() {
        setIcon(reverso);
        descubierta = false;
    }

    public String getImagen() {
        return imagen;
    }

    public boolean estaDescubierta() {
        return descubierta;
    }

    private ImageIcon cargarImagen(String ruta) {
        URL imgURL = getClass().getClassLoader().getResource(ruta);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("No se pudo cargar la imagen: " + ruta);
            return null;
        }
    }
}
