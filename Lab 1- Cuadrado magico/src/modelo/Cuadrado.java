package modelo;

import exception.numeroParException;

public class Cuadrado {

	public double tamañoCelda;
	public int tamaño;
	public boolean orga;
	private int tamañoCuadrado;

	public int x;
	public int y;
	public int iniX;
	public int iniY;

	public int constanteMagica;

	public String cruz[];

	public int numeroDelBoton;

	private int[][] cuadro;

	public Cuadrado() {
		// TODO Auto-generated constructor stub
	}

	public void esImpar(String dato) throws numeroParException, NumberFormatException {
		try {
			int valor = Integer.parseInt(dato);
			if (valor % 2 == 0) {
				throw new numeroParException();

			} else {
				tamañoCuadrado = valor;
				tamañoCelda = 350 / tamañoCuadrado;
			}

		} catch (NumberFormatException e) {
			throw new NumberFormatException("Informacion no valida, intente otra vez.");
		}
	}

	public void cuadradoOrganizado(boolean organizado) {
		orga = organizado;
	}

	public int constanteMagica(int tamaño) {
		int constante = 0;
		constante = (tamaño * ((tamaño * tamaño) + 1)) / 2;

		constanteMagica = constante;
		return constante;

	}

	public void coordenadas(int row, int column) {
		int contador = 0;
		cruz = new String[(tamaño * 2) - 1];
		x = row;
		y = column;
		for (int i = 0; i < tamaño; i++) {
			cruz[contador] = x + "" + i;
			contador++;
		}
		for (int j = 0; j < tamaño; j++) {
			if (j != x) {
				cruz[contador] = j + "" + y;
				contador++;
			}
		}
		for (int i = 0; i < cruz.length; i++) {
		}
	}

