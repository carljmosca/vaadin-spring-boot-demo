package org.vaadin.spring.tutorial.view;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.spring.sidebar.annotation.FontAwesomeIcon;
import org.vaadin.spring.sidebar.annotation.SideBarItem;
import org.vaadin.spring.tutorial.Greeter;
import org.vaadin.spring.tutorial.ViewGreeter;

@SpringView(name = ViewScopedView.VIEW_NAME)
@SideBarItem(sectionId = Sections.REPORTING,
        caption = "ViewScoped View")
@FontAwesomeIcon(FontAwesome.ANCHOR)
@UIScope
public class ViewScopedView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "view";

    // A new instance will be created for every view instance created
    @Autowired
    private ViewGreeter viewGreeter;

    // The same instance will be used by all views of the UI
    @Autowired
    private Greeter uiGreeter;

    @PostConstruct
    void init() {
        setMargin(true);
        setSpacing(true);
        addComponent(new Label("This is a view scoped view"));
        addComponent(new Label(uiGreeter.sayHello()));
        addComponent(new Label(viewGreeter.sayHello()));
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // This view is constructed in the init() method()
    }
}