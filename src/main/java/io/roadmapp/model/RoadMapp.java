package io.roadmapp.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


@NamedNativeQueries({
        @NamedNativeQuery(
                name="@GET_ROADMAPP_BY_ID",
                query="select * from ROADMAPP where roadmapp_id = :id",
                resultClass = RoadMapp.class
        ),
        @NamedNativeQuery(
        name="@DELETE_ROADMAPP_BY_ID",
        query="delete from ROADMAPP where roadmapp_id = :roadmapp_id"
        )
})

@Entity
@Table(name="ROADMAPP")
public class RoadMapp {

    @Transient
    private List<Integer> markersIds;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roadmapp_id", unique = true, nullable = false)
    private int roadmappId;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Size(min=1, max=50)
    @Column(name = "title", nullable = false)
    private String title;

    @NotEmpty
    @Column(name = "description")
    private String description;

    //other way : not working
    /*******************************************************************

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "ROADMAPP_MARKERS",
            joinColumns = @JoinColumn(name = "ROADMAPP_ID"),
            inverseJoinColumns = @JoinColumn(name = "MARKER_ID")
    )
    private List<Marker> markers;

    public List<Marker> getMarkers() { return markers; }
    public void setMarkers(List markers) { this.markers = markers; }

    ********************************************************************/

    public RoadMapp(List<Integer> markersIds, String title, String description, Integer userId) {
        this.markersIds = markersIds;
        this.title = title;
        this.description = description;
        this.userId = userId;
    }

    public RoadMapp() {
    }

    public int getUserId() { return userId; }

    public void setUserId(Integer userId) { this.userId = userId; }

    public int getId() { return roadmappId; }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setRoadmappId(int roadmappId) {
        this.roadmappId = roadmappId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getMarkersIds() {
        return markersIds;
    }

    public void setMarkersIds(List<Integer> markerId) {
        this.markersIds = new ArrayList<Integer>();
        this.markersIds.addAll(markerId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof RoadMapp))
            return false;
        RoadMapp other = (RoadMapp) obj;
        if (roadmappId != other.roadmappId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "RoadMapp{" +
                "roadmappId=" + roadmappId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}