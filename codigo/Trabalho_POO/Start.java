package Trabalho_POO;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Trabalho_POO.Exceptions.MediaNotFoundException;
import Trabalho_POO.enums.GenerosEnum;
import Trabalho_POO.enums.IdiomasEnum;
import Trabalho_POO.models.Comentario;
import Trabalho_POO.models.Midia;

public class Start {
    /**
     * Método para limpar tela
     */
    private static void ClearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Método de exibição dos generos
     * 
     */
    public static void PrintEnumsGenero() {
        System.out.println("1 - Ação");
        System.out.println("2 - Anime");
        System.out.println("3 - Aventura");
        System.out.println("4 - Comédia");
        System.out.println("5 - Documentário");
        System.out.println("6 - Drama");
        System.out.println("7 - Policial");
        System.out.println("8 - Romance");
        System.out.println("9 - Suspense");
    }

    /**
     * Método de exibição dos idiomas
     */
    public static void PrintEnumsIdioma() {
        System.out.println("1 - Inglês");
        System.out.println("2 - Português");
        System.out.println("3 - Francês");
        System.out.println("4 - Alemão");
        System.out.println("5 - Chinês");
        System.out.println("6 - Espanhol");
        System.out.println("7 - Italiano");
    }

    /**
     * Método de exibição de comentários
     * 
     * @param midia
     * @return void
     */
    public static void PrintComentarios(Midia midia) {
        ArrayList<Comentario> comentarios = midia.Comentarios();

        if (comentarios.size() > 0) {
            String mensagem = "Comentários da mídia: " + midia.titulo;
            System.out.println(mensagem);
            System.out.println();

            for (Comentario comentario : comentarios) {
                System.out.println(comentario.userLogin + ": " + comentario.comentario);
                System.out.println();
            }
        } else {
            String mensagem = "Nenhum comentário ainda para a mídia: " + midia.titulo;
            System.out.println(mensagem);
        }

    }

    /**
     * Método de exibiçào de uma mídia
     * 
     * @param midia
     * @return void
     */
    public static void PrintMidia(Cliente clienteAtual, Midia midia) {
        if (clienteAtual.EhEspecialista()) {
            if (midia.getClass().getName().equals("Trabalho_POO.Serie")) {
                Serie serie = (Serie) midia;
                System.out.println("Titulo: " + serie.titulo);
                System.out.println("Gêneros: " + serie.generos[0] + ", " + serie.generos[1]);
                System.out.println("Idiomas: " + serie.idiomas[0] + ", " + serie.idiomas[1]);
                System.out.println("Lançamento: " + (serie.EhLancamento() ? "Sim" : "Não"));
                System.out.println("Avaliação: "
                        + (serie.Avaliacoes().size() > 0 ? serie.MediaDeAvaliacoes() + "%" : "Ainda não avaliado"));

            } else {
                Filme filme = (Filme) midia;
                System.out.println("Titulo: " + filme.titulo);
                System.out.println("Duração: " + filme.duracao);
                System.out.println("Gêneros: " + filme.generos[0] + ", " + filme.generos[1]);
                System.out.println("Idiomas: " + filme.idiomas[0] + ", " + filme.idiomas[1]);
                System.out.println("Lançamento: " + (filme.EhLancamento() ? "Sim" : "Não"));
                System.out.println("Avaliação: "
                        + (filme.Avaliacoes().size() > 0 ? filme.MediaDeAvaliacoes() + "%" : "Ainda não avaliado"));

            }
            System.out.println("-------------------------------------------------------");
        } else {
            if (!midia.EhLancamento()) {
                if (midia instanceof Serie) {
                    Serie serie = (Serie) midia;
                    System.out.println("Titulo: " + serie.titulo);
                    System.out.println("Gêneros: " + serie.generos[0] + ", " + serie.generos[1]);
                    System.out.println("Idiomas: " + serie.idiomas[0] + ", " + serie.idiomas[1]);
                    System.out.println("Lançamento: " + (serie.EhLancamento() ? "Sim" : "Não"));
                    System.out.println("Avaliação: "
                            + (serie.Avaliacoes().size() > 0.0 ? serie.MediaDeAvaliacoes() + "%"
                                    : "Ainda não avaliado"));

                } else {
                    Filme filme = (Filme) midia;
                    System.out.println("Titulo: " + filme.titulo);
                    System.out.println("Duração: " + filme.duracao + " minutos");
                    System.out.println("Gêneros: " + filme.generos[0] + ", " + filme.generos[1]);
                    System.out.println("Idiomas: " + filme.idiomas[0] + ", " + filme.idiomas[1]);
                    System.out.println("Lançamento: " + (filme.EhLancamento() ? "Sim" : "Não"));
                    System.out.println("Avaliação: "
                            + (filme.Avaliacoes().size() > 0 ? filme.MediaDeAvaliacoes() + "%" : "Ainda não avaliado"));

                }
                System.out.println("-------------------------------------------------------");
            }

        }
    }

