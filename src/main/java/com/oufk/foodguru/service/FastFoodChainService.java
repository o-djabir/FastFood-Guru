package com.oufk.foodguru.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oufk.foodguru.models.FastFoodChain;
import com.oufk.foodguru.repository.FastFoodChainRepository;


/*
 * This class uses the FastFoodChain repository to retrieve and add the needed data from and to the database. It will interact directly with the controller.
 */

@Service
public class FastFoodChainService {
	@Autowired
	FastFoodChainRepository fastFoodChainRepo;
	
	public List<FastFoodChain> getAllRestaurants() {
		List<FastFoodChain> all = new ArrayList<FastFoodChain>();
		fastFoodChainRepo.findAll().forEach(restaurant -> all.add(restaurant));
		return all;
	}
	public FastFoodChain getRestaurantByName(String name) {
		return fastFoodChainRepo.findByName(name).get();
	}
	public FastFoodChain getRestaurantById(long id) {
		return fastFoodChainRepo.findById(id).get();
	}
	public FastFoodChain createRestaurant(String name, String address, String logo) {
		FastFoodChain f = new FastFoodChain();
		f.setAddress(address);
		f.setName(name);
		f.setLogo(logo);
		return fastFoodChainRepo.save(f);
	}
	public ArrayList<String> getAllRestaurantsNames() {
		List<FastFoodChain> list = this.getAllRestaurants();
		ArrayList<String> names = new ArrayList<String>();
		for (FastFoodChain restaurant : list) {
			names.add(restaurant.getName());
		}
		return names;
	}
	
}
