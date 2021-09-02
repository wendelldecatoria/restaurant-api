package com.wendecator.restaurant.services;

import com.wendecator.restaurant.models.Menu;
import com.wendecator.restaurant.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> getAllMenus() {
        List<Menu> menus = new ArrayList<>();
        menuRepository.findAll().forEach(menus::add);
        return menus;
    }

    public Optional<Menu> getMenu(Long id) {
        Optional<Menu> getMenu = menuRepository.findById(id);
        return getMenu;

    }

    public Menu saveMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    public void deleteMenu(Menu menu) {
        menuRepository.delete(menu);
    }
}
