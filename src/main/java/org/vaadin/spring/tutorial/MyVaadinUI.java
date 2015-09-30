package org.vaadin.spring.tutorial;

import org.vaadin.spring.tutorial.menu.MainMenu;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import javax.annotation.PostConstruct;

@Theme("valo")
@SpringUI
public class MyVaadinUI extends UI {

    @Autowired
    private SpringViewProvider viewProvider;
    @Autowired
    MainMenu mainMenu;
    HorizontalLayout hl;
    
    private Navigator navigator;

    @Override
    protected void init(VaadinRequest request) {
    }

    @PostConstruct
    void populateMenu() {
        final HorizontalLayout root = new HorizontalLayout();
        root.setSizeFull();
        root.setMargin(true);
        root.setSpacing(true);
        setContent(root);

        root.addComponent(mainMenu);

        final Panel viewContainer = new Panel();
        viewContainer.setSizeFull();
        hl = new HorizontalLayout();

        viewContainer.setContent(hl);
        root.addComponent(viewContainer);
        root.setExpandRatio(viewContainer, 1.0f);
        navigator = new Navigator(this, viewContainer);
        navigator.addProvider(viewProvider);

    }


}
