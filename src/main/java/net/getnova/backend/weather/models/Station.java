package net.getnova.backend.weather.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.getnova.backend.sql.model.TableModelAutoId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "weather_station")
public class Station extends TableModelAutoId {

    @Column(name = "name", nullable = false, updatable = true, length = 128)
    private String name;
}
