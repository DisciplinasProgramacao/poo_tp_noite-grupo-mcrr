package Trabalho_POO;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Trabalho_POO.enums.GenerosEnum;
import Trabalho_POO.enums.IdiomasEnum;
import Trabalho_POO.models.Midia;

public class Reader {
    // #region Public
    /**
     * Método que lê os dados dos clientes no csv que usuário informou, cria e
     * retorna uma lista de usuários
     * 
     * @param pathClientesData
     * @return ArrayList<Cliente>
     * @throws FileNotFoundException
     */
    public ArrayList<Cliente> ReadClients(String pathClientesData) throws FileNotFoundException {
        ArrayList<Cliente> response = new ArrayList<Cliente>();
        try (Scanner reader = new Scanner(new File(pathClientesData))) {

            String linha;
            while (reader.hasNextLine()) {
                linha = reader.nextLine();
                String[] dados = linha.split(";");
                String nome = dados[0];
                String login = dados[1];
                String senha = dados[2];

                Cliente cliente = new Cliente(nome, login, senha, false);
                response.add(cliente);
            }
            return response;
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao ler usuários, o caminho informado é inválido.");
            return response;
        } catch (Exception e) {
            return response;
        }

    }

    /**
     * Método que lê os dados de séries no csv que usuário informou, cria e retorna
     * uma lista de midias do tipo série
     * 
     * @param pathSeriesData
     * @return ArrayList<Midia>
     */
    public ArrayList<Midia> ReadSeries(String pathSeriesData) {

        ArrayList<Midia> response = new ArrayList<>();

        try (Scanner reader = new Scanner(new File(pathSeriesData))) {

            String linha;
            while (reader.hasNextLine()) {
                linha = reader.nextLine();
                String[] dados = linha.split(";");
                String idString = dados[0].replaceAll("[^0-9]", "");
                int id = Integer.parseInt(idString);
                String titulo = dados[1];
                Date dataLancamento = FormatarData(dados[2]);
                String[] generos = GenerosEnum.GenerosAleatorios();
                String[] idiomas = IdiomasEnum.IdiomasAleatorios();

                Serie serie = new Serie(id, titulo, dataLancamento, idiomas, generos, false);
                response.add(serie);
            }
            reader.close();
            return response;
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Erro ao ler séries, o caminho informado é inválido.");
            return response;
        } catch (ParseException parseException) {
            System.err.println("Erro ao converter data.");
            return response;
        } catch (Exception e) {
            return response;
        }

    }

    /**
     * Método que lê os dados de filmes do csv que o usuário informou, cria e
     * retorna uma lista de mídias do tipo filme
     * 
     * @param pathFilmesData
     * @return ArrayList<Midia>
     */
    public ArrayList<Midia> ReadFilmes(String pathFilmesData) {
        ArrayList<Midia> response = new ArrayList<>();

        try (Scanner reader = new Scanner(new File(pathFilmesData))) {

            String linha;

            while (reader.hasNextLine()) {
                linha = reader.nextLine();
                String[] dados = linha.split(";");
                int id = Integer.parseInt(dados[0].replaceAll("[^0-9]", ""));
                String titulo = dados[1];
                Date dataDeLancamento = FormatarData(dados[2]);
                int duracao = Integer.parseInt(dados[3]);
                String[] generos = GenerosEnum.GenerosAleatorios();
                String[] idiomas = IdiomasEnum.IdiomasAleatorios();

                Filme filme = new Filme(id, titulo, dataDeLancamento, idiomas, generos, duracao, false);
                response.add(filme);
            }
            return response;

        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Erro ao ler Filmes, o caminho informado é inválido.");
            return response;
        } catch (ParseException parseException) {
            System.err.println("Erro ao converter data.");
            return response;
        } catch (Exception e) {
            return response;
        }
    }

    /**
     * Método que lê os dados de audiencia do csv informado pelo usuário, cria e
     * retorna uma lista de audiência
     * 
     * @param pathAudienciaData
     * @return ArrayList<Audiencia>
     */
    public ArrayList<Audiencia> ReadAudiencia(String pathAudienciaData) {
        ArrayList<Audiencia> response = new ArrayList<Audiencia>();
        try (Scanner reader = new Scanner(new File(pathAudienciaData))) {
            String linha;

            while (reader.hasNextLine()) {
                linha = reader.nextLine();

                String[] dados = linha.split(";");
                String login = dados[0];
                String tipoDeLista = dados[1];
                int idMidia = Integer.parseInt(dados[2].replaceAll("[^0-9]", ""));

                response.add(new Audiencia(login, idMidia, tipoDeLista.charAt(0)));
            }

            return response;
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao ler Audiencia, o caminho informado é inválido.");
            return response;
        }
    }

    // #endregion Public
    // #region Private
    /**
     * Método criado para formatar as datas e retornar um Date
     * 
     * @param dataString
     * @return Date
     * @throws ParseException
     */
    private Date FormatarData(String dataString) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date data = format.parse(dataString);
        return data;
    }
    // #endregion Private
}
