package io.roadmapp.service;

import io.roadmapp.model.RoadMapp;
import io.roadmapp.model.User;

import java.util.List;

public interface RoadMappService {

    RoadMapp getRoadMapp(int roadmapp_id);

    void saveRoadMapp(RoadMapp rm);

    void updateRoadMapp(RoadMapp rm);

    void deleteRoadMapp(int roadmapp_id);

    List<RoadMapp> getRoadMapps();

}
