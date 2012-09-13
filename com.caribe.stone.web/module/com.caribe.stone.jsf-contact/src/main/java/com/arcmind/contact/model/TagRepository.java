package com.arcmind.contact.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class TagRepository{
	private Map<Long, Tag> tags = new LinkedHashMap<Long, Tag>(); 
	private static long counter = 1l;
	
	{
		Tag tag = new Tag("HTML");
		tag.id = counter++;
		tags.put(tag.id, tag);
		tag = new Tag("jsf");
		tag.id = counter++;
		tags.put(tag.id, tag);
		tag = new Tag("javascript");
		tag.id = counter++;
		tags.put(tag.id, tag);
		tag = new Tag("SQL");
		tag.id = counter++;
		tags.put(tag.id, tag);		
		
	}

	public List<Tag> list() {
		return new ArrayList<Tag>(tags.values());
	}

	public synchronized Tag persist(Tag Tag) {
		if (Tag.id == 0) {
			Tag.id = counter++;
		}
		return tags.put(Tag.id, Tag);
	}

	public synchronized void remove(Tag Tag) {
		tags.remove(Tag.id);
	}
	
	public Tag lookup(long id) {
		return this.tags.get(id);
	}
}
