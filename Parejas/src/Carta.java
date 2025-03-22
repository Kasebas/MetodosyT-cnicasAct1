import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Carta extends JButton {
    private String imagen;
    private boolean descubierta = false;

    public Carta(String imagen) {
        this.imagen = imagen;
        setIcon(new ImageIcon("images/carta_oculta.png"));

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!descubierta) {
                    setIcon(new ImageIcon(imagen));
                    descubierta = true;
                }
            }
        });
    }

    public void ocultar() {
        setIcon(new ImageIcon("images/carta_oculta.png"));
        descubierta = false;
    }

    public String getImagen() {
        return imagen;
    }

    public boolean estaDescubierta() {
        return descubierta;
    }
}
