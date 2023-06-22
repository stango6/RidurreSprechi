package com.generation.wasteless.entities;

import com.generation.wasteless.interfaces.IMapp;

public class Entity implements IMapp{
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "id: " + id;
	}
	
}