package com.ksy.server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ksy.server.domain.ProjectTask;
import com.ksy.server.service.ErrorValidationService;
import com.ksy.server.service.ProjectTaskService;

@RestController
@RequestMapping("/api/task")
public class ProjectTaskController {

	@Autowired
	private ProjectTaskService projectTaskService;
	
	@Autowired
	private ErrorValidationService errorService;
	
	//task 정보 입력
	@PostMapping("/{userId}")
	public ResponseEntity<?> createNewTask(@Valid @RequestBody ProjectTask task,@PathVariable int userId , BindingResult result){
		
		ResponseEntity<?> errorMap = errorService.ValidationService(result);
		if(errorMap !=null) {
			return errorMap;
		}else {
		
		ProjectTask projectTask = projectTaskService.saveProject(task,userId);
		return new ResponseEntity<ProjectTask>(projectTask,HttpStatus.CREATED);
		}
	}
	
	//카테고리별 task 리스트 가져오기
	@GetMapping("/{category}/{userId}")
	public List<?> getTaskByCategory(@PathVariable String category,@PathVariable int userId){
		return projectTaskService.findByCategory(category, userId);
	}
	
	//task 디테일
	@GetMapping("/chosen/{id}") 
	public ResponseEntity<?> getTaskById(@PathVariable Long id){ 
		return new ResponseEntity<ProjectTask>(projectTaskService.getTaskById(id),HttpStatus.OK); 
		  }
	 

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTask(@PathVariable Long id){
		projectTaskService.deleteTaskById(id);
		return new ResponseEntity<String>("deleted",HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateTask(@Valid @RequestBody ProjectTask task,@PathVariable Long id,BindingResult result){
		ResponseEntity<?> errorMap = errorService.ValidationService(result);
		if(errorMap != null) {
			return errorMap;
		}else {
		return new ResponseEntity<ProjectTask>(projectTaskService.updateById(task, id),HttpStatus.OK);
		}
	}
}
