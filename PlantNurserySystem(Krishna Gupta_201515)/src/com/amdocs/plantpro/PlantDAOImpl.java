package com.amdocs.plantpro;

import java.util.ArrayList;
import java.util.List;

import com.amdocs.plantpro.plantstructure.Plant;

public  class PlantDAOImpl implements PlantDAO {
    private List<Plant> plants;
    private int nextPlantId = 1;

    public PlantDAOImpl() {
        plants = new ArrayList<>();
    }

    @Override
    public int addPlant(Plant plant) {
        plant.setPlantId(nextPlantId++);
        plants.add(plant);
        return plant.getPlantId();
    }

    @Override
    public int deletePlant(int plantId) {
        for (Plant plant : plants) {
            if (plant.getPlantId() == plantId) {
                plants.remove(plant);
                return plantId;
            }
        }
        return -1; // Plant not found
    }

    @Override
    public boolean updatePlantCost(int plantId, double newCost) {
        for (Plant plant : plants) {
            if (plant.getPlantId() == plantId) {
                plant.setCost(newCost);
                return true;
            }
        }
        return false; // Plant not found
    }

    @Override
    public List<Plant> showAllPlants() {
        return plants;
    }

    @Override
    public List<Plant> searchByOriginCountryName(String countryName) {
        List<Plant> result = new ArrayList<>();
        for (Plant plant : plants) {
            if (plant.getOriginCountryName().equalsIgnoreCase(countryName)) {
                result.add(plant);
            }
        }
        return result;
    }

    @Override
    public List<Plant> searchOutdoorPlantsWithSunlight() {
        List<Plant> result = new ArrayList<>();
        for (Plant plant : plants) {
            if (plant.isSunlightRequired()) {
                result.add(plant);
            }
        }
        return result;
    }

    @Override
    public int countPlantsByWaterSupplyFrequency(String waterSupplyFrequency) {
        int count = 0;
        for (Plant plant : plants) {
            if (plant.getWaterSupplyFrequency().equalsIgnoreCase(waterSupplyFrequency)) {
                count++;
            }
        }
        return count;
    }

}

