package edu.kh.todoList.todo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Todo {
	
	private int todoNo;
	private String todoTitle;
	private String todoMemo;
	private String todoDate;
	private String todoDeleteFlag;
}
