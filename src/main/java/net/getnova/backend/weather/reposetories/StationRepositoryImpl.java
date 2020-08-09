package net.getnova.backend.weather.reposetories;

import net.getnova.backend.sql.repository.SqlRepositoryImpl;
import net.getnova.backend.weather.models.Station;

import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public final class StationRepositoryImpl extends SqlRepositoryImpl<Station, UUID> implements StationRepository {

    public StationRepositoryImpl() {
        super(Station.class);
    }
}
