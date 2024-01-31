package com.springboot.todomanagement.service.impl;

import com.springboot.todomanagement.controller.ToDoController;
import com.springboot.todomanagement.dto.ToDoDto;
import com.springboot.todomanagement.entity.ToDo;
import com.springboot.todomanagement.exception.ResourceNotFoundException;
import com.springboot.todomanagement.repository.ToDoRepository;
import com.springboot.todomanagement.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToDoServiceImpl implements ToDoService {
    @Autowired
    private ToDoRepository toDoRepository;
    @Override
    public ToDoDto createToDo(ToDoDto toDoDto) {
        ToDo toDo = new ToDo();
        toDo.setTitle(toDoDto.getTitle());
        toDo.setDescription(toDoDto.getDescription());
        toDo.setCompleted(toDoDto.isCompleted());

        ToDo toDoRes = toDoRepository.save(toDo);

        return new ToDoDto(toDoRes.getId(),toDoRes.getTitle(),toDoRes.getDescription(),toDoRes.isCompleted());
    }

    @Override
    public ToDoDto getToDoById(Long id) {
        ToDo toDo = toDoRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("ToDo","Id",String.valueOf(id))
        );
        return new ToDoDto(toDo.getId(),toDo.getTitle(),toDo.getDescription(),toDo.isCompleted());
    }

    @Override
    public List<ToDoDto> getAllToDo() {
        List<ToDo> toDoList = toDoRepository.findAll();
        List<ToDoDto> toDoDtos = toDoList.stream()
                .map(toDo -> new ToDoDto(toDo.getId(),toDo.getTitle(),toDo.getDescription(),toDo.isCompleted()))
                .toList();
        return toDoDtos;
    }

    @Override
    public ToDoDto updateToDo(long id, ToDoDto toDoDto) {
        ToDo toDo = toDoRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("ToDo","Id",String.valueOf(id))
        );
        toDo.setCompleted(toDoDto.isCompleted());
        toDo.setDescription(toDoDto.getDescription());
        toDo.setTitle(toDoDto.getTitle());
        ToDo toDoResult = toDoRepository.save(toDo);
        ToDoDto toDoDtoRes =
                new ToDoDto(toDoResult.getId(),toDoResult.getTitle(),toDoResult.getDescription(),toDoResult.isCompleted());
        return toDoDtoRes;
    }

    @Override
    public void deleteToDo(long id) {
        toDoRepository.deleteById(id);
    }

    @Override
    public ToDoDto completeToDo(Long id) {
        ToDo toDo = toDoRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("ToDo","Id",String.valueOf(id))
        );
        toDo.setCompleted(true);
        ToDo toDoResult = toDoRepository.save(toDo);
        ToDoDto toDoDtoRes =
                new ToDoDto(toDoResult.getId(),toDoResult.getTitle(),toDoResult.getDescription(),toDoResult.isCompleted());
        return toDoDtoRes;
    }

    @Override
    public ToDoDto incompleteToDo(Long id) {
        ToDo toDo = toDoRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("ToDo","Id",String.valueOf(id))
        );
        toDo.setCompleted(false);
        ToDo toDoResult = toDoRepository.save(toDo);
        ToDoDto toDoDtoRes =
                new ToDoDto(toDoResult.getId(),toDoResult.getTitle(),toDoResult.getDescription(),toDoResult.isCompleted());
        return toDoDtoRes;
    }
}
