package com.axis.springbootdemo.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.axis.springbootdemo.entity.Cricketer;

@RestController
public class MyController {
	
	
	private static ArrayList<Cricketer> crickList;
	static {
		crickList=new ArrayList<>();
		crickList.add(new Cricketer(1001,"suryakumar yadav",111,51,8,6,217.2));
		crickList.add(new Cricketer(1002,"Sewage",150,55,10,15,250.9));
		crickList.add(new Cricketer(1003,"sachin tendulakr",100,40,9,12,130.2));
		crickList.add(new Cricketer(1004,"goutham gambeeer",120,31,4,5,95.4));
	}
	
	@GetMapping("/message")
	public String getMessage() {
		return "Hello... first spring boot project..\n Good afternoon";
	}
	
	@GetMapping("/welcome")
	public String getWelcome() {
		return "Welcome to axis batch 9";
	}
	
	//get all cricketers
	@GetMapping("/cricketer")
	public ArrayList<Cricketer> getCricketer() {
		return crickList;
	}
	
	//get cricketer by id
	@GetMapping("/cricketer/{cricketerId}")
	public Cricketer getCricketerById(@PathVariable int cricketerId) {
		for(Cricketer ck:crickList) {
			if(ck.getCricketerId()==cricketerId) {
				return ck; //return cricketer if cricketer id is found
			}
		}
		return null;  //return if cricketer id is not found
	}
	
	@PostMapping("/cricketer")
	public ResponseEntity<String> addCricketer(@RequestBody Cricketer cricketer){
		crickList.add(cricketer);
		return new ResponseEntity<String>("Cricketer added successfully....",HttpStatus.OK);
	}
	
	//update request
	@PutMapping("/cricketer/update/{cricketerId}")
	public ResponseEntity<String> updateCricketer(@PathVariable int cricketerId,@RequestBody Cricketer updatedCricketer){
		if(cricketerId!=updatedCricketer.getCricketerId()) {
			return new ResponseEntity<String>("cricketer id's do not match !!!!",HttpStatus.BAD_REQUEST);
		}
		int index=crickList.indexOf(updatedCricketer);
		if(index==-1) {
			return new ResponseEntity<String>("Cricketer with id: "+cricketerId+" is not found",HttpStatus.NOT_FOUND);
		}else {
			crickList.get(index).setBalls(updatedCricketer.getBalls());
			crickList.get(index).setRunsScored(updatedCricketer.getRunsScored());
			crickList.get(index).setFours(updatedCricketer.getFours());
			crickList.get(index).setSixes(updatedCricketer.getSixes());
			crickList.get(index).setStrikeRate(updatedCricketer.getStrikeRate());
			return new ResponseEntity<String>("cricketer data updated successfully...",HttpStatus.OK);
			
		}
	}
	
	//Delete mapping
	@DeleteMapping("/cricketer/delete/{cricketerId}")
	public ResponseEntity<String> deleteCricketer(@PathVariable int cricketerId){
		Cricketer cricketer=getCricketerById(cricketerId);
		if(cricketer==null) {
			return new ResponseEntity<String>("cricketer with id : "+cricketerId+" is not found",HttpStatus.NOT_FOUND);
		}else {
			crickList.remove(cricketer);
			return new ResponseEntity<String>("cricketer with id : "+cricketerId+" deleted successfully...",HttpStatus.OK);
		}
	}
	

}
