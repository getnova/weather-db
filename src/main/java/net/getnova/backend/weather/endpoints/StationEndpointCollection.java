package net.getnova.backend.weather.endpoints;

import net.getnova.backend.api.annotations.ApiEndpoint;
import net.getnova.backend.api.annotations.ApiEndpointCollection;
import net.getnova.backend.api.annotations.ApiParameter;
import net.getnova.backend.api.data.ApiResponse;
import net.getnova.backend.api.data.ApiResponseStatus;
import net.getnova.backend.weather.models.Station;
import net.getnova.backend.weather.reposetories.StationRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.UUID;

@Singleton
@ApiEndpointCollection(id = "station", description = "Handle all stations.")
public final class StationEndpointCollection {

    @Inject
    private StationRepository stationRepository;

    @ApiEndpoint(id = "list", description = "Lists all stations.")
    private ApiResponse list() {
        return new ApiResponse(ApiResponseStatus.OK, this.stationRepository.list());
    }

    @ApiEndpoint(id = "add", description = "Add a station.")
    private ApiResponse add(@ApiParameter(id = "name", description = "The name of the station.") final String name) {
        return new ApiResponse(ApiResponseStatus.OK, this.stationRepository.save(new Station(name)));
    }

    @ApiEndpoint(id = "update", description = "Update a station.")
    private ApiResponse update(@ApiParameter(id = "id", description = "The id of the existing station.") final UUID id,
                               @ApiParameter(id = "name", description = "The new name of the station.") final String name) {
        final Station station = this.stationRepository.find(id);
        station.setName(name);
        return new ApiResponse(ApiResponseStatus.OK, this.stationRepository.update(station));
    }

    @ApiEndpoint(id = "delete", description = "Delete a station.")
    private ApiResponse delete(@ApiParameter(id = "id", description = "The id of the station, witch should be deleted.") final UUID id) {
        return this.stationRepository.delete(id) ? new ApiResponse(ApiResponseStatus.OK) : new ApiResponse(ApiResponseStatus.NOT_FOUND, "STATION");
    }
}
