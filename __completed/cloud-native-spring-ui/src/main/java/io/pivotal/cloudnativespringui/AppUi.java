package io.pivotal.cloudnativespringui;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import io.pivotal.cloudnativespring.domain.City;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;

@Route(value = "ui")
@Theme(Lumo.class)
public class AppUi extends VerticalLayout {
    private final CloudNativeSpringUiApplication.CityClient client;
    final Grid<City> grid;

    @Autowired
    public AppUi(CloudNativeSpringUiApplication.CityClient client) {
        this.client = client;
        this.grid = new Grid(City.class);
        add(grid);
        getCities();
    }

    private void getCities() {
        Collection<City> collection = new ArrayList<>();
        client.getCities().forEach(collection::add);
        grid.setItems(collection);
    }
}