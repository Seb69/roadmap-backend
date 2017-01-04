package io.roadmapp.service;

import io.roadmapp.model.Marker;
import io.roadmapp.model.User;

import java.util.List;

public interface MarkerService {

    Marker getMarker(int id);

    Marker getMarker(int id, int index);

    void saveMarker(Marker user);

    void updateMarker(Marker user);

    void deleteMarker(int id);

    List<Marker> getMarkers();

}