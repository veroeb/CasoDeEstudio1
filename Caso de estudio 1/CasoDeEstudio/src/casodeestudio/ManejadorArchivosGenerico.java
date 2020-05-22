package casodeestudio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ManejadorArchivosGenerico {
	/**
	 * @param nombreCompletoArchivo
	 * @param listaLineasArchivo    lista con las lineas del archivo
	 * @throws IOException
	 */
	public static void escribirArchivo(String nombreCompletoArchivo, String[] listaLineasArchivo) {
		FileWriter fw;
		try {
			fw = new FileWriter(nombreCompletoArchivo, true);
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i = 0; i < listaLineasArchivo.length - 1; i++) {
				String lineaActual = listaLineasArchivo[i];
				bw.write(lineaActual + "\n"); // se agrego + " " porque sino queda cada campo en una linea nueva
			}
			String lineaActual = listaLineasArchivo[listaLineasArchivo.length - 1];
			bw.write(lineaActual);
			bw.newLine(); // se paso el newLine() para aca
			bw.close();
			fw.close();
		} catch (IOException e) {
			System.out.println("Error al escribir el archivo " + nombreCompletoArchivo);
			e.printStackTrace();
		}
	}

	public static String[] leerArchivo(String nombreCompletoArchivo) {
		FileReader fr;
		ArrayList<String> listaLineasArchivo = new ArrayList<>();
		try {
			fr = new FileReader(nombreCompletoArchivo);
			BufferedReader br = new BufferedReader(fr);
			String lineaActual = br.readLine();
			while (lineaActual != null) {
				listaLineasArchivo.add(lineaActual);
				lineaActual = br.readLine();
			}
//			System.out.println("Archivo leido satisfactoriamente");
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error al leer el archivo " + nombreCompletoArchivo);
			// e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error al leer el archivo " + nombreCompletoArchivo);
			// e.printStackTrace();
		}

		return listaLineasArchivo.toArray(new String[0]);
	}
}
