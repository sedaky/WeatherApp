package com.havaDurumu.havaDurumu;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@UIScope
@SpringComponent
class List extends VerticalLayout implements ChangeListener {
    @Autowired
    Repository repository;
    private java.util.List<city> cities;

    @PostConstruct
    void init() {
        setWidth("80%");

        update();
    }

    private void update() {
        setCities(repository.findAll());
    }

    private void setCities(java.util.List<city> cities) {
        this.cities = cities;
        removeAllComponents();
        cities.forEach(city -> addComponent(new Layout(city, this)));
    }

     void addCity(city city) {
        repository.save(city);
        update();
    }

    @Override
    public void cityChanged(city city) {
        addCity(city);
    }


    public void deleteCompleted() {
        repository.deleteByDone(true);
        update();
    }
}
