package io.roadmapp.service;

import io.roadmapp.dao.MarkerDao;
import io.roadmapp.dao.RoadMappDao;
import io.roadmapp.model.Marker;
import io.roadmapp.model.RoadMapp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("markerService")
@Transactional
public class MarkerServiceImpl implements MarkerService {

    @Autowired
    private MarkerDao dao;


    public Marker getMarker(int id) {
        return dao.getMarker(id);
    }

    public Marker getMarker(int marker_id, int index) {
        return dao.getMarker(marker_id, index);
    }

    public void saveMarker(Marker marker) {
        dao.saveMarker(marker);
    }

    public void updateMarker(Marker marker) {

        Marker entity = dao.getMarker(marker.getId());
        if(entity!=null){
            entity.setTitle(marker.getTitle());
            entity.setmIndex(marker.getmIndex());
            entity.setLat(marker.getLat());
            entity.setLng(marker.getLng());
        }

    }

    public void deleteMarker(int marker_id) {
        dao.deleteMarker(marker_id);
    }

    public List<Marker> getMarkers() {
        return dao.getMarkers();
    }
}
