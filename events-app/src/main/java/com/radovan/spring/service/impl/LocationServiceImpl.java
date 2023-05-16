package com.radovan.spring.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.radovan.spring.converter.TempConverter;
import com.radovan.spring.dto.LocationDto;
import com.radovan.spring.entity.LocationEntity;
import com.radovan.spring.repository.LocationRepository;
import com.radovan.spring.service.LocationService;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private TempConverter tempConverter;

	@Override
	public LocationDto addLocation(LocationDto location) {
		// TODO Auto-generated method stub
		LocationEntity locationEntity = tempConverter.locationDtoToEntity(location);
		LocationEntity storedLocation = locationRepository.save(locationEntity);
		LocationDto returnValue = tempConverter.locationEntityToDto(storedLocation);
		return returnValue;
	}

	@Override
	public LocationDto getLocationById(Integer locationId) {
		// TODO Auto-generated method stub
		LocationDto returnValue = null;
		Optional<LocationEntity> locationOpt = locationRepository.findById(locationId);
		if (locationOpt.isPresent()) {
			returnValue = tempConverter.locationEntityToDto(locationOpt.get());
		}
		return returnValue;
	}

	@Override
	public void deleteLocation(Integer locationId) {
		// TODO Auto-generated method stub
		locationRepository.deleteById(locationId);
		locationRepository.flush();
	}

	@Override
	public List<LocationDto> listAll() {
		// TODO Auto-generated method stub
		List<LocationDto> returnValue = new ArrayList<>();
		Optional<List<LocationEntity>> allLocationsOpt = Optional.ofNullable(locationRepository.findAll());
		if (!allLocationsOpt.isEmpty()) {
			allLocationsOpt.get().forEach((location) -> {
				LocationDto locationDto = tempConverter.locationEntityToDto(location);
				returnValue.add(locationDto);
			});
		}
		return returnValue;
	}

}
