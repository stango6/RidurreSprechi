package com.generation.wasteless.interfaces;

import java.util.List;
import java.util.Map;

public interface IDao {

		void add(Map<String, String> map);
		List<Map<String, String>> read();
		void update(Map<String, String> map);
		void delete(int id);

	}


