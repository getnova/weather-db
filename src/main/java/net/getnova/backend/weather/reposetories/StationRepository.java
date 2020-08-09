package net.getnova.backend.weather.reposetories;

import net.getnova.backend.sql.repository.SqlRepository;
import net.getnova.backend.weather.models.Station;

import java.util.UUID;

public interface StationRepository extends SqlRepository<Station, UUID> {
}
