package com.example.WMS_JB_Springs_V10.controller;

import com.example.WMS_JB_Springs_V10.model.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@Controller
public class HomeController {

    RestTemplate restTemplate;

    @GetMapping("/listAllItems")
    public String getListAllItemsePage(HttpServletRequest request, Model model) {
        restTemplate = new RestTemplate();
        String itemResourceUrl = "http://localhost:" + request.getLocalPort() + "/warehouse/getAllItems";
        String warehouseResourceUrl = "http://localhost:" + request.getLocalPort() + "/warehouse/getWarehouses";

        List<Item> response = restTemplate.getForObject(
                itemResourceUrl,
                List.class
        );

        Set<Integer> warehouseResponse = restTemplate.getForObject(
                warehouseResourceUrl,
                Set.class
        );

        model.addAttribute("warehouses", warehouseResponse);
        model.addAttribute("items", response);
        model.addAttribute("itemCount", response.size());
        return "items_list";
    }

    @GetMapping("/lisItemsByWarehouse/{warehouseId}")
    public String getListItemByWarehousePage(HttpServletRequest request, Model model, @PathVariable("warehouseId") int warehouseId) {
        restTemplate = new RestTemplate();
        String itemResourceUrl = "http://localhost:" + request.getLocalPort() + "/warehouse/getAllItems/" + warehouseId;

        List<Item> response = restTemplate.getForObject(
                itemResourceUrl,
                List.class
        );


        model.addAttribute("warehouse", warehouseId);
        model.addAttribute("items", response);
        model.addAttribute("itemCount", response.size());
        return "items_list_by_warehouse";
    }

    @GetMapping("/browseByCategory")
    public String getListAllCategories(HttpServletRequest request, Model model) {

        restTemplate = new RestTemplate();
        //a rest call to our rest-endpoint "/warehouse/getCategories"
        String categoriesResourceUrl = "http://localhost:" + request.getLocalPort() + "/warehouse/getCategories";

        Set<String> categoriesResponse = restTemplate.getForObject(
                categoriesResourceUrl,
                Set.class
        );

        //add the response to the spring ui model.
        model.addAttribute("categories", categoriesResponse);
        model.addAttribute("categoryCount", categoriesResponse.size());
        return "browse_by_category.html";
    }

    //add a GetMapping method for "/browseByCategory/{category}".
    @GetMapping("/browseByCategory/{category}")
    public String getListItemByCategoryPage(HttpServletRequest request, Model model, @PathVariable("category") String category) {
        restTemplate = new RestTemplate();
        String itemResourceUrl = "http://localhost:" + request.getLocalPort() + "/warehouse/getItemsByCategory/" + category;

        List<Item> response = restTemplate.getForObject(
                itemResourceUrl,
                List.class
        );


        model.addAttribute("category", category);
        model.addAttribute("items", response);
        model.addAttribute("itemCount", response.size());
        return "browse_by_specific_category.html";
    }

}

