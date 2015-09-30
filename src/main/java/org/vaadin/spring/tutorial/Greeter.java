package org.vaadin.spring.tutorial;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import java.io.Serializable;

@SpringComponent
@UIScope
public class Greeter implements Serializable {
    public String sayHello() {
        return "Hello from bean " + toString();
    }
}