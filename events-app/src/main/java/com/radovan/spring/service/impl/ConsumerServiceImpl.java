package com.radovan.spring.service.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.radovan.spring.converter.TempConverter;
import com.radovan.spring.dto.ConsumerDto;
import com.radovan.spring.dto.UserDto;
import com.radovan.spring.entity.ConsumerEntity;
import com.radovan.spring.entity.RoleEntity;
import com.radovan.spring.entity.UserEntity;
import com.radovan.spring.exceptions.ExistingEmailException;
import com.radovan.spring.form.RegistrationForm;
import com.radovan.spring.repository.ConsumerRepository;
import com.radovan.spring.repository.RoleRepository;
import com.radovan.spring.repository.UserRepository;
import com.radovan.spring.service.ConsumerService;

@Service
@Transactional
public class ConsumerServiceImpl implements ConsumerService {

	@Autowired
	private ConsumerRepository consumerRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private TempConverter tempConverter;

	@Override
	public ConsumerDto storeConsumer(RegistrationForm form) {
		// TODO Auto-generated method stub
		UserDto userDto = form.getUser();
		Optional<UserEntity> testUser = Optional.ofNullable(userRepository.findByEmail(userDto.getEmail()));
		if (testUser.isPresent()) {
			Error error = new Error("Email exists");
			throw new ExistingEmailException(error);
		}

		RoleEntity role = roleRepository.findByRole("ROLE_USER");
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		userDto.setEnabled((byte) 1);
		List<RoleEntity> roles = new ArrayList<RoleEntity>();
		roles.add(role);
		UserEntity userEntity = tempConverter.userDtoToEntity(userDto);
		userEntity.setRoles(roles);
		userEntity.setEnabled((byte) 1);
		UserEntity storedUser = userRepository.save(userEntity);
		List<UserEntity> users = new ArrayList<UserEntity>();
		users.add(storedUser);
		role.setUsers(users);
		roleRepository.saveAndFlush(role);
		
		ConsumerDto consumer = form.getConsumer();
		consumer.setUserId(storedUser.getId());
		consumer.setRegistrationDate(Timestamp.valueOf(LocalDateTime.now()));
		ConsumerEntity consumerEntity = tempConverter.consumerDtoToEntity(consumer);
		ConsumerEntity storedConsumer = consumerRepository.save(consumerEntity);
		ConsumerDto returnValue = tempConverter.consumerEntityToDto(storedConsumer);
		return returnValue;
	}

	@Override
	public ConsumerDto getConsumerById(Integer consumerId) {
		// TODO Auto-generated method stub
		ConsumerDto returnValue = null;
		Optional<ConsumerEntity> consumerOpt = consumerRepository.findById(consumerId);
		if (consumerOpt.isPresent()) {
			returnValue = tempConverter.consumerEntityToDto(consumerOpt.get());
		}
		return returnValue;
	}

	@Override
	public ConsumerDto getConsumerByUserId(Integer userId) {
		// TODO Auto-generated method stub
		ConsumerDto returnValue = null;
		Optional<ConsumerEntity> consumerOpt = Optional.ofNullable(consumerRepository.findByUserId(userId));
		if (consumerOpt.isPresent()) {
			returnValue = tempConverter.consumerEntityToDto(consumerOpt.get());
		}
		return returnValue;
	}

	@Override
	public void deleteConsumer(Integer consumerId) {
		// TODO Auto-generated method stub
		consumerRepository.deleteById(consumerId);
		consumerRepository.flush();
	}

	@Override
	public List<ConsumerDto> listAll() {
		// TODO Auto-generated method stub
		List<ConsumerDto> returnValue = new ArrayList<>();
		Optional<List<ConsumerEntity>> allConsumersOpt = Optional.ofNullable(consumerRepository.findAll());
		if (!allConsumersOpt.isEmpty()) {
			allConsumersOpt.get().forEach((consumer) -> {
				ConsumerDto consumerDto = tempConverter.consumerEntityToDto(consumer);
				returnValue.add(consumerDto);
			});
		}
		return returnValue;
	}

}
