package org.vaadin.spring.tutorial;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import org.vaadin.spring.i18n.annotation.EnableI18N;
import org.vaadin.spring.sidebar.components.AbstractSideBar;
import org.vaadin.spring.sidebar.components.ValoSideBar;

@SpringUI
@Theme("sidebar") // A custom theme based on Valo
@EnableI18N
public class ValoSideBarUI extends AbstractSideBarUI {

    @Autowired
    ValoSideBar sideBar;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        super.init(vaadinRequest);
        CssLayout header = new CssLayout();

        MenuBar menuBar = new MenuBar();
        header.addComponent(menuBar);

        MenuBar.MenuItem settingsItem = menuBar.addItem("", FontAwesome.WRENCH, null);

        MenuBar.MenuItem useLargeIconsItem = settingsItem.addItem("Use large icons", new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                sideBar.setLargeIcons(selectedItem.isChecked());
            }
        });
        useLargeIconsItem.setCheckable(true);

        MenuBar.MenuItem showLogoItem = settingsItem.addItem("Show logo", new MenuBar.Command() {

            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                if (selectedItem.isChecked()) {
                    showLogo();
                } else {
                    hideLogo();
                }
            }
        });
        showLogoItem.setCheckable(true);

        sideBar.setHeader(header);
    }

    private void showLogo() {
        sideBar.setLogo(new Label(FontAwesome.ROCKET.getHtml(), com.vaadin.shared.ui.label.ContentMode.HTML));
    }

    private void hideLogo() {
        sideBar.setLogo(null);
    }

    @Override
    protected AbstractSideBar getSideBar() {
        return sideBar;
    }
}
