package com.train_hibernate;

// ----------------------------------------------------------------------------------------------------- //

// Import DbOp
import com.train_hibernate.utils.DbOp;

// Import Java classes
import java.util.List;
import java.util.Scanner;

// ----------------------------------------------------------------------------------------------------- //

public class Main {

    // ---------------------------------------------- //

    public static void main(String[] args) {

        // Start database
        DbOp.init();

        // Start scanner
        Scanner scr = new Scanner(System.in);

        // --------------------- //

        // Show menu while user does not choose to exit or enter an invalid option
        int option = -1;

        do {

            System.out.println("\n----------------------------------------\n");
            System.out.println("[ES 2 - MENU DE EXEMPLO CRUD]\n");

            System.out.println("#1 - Criar registro");
            System.out.println("#2 - Criar registros com dados aleatórios");
            System.out.println("#3 - Ler registros");
            System.out.println("#4 - Atualizar registro");
            System.out.println("#5 - Atualizar registro com dados aleatórios");
            System.out.println("#6 - Deletar registro");
            System.out.println("#0 - Sair\n");

            System.out.print("> Digite uma opção: ");

            option = Integer.parseInt(scr.nextLine());

            System.out.println("\n----------------------------------------");

            // --------------------- //

            switch (option) {

                // ------------------------------------------------ //
                
                case 1:

                    System.out.println("\n[CRIAR REGISTRO]\n");
                    Contact contact = new Contact();
            
                    // --------------------- //

                    // Get name
                    contact.setName(prompt_getName(scr));

                    // Get address
                    contact.setAddress(prompt_getAddress(scr));

                    // Get phone
                    contact.setPhone(prompt_getPhone(scr));

                    // Insert contact
                    DbOp.insertContact(contact);

                    System.out.println("\n> Contato inserido com sucesso!");

                    // ------------ //

                    option = -1;
                    break;

                // ------------------------------------------------ //

                case 2:

                    Contact contact_random = new Contact();

                    contact_random.setName("753045_" + (int) (Math.random() * 1000));
                    contact_random.setAddress("Avenida Dom José Gaspar, " + (int) (Math.random() * 1000));
                    contact_random.setPhone("(31) " + (int) (Math.random() * 100000) + "-" + (int) (Math.random() * 10000));

                    DbOp.insertContact(contact_random);

                    System.out.println("\n> Contato inserido com sucesso!");
                
                    // ------------ //

                    option = -1;
                    break;

                // ------------------------------------------------ //
                
                case 3:

                    List<Contact> contacts = DbOp.getContacts();

                    System.out.println("\n[LER CONTATOS]");

                    for (Contact c : contacts) System.out.println(c.toString());
      
                    // ------------ //

                    option = -1;
                    break;

                // ------------------------------------------------ //
                
                case 4:

                    System.out.println("\n[ATUALIZAR REGISTRO]\n");

                    // --------------------- //

                    // Search contact by ID
                    Contact found = prompt_getContactById(scr);

                    if (found != null) {

                        // Get name
                        found.setName(prompt_getName(scr));
                        found.setAddress(prompt_getAddress(scr));
                        found.setPhone(prompt_getPhone(scr));

                        // --------------------- //

                        // Update contact
                        DbOp.updateContact(found);

                        System.out.println("\n> Contato atualizado com sucesso!");
                    }
    
                    // ------------ //

                    option = -1;
                    break;

                // ------------------------------------------------ //
                
                case 5:

                    System.out.println("\n[ATUALIZAR REGISTRO COM DADOS ALEATÓRIOS]\n");

                    // --------------------- //

                    // Search contact by ID
                    Contact found_random = prompt_getContactById(scr);

                    if (found_random != null) {

                        found_random.setName("753045_" + (int) (Math.random() * 1000));
                        found_random.setAddress("Avenida Dom José Gaspar, " + (int) (Math.random() * 1000));
                        found_random.setPhone("(31) " + (int) (Math.random() * 100000) + "-" + (int) (Math.random() * 10000));

                        // --------------------- //

                        // Update contact
                        DbOp.updateContact(found_random);

                        System.out.println("> Contato atualizado com sucesso!");
                    }

                    // ------------ //

                    option = -1;
                    break;

                // ------------------------------------------------ //
                
                case 6:

                    System.out.println("\n[DELETAR REGISTRO]\n");

                    // --------------------- //

                    // Search contact by ID
                    Contact found_delete = prompt_getContactById(scr);

                    if (found_delete != null) {

                        // Delete contact
                        DbOp.deleteContact(found_delete);

                        System.out.println("> Contato deletado com sucesso!");
                    }

                    // ------------ //

                    option = -1;
                    break;

                // ------------------------------------------------ //
                
                case 0:

                    System.out.println("\n> Saindo do programa...\n");
                    break;

                default:

                    System.out.println("\nx Opção inválida!");
                    break;
            }
        }
        while (option < 0 || option > 6);

        // --------------------- //
        
        scr.close();
    }

    // ---------------------------------------------- //

    private static String prompt_getName(Scanner scr) {

        boolean valid = true;
        String name = "";

        do {

            System.out.print("> Digite o nome do contato: ");
            name = scr.nextLine();

            System.out.println("DEBUG: " + name);

            if (name.length() < 3 || name.length() > 50 || name == null) {

                System.out.println("\nx O nome deve ter entre 3 e 50 caracteres!\n");
                valid = false;
            }
            else valid = true;
        }
        while (!valid);

        return name;
    }

    private static String prompt_getAddress(Scanner scr) {

        boolean valid = true;
        String address = "";

        do {

            System.out.print("> Digite o endereço do contato: ");
            address = scr.nextLine();

            if (address.length() < 3 || address.length() > 50 || address == null) {

                System.out.println("\nx O endereço deve ter entre 3 e 50 caracteres!\n");
                valid = false;
            }
            else valid = true;
        }
        while (!valid);

        return address;
    }

    private static String prompt_getPhone(Scanner scr) {

        boolean valid = true;
        String phone = "";

        do {

            System.out.print("> Digite o telefone do contato no formato (xx) xxxxx-xxxx: ");
            phone = scr.nextLine();

            // Validate phone in format (xx) xxxxx-xxxx
            if (phone.length() != 15) {

                System.out.println("\nx O telefone deve estar no formato (xx) xxxxx-xxxx!\n");
                valid = false;
            }
            else {

                if (phone.charAt(0) != '(' || phone.charAt(3) != ')' || phone.charAt(10) != '-' || phone == null) {

                    System.out.println("\nx O telefone deve estar no formato (xx) xxxxx-xxxx!\n");
                    valid = false;
                }
                else valid = true;
            }
        }
        while (!valid);

        return phone;
    }

    private static Contact prompt_getContactById(Scanner scr) {

        Contact contact = null;

        // --------------------- //

        // Search contact by ID
        System.out.print("> Digite o ID do contato: ");

        int id = Integer.parseInt(scr.nextLine());

        contact = DbOp.getContactById(id);

        if (contact != null) {

            System.out.println("\n[CONTATO ENCONTRADO]");
            System.out.println(contact.toString() + "\n");
        }
        else System.out.println("\nx Contato não encontrado!");

        // --------------------- //

        return contact;
    }

    // ---------------------------------------------- //
}