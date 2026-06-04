import java.util.Scanner;

public class Main {
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static Pessoa lerPessoa(Scanner scanner) {
        Pessoa p = new Pessoa("", "", "", null, 0, 0.0);    

        while (true) {
            System.out.print("CPF: ");
            String cpf = scanner.nextLine();
            try {
                p.setCpf(cpf);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage() + " Tente novamente.");
            }
        }

        while (true) {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            try {
                p.setNome(nome);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage() + " Tente novamente.");
            }
        }

        while (true) {
            System.out.print("Estado Civil ( solteiro - 1, casado - 2, viuvo - 3, divorciado - 4 ): ");
            String estadocivil = scanner.nextLine();
            try {
                switch (estadocivil) {
                    case "1":
                        estadocivil = "solteiro";
                        break;
                    case "2":
                        estadocivil = "casado";
                        break;
                    case "3":
                        estadocivil = "viuvo";
                        break;
                    case "4":
                        estadocivil = "divorciado";
                        break;
                    default:
                        System.out.println("Opção inválida, tente novamente.");
                        continue;
                }
                p.setEstadocivil(estadocivil);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage() + " Tente novamente.");
            }
        }

        while (true) {
            System.out.print("Nascimento (YYYY-MM-DD): ");
            String nascimentoStr = scanner.nextLine();
            try {
                java.sql.Date nascimento = java.sql.Date.valueOf(nascimentoStr);
                p.setNascimento(nascimento);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage() + " Tente novamente.");
            }
        }

        while (true) {
            System.out.print("Altura (cm): ");
            try {
                int altura_cm = Integer.parseInt(scanner.nextLine());
                p.setAltura_cm(altura_cm);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Erro: Altura inválida. Tente novamente.");
            }
        }

        while (true) {
            System.out.print("Peso (kg): ");
            try {
                double peso_kg = Double.parseDouble(scanner.nextLine());
                p.setPeso_kg(peso_kg);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Erro: Peso inválido. Tente novamente.");
            }
        }

        return p;
    }

    public static Pessoa lerPessoaParaAtualizar(Scanner scanner, Pessoa p) {
        System.out.println("Digite os novos dados (deixe em branco para manter o valor atual):");

        while (true) {
            System.out.print("Nome (" + p.getNome() + "): ");
            String nome = scanner.nextLine();
            if (!nome.trim().isEmpty()) {
                try {
                    p.setNome(nome);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Erro: " + e.getMessage() + " Mantendo valor atual.");
                }
            }
            else {
                break;
            }
        }

        while (true) {
            System.out.print("Estado Civil ( solteiro - 1, casado - 2, viuvo - 3, divorciado - 4 ) (" + p.getEstadocivil() + "): ");
            String estadocivil = scanner.nextLine();
            if (!estadocivil.trim().isEmpty()) {
                try {
                    switch (estadocivil) {
                        case "1":
                            estadocivil = "solteiro";
                            break;
                        case "2":
                            estadocivil = "casado";
                            break;
                        case "3":
                            estadocivil = "viuvo";
                            break;
                        case "4":
                            estadocivil = "divorciado";
                            break;
                        default:
                            System.out.println("Opção inválida, mantendo estado civil atual.");
                            estadocivil = p.getEstadocivil();
                    }
                    p.setEstadocivil(estadocivil);
                } catch (IllegalArgumentException e) {
                    System.out.println("Erro: " + e.getMessage() + " Mantendo valor atual.");
                }
            }
            else {
                break;
            }
        }
        
        while (true) {
            System.out.print("Nascimento (" + p.getNascimento() + "): ");
            String nascimentoStr = scanner.nextLine();
            if (!nascimentoStr.trim().isEmpty()) {
                try {
                    java.sql.Date nascimento = java.sql.Date.valueOf(nascimentoStr);
                    p.setNascimento(nascimento);
                } catch (IllegalArgumentException e) {
                    System.out.println("Erro: " + e.getMessage() + " Mantendo valor atual.");
                }
            }
            else {
                break;
            }
        }

        while (true) {
            System.out.print("Altura (" + p.getAltura_cm() + "): ");
            String alturaStr = scanner.nextLine();
            if (!alturaStr.trim().isEmpty()) {
                try {
                    int altura_cm = Integer.parseInt(alturaStr);
                    p.setAltura_cm(altura_cm);
                } catch (NumberFormatException e) {
                    System.out.println("Erro: Altura inválida. Mantendo valor atual.");
                }
            }
            else {
                break;
            }
        }

        while (true) {
            System.out.print("Peso (" + p.getPeso_kg() + "): ");
            String pesoStr = scanner.nextLine();
            if (!pesoStr.trim().isEmpty()) {
                try {
                    double peso_kg = Double.parseDouble(pesoStr);
                    p.setPeso_kg(peso_kg);
                } catch (NumberFormatException e) {
                    System.out.println("Erro: Peso inválido. Mantendo valor atual.");
                }
            }
            else {
                break;
            }
        }

        return p;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PessoaDAO dao = new PessoaDAO();
        int opcao;
        do {
            limparTela();

            System.out.println("\n1 - Insere");
            System.out.println("2 - Atualiza");
            System.out.println("3 - Exclui");
            System.out.println("4 - Busca");
            System.out.println("5 - listar");
            System.out.println("0 - Finaliza");
            System.out.print("opcao: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            if ( opcao == 1) {
                try {
                    System.out.println("Insere");
                    Pessoa p = lerPessoa(scanner);

                    dao.inserir(p);
                } catch (Exception e) {
                    System.out.println("Erro ao inserir: " + e.getMessage());
                }

                System.out.println("\nPressione ENTER para continuar...");
                scanner.nextLine();
            }
            else if (opcao == 2) {
                System.out.print("Digite o CPF para atualizar: ");
                String cpfAtualizar = scanner.nextLine();
                try {
                    if(cpfAtualizar.length() != 11) {
                        throw new IllegalArgumentException("CPF deve conter exatamente 11 caracteres.");
                    }
                    Pessoa p = dao.buscar(cpfAtualizar);
                    if (p != null) {
                        System.out.println("Pessoa encontrada:");
                        p.imprimirDados();  
                        p = lerPessoaParaAtualizar(scanner, p);
                        dao.atualizar(p);
                        System.out.println("Pessoa atualizada:");
                        p.imprimirDados();
                    } else {
                        System.out.println("Pessoa não encontrada.");
                    }
                } catch (Exception e) {
                    System.out.println("Erro ao atualizar: " + e.getMessage());
                }

                System.out.println("\nPressione ENTER para continuar...");
                scanner.nextLine();
            }
            else if (opcao == 3) {
                System.out.print("Digite o CPF para excluir: ");
                String cpfExcluir = scanner.nextLine();
                try {
                    if(cpfExcluir.length() != 11) {
                        throw new IllegalArgumentException("CPF deve conter exatamente 11 caracteres.");
                    }
                    Pessoa p = dao.excluir(cpfExcluir);
                    if (p != null) {
                        System.out.println("Pessoa excluída:");
                        p.imprimirDados();
                    } else {
                        System.out.println("Pessoa não encontrada.");
                    }
                } catch (Exception e) {
                    System.out.println("Erro ao excluir: " + e.getMessage());
                }

                System.out.println("\nPressione ENTER para continuar...");
                scanner.nextLine();
            }
            else if (opcao == 4) {
                System.out.print("Digite o CPF para buscar: ");
                String cpfBuscar = scanner.nextLine();
                try {
                    if(cpfBuscar.length() != 11) {
                        throw new IllegalArgumentException("CPF deve conter exatamente 11 caracteres.");
                    }
                    Pessoa p = dao.buscar(cpfBuscar);
                    if (p != null) {
                        System.out.println("Pessoa encontrada:");
                        p.imprimirDados();
                    } else {
                        System.out.println("Pessoa não encontrada.");
                    }
                } catch (Exception e) {
                    System.out.println("Erro ao buscar: " + e.getMessage());
                }

                System.out.println("\nPressione ENTER para continuar...");
                scanner.nextLine();
            }
            else if (opcao == 5) {
                System.out.print("Digite o limite de resultados: ");
                int limit = Integer.parseInt(scanner.nextLine());

                try {
                    java.util.List<Pessoa> pessoas = dao.listar(limit);
                    System.out.println("CPF | Nome | Estado Civil | Nascimento | Altura | Peso");
                    for (Pessoa p : pessoas) {
                        System.out.println(p);
                    }
                } catch (Exception e) {
                    System.out.println("Erro ao listar: " + e.getMessage());
                }

                System.out.println("\nPressione ENTER para continuar...");
                scanner.nextLine();
            }
        } while (opcao != 0);
    }
}