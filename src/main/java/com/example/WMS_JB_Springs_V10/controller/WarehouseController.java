package com.example.WMS_JB_Springs_V10.controller;

import com.example.WMS_JB_Springs_V10.service.WarehouseService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WarehouseController {

    WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }


}
