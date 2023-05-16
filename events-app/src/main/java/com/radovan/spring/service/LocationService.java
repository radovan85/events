package com.radovan.spring.service;

import java.util.List;

import com.radovan.spring.dto.LocationDto;

public interface LocationService {

	LocationDto addLocation(LocationDto location);

	LocationDto getLocationById(Integer locationId);

	void deleteLocation(Integer locationId);

	List<LocationDto> listAll();
}
