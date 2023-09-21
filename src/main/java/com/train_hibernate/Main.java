package com.train_hibernate;

// ----------------------------------------------------------------------------------------------------- //

// Import native classes
import com.train_hibernate.utils.DbOp;
import com.train_hibernate.utils.HibernateUtil;
import com.train_hibernate.fk.ContactAppointment;

// Import Java classes
import java.util.List;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

// ----------------------------------------------------------------------------------------------------- //

public class Main {

    // ---------------------------------------------- //

    public static void main(String[] args) {

        System.out.println("\n> Iniciando programa, aguarde...\n");

        // Start database
        DbOp.init();

        // Start scanner
        Scanner scr = new Scanner(System.in);

        // --------------------- //

        // Show main menu while user does not choose to exit or enter an invalid option
        int mm_option;

        do {

            mm_option = prompt_showMainMenu(scr);
        
            // --------------------- //

            switch (mm_option) {

                // ------------------------------------------------ //
                
                case 1:

                    int sm_option = -1;

                    // Show sub menu while user does not choose to go back or enter an invalid option
                    do {

                        sm_option = prompt_showSubMenu(scr, mm_option);

                        // --------------------- //

                        switch (sm_option) {

                            // ------------ //

                            case 1:

                                System.out.println("\n[NOVO CONTATO]\n");
                                Contact contact = new Contact();

                                contact.setName(prompt_getName(scr));
                                contact.setAddress(prompt_getAddress(scr));
                                contact.setPhone(prompt_getPhone(scr));

                                DbOp.createRecord(contact);

                                System.out.println("\n> Contato cadastrado com sucesso!");

                                // ------------ //

                                sm_option = -1;
                                break;

                            // ------------ //

                            case 2:

                                System.out.println("\n[LISTAR CONTATOS]");
                                List<Object> contacts = DbOp.readRecords(Constants.CONTACTS_ENTITY_NAME);

                                if (contacts != null) {

                                    for (Object c : contacts) System.out.println(c.toString());
                                }

                                // ------------ //

                                sm_option = -1;
                                break;

                            // ------------ //

                            case 3:

                                System.out.println("\n[EDITAR CONTATO]\n");
                                Contact contact_edit = prompt_getContactById(scr);

                                if (contact_edit != null) {

                                    contact_edit.setName(prompt_getName(scr));
                                    contact_edit.setAddress(prompt_getAddress(scr));
                                    contact_edit.setPhone(prompt_getPhone(scr));

                                    DbOp.updateRecord(contact_edit);

                                    System.out.println("\n> Contato editado com sucesso!");
                                }

                                // ------------ //

                                sm_option = -1;
                                break;

                            // ------------ //

                            case 4:

                                System.out.println("\n[EXCLUIR CONTATO]\n");
                                Contact contact_delete = prompt_getContactById(scr);

                                if (contact_delete != null) {

                                    DbOp.deleteRecord(contact_delete);

                                    System.out.println("\n> Contato excluído com sucesso!");
                                }

                                // ------------ //

                                sm_option = -1;
                                break;

                            // ------------ //

                            case 5:

                                System.out.println("\n[LISTAR COMPROMISSOS DE UM CONTATO]\n");
                                Contact contact_search = prompt_getContactById(scr);

                                if (contact_search != null) {

                                    List<ContactAppointment> contactAppointments = DbOp.readAppointments(contact_search, Constants.CONTACTS_ENTITY_NAME);

                                    System.out.println("[COMPROMISSOS DO CONTATO]");

                                    if (contactAppointments != null && contactAppointments.size() > 0) {

                                        for (ContactAppointment ca : contactAppointments) {

                                            Appointment a = ca.getAppointment();

                                            System.out.println("\nData: " + a.getDate() + "\n" + "Descrição: " + a.getDescription() + "\n" + "Função: " + ca.getRole());
                                        }
                                    }
                                    else System.out.println("\nx Nenhum compromisso encontrado para este contato!");
                                }

                                // ------------ //

                                sm_option = -1;
                                break;

                            // ------------ //

                            case 0:

                                System.out.println("\n> Voltando ao menu principal...");
                                break;

                            // ------------ //

                            default:

                                System.out.println("\nx Opção inválida!");
                                break;
                        }
                    }
                    while (sm_option < 0 || sm_option > 5);

                    // ------------ //

                    mm_option = -1;
                    break;

                // ------------------------------------------------ //

                case 2:

                    // Show sub menu while user does not choose to go back or enter an invalid option
                    do {

                        sm_option = prompt_showSubMenu(scr, mm_option);

                        // --------------------- //

                        switch (sm_option) {

                            // ------------ //

                            case 1:

                                System.out.println("\n[NOVO COMPROMISSO]\n");
                                Appointment appointment_new = new Appointment();

                                appointment_new.setDate(prompt_getDate(scr));
                                appointment_new.setDescription(prompt_getDescription(scr));

                                DbOp.createRecord(appointment_new);

                                System.out.println("\n> Compromisso cadastrado com sucesso!");

                                // ------------ //

                                sm_option = -1;
                                break;
                            
                            // ------------ //

                            case 2:

                                System.out.println("\n[LISTAR COMPROMISSOS]");
                                List<Object> appointments = DbOp.readRecords(Constants.APPOINTMENTS_ENTITY_NAME);

                                if (appointments != null) {

                                    for (Object a : appointments) System.out.println(a.toString());
                                }

                                // ------------ //

                                sm_option = -1;
                                break;


                            // ------------ //

                            case 3:

                                System.out.println("\n[EDITAR COMPROMISSO]\n");
                                Appointment appointment_edit = prompt_getAppointmentById(scr);

                                if (appointment_edit != null) {

                                    appointment_edit.setDate(prompt_getDate(scr));
                                    appointment_edit.setDescription(prompt_getDescription(scr));

                                    DbOp.updateRecord(appointment_edit);

                                    System.out.println("\n> Compromisso editado com sucesso!");
                                }

                                // ------------ //

                                sm_option = -1;
                                break;

                            // ------------ //

                            case 4:

                                System.out.println("\n[EXCLUIR COMPROMISSO]\n");
                                Appointment appointment_delete = prompt_getAppointmentById(scr);

                                if (appointment_delete != null) {

                                    DbOp.deleteRecord(appointment_delete);

                                    System.out.println("\n> Compromisso excluído com sucesso!");
                                }
                                
                                // ------------ //

                                sm_option = -1;
                                break;

                            // ------------ //

                            case 5:

                                System.out.println("\n[LISTAR CONTATOS DE UM COMPROMISSO]\n");
                                Appointment appointment_search = prompt_getAppointmentById(scr);

                                if (appointment_search != null) {

                                    List<ContactAppointment> appointmentContacts = DbOp.readAppointments(appointment_search, Constants.APPOINTMENTS_ENTITY_NAME);

                                    System.out.println("[CONTATOS DO COMPROMISSO]");

                                    if (appointmentContacts != null && appointmentContacts.size() > 0) {

                                        for (ContactAppointment ac : appointmentContacts) {

                                            Contact c = ac.getContact();

                                            System.out.println("\nNome: " + c.getName() + "\n" + "Telefone: " + c.getPhone() + "\n" + "Função: " + ac.getRole());
                                        }
                                    }
                                    else System.out.println("\nx Nenhum contato encontrado para este compromisso!");
                                }

                                // ------------ //

                                sm_option = -1;
                                break;

                            // ------------ //

                            case 0:

                                System.out.println("\n> Voltando ao menu principal...");
                                break;

                            // ------------ //

                            default:

                                System.out.println("\nx Opção inválida!");
                                break;
                        }
                    }
                    while (sm_option < 0 || sm_option > 5);
                   
                    // ------------ //

                    mm_option = -1;
                    break;

                // ------------------------------------------------ //
                
                case 3:

                    System.out.println("\n[ADICIONAR CONTATO A UM COMPROMISSO]\n");
                    Contact contact_add = prompt_getContactById(scr);

                    if (contact_add != null) {

                        Appointment appointment_add = prompt_getAppointmentById(scr);

                        if (appointment_add != null) {

                            ContactAppointment contactAppointment = new ContactAppointment();

                            contactAppointment.setContact(contact_add);
                            contactAppointment.setAppointment(appointment_add);
                            contactAppointment.setRole(prompt_getRole(scr));

                            DbOp.updateRecord(contactAppointment);

                            System.out.println("\n> Contato adicionado ao compromisso com sucesso!");
                        }
                    }

                    // ------------ //

                    mm_option = -1;
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
        while (mm_option < 0 || mm_option > 3);

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

    // -------------------------------------------------------------------------------------------- //

    private static Date prompt_getDate(Scanner scr) {

        boolean valid = true;
        Date date = null;

        do {

            System.out.print("> Digite a data do compromisso no formato dd/mm/aaaa: ");
            String date_str = scr.nextLine();

            // Validate date in format dd/mm/aaaa
            if (date_str.length() != 10) {

                System.out.println("\nx A data deve estar no formato dd/mm/aaaa!\n");
                valid = false;
            }
            else {

                if (date_str.charAt(2) != '/' || date_str.charAt(5) != '/' || date_str == null) {

                    System.out.println("\nx A data deve estar no formato dd/mm/aaaa!\n");
                    valid = false;
                }
                else valid = true;
            }

            // --------------------- //

            if (valid) {

                // Parse date
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                try { date = sdf.parse(date_str); }
                catch (Exception e) {

                    System.out.println("\nx A data deve estar no formato dd/mm/aaaa!\n");
                    valid = false;
                }
            }
        }
        while (!valid);
        return date;
    }

    private static String prompt_getDescription(Scanner scr) {

        boolean valid = true;
        String description = "";

        do {

            System.out.print("> Digite a descrição do compromisso: ");
            description = scr.nextLine();

            if (description.length() < 3 || description.length() > 100 || description == null) {

                System.out.println("\nx A descrição deve ter entre 3 e 100 caracteres!\n");
                valid = false;
            }
            else valid = true;
        }
        while (!valid);

        return description;
    }

    // -------------------------------------------------------------------------------------------- //

    private static String prompt_getRole(Scanner scr) {

        boolean valid = true;
        String role = "";

        do {

            System.out.print("> Digite a função do contato no compromisso: ");
            role = scr.nextLine();

            if (role.length() < 3 || role.length() > 50 || role == null) {

                System.out.println("\nx A função deve ter entre 3 e 50 caracteres!\n");
                valid = false;
            }
            else valid = true;
        }
        while (!valid);

        return role;
    }

    // -------------------------------------------------------------------------------------------- //

    private static Contact prompt_getContactById(Scanner scr) {

        Contact contact = null;

        // --------------------- //

        // Search contact by ID
        System.out.print("> Digite o ID do contato: ");

        int id = Integer.parseInt(scr.nextLine());

        contact = DbOp.getContactById(id);

        if (contact != null) {

            System.out.println("\n> Contato encontrado!");
            System.out.println(contact.toString() + "\n");
        }
        else System.out.println("\nx Contato não encontrado!");

        // --------------------- //

        return contact;
    }

    private static Appointment prompt_getAppointmentById(Scanner scr) {

        Appointment appointment = null;

        // --------------------- //

        // Search appointment by ID
        System.out.print("> Digite o ID do compromisso: ");

        int id = Integer.parseInt(scr.nextLine());

        appointment = DbOp.getAppointmentById(id);

        if (appointment != null) {

            System.out.println("\n> Compromisso encontrado!");
            System.out.println(appointment.toString() + "\n");
        }
        else System.out.println("\nx Compromisso não encontrado!");

        // --------------------- //

        return appointment;
    }

    // -------------------------------------------------------------------------------------------- //

    private static int prompt_showMainMenu(Scanner scr) {

        System.out.println("\n----------------------------------------\n");
        System.out.println("[MENU PRINCIPAL - CRUD ES 2]\n");

        System.out.println("#1 - Contatos");
        System.out.println("#2 - Compromissos");
        System.out.println("#3 - Adicionar contato a um compromisso");
        System.out.println("#0 - SAIR");

        System.out.print("\n> Digite uma opção: ");

        int mm_option = Integer.parseInt(scr.nextLine());

        System.out.println("\n----------------------------------------");

        return mm_option;
    }

    private static int prompt_showSubMenu(Scanner scr, int option) {

        System.out.println("\n----------------------------------------\n");
        System.out.println("[MENU - " + (option == 1 ? "CONTATOS" : "COMPROMISSOS") + "]\n");

        switch (option) {

            // ------------------------------------------------ //

            case 1:

                System.out.println("#1 - Cadastrar contato");
                System.out.println("#2 - Listar contatos");
                System.out.println("#3 - Editar contato");
                System.out.println("#4 - Excluir contato");
                System.out.println("#5 - Listar compromissos de um contato");
                System.out.println("#0 - VOLTAR");
                break;

            // ------------------------------------------------ //

            case 2:

                System.out.println("#1 - Cadastrar compromisso");
                System.out.println("#2 - Listar compromissos");
                System.out.println("#3 - Editar compromisso");
                System.out.println("#4 - Excluir compromisso");
                System.out.println("#5 - Listar contatos de um compromisso");
                System.out.println("#0 - VOLTAR");
                break;

            // ------------------------------------------------ //
        }

        System.out.print("\n> Digite uma opção: ");

        int sm_option = Integer.parseInt(scr.nextLine());

        System.out.println("\n----------------------------------------");

        return sm_option;
    }

    // -------------------------------------------------------------------------------------------- //
}