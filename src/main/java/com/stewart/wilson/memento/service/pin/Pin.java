package com.stewart.wilson.memento.service.pin;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "city", "state", "country", "latitude", "longitude", "user", "imageLocation", "creationDate", "lastUpdateDate", "isVideo", "rating", "tags" })
@Entity
@Table(name = "allpins")
public class Pin {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pin_id")
	private Integer pinId;
	@Column(name="city")
	@JsonProperty("city")
	private String city;
	@Column(name="state")
	@JsonProperty("state")
	private String state;
	@Column(name="country")
	@JsonProperty("country")
	private String country;
	@Column(name="latitude")
	@JsonProperty("latitude")
	private double latitude;
	@Column(name="longitude")
	@JsonProperty("longitude")
	private double longitude;
	@Column(name="viewable_range")
	@JsonProperty("viewable_range")
	private int viewableRange;
	@Column(name="user")
	@JsonProperty("user")
	private String user;
	@Column(name="image_location")
	@JsonProperty("imageLocation")
	private String imageLocation;
	@Column(name="description")
	@JsonProperty("description")
	private String description;
	@GeneratedValue
	@Column(name="creation_date")
	@JsonProperty("creationDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date creationDate;
	@GeneratedValue
	@Column(name="last_update_date")
	@JsonProperty("lastUpdateDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date lastUpdateDate;
	@Column(name="is_video")
	@JsonProperty("isVideo")
	private boolean isVideo;
	@Column(name="rating")
	@JsonProperty("rating")
	private int rating;
	@Column(name="tags")
	@JsonProperty("tags")
	private String tags;

	
	public Integer getPinId() {
		return pinId;
	}

	public void setPinId(Integer pinId) {
		this.pinId = pinId;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getViewableRange() {
		return viewableRange;
	}

	public void setViewableRange(int viewableRange) {
		this.viewableRange = viewableRange;
	}

	public String getImageLocation() {
		return imageLocation;
	}

	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	
	public boolean isVideo() {
		return isVideo;
	}

	public void setIsVideo(boolean isVideo) {
		this.isVideo = isVideo;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	
}