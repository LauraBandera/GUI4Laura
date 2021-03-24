/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author laura
 */
public class PanelPrincipal extends JPanel implements ActionListener {

    // Atributos de la clase (privados)
    private PanelBotones botonera;
    private JTextArea areaTexto;
    private int tipoOperacion;

    // Constructor
    public PanelPrincipal() {
        initComponents();
        tipoOperacion = -1; // No hay operaciones en la calculadora
    }

    // Se inicializan los componentes gráficos y se colocan en el panel
    private void initComponents() {
        // Creamos el panel de botones
        botonera = new PanelBotones();
        // Creamos el área de texto
        areaTexto = new JTextArea(10, 50);
        areaTexto.setEditable(false);
        areaTexto.setBackground(Color.white);

        //Establecemos layout del panel principal
        this.setLayout(new BorderLayout());
        // Colocamos la botonera y el área texto
        this.add(areaTexto, BorderLayout.NORTH);
        this.add(botonera, BorderLayout.SOUTH);

        for (JButton boton : this.botonera.getgrupoBotones()) {
            boton.addActionListener(this);
        }

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        // Se obtiene el objeto que desencadena el evento
        Object o = ae.getSource();
        // Si es un botón
        if (o instanceof JButton) {
            JButton aux = (JButton) o;
            System.out.println(aux.getText());
            if (!comprobarNumero(areaTexto, aux.getText())) {
                areaTexto.setText(aux.getText());
            } else {
                areaTexto.setText(areaTexto.getText() + aux.getText());
            }

            operacionRealizar(aux.getText(), areaTexto.getText());

            if (aux.getText().equals("+") || aux.getText().equals("-") || aux.getText().equals("*") || aux.getText().equals("/")) {
                for (int i = 10; i < this.botonera.getgrupoBotones().length - 1; i++) {
                    this.botonera.getgrupoBotones()[i].setEnabled(false);
                }
            }
            for (int i = 0; i < 10; i++) {
                if (aux.getText().equals(Integer.toString(i)) || aux.getText().equals("C")) {
                    for (int j = 10; j < this.botonera.getgrupoBotones().length - 1; j++) {
                        this.botonera.getgrupoBotones()[j].setEnabled(true);
                    }

                }
            }
        }
    }

    private boolean comprobarNumero(JTextArea areaTexto, String siguienteBoton) {
        try {
            Integer.parseInt(areaTexto.getText());
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private int operacionRealizar(String operador, String numero) {
        return 0;
    }
}
