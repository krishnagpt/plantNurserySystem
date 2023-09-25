package com.amdocs.plantpro.main;


import java.util.List;
import java.util.Scanner;

import com.amdocs.plantpro.PlantDAO;
import com.amdocs.plantpro.PlantDAOImpl;
import com.amdocs.plantpro.plantstructure.Plant;




public class PlantNurserySystem {
    public static void main(String[] args) {
        PlantDAO plantDAO = new PlantDAOImpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("===== Plant Nursery System =====");
            System.out.println("1. Add new plant");
            System.out.println("2. Update plant cost");
            System.out.println("3. Delete plant");
            System.out.println("4. View all plants");
            System.out.println("5. Find plant by origin country name");
            System.out.println("6. Find outdoor plants which require sunlight");
            System.out.println("7. Count plants by water supply frequency");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    // Add new plant
                    System.out.print("Enter plant name: ");
                    String name = scanner.next();
                    System.out.print("Enter origin country name: ");
                    String country = scanner.next();
                    System.out.print("Does it require sunlight? (true/false): ");
                    boolean sunlightRequired = scanner.nextBoolean();
                    System.out.print("Enter water supply frequency: ");
                    String waterSupplyFrequency = scanner.next();
                    System.out.print("Enter plant type: ");
                    String type = scanner.next();
                    System.out.print("Enter plant cost: ");
                    double cost = scanner.nextDouble();

                    Plant newPlant = new Plant(0, name, country, sunlightRequired, waterSupplyFrequency, type, cost);
                    int generatedId = plantDAO.addPlant(newPlant);
                    System.out.println("Plant added with ID: " + generatedId);
                    break;

                case 2:
                    // Update plant cost
                    System.out.print("Enter plant ID to update cost: ");
                    int plantIdToUpdate = scanner.nextInt();
                    System.out.print("Enter new cost: ");
                    double newCost = scanner.nextDouble();
                    boolean updated = plantDAO.updatePlantCost(plantIdToUpdate, newCost);
                    if (updated) {
                        System.out.println("Plant cost updated successfully.");
                    } else {
                        System.out.println("Plant not found.");
                    }
                    break;

                case 3:
                    // Delete plant
                    System.out.print("Enter plant ID to delete: ");
                    int plantIdToDelete = scanner.nextInt();
                    int deletedPlantId = plantDAO.deletePlant(plantIdToDelete);
                    if (deletedPlantId != -1) {
                        System.out.println("Plant with ID " + deletedPlantId + " deleted.");
                    } else {
                        System.out.println("Plant not found.");
                    }
                    break;
                    
                case 4:
                    // View all plants
                    List<Plant> allPlants = plantDAO.showAllPlants();
                    if (allPlants.isEmpty()) {
                        System.out.println("No plants available.");
                    } else {
                        for (Plant plant : allPlants) {
                            System.out.println(plant);
                        }
                    }
                    break;

                case 5:
                    // Find plant by origin country name
                    System.out.print("Enter origin country name: ");
                    String countryName = scanner.nextLine();
                    List<Plant> plantsByCountry = plantDAO.searchByOriginCountryName(countryName);
                    if (plantsByCountry.isEmpty()) {
                        System.out.println("Plant not available for the given origin country.");
                    } else {
                        for (Plant plant : plantsByCountry) {
                            System.out.println(plant);
                        }
                    }
                    break;

                case 6:
                    // Find outdoor plants which require sunlight
                    List<Plant> outdoorPlants = plantDAO.searchOutdoorPlantsWithSunlight();
                    if (outdoorPlants.isEmpty()) {
                        System.out.println("No outdoor plants requiring sunlight available.");
                    } else {
                        for (Plant plant : outdoorPlants) {
                            System.out.println(plant);
                        }
                    }
                    break;

                case 7:
                    // Count plants by water supply frequency
                    System.out.print("Enter water supply frequency (daily/weekly/alternate days): ");
                    String frequency = scanner.nextLine();
                    int count = plantDAO.countPlantsByWaterSupplyFrequency(frequency);
                    System.out.println("Number of plants with water supply frequency " + frequency + ": " + count);
                    break;

               
                
                case 8:
                    System.out.println("Exiting Plant Nursery System. Goodbye!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
