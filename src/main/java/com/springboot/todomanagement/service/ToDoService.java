package com.springboot.todomanagement.service;

import com.springboot.todomanagement.dto.ToDoDto;
import com.springboot.todomanagement.entity.ToDo;

import java.util.List;

public interface ToDoService {
    ToDoDto createToDo(ToDoDto toDoDto);
    ToDoDto getToDoById(Long id);
    List<ToDoDto> getAllToDo();
    ToDoDto updateToDo(long id,ToDoDto toDoDto);
    void deleteToDo(long id);
    ToDoDto completeToDo(Long id);
    ToDoDto incompleteToDo(Long id);
}
