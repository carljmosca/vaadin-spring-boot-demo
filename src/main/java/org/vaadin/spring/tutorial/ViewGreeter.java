package org.vaadin.spring.tutorial;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import java.io.Serializable;

@SpringComponent
@ViewScope
public class ViewGreeter implements Serializable {
    public String sayHello() {
        return "Hello from a view scoped bean " + toString();
    }
}
