package com.arcmind.contact.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class GroupRepository{
	private Map<Long, Group> groups = new LinkedHashMap<Long, Group>(); 
	private static long counter = 1l;
	
	{
		Group group = new Group("friends");
		group.id = counter++;
		groups.put(group.id, group);
		group = new Group("family");
		group.id = counter++;
		groups.put(group.id, group);
		group = new Group("co-workers");
		group.id = counter++;
		groups.put(group.id, group);		
	}

	public List<Group> list() {
		return new ArrayList<Group>(groups.values());
	}

	public synchronized Group persist(Group group) {
		if (group.id == 0) {
			group.id = counter++;
		}
		return groups.put(group.id, group);
	}

	public synchronized void remove(Group group) {
		groups.remove(group.id);
	}
	
	public Group lookup(long id) {
		return this.groups.get(id);
	}
}
