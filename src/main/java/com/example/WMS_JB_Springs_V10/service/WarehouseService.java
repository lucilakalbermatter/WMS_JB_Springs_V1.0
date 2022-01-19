package com.example.WMS_JB_Springs_V10.service;

import com.example.WMS_JB_Springs_V10.model.Item;
import com.example.WMS_JB_Springs_V10.model.Warehouse;
import com.example.WMS_JB_Springs_V10.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class WarehouseService {

    //      - GET -> /warehouse/getWarehouses  -> returns Set<Integer>
    //		- GET -> /warehouse/getAllItems    -> returns List<Item>
    //		- GET -> /warehouse/getAllItems/{warehouseId} -> returns List<Item>
    //		- GET -> /warehouse/getCategories  -> returns Set<String>
    //		- GET -> /warehouse/getItemsByCategory/{category}  -> returns List<Item>

    public Set<Integer> getWarehouses(){
        return WarehouseRepository.getWarehouseIds();
    }

    public List<Item> getAllItems(){
        return WarehouseRepository.getAllItems();
    }

    public List<Item> getItemsByWarehouse(int id) {
        List<Item> result = null;
        for (Warehouse x : WarehouseRepository.getWarehouseList()) {
            if (x.getId() == id) {
                result = x.getStock();
            }
        }
        return result;
    }

    public Set<String> getCategories() {
        return WarehouseRepository.getCategories();
    }

    public List<Item> getItemsByCategory(String category){
        return WarehouseRepository.getItemsByCategory(category);
    }
}
