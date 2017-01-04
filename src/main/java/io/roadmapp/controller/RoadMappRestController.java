package io.roadmapp.controller;

import io.roadmapp.model.CurrentUserDetails;
import io.roadmapp.model.RoadMapp;
import io.roadmapp.service.RoadMappMarkersJoinTableService;
import io.roadmapp.service.RoadMappService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/roadmapps")
public class RoadMappRestController {

    @Autowired
    RoadMappService service;
    @Autowired
    RoadMappMarkersJoinTableService roadMappMarkersJoinTableService;

    /*
     * This method will list all existing RM.
     */
    @RequestMapping(value = { "" }, method = RequestMethod.GET)
    public @ResponseBody List<RoadMapp> getRoadMapp() {
        System.out.println("Fetching roadmapp...");
        List<RoadMapp> roadMapps = service.getRoadMapps();
        if(roadMapps.isEmpty()){
            return null;
        }
        return roadMapps;
    }


    /*
     * This method will return RM via the param id.
     */
    @PostAuthorize("hasPermission(returnObject.getUserId(), principal)")
    @RequestMapping(value = { "/{id}" }, method = RequestMethod.GET)
    public @ResponseBody RoadMapp getRoadMapp(@PathVariable("id") int id) {
        System.out.println("Fetching roadmapp " + id);
        RoadMapp rm = service.getRoadMapp(id);
        if (rm == null) {
            System.out.println("RoadMapp with id " + id + " not found");
            return null;
        }
        return rm;
    }

    /*
     * This method will provide the medium to add a new RM.
     */
    @RequestMapping(value = { "" }, method = RequestMethod.POST)
    public @ResponseBody RoadMapp createRoadMapp(@RequestBody RoadMapp rm, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating RoadMapp...");
        CurrentUserDetails currentUser = (CurrentUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        rm.setUserId(currentUser.getId());
        service.saveRoadMapp(rm);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/roadmapps/{id}").buildAndExpand(rm.getId()).toUri());
        return rm;
    }

    /*
     * This method will update user via id.
     */
    @PreAuthorize("hasPermission(#rm.getUserId(), principal)")
    @RequestMapping(value = { "{id}" }, method = RequestMethod.PUT)
    public @ResponseBody RoadMapp updateRoadMapp(@PathVariable("id") int id, @RequestBody RoadMapp rm) {
        System.out.println("Updating RoadMapp " + id);
        RoadMapp currentRm = service.getRoadMapp(id);
        if (currentRm == null) {
            System.out.println("RoadMapp with id " + id + " not found");
            return null;
        }

        currentRm.setTitle(rm.getTitle());
        currentRm.setDescription(rm.getDescription());
        service.updateRoadMapp(currentRm);

        return currentRm;
    }

    @RequestMapping(value = { "{id}" }, method = RequestMethod.DELETE)
    public @ResponseBody void deleteMarker(@PathVariable("id") int id) {
        System.out.println("deleting RoadMapp " + id);

       service.deleteRoadMapp(id);



    }

}