	public void organizarValores(String orden, String inicio) {
		int contador = 1;
		int fila = 0;
		int columna = 0;
		cuadro = new int[tamañoCuadrado][tamañoCuadrado];

		if (orden.equals("NE")) {

			if (inicio.equals("NEU")) {
				fila = 0;
				columna = tamañoCuadrado / 2;
				for (int i = 0; i < cuadro.length * cuadro.length; i++) {
					cuadro[fila][columna] = contador++;
					if ((fila - 1) < 0) {
						if ((columna - 1) < 0) {
							fila += 1;
						} else {
							fila = cuadro.length - 1;
							columna -= 1;
						}
					} else if ((columna - 1) < 0) {
						fila -= 1;
						columna = cuadro.length - 1;
						if ((fila - 1) < 0) {
							fila += 1;
						}
					} else if (cuadro[fila - 1][columna - 1] == 0) {
						fila -= 1;
						columna -= 1;
					} else {
						fila += 1;
					}
				}
			} else {
				fila = tamañoCuadrado / 2;
				columna = cuadro.length - 1;
				for (int i = 0; i < cuadro.length * cuadro.length; i++) {
					cuadro[fila][columna] = contador++;
					if ((fila - 1) < 0) {
						if ((fila - 1) < 0 && (columna + 1) == cuadro.length) {
							columna -= 1;
						} else {
							fila = cuadro.length - 1;
							columna += 1;
						}
					} else if ((columna + 1) == cuadro.length) {
						if ((fila - 1) < 0 && (columna + 1) == cuadro.length) {
							columna -= 1;
						} else {
							fila -= 1;
							columna = 0;
						}
					} else if (cuadro[fila - 1][columna + 1] == 0) {
						fila -= 1;
						columna += 1;
					} else {
						columna -= 1;
					}
				}
			}

		} else if (orden.equals("NO")) {
			if (inicio.equals("NOU")) {
				fila = 0;
				columna = tamañoCuadrado / 2;
				for (int i = 0; i < cuadro.length * cuadro.length; i++) {
					cuadro[fila][columna] = contador++;
					if ((fila - 1) < 0) {
						if ((fila - 1) < 0 && (columna - 1) < 0) {
							fila += 1;
						} else {
							fila = cuadro.length - 1;
							columna -= 1;

						}

					} else if ((columna - 1) < 0) {
						fila -= 1;
						columna = cuadro.length - 1;
						if ((fila - 1) < 0 && (columna - 1) < 0) {
							fila += 1;
						}
					} else if (cuadro[fila - 1][columna - 1] == 0) {
						fila -= 1;
						columna -= 1;
					} else {
						fila += 1;
					}
				}

			} else {
				fila = tamañoCuadrado / 2;
				columna = 0;
				for (int i = 0; i < cuadro.length * cuadro.length; i++) {
					cuadro[fila][columna] = contador++;
					if ((fila - 1) < 0) {
						if ((fila - 1) < 0 && (columna - 1) < 0) {
							columna += 1;
						} else {
							fila = cuadro.length - 1;
							columna -= 1;
						}

					} else if ((columna - 1) < 0) {
						fila -= 1;
						columna = cuadro.length - 1;
						if ((fila - 1) < 0 && (columna - 1) < 0) {
							columna += 1;
						}
					} else if (cuadro[fila - 1][columna - 1] == 0) {
						fila -= 1;
						columna -= 1;
					} else {
						columna += 1;
					}
				}

			}

		} else if (orden.equals("SE")) {
			if (inicio.equals("SED")) {
				columna = (tamañoCuadrado - 1) / 2;
				fila = cuadro.length - 1;
				for (int i = 0; i < cuadro.length * cuadro.length; i++) {
					cuadro[fila][columna] = contador++;
					if ((fila + 1) == cuadro.length) {
						if ((fila + 1) == cuadro.length && (columna + 1) == cuadro.length) {
							fila -= 1;
						} else {
							fila = 0;
							columna += 1;
						}
					} else if ((columna + 1) == cuadro.length) {
						fila += 1;
						columna = 0;
						if ((fila + 1) == cuadro.length && (columna + 1) == cuadro.length) {
							fila -= 1;
						}
					} else if (cuadro[fila + 1][columna + 1] == 0) {
						fila += 1;
						columna += 1;
					} else {
						fila -= 1;
					}
				}

			} else {
				fila = tamañoCuadrado / 2;
				columna = cuadro.length - 1;

				for (int i = 0; i < cuadro.length * cuadro.length; i++) {
					cuadro[fila][columna] = contador++;
					if ((fila + 1) == cuadro.length) {
						if ((fila + 1) == cuadro.length && (columna + 1) == cuadro.length) {
							columna -= 1;
						} else {
							fila = 0;
							columna += 1;
						}
					} else if ((columna + 1) == cuadro.length) {
						if ((fila + 1) == cuadro.length && (columna + 1) == cuadro.length) {
							columna -= 1;
						} else {
							fila += 1;
							columna = 0;
						}
					} else if (cuadro[fila + 1][columna + 1] == 0) {
						fila += 1;
						columna += 1;
					} else {
						columna -= 1;
					}
				}

			}

		} else {
			if (inicio.equals("SOD")) {
				columna = (tamañoCuadrado - 1) / 2;
				fila = cuadro.length - 1;
				for (int i = 0; i < cuadro.length * cuadro.length; i++) {
					cuadro[fila][columna] = contador++;
					if ((fila + 1) == cuadro.length) {
						if ((fila + 1) == cuadro.length && (columna - 1) < 0) {
							fila -= 1;
						} else {
							fila = 0;
							columna -= 1;
						}
					} else if ((columna - 1) < 0) {
						fila += 1;
						columna = cuadro.length - 1;
						if ((fila + 1) == cuadro.length && (columna - 1) < 0) {
							fila -= 1;
						}
					} else if (cuadro[fila + 1][columna - 1] == 0) {
						fila += 1;
						columna -= 1;
					} else {
						fila -= 1;
					}
				}

			} else {
				fila = tamañoCuadrado / 2;
				columna = 0;
				for (int i = 0; i < cuadro.length * cuadro.length; i++) {
					cuadro[fila][columna] = contador++;
					if ((fila + 1) == cuadro.length) {
						if ((fila + 1) == (cuadro.length) && (columna - 1) < 0) {
							columna += 1;
						} else {
							fila = 0;
							columna -= 1;
						}
					} else if ((columna - 1) < 0) {
						if ((fila + 1) == cuadro.length && (columna - 1) < 0) {
							columna += 1;
						} else {
							fila += 1;
							columna = cuadro.length - 1;
						}
					} else if (cuadro[fila + 1][columna - 1] == 0) {
						fila += 1;
						columna -= 1;
					} else {
						columna += 1;
					}
				}

			}

		}

	}

	public int[][] getCuadro() {
		return cuadro;
	}

	public void setCuadro(int[][] cuadro) {
		this.cuadro = cuadro;
	}

	public String[] getCruz() {
		return cruz;
	}

	public int getTamañoCuadrado() {
		return tamañoCuadrado;
	}

}
