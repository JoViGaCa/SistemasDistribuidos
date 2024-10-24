/**
 * Lab0: Leitura de Base de Dados N�o-Distribuida
 * 
 * Autor: Lucio A. Rocha
 * Ultima atualizacao: 20/02/2023
 * 
 * Referencias: 
 * https://docs.oracle.com/javase/tutorial/essential/io
 * 
 */

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Random;

public class Principal_v0 {

	public final static Path path = Paths			
			.get("lab1/src/fortune-br.txt");
	private int NUM_FORTUNES = 0;

	public class FileReader {

		public int countFortunes() throws FileNotFoundException {

			int lineCount = 0;

			InputStream is = new BufferedInputStream(new FileInputStream(
					path.toString()));
			try (BufferedReader br = new BufferedReader(new InputStreamReader(
					is))) {

				String line = "";
				while (!(line == null)) {

					if (line.equals("%"))
						lineCount++;

					line = br.readLine();

				}// fim while

				System.out.println(lineCount);
			} catch (IOException e) {
				System.out.println("SHOW: Excecao na leitura do arquivo.");
			}
			return lineCount;
		}

		public void parser(HashMap<Integer, String> hm)
				throws FileNotFoundException {

			InputStream is = new BufferedInputStream(new FileInputStream(
					path.toString()));
			try (BufferedReader br = new BufferedReader(new InputStreamReader(
					is))) {

				int lineCount = 0;

				String line = "";
				while (!(line == null)) {

					if (line.equals("%"))
						lineCount++;

					line = br.readLine();
					StringBuffer fortune = new StringBuffer();
					while (!(line == null) && !line.equals("%")) {
						fortune.append(line + "\n");
						line = br.readLine();
						// System.out.print(lineCount + ".");
					}

					hm.put(lineCount, fortune.toString());
					// System.out.println(fortune.toString());

					System.out.println(lineCount);
				}// fim while

			} catch (IOException e) {
				System.out.println("SHOW: Excecao na leitura do arquivo.");
			}
		}

		public void read(HashMap<Integer, String> hm)throws FileNotFoundException {
			// criar um array de strings para armazenar as fortunas
			int max = countFortunes();
			String [] fortunas = new String[max+1];
			int count = 0;
			// tentar ler o buffer
			try(BufferedReader bf_reader = new BufferedReader(new java.io.FileReader(path.toFile()))) {
				// Criar a String para armazenar a linha
				String linha = "";
				// Percorre o arquivo
				while(linha != null){
					String fortuna = "";
					// Lê uma linha
					linha = bf_reader.readLine();
					// verifica se é nula ou querba de fortuna
					while (linha != null && !linha.equals("%")){
						// Armazena as linhas em uma fortuna e lê a próxima
						fortuna = fortuna + linha;
						linha = bf_reader.readLine();
					}
					// Salvar a fortuna
					fortunas[count] = fortuna;
					count = count+1;

				}
				
				
			} catch (IOException e) {
				// Exibir um erro
				System.out.println("Erro na leitura da linha");
			}
			
			// Exibir uma aleatória
			Random rand = new Random();
			System.out.println(max);
			int num = rand.nextInt(max);

			System.out.println("Fortuna Aleatória");
			System.out.println(fortunas[num]);


           
       }


		public void write(HashMap<Integer, String> hm)throws FileNotFoundException {
			// declara um escritor
			try (BufferedWriter bf_writer = new BufferedWriter(new FileWriter(path.toFile()))) {
				// Percorre as strings do hm
				System.out.println("Escrevendo");
                for (String value : hm.values()) {
					// Escreve as strings e quebra a linha
                    bf_writer.write(value);
                    bf_writer.newLine(); // Para separar as linhas
                }
            } catch (IOException e) {
                System.out.println("SHOW: Excecao na escrita do arquivo.");
            }
			
		}
	}

	public void iniciar() {

		FileReader fr = new FileReader();
		try {
			NUM_FORTUNES = fr.countFortunes();
			HashMap hm = new HashMap<Integer, String>();
			fr.parser(hm);
			fr.read(hm);
			fr.write(hm);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new Principal_v0().iniciar();
	}

}
