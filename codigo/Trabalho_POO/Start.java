package Trabalho_POO;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Trabalho_POO.Exceptions.MediaNotFoundException;
import Trabalho_POO.enums.GenerosEnum;
import Trabalho_POO.enums.IdiomasEnum;
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

                        if (optVerCatalogo == 1) {

                            ArrayList<Midia> catalogo = service.Catalogo();
                            for (Midia midia : catalogo) {
                                service.PrintMidia(midia);
                            }
                        } else if (optVerCatalogo == 2) {

                            System.out.print("Digite o título que deseja buscar: ");
                            String titulo = ent.nextLine();

                            try {
                                Midia midia = service.findMediaByTitle(titulo, service.Catalogo());
                                service.PrintMidia(midia);
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

                                if (optMidia == 1) {
                                    try {
                                        service.PrintComentarios(midia);

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
                            GenerosEnum.PrintEnums();
                            System.out.println();
                            System.out.print("Sua opção: ");
                            int optGeneros = ent.nextInt();
                            ent.nextLine();

                            String genero = GenerosEnum.values()[optGeneros - 1].toString();
                            try {
                                ArrayList<Midia> midiasComGeneroEspecifico = service.findMediasByGender(genero,
                                        service.Catalogo());
                                for (Midia midia : midiasComGeneroEspecifico) {
                                    service.PrintMidia(midia);
                                }

                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                                continue;
                            }

                        } else if (optVerCatalogo == 4) {
                            System.out.println();
                            IdiomasEnum.PrintEnums();
                            System.out.println();
                            System.out.print("Sua opção: ");
                            int optIdiomas = ent.nextInt();
                            ent.nextLine();

                            String idioma = IdiomasEnum.values()[optIdiomas - 1].toString();
                            try {
                                ArrayList<Midia> midiasComGeneroEspecifico = service.findMediaByLanguage(idioma,
                                        service.Catalogo());
                                for (Midia midia : midiasComGeneroEspecifico) {
                                    service.PrintMidia(midia);
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

                            if (optFuturas == 1) {
                                for (Midia midia : midiasFuturas) {
                                    service.PrintMidia(midia);
                                }
                            } else if (optFuturas == 2) {

                                System.out.print("Digite o título que deseja buscar: ");
                                String titulo = ent.nextLine();

                                try {
                                    Midia midia = service.findMediaByTitle(titulo, service.Assistida());

                                    service.PrintMidia(midia);
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

                                    if (optMidia == 1) {
                                        try {
                                            service.PrintComentarios(midia);

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

                                } catch (MediaNotFoundException e) {
                                    System.out.println(e.getMessage());
                                    continue;
                                }
                            } else if (optFuturas == 3) {
                                ArrayList<Midia> MidiasFuturas = service.Futuras();
                                System.out.println();
                                GenerosEnum.PrintEnums();
                                System.out.println();
                                System.out.print("Sua opção: ");
                                int optGeneros = ent.nextInt();
                                ent.nextLine();

                                String genero = GenerosEnum.values()[optGeneros - 1].toString();
                                try {
                                    ArrayList<Midia> midiasComGeneroEspecifico = service.findMediasByGender(genero,
                                            MidiasFuturas);
                                    for (Midia midia : midiasComGeneroEspecifico) {
                                        service.PrintMidia(midia);
                                    }

                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                    continue;
                                }
                            } else if (optFuturas == 4) {
                                ArrayList<Midia> MidiasFuturas = service.Futuras();
                                System.out.println();
                                IdiomasEnum.PrintEnums();
                                System.out.println();
                                System.out.print("Sua opção: ");
                                int optIdiomas = ent.nextInt();
                                ent.nextLine();

                                String idioma = IdiomasEnum.values()[optIdiomas - 1].toString();
                                try {
                                    ArrayList<Midia> midiasComGeneroEspecifico = service.findMediaByLanguage(idioma,
                                            MidiasFuturas);
                                    for (Midia midia : midiasComGeneroEspecifico) {
                                        service.PrintMidia(midia);
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

                            if (optFuturas == 1) {
                                for (Midia midia : midiasFuturas) {
                                    service.PrintMidia(midia);
                                }
                            } else if (optFuturas == 2) {

                                System.out.print("Digite o título que deseja buscar: ");
                                String titulo = ent.nextLine();

                                try {
                                    Midia midia = service.findMediaByTitle(titulo, midiasFuturas);

                                    service.PrintMidia(midia);
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

                                    if (optMidia == 1) {
                                        try {
                                            service.PrintComentarios(midia);
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
                                GenerosEnum.PrintEnums();
                                System.out.println();
                                System.out.print("Sua opção: ");
                                int optGeneros = ent.nextInt();
                                ent.nextLine();

                                String genero = GenerosEnum.values()[optGeneros - 1].toString();
                                try {
                                    ArrayList<Midia> midiasComGeneroEspecifico = service.findMediasByGender(genero,
                                            MidiasFuturas);
                                    for (Midia midia : midiasComGeneroEspecifico) {
                                        service.PrintMidia(midia);
                                    }

                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                    continue;
                                }
                            } else if (optFuturas == 4) {
                                ArrayList<Midia> MidiasFuturas = service.Futuras();
                                System.out.println();
                                IdiomasEnum.PrintEnums();
                                System.out.println();
                                System.out.print("Sua opção: ");
                                int optIdiomas = ent.nextInt();
                                ent.nextLine();

                                String idioma = IdiomasEnum.values()[optIdiomas - 1].toString();
                                try {
                                    ArrayList<Midia> midiasComGeneroEspecifico = service.findMediaByLanguage(idioma,
                                            MidiasFuturas);
                                    for (Midia midia : midiasComGeneroEspecifico) {
                                        service.PrintMidia(midia);
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
                                    ClearScreen();
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
        String pathClienteData = "C:\\Users\\LENOVO\\Desktop\\POO\\poo_tp_noite-grupo-mcrr-master\\codigo\\Trabalho_POO\\lib\\POO_Espectadores.csv";
        String pathSeriesData = "C:\\Users\\LENOVO\\Desktop\\POO\\poo_tp_noite-grupo-mcrr-master\\codigo\\Trabalho_POO\\lib\\POO_Series.csv";
        String pathFilmesData = "C:\\Users\\LENOVO\\Desktop\\POO\\poo_tp_noite-grupo-mcrr-master\\codigo\\Trabalho_POO\\lib\\POO_Filmes.csv";
        String pathAudienciaData = "C:\\Users\\LENOVO\\Desktop\\POO\\poo_tp_noite-grupo-mcrr-master\\codigo\\Trabalho_POO\\lib\\POO_Audiencia.csv";
        try (Scanner ent = new Scanner(System.in)) {
            ServicoStreaming service = new ServicoStreaming(pathClienteData, pathSeriesData, pathFilmesData,
                    pathAudienciaData);

            Menu(service, ent);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}