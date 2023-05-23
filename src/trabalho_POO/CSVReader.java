package trabalho_POO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    
    public List<Cliente> readClientes(String arquivo) throws IOException {
        List<Cliente> clientes = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(arquivo));
        String line;

        
        reader.readLine();

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";");
            
            clientes.add(new Cliente(parts[1]));
        }

        reader.close();
        return clientes;
    }

    public List<Serie> readSeries(String arquivo) throws IOException {
        List<Serie> series = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(arquivo));
        String line;

        reader.readLine();

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";");
            series.add(new Serie(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], 10)); 
        }

        reader.close();
        return series;
    }
    
    public List<Filme> readFilmes(String arquivo) throws IOException {
        List<Filme> filmes = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(arquivo));
        String line;

        reader.readLine();

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";");
            filmes.add(new Filme(Integer.parseInt(parts[0]), parts[1], "Idioma aleatório", "Gênero aleatório", Integer.parseInt(parts[3])));
        }

        reader.close();
        return filmes;
    }

    public List<Auditoria> readAudiencia(String arquivo) throws IOException {
        List<Auditoria> auditorias = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(arquivo));
        String line;

        reader.readLine();

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";");
            auditorias.add(new Auditoria(parts[0], parts[1].charAt(0), Integer.parseInt(parts[2])));
        }

        reader.close();
        return auditorias;
    }
}

