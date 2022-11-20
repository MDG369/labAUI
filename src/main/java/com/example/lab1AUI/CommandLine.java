package com.example.lab1AUI;

import com.example.lab1AUI.entity.Prison;
import com.example.lab1AUI.entity.Prisoner;
import com.example.lab1AUI.service.PrisonService;
import com.example.lab1AUI.service.PrisonerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Component
public class CommandLine implements CommandLineRunner {
    private PrisonerService prisonerService;
    private PrisonService prisonService;

    @Autowired
    public CommandLine(PrisonerService prisonerService, PrisonService prisonService) {
        this.prisonerService = prisonerService;
        this.prisonService = prisonService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scan = new Scanner(System.in);
        String command = scan.nextLine();;
        while(!command.equals("exit")) {
            switch(command) {
                case "help":
                    System.out.println("listPrisoners - lists all the prisoners in the database \n" +
                            "listPrisons - lists all prisons\n" +
                            "addPrisoner - add a prisoner\n" +
                            "addPrison - add a prison\n" +
                            "deletePrisoner - delete a prisoner by id\n" +
                            "help - list available commands\n" +
                            "exit - exit the program");
                    break;
                case "listPrisoners":
                    System.out.println(prisonerService.findAll());
                    break;
                case "listPrisons":
                    System.out.println(prisonService.findAll());
                    break;
                case "addPrisoner":
                    addPrisoner(scan);
                    break;
                case "addPrison":
                    System.out.println("Please input the name of the prison:");
                    prisonService.create(Prison.builder().name(scan.nextLine()).build());
                    System.out.println("Prison created successfully");
                    break;
                case "deletePrisoner":
                    deletePrisoner(scan);
                    break;
                case "deletePrison":
                    // TODO deleting prisons + cascade delete prisoners
                    System.out.println("Not yet implemented");
                    break;
                default:
                    System.out.println("Incorrect command");
            }
            command = scan.nextLine();
        }
    }

    private void addPrisoner(Scanner scan){
        Prisoner prisoner = new Prisoner();
        System.out.println("Please input the name of the prisoner:");
        prisoner.setName(scan.nextLine());
        System.out.println("Please input the surname of the prisoner:");
        prisoner.setSurname(scan.nextLine());
        System.out.println("Please input the age of the prisoner:");
        prisoner.setAge(Integer.parseInt(scan.nextLine()));
        System.out.println("Please input the cell number of the prisoner:");
        prisoner.setCell_number(Integer.parseInt(scan.nextLine()));
        System.out.println("Please input the name of prison of the prisoner:");
        while(true) {
            String prisonName = scan.nextLine();
            Optional<Prison> prison = prisonService.find(prisonName);
            if (prison.isPresent()) {
                prisoner.setPrison(prison.get());
                break;
            } else {
                System.out.println("Such prison doesn't exist, try again");
            }
        }
        prisonerService.create(prisoner);
        System.out.println("Prisoner created successfully");
    }
    private void deletePrisoner(Scanner scan){
        System.out.println("Please input the id of the prisoner you wish to delete");
        while(true) {
            int prisonerId = Integer.parseInt(scan.nextLine());
            Optional<Prisoner> prisoner = prisonerService.find(prisonerId);
            if (prisoner.isPresent()) {
                prisonerService.delete(prisonerId);
                break;
            } else {
                System.out.println("Such prisoner doesn't exist, try again");
            }
        }
        System.out.println("Prisoner deleted successfully");
    }
}
;