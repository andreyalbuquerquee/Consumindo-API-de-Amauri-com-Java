import java.util.Scanner;
import javax.print.DocFlavor.STRING;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        Contact contact = new Contact();
        Categorie categorie = new Categorie();
        
        int option;


        do {
            System.out.println("\n****\nMENU\n****\n");
            System.out.println("1 - Contatos");
            System.out.println("2 - Categorias");
            System.out.println("0 - Sair");
            System.out.print("Opção: \n\n");
            option = in.nextInt();
            in.nextLine();

            switch (option) {
                case 1:
                System.out.println("\n****\nContatos\n****\n");
                System.out.println("1 - Listar contatos");
                System.out.println("2 - Obter um contato");
                System.out.println("3 - Criar contato");
                System.out.println("4 - Atualizar contato");
                System.out.println("5 - Excluir contato");
                System.out.print("Opção: \n\n");
                
                int firstOption = in.nextInt();
                in.nextLine();

                switch (firstOption) {
                    case 1:
                    System.out.println(contact.listContacts()); 
                    break;

                    case 2:
                    System.out.println("Informe o id do contato: \n");
                    String id = in.nextLine();
                    contact.findContact(id);
                    break;

                    case 3:
                    System.out.println("Informe o nome do contato: \n");
                    String name = in.nextLine();
                    System.out.println("Informe o email do contato: \n");
                    String email = in.nextLine();
                    System.out.println("Informe o número do contato: \n");
                    String phone = in.nextLine();
                    contact.createContact(name, email, phone);
                    break;

                    case 4:
                    System.out.println("Informe o id do contato: \n");
                    String informedId = in.nextLine();
                    int editOption;
                    Contact updatedInfos;

                    if (contact.findContact(informedId).getName() != null) {
                        updatedInfos = new Contact();

                        do {
                        
                        System.out.println("Escolha a opção que deseja alterar:\n");
                        System.out.println("1 - Nome\n");
                        System.out.println("2 - Email\n");
                        System.out.println("3 - Número\n");
                        System.out.println("4 - Categoria\n");
                        System.out.println("5 - Atualizar\n");
                        System.out.println("0 - Sair\n");
                        editOption = in.nextInt();
                        in.nextLine();
                        switch (editOption) {
                            case 1:
                            System.out.println("Informe o novo nome do contato:\n");
                            String newName = in.nextLine();
                            updatedInfos.setName(newName);
                            break;

                            case 2:
                            System.out.println("Informe o novo email do contato:\n");
                            String newEmail = in.nextLine();
                            updatedInfos.setEmail(newEmail);
                            break;

                            case 3:
                            System.out.println("Informe o novo número do contato:\n");
                            String newNumber = in.nextLine();
                            updatedInfos.setPhone(newNumber);
                            break;

                            case 4:
                            categorie.listCategories();
                            System.out.println("Digite o id da categoria que deseja por o contato: \n");
                            String categoryId = in.nextLine();

                            if (categorie.findCategorie(categoryId).getId() != null) {
                                updatedInfos.setCategory_id(categoryId);
                            } else {
                                System.out.println("Categoria não encontrada.\n");
                            }
                            break;

                            case 5:
                            System.out.println("Deseja atualizar essas informações? \n");
                            if (updatedInfos.getName() != null) {
                            System.out.println(String.format("Nome: %s\n", updatedInfos.getName()));
                            }
                            if (updatedInfos.getEmail() != null) {
                            System.out.println(String.format("Email: %s\n", updatedInfos.getEmail()));
                            }
                            if (updatedInfos.getPhone() != null) {
                            System.out.println(String.format("Número: %s\n", updatedInfos.getPhone()));
                            }
                            if (updatedInfos.getCategory_id() != null) {
                            String newCategoryId = updatedInfos.getCategory_id();
                            System.out.println(categorie.findCategorie(newCategoryId));
                            }

                            System.out.println("1 - Sim\n");
                            System.out.println("2 - Não\n");
                            int updateConfirm = in.nextInt();
                            in.nextLine();
                            switch (updateConfirm) {
                            case 1:
                            contact.updateContact(informedId, updatedInfos);
                            break;

                            case 2:
                            System.out.println("Atualização de contato cancelada!\n");
                            break;

                            default:
                            System.out.println("Opção inválida!\n");
                            break;
                            }
                            break;

                            default:
                            System.out.println("Opção inválida!\n");
                            break;
                            }
                        } while (editOption != 0);
                    } else {}
                    break;

                    case 5:
                    System.out.println("Informe o id do contato: \n");
                    String contactToDeleteId = in.nextLine();
                    contact.deleteContact(contactToDeleteId);
                    break;
                
                    default:
                    System.out.println("Opção inválida!");
                    break;
                }


                
                break;

                case 2:
                System.out.println("\n****\nContatos\n****\n");
                System.out.println("1 - Listar categorias");
                System.out.println("2 - Obter uma categoria");
                System.out.println("3 - Criar categoria");
                System.out.println("4 - Atualizar categoria");
                System.out.println("5 - Excluir categoria");
                System.out.print("Opção: \n\n");

                int secondOption = in.nextInt();
                in.nextLine();
                
                switch (secondOption) {
                    case 1:
                    categorie.listCategories();
                    break;

                    case 2:
                    System.out.println("Informe o id da categoria: \n");
                    String id = in.nextLine();
                    categorie.findCategorie(id);
                    break;

                    case 3:
                    System.out.println("Informe o nome da categoria que deseja adicionar: \n");
                    String newCategoryName = in.nextLine();
                    categorie.createCategorie(newCategoryName);
                    break;
                    
                    case 4:
                    categorie.listCategories();
                    System.out.println("\nInforme o id da categoria que deseja atualizar: \n");
                    String informedId = in.nextLine();

                    if (categorie.findCategorie(informedId).getName() != null) {
                        System.out.println(categorie.findCategorie(informedId));
                        System.out.println("Informe o novo nome da categoria: ");
                        String updatedCategoryName = in.nextLine();
                        Categorie updatedCategory = new Categorie();
                        updatedCategory.setName(updatedCategoryName);
                        categorie.updateCategorie(informedId, updatedCategory);
                    } else {
                        System.out.println("Categoria não encontrada!\n");
                    }
                    break;

                    case 5:
                    System.out.println("Informe o id da categoria: \n");
                    String categoryToDeleteId = in.nextLine();
                    categorie.deleteCategorie(categoryToDeleteId);
                    break;

                    default:
                    System.out.println("Opção inválida!\n");
                }

                break;

                case 0:
                System.out.println("Finalizando o programa...");
                break;

                default:
                System.out.println("Opção inválida!");
            }
        
        } while (option != 0);
    }
}
