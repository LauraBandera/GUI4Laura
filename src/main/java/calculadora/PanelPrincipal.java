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

	private String operando1 = "", operando2 = "", operacion = "", igual = "";

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

		// Establecemos layout del panel principal
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
				if (operando1.equals("")) {
					operando1 = areaTexto.getText();
					System.out.println("El operando 1 es: " + operando1);
				} else if(!operacion.equals("")){
					operando2 = areaTexto.getText().substring(operando1.length() + 1);
					System.out.println("El operando 2 es: " + operando2);
				}
				if (aux.getText().equals("=") && !operacion.equals(null)) {
					operando1 = "" + realizarOperacion(operando1, operando2, operacion);
					areaTexto.setText(operando1);
					operando2 = "";
					operacion = "";
					System.out.println("El operando 1 es: " + operando1);
					System.out.println("El operando 2 es: " + operando2);
					System.out.println("La operación es: " + operacion);
				}else if(aux.getText().equals("C")) {
					areaTexto.setText("");
					operando1 = "";
					operando2 = "";
					operacion = "";
				}else if (aux.getText().equals("+") || aux.getText().equals("-") || aux.getText().equals("*")
						|| aux.getText().equals("/")){
					operacion = aux.getText();
					System.out.println("La operación es: " + operacion);
				}
			}
			if(!aux.getText().equals("=")) {
				areaTexto.setText(areaTexto.getText() + aux.getText());
			}

			if (aux.getText().equals("+") || aux.getText().equals("-") || aux.getText().equals("*")
					|| aux.getText().equals("/")) {
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
			Integer.parseInt(siguienteBoton);

		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	private float realizarOperacion(String num1, String num2, String ope) {
		float numero1, numero2;
		numero1 = Float.parseFloat(num1);
		numero2 = Float.parseFloat(num2);
		switch (ope) {
		case "+":
			return numero1 + numero2;
		case "/":
			return numero1 / numero2;
		case "*":
			return numero1 * numero2;
		case "-":
			return numero1 - numero2;
		default:
			return 0;
		}
	}

}
