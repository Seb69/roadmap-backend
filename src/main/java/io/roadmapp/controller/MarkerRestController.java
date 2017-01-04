package io.roadmapp.controller;

import io.roadmapp.model.Marker;
import io.roadmapp.model.RoadMapp;
import io.roadmapp.model.RoadMappMarkersJoinTable;
import io.roadmapp.service.MarkerService;
import io.roadmapp.service.RoadMappMarkersJoinTableService;
import io.roadmapp.service.RoadMappService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * RM stands for RoadMapp
 */
@RestController
@RequestMapping("/api/markers")
public class MarkerRestController {

    @Autowired
    MarkerService MarkerService;
    @Autowired
    RoadMappMarkersJoinTableService RMMservice;

    /*
     * This method will list all existing RM.
     */
    @RequestMapping(value = { "" }, method = RequestMethod.GET)
    public @ResponseBody List<Marker> getMarkers() {
        System.out.println("Fetching Marker...");
        List<Marker> markers = MarkerService.getMarkers();
        if(markers.isEmpty()){
            return null;
        }
        return markers;
    }


    /*
     * This method will return RM via the param id.
     */
    @RequestMapping(value = { "/{roadmapp_id}/{index}" }, method = RequestMethod.GET)
    public @ResponseBody Marker getMarker(@PathVariable("roadmapp_id") int id,@PathVariable("index") int index ) {
        System.out.println("Fetching Marker roadmapp_id " + id);
        System.out.println("Fetching Marker index " + index);
        Marker marker = MarkerService.getMarker(id,index);
        if (marker == null) {
            System.out.println("Marker with id " + id + " and/or "+ index +" not found");
            return null;
        }
        return marker;
    }

    /*
 * This method will return RM via the param id.
 */
    @RequestMapping(value = { "/{id}" }, method = RequestMethod.GET)
    public @ResponseBody Marker getMarker(@PathVariable("id") int id) {
        System.out.println("Fetching Marker " + id);

        Marker marker = MarkerService.getMarker(id);
        if (marker == null) {
            System.out.println("Marker with id " + id + " not found");
            return null;
        }
        return marker;
    }

    /*
     * This method will provide the medium to add a new RM.
     */
    @RequestMapping(value = { "" }, method = RequestMethod.POST)
    public @ResponseBody Marker createMarker(@RequestBody Marker marker, UriComponentsBuilder ucBuilder) {

        //create marker
        System.out.println("Creating Marker...");
        System.out.println(marker.getLat());
        MarkerService.saveMarker(marker);

        //HttpHeaders headers = new HttpHeaders();
        //headers.setLocation(ucBuilder.path("/users/{id}").buildAndExpand(marker.getId()).toUri());

        //create association in join table
        RoadMappMarkersJoinTable rmm = new RoadMappMarkersJoinTable(marker.getRoadmappId(), marker.getId());
        RMMservice.saveAssociation(rmm);

        return marker;
    }

    /*
     * This method will update user via id.
     */
    /*@RequestMapping(value = { "{id}" }, method = RequestMethod.PUT)
    public @ResponseBody Marker updateMarker(@PathVariable("id") int id, @RequestBody Marker marker) {
        System.out.println("Updating Marker " + id);
        Marker currentMarker= MarkerService.getMarker(id);

        if (currentMarker == null) {
            System.out.println("RoadMapp with id " + id + " not found");
            return null;
        }

        currentMarker.setLat(marker.getLat());
        currentMarker.setLng(marker.getLng());
        MarkerService.updateMarker(currentMarker);
        return currentMarker;
    }*/

    @RequestMapping(value = { "{id}" }, method = RequestMethod.PUT)
    public @ResponseBody Marker updateMarker(@PathVariable("id") int id, @RequestBody Marker marker) {
        System.out.println("Updating Marker " + id);
        Marker currentMarker= MarkerService.getMarker(id);

        if (currentMarker == null) {
            System.out.println("Marker" + id + " not found");
            return null;
        }

        currentMarker.setLat(marker.getLat());
        currentMarker.setLng(marker.getLng());
        currentMarker.setmIndex(marker.getmIndex());
        currentMarker.setTitle(marker.getTitle());
        MarkerService.updateMarker(currentMarker);
        return currentMarker;
    }

    @RequestMapping(value = { "{id}" }, method = RequestMethod.DELETE)
    public @ResponseBody void deleteMarker(@PathVariable("id") int id) {
        System.out.println("deleting Marker " + id);

        MarkerService.deleteMarker(id);

        RMMservice.deleteAssociation(id);

    }

}