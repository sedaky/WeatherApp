package com.havaDurumu.havaDurumu;

import com.vaadin.data.Binder;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;


public class Layout extends HorizontalLayout {
    private final CheckBox done;
    private final TextField text;

    public Layout(city city, ChangeListener changeListener) {
        setWidth("100%");
        setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

        done = new CheckBox();
        text = new TextField();
        text.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
        text.setValueChangeMode(ValueChangeMode.BLUR);

        Binder<city> binder = new Binder<>(city.class);
        //Binds fields in this class to those in city based on their names
        binder.bindInstanceFields(this);
        // The following does the same more explicitly
        // binder.bind(text, city::getText, city::setText);
        // binder.bind(done, city::isDone, city::setDone);

        binder.setBean(city);

        addComponent(done);
        addComponentsAndExpand(text);

        binder.addValueChangeListener(event -> changeListener.cityChanged(city));
    }
}
