package application;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import exception.numeroParException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import modelo.Cuadrado;

public class PrincipalController implements Initializable {

	private ObservableList<String> opciones = FXCollections.observableArrayList("NE", "NO", "SO", "SE");
	private Cuadrado cuadrado = new Cuadrado();

	@FXML
	private Button armar;
	@FXML
	private ChoiceBox<String> opcionesBox;
	@FXML
	private TextField TxtTamanio;
	@FXML
	private GridPane grid = new GridPane();
	@FXML
	private Label labelOrientacion;
	@FXML
	private Label LabelTamanioFijo1;
	@FXML
	private Label LabelTamanioFijo2;
	@FXML
	private Label LabelTamanioVariable;
	@FXML
	private Label LabelOrdenFijo;
	@FXML
	private Label LabelOrdenVariable;
	@FXML
	private Label LabelValorFilaFijo;
	@FXML
	private Label LabelValorFilaVariable;
	@FXML
	private Label LabelValorColumnaFijo;
	@FXML
	private Label LabelValorColumnaVariable;
	@FXML
	private Label LabelConstanteMagicaFija;
	@FXML
	private Label LabelConstanteMagicaVariable;
	private Button[][] buttons;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO
		opcionesBox.setValue("NE");
		opcionesBox.setItems(opciones);
		cuadrado.cuadradoOrganizado(false);
	}

	public void crear(ActionEvent event) throws numeroParException, NumberFormatException {
		try {
			cuadrado.esImpar(TxtTamanio.getText());
			TxtTamanio.setVisible(false);
			LabelTamanioFijo1.setVisible(false);
			armar.setVisible(true);
			opcionesBox.setVisible(true);
			armar.setDisable(false);
			opcionesBox.setDisable(false);
			LabelTamanioFijo2.setVisible(true);
			LabelTamanioVariable.setVisible(true);
			LabelTamanioVariable.setText(cuadrado.getTamañoCuadrado() + " x " + cuadrado.getTamañoCuadrado());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString(), null, JOptionPane.ERROR_MESSAGE);
		}

	}

	public void armar(ActionEvent event) {
		opcionesBox.setDisable(true);
		opcionesBox.setVisible(false);
		armar.setVisible(false);
		armar.setDisable(true);
		LabelOrdenFijo.setVisible(true);
		LabelOrdenVariable.setVisible(true);
		LabelOrdenVariable.setText(opcionesBox.getValue());
		crearGrid(cuadrado.getTamañoCuadrado());

	}

	private void crearGrid(int tamaño) {
		LabelConstanteMagicaVariable.setText(cuadrado.constanteMagica(tamaño) + "");
		LabelConstanteMagicaVariable.setVisible(true);
		LabelConstanteMagicaFija.setVisible(true);
		limpiarGrid();
		grid.setVisible(true);

		for (int i = 0; i < tamaño; i++) {
			RowConstraints row = new RowConstraints(cuadrado.tamañoCelda);
			ColumnConstraints col = new ColumnConstraints(cuadrado.tamañoCelda);
			grid.getColumnConstraints().add(col);
			grid.getRowConstraints().add(row);
		}
		agregarBotones(cuadrado.tamañoCelda);
	}

	private void limpiarGrid() {
		while (grid.getRowConstraints().size() > 0) {
			grid.getRowConstraints().remove(0);
		}
		while (grid.getColumnConstraints().size() > 0) {
			grid.getColumnConstraints().remove(0);
		}
	}

	private void agregarBotones(double tamaño) {
		int contador = 0;
		int n = grid.getRowConstraints().size();
		int abrir1 = cuadrado.getTamañoCuadrado() / 2;
		int abrir2 = cuadrado.getTamañoCuadrado() - 1;

		buttons = new Button[n][n];

		for (int i = 0; i < n; i++) {

			for (int j = 0; j < n; j++) {

				buttons[i][j] = new Button();
				buttons[i][j].setPrefSize(tamaño, tamaño);
				buttons[i][j].setId(j + "," + i);
				buttons[i][j].setDisable(true);
				final int x = j;
				final int y = i;
				buttons[i][j].setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if (cuadrado.orga == false) {
							cuadrado.organizarValores(opcionesBox.getValue(), buttons[y][x].getId());
							bloqueaBotones();
							cuadradoImpar();
						} else {
							cuadrado.coordenadas(grid.getRowIndex(buttons[y][x]), grid.getColumnIndex(buttons[y][x]));
							cambiarColor();
							ponerValorLabel();
							bloqueaBotones();
						}

					}
				});
				grid.add(buttons[i][j], i, j);
				contador++;
			}

		}

		if (opcionesBox.getValue().equals("NE")) {
			buttons[abrir1][0].setDisable(false);
			buttons[abrir1][0].setId("NEU");
			buttons[abrir2][abrir1].setDisable(false);
			buttons[abrir2][abrir1].setId("NER");

		} else if (opcionesBox.getValue().equals("NO")) {
			buttons[abrir1][0].setDisable(false);
			buttons[abrir1][0].setId("NOU");
			buttons[0][abrir1].setDisable(false);
			buttons[0][abrir1].setId("NOL");

		} else if (opcionesBox.getValue().equals("SE")) {
			buttons[abrir1][abrir2].setDisable(false);
			buttons[abrir1][abrir2].setId("SED");
			buttons[abrir2][abrir1].setDisable(false);
			buttons[abrir2][abrir1].setId("SER");

		} else {
			buttons[abrir1][abrir2].setDisable(false);
			buttons[abrir1][abrir2].setId("SOD");
			buttons[0][abrir1].setDisable(false);
			buttons[0][abrir1].setId("SOL");

		}

	}

	private void bloqueaBotones() {
		for (int i = 0; i <= cuadrado.tamaño * cuadrado.tamaño; i++) {
			grid.getChildren().get(i).setDisable(true);

		}
	}

	private void desbloquearBotones() {
		for (int i = 0; i <= cuadrado.tamaño * cuadrado.tamaño; i++) {
			grid.getChildren().get(i).setDisable(false);

		}
	}

	public void cuadradoImpar() {
		Organizar();

		int valores[][] = cuadrado.getCuadro();

		for (int i = 0; i < valores.length; i++) {
			for (int j = 0; j < valores[i].length; j++) {
				Button b = buttons[i][j];
				b.setText(valores[j][i] + "");
			}
		}

	}

	public void Organizar() {
		cuadrado.cuadradoOrganizado(true);
		desbloquearBotones();
	}

	public void cambiarColor() {
		String[] cruz = cuadrado.getCruz();
		int x;
		int y;
		for (int i = 0; i < cruz.length; i++) {
			y = Character.getNumericValue(cruz[i].charAt(0));
			x = Character.getNumericValue(cruz[i].charAt(1));
			grid.getChildren().get((x * cuadrado.tamaño) + y + 1).setStyle("-fx-background-color: Blue");
		}

	}

	public void darValores() {
		String[] cruz = cuadrado.getCruz();
		int fila;
		int columna;

		for (int i = 0; i < cruz.length; i++) {
			fila = Character.getNumericValue(cruz[i].charAt(0));
			columna = Character.getNumericValue(cruz[i].charAt(1));
			grid.getChildren().get((columna * cuadrado.tamaño) + fila + 1);
		}
		for (int i = 0; i < cruz.length; i++) {
			fila = Character.getNumericValue(cruz[i].charAt(0));
			columna = Character.getNumericValue(cruz[i].charAt(1));
			grid.getChildren().get((columna * cuadrado.tamaño) + fila + 1);
		}

	}

	public void ponerValorLabel() {
		LabelValorColumnaFijo.setVisible(true);
		LabelValorColumnaVariable.setVisible(true);
		LabelValorFilaFijo.setVisible(true);
		LabelValorFilaVariable.setVisible(true);
		LabelValorColumnaVariable.setText(cuadrado.constanteMagica + "");
		LabelValorFilaVariable.setText(cuadrado.constanteMagica + "");

	}

}