    /**
     * Método para mostrar ao usuário todas as opções do que pode ser feito em nosso
     * programa
     * 
     * @param service
     * @param ent
     */
    private static void Menu(ServicoStreaming service, Scanner ent) {
        ClearScreen();

        boolean logado = service.IsLogged();
        System.out.println();

        while (true) {
            try {
                if (logado) {
                    System.out.println();
                    String primeiroNome = service.GetCliente().Nome().split(" ")[0];
                    if (primeiroNome == null) {
                        logado = false;
                        continue;
                    }
                    System.out.println("Bem vindo ao serviço de streaming - " + primeiroNome);
                    int opt = -1;
                    System.out.println();
                    System.out.println("1 - Ver catálogo");
                    System.out.println("2 - Assistidas");
                    System.out.println("3 - Assistir futuramente");
                    System.out.println("4 - Perfil de usuário");
                    System.out.println("5 - Deslogar");

                    System.out.println();
                    System.out.print("Sua opção: ");
                    try {
                        int newOpt = ent.nextInt();
                        opt = newOpt;

                    } catch (InputMismatchException e) {
                        System.out.println("Opção inválida");
                        System.out.println();
                    }
                    ent.nextLine();
                    System.out.println();

                    if (opt == 1) {

                        System.out.println("1 - Ver todo o catálogo");
                        System.out.println("2 - Buscar por nome");
                        System.out.println("3 - Buscar por gênero");
                        System.out.println("4 - Buscar por idioma");
                        System.out.println("5 - Cancelar");
                        System.out.println();
                        System.out.print("Sua opção: ");
                        int optVerCatalogo = ent.nextInt();
                        ent.nextLine();
                        System.out.println();
                        ClearScreen();

                        if (optVerCatalogo == 1) {

                            ArrayList<Midia> catalogo = service.Catalogo();
                            for (Midia midia : catalogo) {
                                PrintMidia(service.GetCliente(), midia);
                            }
                        } else if (optVerCatalogo == 2) {

                            System.out.print("Digite o título que deseja buscar: ");
                            String titulo = ent.nextLine();

                            try {
                                Midia midia = service.findMediaByTitle(titulo, service.Catalogo());

                                PrintMidia(service.GetCliente(), midia);
                                System.out.println();

                                System.out.println("1 - Ver comentários");
                                System.out.println("2 - Marcar como assistida");
                                System.out.println("3 - Marcar para assistir futuramente");
                                System.out.println("4 - Desmarcar para assistir futuramente");
                                System.out.println("5 - Avaliar");
                                System.out.println("6 - Comentar");
                                System.out.println("7 - Cancelar");
                                System.out.println();
                                System.out.print("Sua opção: ");

                                int optMidia = ent.nextInt();
                                ent.nextLine();
                                ClearScreen();

                                if (optMidia == 1) {
                                    try {
                                        PrintComentarios(midia);

                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                        continue;
                                    }
                                } else if (optMidia == 2) {
                                    try {
                                        service.AdicionarAssistida(midia);

                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                        continue;
                                    }
                                } else if (optMidia == 3) {
                                    try {
                                        service.AdicionarMidiaFutura(midia);

                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                        continue;
                                    }
                                } else if (optMidia == 4) {
                                    try {
                                        service.RemoverMidiaFutura(midia);

                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                        continue;
                                    }
                                } else if (optMidia == 5) {
                                    try {
                                        service.AdicionarAvaliacao(midia, ent);
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                        continue;
                                    }
                                } else if (optMidia == 6) {
                                    try {
                                        service.AdicionarComentario(midia, ent);

                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                        continue;
                                    }
                                } else if (optMidia == 7) {

                                    continue;
                                } else {
                                    System.out.println("Opção inválida");

                                    continue;
                                }
                            } catch (MediaNotFoundException e) {
                                System.out.println(e.getMessage());
                                continue;
                            } catch (Exception e) {

                                continue;
                            } finally {
                                opt = -1;
                            }

                        } else if (optVerCatalogo == 3) {
                            System.out.println();
                            PrintEnumsGenero();
                            System.out.println();
                            System.out.print("Sua opção: ");
                            int optGeneros = ent.nextInt();
                            ent.nextLine();
                            ClearScreen();

                            String genero = GenerosEnum.values()[optGeneros - 1].toString();
                            try {
                                ArrayList<Midia> midiasComGeneroEspecifico = service.findMediasByGender(genero,
                                        service.Catalogo());
                                for (Midia midia : midiasComGeneroEspecifico) {
                                    PrintMidia(service.GetCliente(), midia);
                                }

                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                                continue;
                            }

                        } else if (optVerCatalogo == 4) {
                            System.out.println();
                            PrintEnumsIdioma();
                            System.out.println();
                            System.out.print("Sua opção: ");
                            int optIdiomas = ent.nextInt();
                            ent.nextLine();
                            ClearScreen();

                            String idioma = IdiomasEnum.values()[optIdiomas - 1].toString();
                            try {
                                ArrayList<Midia> midiasComGeneroEspecifico = service.findMediaByLanguage(idioma,
                                        service.Catalogo());
                                for (Midia midia : midiasComGeneroEspecifico) {
                                    PrintMidia(service.GetCliente(), midia);
                                }

                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                                continue;
                            }
                        } else if (optVerCatalogo == 5) {

                        } else {
                            System.out.println("Opção inválida");

                            continue;
                        }
                    } else if (opt == 2) {

                        ArrayList<Midia> midiasFuturas = service.Assistida();

                        if (midiasFuturas.size() > 0) {
                            System.out.println("1 - Ver todas");
                            System.out.println("2 - Buscar por nome");
                            System.out.println("3 - Buscar por gênero");
                            System.out.println("4 - Buscar por idioma");

                            System.out.println();
                            System.out.print("Sua opção: ");
                            int optFuturas = ent.nextInt();
                            ent.nextLine();
                            ClearScreen();

                            if (optFuturas == 1) {
                                for (Midia midia : midiasFuturas) {
                                    PrintMidia(service.GetCliente(), midia);
                                }
                            } else if (optFuturas == 2) {

                                System.out.print("Digite o título que deseja buscar: ");
                                String titulo = ent.nextLine();

                                try {
                                    Midia midia = service.findMediaByTitle(titulo, service.Assistida());
                                    if (midia != null) {
                                        PrintMidia(service.GetCliente(), midia);
                                        System.out.println();

                                        System.out.println("1 - Ver comentários");
                                        System.out.println("2 - Marcar para assistir futuramente");
                                        System.out.println("3 - Desmarcar para assistir futuramente");
                                        System.out.println("4 - Avaliar");
                                        System.out.println("5 - Comentar");
                                        System.out.println("6 - Cancelar");
                                        System.out.println();
                                        System.out.print("Sua opção: ");

                                        int optMidia = ent.nextInt();
                                        ent.nextLine();
                                        ClearScreen();

                                        if (optMidia == 1) {
                                            try {
                                                PrintComentarios(midia);

                                            } catch (Exception e) {
                                                System.out.println(e.getMessage());
                                                continue;
                                            }
                                        } else if (optMidia == 2) {
                                            try {
                                                service.AdicionarMidiaFutura(midia);
                                            } catch (Exception e) {
                                                System.out.println(e.getMessage());
                                                continue;
                                            }
                                        } else if (optMidia == 3) {
                                            try {
                                                service.RemoverMidiaFutura(midia);

                                            } catch (Exception e) {
                                                System.out.println(e.getMessage());
                                                continue;
                                            }
                                        } else if (optMidia == 4) {
                                            try {
                                                service.AdicionarAvaliacao(midia, ent);

                                            } catch (Exception e) {
                                                System.out.println(e.getMessage());
                                                System.out.println();
                                            }
                                        } else if (optMidia == 5) {
                                            try {
                                                service.AdicionarComentario(midia, ent);
                                            } catch (Exception e) {
                                                System.out.println(e.getMessage());
                                            }

                                        } else if (optMidia == 6) {
                                            continue;
                                        } else {
                                            System.out.println("Opção inválida");
                                            continue;
                                        }

                                    } else {
                                        System.out.println("Nenhum filme encontrado com esse título");
                                    }

                                } catch (MediaNotFoundException e) {
                                    System.out.println(e.getMessage());
                                    continue;
                                }
                            } else if (optFuturas == 3) {
                                ArrayList<Midia> MidiasFuturas = service.Futuras();
                                System.out.println();
                                PrintEnumsGenero();
                                System.out.println();
                                System.out.print("Sua opção: ");
                                int optGeneros = ent.nextInt();
                                ent.nextLine();
                                ClearScreen();
                                String genero = GenerosEnum.values()[optGeneros - 1].toString();
                                try {
                                    ArrayList<Midia> midiasComGeneroEspecifico = service.findMediasByGender(genero,
                                            MidiasFuturas);
                                    for (Midia midia : midiasComGeneroEspecifico) {
                                        PrintMidia(service.GetCliente(), midia);
                                    }

                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                    continue;
                                }
                            } else if (optFuturas == 4) {
                                ArrayList<Midia> MidiasFuturas = service.Futuras();
                                System.out.println();
                                PrintEnumsIdioma();
                                System.out.println();
                                System.out.print("Sua opção: ");
                                int optIdiomas = ent.nextInt();
                                ent.nextLine();
                                ClearScreen();
                                String idioma = IdiomasEnum.values()[optIdiomas - 1].toString();
                                try {
                                    ArrayList<Midia> midiasComGeneroEspecifico = service.findMediaByLanguage(idioma,
                                            MidiasFuturas);
                                    for (Midia midia : midiasComGeneroEspecifico) {
                                        PrintMidia(service.GetCliente(), midia);
                                    }

                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                    continue;
                                }
                            }
                        } else {
                            System.out.println("Nenhum filme/série assistido.");
                            continue;
                        }

                    } else if (opt == 3) {

                        ArrayList<Midia> midiasFuturas = service.Futuras();
                        if (midiasFuturas.size() > 0) {
                            System.out.println("1 - Ver todas");
                            System.out.println("2 - Buscar por nome");
                            System.out.println("3 - Buscar por gênero");
                            System.out.println("4 - Buscar por idioma");

                            System.out.println();
                            System.out.print("Sua opção: ");
                            int optFuturas = ent.nextInt();
                            ent.nextLine();
                            ClearScreen();
                            if (optFuturas == 1) {
                                for (Midia midia : midiasFuturas) {
                                    PrintMidia(service.GetCliente(), midia);
                                }
                            } else if (optFuturas == 2) {

                                System.out.print("Digite o título que deseja buscar: ");
                                String titulo = ent.nextLine();

                                try {
                                    Midia midia = service.findMediaByTitle(titulo, midiasFuturas);

                                    PrintMidia(service.GetCliente(), midia);
                                    System.out.println();

                                    System.out.println("1 - Ver comentários");
                                    System.out.println("2 - Desmarcar para assistir futuramente");
                                    System.out.println("3 - Avaliar");
                                    System.out.println("4 - Comentar");
                                    System.out.println("5 - Cancelar");
                                    System.out.println();
                                    System.out.print("Sua opção: ");

                                    int optMidia = ent.nextInt();
                                    ent.nextLine();
                                    ClearScreen();
                                    if (optMidia == 1) {
                                        try {
                                            PrintComentarios(midia);
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                            continue;
                                        }
                                    } else if (optMidia == 2) {
                                        try {
                                            service.RemoverMidiaFutura(midia);
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                            continue;
                                        }
                                    } else if (optMidia == 3) {
                                        try {
                                            service.AdicionarAvaliacao(midia, ent);

                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                            System.out.println();
                                        }
                                    } else if (optMidia == 4) {
                                        try {
                                            service.AdicionarComentario(midia, ent);
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }

                                    } else if (optMidia == 5) {

                                        continue;
                                    } else {
                                        System.out.println("Opção inválida");

                                        continue;
                                    }

                                } catch (MediaNotFoundException e) {
                                    System.out.println(e.getMessage());
                                    continue;
                                }
                            } else if (optFuturas == 3) {
                                ArrayList<Midia> MidiasFuturas = service.Futuras();
                                System.out.println();
                                PrintEnumsGenero();
                                System.out.println();
                                System.out.print("Sua opção: ");
                                int optGeneros = ent.nextInt();
                                ent.nextLine();
                                ClearScreen();
                                String genero = GenerosEnum.values()[optGeneros - 1].toString();
                                try {
                                    ArrayList<Midia> midiasComGeneroEspecifico = service.findMediasByGender(genero,
                                            MidiasFuturas);
                                    for (Midia midia : midiasComGeneroEspecifico) {
                                        PrintMidia(service.GetCliente(), midia);
                                    }

                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                    continue;
                                }
                            } else if (optFuturas == 4) {
                                ArrayList<Midia> MidiasFuturas = service.Futuras();
                                System.out.println();
                                PrintEnumsIdioma();
                                System.out.println();
                                System.out.print("Sua opção: ");
                                int optIdiomas = ent.nextInt();
                                ent.nextLine();
                                ClearScreen();
                                String idioma = IdiomasEnum.values()[optIdiomas - 1].toString();
                                try {
                                    ArrayList<Midia> midiasComGeneroEspecifico = service.findMediaByLanguage(idioma,
                                            MidiasFuturas);
                                    for (Midia midia : midiasComGeneroEspecifico) {
                                        PrintMidia(service.GetCliente(), midia);
                                    }

                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                    continue;
                                }
                            }
                        } else {
                            System.out.println("Nenhum filme/série marcado para assistir futuramente ainda.");
                            continue;
                        }

                    } else if (opt == 4) {

                        service.PrintUsuario();
                        System.out.println();
                        System.out.println("1 - Alterar login");
                        System.out.println("2 - Alterar senha");
                        System.out.println("3 - Alterar nome");
                        System.out.println("4 - Apagar conta");
                        System.out.println("5 - Cancelar");

                        System.out.print("Sua opção: ");
                        int optUser = ent.nextInt();
                        ent.nextLine();
                        ClearScreen();
                        if (optUser == 1) {
                            try {
                                System.out.print("Digite seu novo login: ");
                                String novoLogin = ent.nextLine();
                                service.AlterarLogin(novoLogin);
                                service.Logout();
                                logado = service.IsLogged();
                                continue;
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        } else if (optUser == 2) {
                            try {
                                System.out.print("Digite sua senha antiga: ");
                                String senhaAntiga = ent.nextLine();

                                System.out.print("Digite sua nova senha: ");
                                String novaSenha = ent.nextLine();
                                System.out.print("Confirme sua nova senha: ");
                                String confirmacaoNovaSenha = ent.nextLine();

                                boolean senhaCorreta = service.GetCliente().VerificarUsuario(senhaAntiga);

                                if (!senhaCorreta) {
                                    System.out.println("Senha antiga incorreta.");
                                    continue;
                                }
                                if (!novaSenha.equalsIgnoreCase(confirmacaoNovaSenha)) {
                                    System.out.println("As senhas divergem entre si.");
                                    continue;
                                }
                                service.AlterarSenha(confirmacaoNovaSenha);

                                service.Logout();
                                logado = service.IsLogged();
                                continue;
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                                continue;
                            }

                        } else if (optUser == 3) {
                            try {
                                System.out.print("Digite seu novo nome: ");
                                String novoNome = ent.nextLine();
                                service.AlterarNome(novoNome);

                                service.Logout();
                                logado = service.IsLogged();
                                continue;
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                                continue;
                            }

                        } else if (optUser == 4) {
                            try {
                                System.out.print("Digite sua senha: ");
                                String senha = ent.nextLine();
                                boolean senhaCorreta = service.GetCliente().VerificarUsuario(senha);

                                if (senhaCorreta) {
                                    service.DeleteUser();
                                    service.Logout();
                                    logado = service.IsLogged();
                                    continue;
                                } else {
                                    System.out.println("Senha inválida");
                                    continue;
                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                                continue;
                            }

                        } else if (optUser == 5)
                            continue;
                        else {
                            System.out.println("Opção inválida");
                            System.out.println();
                            continue;
                        }
                    } else if (opt == 5) {
                        try {
                            service.Logout();
                            logado = service.IsLogged();

                            continue;
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            continue;
                        }

                    }
                } else {
                    String mensagem = "Bem vindo ao serviço de streaming";
                    System.out.println(mensagem);
                    System.out.println();
                    System.out.println("1 - Registrar");
                    System.out.println("2 - Logar");
                    System.out.println("3 - Finalizar");
                    System.out.println();
                    System.out.print("Sua opção: ");
                    int opt = ent.nextInt();
                    ent.nextLine();
                    System.out.println();

                    if (opt == 1) {
                        try {
                            System.out.print("Login: ");
                            String login = ent.nextLine();
                            System.out.print("Senha: ");
                            String senha = ent.nextLine();
                            System.out.print("Nome: ");
                            String nome = ent.nextLine();

                            service.Registrar(login, senha, nome);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            continue;
                        }

                    } else if (opt == 2) {
                        try {
                            System.out.print("Login: ");
                            String login = ent.nextLine();
                            System.out.print("Senha: ");
                            String senha = ent.nextLine();

                            service.Login(login, senha);

                            logado = service.IsLogged();
                            if (logado) {

                                System.out.println("Usuário logado com sucesso!");
                                System.out.println();
                            } else {

                                System.out.println("Usuário ou senha incorretos.");
                                System.out.println();
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            continue;
                        }

                    } else if (opt == 3) {
                        try {
                            service.GerarRelatorio();
                            System.out.println("Serviço de streaming finalizado com sucesso.");
                            break;
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            break;
                        }

                    } else {
                        System.out.println("Opção inválida");

                        continue;
                    }
                }

            } finally {
                ent.reset();
            }
        }
    }

    public static void main(String[] args) {
        String pathClienteData = "C:\\Users\\gdavi\\Onedrive\\Área de Trabalho\\POO_Matheus\\poo_tp_noite-grupo-mcrr\\codigo\\src\\Trabalho_POO\\Trabalho_POO\\lib\\POO_Espectadores.csv";
        String pathSeriesData = "C:\\Users\\gdavi\\Onedrive\\Área de Trabalho\\POO_Matheus\\poo_tp_noite-grupo-mcrr\\codigo\\src\\Trabalho_POO\\Trabalho_POO\\lib\\POO_Series.csv";
        String pathFilmesData = "C:\\Users\\gdavi\\Onedrive\\Área de Trabalho\\POO_Matheus\\poo_tp_noite-grupo-mcrr\\codigo\\src\\Trabalho_POO\\Trabalho_POO\\lib\\POO_Filmes.csv";
        String pathAudienciaData = "C:\\Users\\gdavi\\Onedrive\\Área de Trabalho\\POO_Matheus\\poo_tp_noite-grupo-mcrr\\codigo\\src\\Trabalho_POO\\Trabalho_POO\\lib\\POO_Audiencia.csv";
        try (Scanner ent = new Scanner(System.in)) {
            ServicoStreaming service = new ServicoStreaming(pathClienteData, pathSeriesData, pathFilmesData,
                    pathAudienciaData);

            Menu(service, ent);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}