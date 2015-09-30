/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vaadin.spring.tutorial.menu;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.internal.Conventions;
import com.vaadin.spring.navigator.SpringViewProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author moscac
 */
@Component
public class ViewManager {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(SpringViewProvider.class);
    private final Map<String, Set<String>> viewNameToBeanNamesMap = new ConcurrentHashMap<>();
    @Autowired
    ApplicationContext applicationContext;

    public String[] getViewNames() {
        String[] names = applicationContext.getBeanNamesForType(View.class);
        return names;
    }

    public List<MenuViewItem> getViews() {
        int count = 0;
        List<MenuViewItem> menuViewItems = new ArrayList<>();
        final String[] viewBeanNames = applicationContext
                .getBeanNamesForAnnotation(SpringView.class);
        for (String beanName : viewBeanNames) {
            final Class<?> type = applicationContext.getType(beanName);
            if (View.class.isAssignableFrom(type)) {
                final SpringView annotation = applicationContext
                        .findAnnotationOnBean(beanName, SpringView.class);
                final String viewName = getViewNameFromAnnotation(type,
                        annotation);
                LOGGER.debug("Found SpringView bean [{}] with view name [{}]",
                        beanName, viewName);
                if (applicationContext.isSingleton(beanName)) {
                    throw new IllegalStateException("SpringView bean ["
                            + beanName + "] must not be a singleton");
                }
                Set<String> beanNames = viewNameToBeanNamesMap.get(viewName);
                if (beanNames == null) {
                    beanNames = new ConcurrentSkipListSet<>();
                    viewNameToBeanNamesMap.put(viewName, beanNames);
                }
                beanNames.add(beanName);
                count++;
                MenuViewItem menuViewItem = new MenuViewItem();
                menuViewItem.setName(viewName);
                menuViewItem.setDescription(beanName);
                menuViewItems.add(menuViewItem);
            } else {
                LOGGER.error("The view bean [{}] does not implement View",
                        beanName);
                throw new IllegalStateException("SpringView bean [" + beanName
                        + "] must implement View");
            }
        }
        return menuViewItems;
    }

     protected String getViewNameFromAnnotation(Class<?> beanClass,
            SpringView annotation) {
        return Conventions.deriveMappingForView(beanClass, annotation);
    }
}
