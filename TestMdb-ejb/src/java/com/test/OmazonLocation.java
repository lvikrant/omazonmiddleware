/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vikaram
 */
@Entity
@Table(name = "omazon_location")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OmazonLocation.findAll", query = "SELECT o FROM OmazonLocation o"),
    @NamedQuery(name = "OmazonLocation.findByLocationId", query = "SELECT o FROM OmazonLocation o WHERE o.locationId = :locationId"),
    @NamedQuery(name = "OmazonLocation.findByLocationCity", query = "SELECT o FROM OmazonLocation o WHERE o.locationCity = :locationCity"),
    @NamedQuery(name = "OmazonLocation.findByLocationLat", query = "SELECT o FROM OmazonLocation o WHERE o.locationLat = :locationLat"),
    @NamedQuery(name = "OmazonLocation.findByLocationLong", query = "SELECT o FROM OmazonLocation o WHERE o.locationLong = :locationLong")})
public class OmazonLocation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "location_id")
    private Integer locationId;
    @Size(max = 45)
    @Column(name = "location_city")
    private String locationCity;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "location_lat")
    private BigDecimal locationLat;
    @Column(name = "location_long")
    private BigDecimal locationLong;

    public OmazonLocation() {
    }

    public OmazonLocation(Integer locationId) {
        this.locationId = locationId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public BigDecimal getLocationLat() {
        return locationLat;
    }

    public void setLocationLat(BigDecimal locationLat) {
        this.locationLat = locationLat;
    }

    public BigDecimal getLocationLong() {
        return locationLong;
    }

    public void setLocationLong(BigDecimal locationLong) {
        this.locationLong = locationLong;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (locationId != null ? locationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OmazonLocation)) {
            return false;
        }
        OmazonLocation other = (OmazonLocation) object;
        if ((this.locationId == null && other.locationId != null) || (this.locationId != null && !this.locationId.equals(other.locationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.test.OmazonLocation[ locationId=" + locationId + " ]";
    }
    
}
