/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vaadin.spring.tutorial.menu;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.themes.ValoTheme;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author moscac
 */
@SpringComponent
public class MainMenu extends CssLayout {

    @Autowired
    ViewManager menuManager;

    @PostConstruct
    void init() {
        addStyleName(ValoTheme.LAYOUT_HORIZONTAL_WRAPPING);
    }

    public void addMenuItems() {

        removeAllComponents();
        menuManager.getViews().stream().forEach((MenuViewItem item) -> {
            addComponent(createNavigationButton(item));
        });
    }

    private Button createNavigationButton(MenuViewItem item) {
        Button button = new Button(item.getDescription());
        button.addStyleName(ValoTheme.BUTTON_SMALL);
        button.setWidth("90%");
        button.addClickListener(event -> getUI().getNavigator().navigateTo(
                item.getName()));
        if (item.getIcon() != null) {
            button.setIcon(item.getIcon());
        }
        return button;
    }

}
