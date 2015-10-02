package org.vaadin.spring.tutorial.view;

import javax.annotation.PostConstruct;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.tutorial.menu.MainMenu;

@SpringView(name = DefaultView.VIEW_NAME)
public class DefaultView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "";
    @Autowired
    MainMenu mainMenu;
    private Button button;

    @PostConstruct
    void init() {
        addComponent(new Label("This is the default view"));
        button = new Button("add");
        addComponent(button);
        button.addClickListener((Button.ClickEvent event) -> {
            doLogin();
        });
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // This view is constructed in the init() method()
    }

    private void doLogin() {

        mainMenu.addMenuItems();

    }
}
