package com.havaDurumu.havaDurumu;
import com.vaadin.annotations.Theme;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
@Theme("valo")
public class weatherUI extends UI {

    private VerticalLayout layout;
    @Autowired
    private Service service;
    private Weather weather;

    @Autowired
    List cityList;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setupLayout();
        addHeader();
        addForm();
        addTodoList();
        addActionButtons();
    }

    private void setupLayout() {
        layout = new VerticalLayout();
        layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setContent(layout);
    }

    private void addHeader() {
        Label header = new Label("HAVA DURUMU");
        header.addStyleName(ValoTheme.LABEL_H1);
        layout.addComponent(header);

    }

    private void addForm() {
        HorizontalLayout formLayout = new HorizontalLayout();
        formLayout.setWidth("80%");

        TextField taskField = new TextField();
        taskField.focus();
        Button addButton = new Button("");

        formLayout.addComponentsAndExpand(taskField);
        formLayout.addComponent(addButton);
        layout.addComponent(formLayout);

        addButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        addButton.setIcon(VaadinIcons.PLUS);


            addButton.addClickListener(click -> {
                weather = service.getWeather(taskField.getValue());
                cityList.addCity(new city(weather.getName() + "  " +String.valueOf(weather.getMain().toString()) + "°C"));

               taskField.setValue("");
               taskField.focus();
            });
            addButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);


    }


private void addTodoList() {
        layout.addComponent(cityList);
    }

    private void addActionButtons() {
        Button deleteButton = new Button("seçilen şehiri sil");

        deleteButton.addClickListener(click->cityList.deleteCompleted());

        layout.addComponent(deleteButton);

    }
}
