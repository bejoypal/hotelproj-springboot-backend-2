package com.hotel.backend.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.backend.exception.ResourceNotFound;
import com.hotel.backend.model.Cuisine;
import com.hotel.backend.repository.CuisineRepository;

@RestController
@RequestMapping("/api/v1")
public class CuisineController implements Filter {

	@Autowired
	CuisineRepository cuisinerepo;
	@GetMapping("/getcuisines")
	List<Cuisine>	getAllcuisine(){
		return	cuisinerepo.findAll();
	}
	
	@Override
	public void doFilter(ServletRequest ServletReq, ServletResponse ServletResp, FilterChain FiltChn) 
	throws IOException, ServletException{
		HttpServletResponse	HttpServletRes	=	((HttpServletResponse)ServletResp);
		HttpServletRequest	HttpServletReq	=	((HttpServletRequest)ServletReq);
		HttpServletRes.setHeader("Access-Control-Allow-Origin", "*");//HttpServletReq.getHeader("Origin"));
		HttpServletRes.setHeader("Access-Control-Allow-Headers", "*");//HttpServletReq.getHeader("Origin"));
		HttpServletRes.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");//HttpServletReq.getHeader("Origin"));
//		HttpServletRes.setHeader("Access-Control-Allow-Credentials","*");// HttpServletReq.getHeader("Origin"));
		
		FiltChn.doFilter(HttpServletReq,HttpServletRes);
	}
	
	//Create Cuisine Rest API
	@PostMapping("/getcuisines")
	Cuisine	addCuisine(@RequestBody Cuisine	newCuisine) {
		System.out.println("name.."+newCuisine.getCuisineName());
		System.out.println("price.."+newCuisine.getCuisinePrice());
		return	cuisinerepo.save(newCuisine);
	}
	
	@GetMapping("/getcuisines/{id}")
	ResponseEntity<Cuisine>	getCuisine(@PathVariable	long id) {
		Cuisine	cuisine	=cuisinerepo.findById(id).orElseThrow(()-> new	ResourceNotFound(id+"..ID of Cuisine not there"));
		return	ResponseEntity.ok(cuisine);
	}
	
	@PutMapping("/getcuisines/{id}")
	ResponseEntity<Cuisine>	updateCuisine(@PathVariable long	id,@RequestBody Cuisine	cuisn){
		Cuisine	cuisine	=cuisinerepo.findById(id).orElseThrow(()-> new	ResourceNotFound(id+"..ID of Cuisine not there"));

		cuisine.setCuisineName(cuisn.getCuisineName());
		cuisine.setCuisinePrice(cuisn.getCuisinePrice());
		Cuisine	updCuisine	=	cuisinerepo.save(cuisine);
		return	ResponseEntity.ok(cuisine);
		
	}
	
	@DeleteMapping("/getcuisines/{id}")
	ResponseEntity<Map<String,Boolean>>	delCuisine(@PathVariable long	id){
		Cuisine	cuisine	=cuisinerepo.findById(id).orElseThrow(()-> new	ResourceNotFound(id+"..ID of Cuisine not there"));

		System.out.println("..cuisine.."+cuisine);
		cuisinerepo.delete(cuisine);
		Map<String,Boolean> resp	=	new	HashMap<>();
		resp.put("Deleted..", true);
		return ResponseEntity.ok(resp);
//		ResponseEntity<T>
	}
}
