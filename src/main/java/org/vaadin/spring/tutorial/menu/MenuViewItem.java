/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vaadin.spring.tutorial.menu;

import com.vaadin.navigator.View;
import com.vaadin.server.Resource;
import lombok.Data;

/**
 *
 * @author moscac
 */
@Data
public class MenuViewItem {
    
    private View view;
    private String name;
    private String description;
    private Resource icon;
        
}
