package io.roadmapp.dao;

import io.roadmapp.model.RoadMapp;

import java.util.List;

public interface RoadMappDao {

    RoadMapp getRoadMapp(int roadmapp_id);

    void saveRoadMapp(RoadMapp rm);

    void deleteRoadMapp(int roadmapp_id);

    List<RoadMapp> getRoadMapps();

}