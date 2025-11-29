package com.caragies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caragies.entitymodel.Accessory;
import com.caragies.service.Accessoryservice;

@RestController
@RequestMapping("/api/accessories")

public class AccessoryController {

    @Autowired
    private Accessoryservice service;

    @PostMapping
    public Accessory addAccessory(@RequestBody Accessory dto) {
        return service.createAccessory(dto);
    }

    @GetMapping("/{id}")
    public Accessory getAccessory(@PathVariable Long id) {
        return (Accessory) service.getAccessoryById(id);
    }

    @GetMapping
    public List<Accessory> getAll() {
        return service.getAllAccessories();
    }

    @PutMapping("/{id}")
    public Accessory updateAccessory(@PathVariable Long id, @RequestBody Accessory dto) {
        return service.updateAccessory(id, dto);
    }

    @DeleteMapping("/{id}")
    public String deleteAccessory(@PathVariable Long id) {
        return service.deleteAccessory(id);
    }
}

