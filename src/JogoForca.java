import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class JogoForca {

    static String[] fasesForca = {
            "  +---+\n" +
                    "  |   |\n" +
                    "      |\n" +
                    "      |\n" +
                    "      |\n" +
                    "      |\n" +
                    "=========",

            "  +---+\n" +
                    "  |   |\n" +
                    "  0   |\n" +
                    "      |\n" +
                    "      |\n" +
                    "      |\n" +
                    "=========",

            "  +---+\n" +
                    "  |   |\n" +
                    "  0   |\n" +
                    "  |   |\n" +
                    "      |\n" +
                    "      |\n" +
                    "=========",

            "  +---+\n" +
                    "  |   |\n" +
                    "  0   |\n" +
                    " /|   |\n" +
                    "      |\n" +
                    "      |\n" +
                    "=========",

            "  +---+\n" +
                    "  |   |\n" +
                    "  0   |\n" +
                    " /|\\  |\n" +
                    "      |\n" +
                    "      |\n" +
                    "=========",

            "  +---+\n" +
                    "  |   |\n" +
                    "  0   |\n" +
                    " /|\\  |\n" +
                    " /    |\n" +
                    "      |\n" +
                    "=========",

            "  +---+\n" +
                    "  |   |\n" +
                    "  0   |\n" +
                    " /|\\  |\n" +
                    " / \\  |\n" +
                    "      |\n" +
                    "=========",
    };


    public static void main(String[] args) {
        String[][] temas = new String[50][];

        Scanner leitor = new Scanner(System.in);

        System.out.println("=== Bem vindo ao jogo da forca ===");
        System.out.println("Deseja carregar o jogo com palavras existentes? (sim/nao)");
        String carregar = leitor.next();
        if (carregar.equalsIgnoreCase("sim")) {
            temas = carregarArquivoTemas();
        }

        int opcao;

        do {
            System.out.println("Escolha a opção desejada");
            System.out.println("1 - Gerenciar Temas");
            System.out.println("2 - Gerenciar Palavras");
            System.out.println("3 - Jogar");
            System.out.println("4 - Sair");
            System.out.println("Digite a opcao desejada:");
            opcao = leitor.nextInt();
            switch (opcao) {
                case 1:
                    int opcaoTema;
                    do {
                        System.out.println("Informacoes sobre os temas");
                        System.out.println("1 - Cadastrar Temas");
                        System.out.println("2 - Excluir tema");
                        System.out.println("3 - Buscar temas");
                        System.out.println("4 - Voltar");
                        opcaoTema = leitor.nextInt();
                        switch (opcaoTema) {
                            case 1:
                                System.out.print("Informe o nome do tema: ");
                                String nomeTema = leitor.next();

                                if (temaExisteNoArray(temas, nomeTema)) {
                                    System.out.println("Tema já cadastrado.");
                                } else {
                                    adicionaTema(temas, nomeTema);
                                    System.out.println("Tema cadastrado com sucesso.");
                                }
                                break;
                            case 2:
                                System.out.print("Informe o nome do tema para exclusao: ");
                                String nomeTemaExclusao = leitor.next();
                                String[] palavras = recuperarListaDePalavras(temas, nomeTemaExclusao);
                                if (palavras != null) {
                                    if (palavras.length == 1) {
                                        excluirTema(temas, nomeTemaExclusao);
                                        System.out.println("Tema " + nomeTemaExclusao + " excluido com sucesso.");
                                    } else {
                                        System.out.println("Nao foi possivel excluir o tema. Verifique se existem palavras cadastradas nesse tema. ");
                                    }
                                } else {
                                    System.out.println("Tema nao encontrado");
                                }
                                break;
                            case 3:
                                System.out.print("Informe o nome do tema para buscar: ");
                                String nomeTemaBuscar = leitor.next().toLowerCase();
                                palavras = recuperarListaDePalavras(temas, nomeTemaBuscar);
                                if (palavras != null) {
                                    System.out.println("Tema encontrado");
                                    System.out.println(Arrays.toString(palavras));
                                } else {
                                    System.out.println("Tema nao encontrado");
                                }
                                break;
                        }
                    } while (opcaoTema != 4);
                    break;
                case 2:
                    int opcaoPalavras;
                    do {
                        System.out.println("Informacoes sobre as palavras");
                        System.out.println("1 - Cadastrar Palavas");
                        System.out.println("2 - Excluir Palavras");
                        System.out.println("3 - Buscar palavras");
                        System.out.println("4 - Listagem");
                        System.out.println("5 - Voltar");
                        opcaoPalavras = leitor.nextInt();
                        switch (opcaoPalavras) {
                            case 1:
                                System.out.println("informe tema");
                                String nomeTema = leitor.next();
                                System.out.println("Informe  palavra");
                                String nomePaalavra = leitor.next();

                                if (recuparTemaPorPalavra(temas, nomePaalavra) != null) {
                                    System.out.println("Palavra ja cadastrada");
                                } else {
                                    adicionaPalavraNoTema(temas, nomeTema, nomePaalavra);
                                    System.out.println("Palavra cadastrada");
                                }
                                break;
                            case 2:
                                System.out.println("Informa palavra para exclusao");
                                String palavra = leitor.next();
                                System.out.println("informe tema");
                                String tema = leitor.next();

                                String[] palavras = recuperarListaDePalavras(temas, tema);

                                if (palavraExisteNoArray(palavras, palavra)) {
                                    apagarPalavraDoArray(palavras, palavra);
                                    System.out.println("Palavra " + palavra + "excluido com sucesso");
                                } else {
                                    System.out.println("Palavra nao encontrada");
                                }
                                break;
                            case 3:
                                System.out.println("Buscar Palavras");
                                System.out.println("Informe o nome da palavra que deseja buscar:");
                                String buscarPalavra = leitor.next();

                                tema = recuparTemaPorPalavra(temas, buscarPalavra);
                                if (tema != null) {
                                    System.out.println("Palavra encontrada no tema: " + tema);
                                } else {
                                    System.out.println("Palavra nao encontrada");
                                }
                                break;
                            case 4:
                                System.out.println("Listagem de palavras por tema");
                                System.out.println("Informe tema: ");
                                tema = leitor.next();
                                palavras = recuperarListaDePalavras(temas, tema);
                                if (palavras != null) {
                                    System.out.println(Arrays.toString(palavras));
                                } else {
                                    System.out.println("Tema nao encontrado");
                                }
                                break;
                        }
                    } while (opcaoPalavras != 5);
                    break;
                case 3:
                    String continuar = "sim";
                    do {
                        System.out.println("Selecione o tema para comecar o jogo");
                        String buscarTema = leitor.next();
                        String[] palavras = recuperarListaDePalavras(temas, buscarTema);
                        if (palavras == null || palavras.length == 0) {
                            System.out.println("Não existem palavras para este tema.");
                            continue;
                        }

                        Random random = new Random();
                        int index = random.nextInt(palavras.length - 1);
                        System.out.println(index);
                        String palavraSelecionada = palavras[index].toLowerCase();
                        System.out.println(palavraSelecionada);

                        int qtdErros = 0;
                        List<String> caracteresDigitados = new ArrayList<>();
                        int letrasRestantesAcertar = palavraSelecionada.replace(" ", "").length();

                        do {
                            System.out.println(fasesForca[qtdErros]);

                            if (qtdErros == 6) {
                                System.out.println("*** VOCÊ PERDEU *** ");
                                break;
                            }
                            if (letrasRestantesAcertar == 0) {
                                System.out.println("*** PARABÉNS VOCÊ ACERTOU *** ");
                                break;
                            }


                            System.out.println();
                            System.out.println();
                            for (int i = 0; i < palavraSelecionada.length(); i++) {
                                char caractere = palavraSelecionada.charAt(i);
                                if (caractere == ' ') {
                                    System.out.print(" ");
                                } else {
                                    if (caracteresDigitados.contains(String.valueOf(caractere))) {
                                        System.out.print(caractere);
                                    } else {
                                        System.out.print("_");
                                    }
                                }
                            }
                            System.out.println();
                            System.out.println();

                            System.out.print("Informe uma letra:");
                            String caractereInformado = leitor.next().toLowerCase();
                            if (caracteresDigitados.contains(caractereInformado)) {
                                System.out.println("Tente outra letra");
                            } else {
                                caracteresDigitados.add(caractereInformado);
                                System.out.println(caracteresDigitados);

                                long qtd = palavraSelecionada.chars().filter(ch -> ch == caractereInformado.charAt(0)).count();
                                if (qtd > 0) {
                                    letrasRestantesAcertar = (int) (letrasRestantesAcertar - qtd);
                                } else {
                                    qtdErros = qtdErros + 1;
                                }
                            }

                        } while (true);

                        System.out.println("Deseja jogar novamente? (sim/nao)");
                        continuar = leitor.next();
                    } while (continuar.equalsIgnoreCase("sim"));
                    break;
            }
        } while (opcao != 4);
    }

    private static String recuparTemaPorPalavra(String[][] temas, String palavra) {
        for (int i = 0; i < temas.length; i++) {
            if (palavraExisteNoArray(temas[i], palavra)) {
                return temas[i][0];
            }
        }
        return null;
    }

    private static void apagarPalavraDoArray(String[] palavras, String palavra) {
        List<String> lista = Arrays.asList(palavras);
        lista.remove(palavra);
        palavras = lista.toArray(new String[0]);
    }

    private static void excluirTema(String[][] temas, String nomeTema) {
        Integer posicaoTema = recuperarIndiceDoTema(temas, nomeTema);
        if (posicaoTema != null) {
            temas[posicaoTema] = null;
        }
    }

    private static Integer recuperarIndiceDoTema(String[][] temas, String nomeTema) {
        for (int i = 0; i < temas.length; i++) {
            if (temas[i] != null) {
                if (temas[i].length > 0) {
                    if (temas[i][0].equalsIgnoreCase(nomeTema)) {
                        return i;
                    }
                }
            }
        }
        return null;
    }


    private static void adicionaPalavraNoTema(String[][] temas, String tema, String palavra) {
        Integer indiceDoTema = recuperarIndiceDoTema(temas, tema);
        if (indiceDoTema == null) {
            String[] palavras = new String[]{tema, palavra};
            for (int i = 0; i < temas.length; i++) {
                if (temas[i] == null) {
                    temas[i] = palavras;
                    return;
                }
            }
        } else {
            String[] palavras = temas[indiceDoTema];
            List<String> lista = new ArrayList<>();
            Collections.addAll(lista, palavras);
            lista.add(palavra);
            temas[indiceDoTema] = lista.toArray(new String[0]);
        }
    }

    private static boolean palavraExisteNoArray(String[] palavras, String palavra) {
        if (palavras == null) {
            return false;
        }
        for (int i = 1; i < palavras.length; i++) {
            if (palavras[i].equalsIgnoreCase(palavra)) {
                return true;
            }
        }
        return false;
    }

    private static String[] recuperarListaDePalavras(String[][] temas, String nomeTema) {
        for (String[] palavras : temas) {
            if (palavras != null && palavras[0].trim().equalsIgnoreCase(nomeTema)) {
                return Arrays.copyOfRange(palavras, 1, palavras.length);
            }
        }
        return null;
    }

    private static void adicionaTema(String[][] temas, String nomeTema) {
        for (int i = 0; i < temas.length; i++) {
            if (temas[i] == null) {
                temas[i] = new String[]{nomeTema};
                return;
            }
        }
    }

    private static boolean temaExisteNoArray(String[][] temas, String nomeTema) {
        for (String[] palavras : temas) {
            if (palavras != null && palavras[0].equalsIgnoreCase(nomeTema)) {
                return true;
            }
        }
        return false;
    }

    private static String[][] carregarArquivoTemas() {
        List<String[]> temas = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("temas.csv"))) {
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                linha = linha.replace("\uFEFF", "");
                String[] tema = linha.split(";");
                temas.add(tema);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error ao carregar o arquivo temas.csv");
            System.exit(1);
        }
        return temas.toArray(new String[50][]);
    }

}


