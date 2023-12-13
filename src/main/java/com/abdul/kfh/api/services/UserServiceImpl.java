package com.abdul.kfh.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.abdul.kfh.api.request.UserFilter;
import com.abdul.kfh.entity.User;
import com.abdul.kfh.repository.UserRepository;

import jakarta.persistence.criteria.Predicate;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> getUsersBy(UserFilter userFilter) {
		List<User> userList = null;
		if(userFilter != null) {
			 userList = getUserByFilter(userFilter);
		}else {
			 userList = userRepository.findAll();
		}
		return userList;
	}
	
	
	private List<User> getUserByFilter(UserFilter filter) {

		Specification<User> spec = (root, query, criteriaBuilder) -> {
			Predicate predicate = criteriaBuilder.conjunction();
			if (filter != null) {

				
				if (filter.userid > 0 ) {
					predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("id"), filter.userid ));
				}

				if (filter.name != null && !"".equals(filter.name.trim())) {
					predicate = criteriaBuilder.and(predicate,
							criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
									"%" + filter.name.toLowerCase() + "%"));
					
				}
				
				if (filter.status != null && !"".equals(filter.status.trim())) {
					predicate = criteriaBuilder.and(predicate,
							criteriaBuilder.equal(root.get("status"), filter.status));
					
				}
				
				if (filter.gender != null && !"".equals(filter.gender.trim())) {
					predicate = criteriaBuilder.and(predicate,
							criteriaBuilder.equal(root.get("gender"), filter.gender));
					
				}
				
				if (filter.username != null && !"".equals(filter.username.trim())) {
					predicate = criteriaBuilder.and(predicate,
							criteriaBuilder.equal(root.get("username"), filter.username));
					
				}
				
				

			}
			return predicate;
		};

		List<User> userList = userRepository.findAll(spec);

		return userList;

	}
	
	
	
	
	
}
