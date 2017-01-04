package io.roadmapp.dao;

import io.roadmapp.model.Marker;
import io.roadmapp.model.RoadMapp;

import java.util.List;

public interface MarkerDao {

    Marker getMarker(int marker_id);

    Marker getMarker(int marker_id, int index);

    void saveMarker(Marker rm);

    void deleteMarker(int marker_id);

    List<Marker> getMarkers();

}