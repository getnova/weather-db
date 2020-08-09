package net.getnova.backend.weather;

import net.getnova.backend.api.handler.playground.PlaygroundService;
import net.getnova.backend.api.handler.websocket.WebsocketApiService;
import net.getnova.backend.service.Service;
import net.getnova.backend.service.event.InitService;
import net.getnova.backend.service.event.InitServiceEvent;
import net.getnova.backend.service.event.PostInitService;
import net.getnova.backend.service.event.PostInitServiceEvent;
import net.getnova.backend.service.event.PreInitService;
import net.getnova.backend.service.event.PreInitServiceEvent;
import net.getnova.backend.sql.SqlService;
import net.getnova.backend.weather.endpoints.StationEndpointCollection;
import net.getnova.backend.weather.models.Station;
import net.getnova.backend.weather.reposetories.StationRepository;
import net.getnova.backend.weather.reposetories.StationRepositoryImpl;

import javax.inject.Inject;
import javax.inject.Singleton;

@Service(id = "weather-db", depends = {SqlService.class, WebsocketApiService.class, PlaygroundService.class})
@Singleton
public class WeatherDatabase {

    @Inject
    private SqlService sqlService;

    @Inject
    private WebsocketApiService websocketApiService;

    @Inject
    private PlaygroundService playgroundService;

    @PreInitService
    private void preInit(final PreInitServiceEvent event) {
        this.sqlService.addEntity(Station.class);
        event.getBinder().bind(StationRepository.class).to(StationRepositoryImpl.class);
    }

    @InitService
    private void init(final InitServiceEvent event) {
        this.websocketApiService.addEndpointCollection(StationEndpointCollection.class);
    }

    @PostInitService
    private void postInit(final PostInitServiceEvent event) {
        this.playgroundService.addCollections(this.websocketApiService.getCollectionsData());
    }
}
