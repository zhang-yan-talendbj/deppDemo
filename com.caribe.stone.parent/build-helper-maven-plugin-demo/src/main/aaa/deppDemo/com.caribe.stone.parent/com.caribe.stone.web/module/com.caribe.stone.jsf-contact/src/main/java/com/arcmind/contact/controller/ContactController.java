package com.arcmind.contact.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UICommand;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.arcmind.contact.model.Contact;
import com.arcmind.contact.model.ContactRepository;
import com.arcmind.contact.model.Group;
import com.arcmind.contact.model.GroupRepository;
import com.arcmind.contact.model.Tag;
import com.arcmind.contact.model.TagRepository;

public class ContactController {
	/** Contact Controller collaborates with contactRepository. */
	private ContactRepository contactRepository;
	
	/** The current contact that is being edited. */
	private Contact contact = new Contact();
	
	/** Contact to remove. */
	private Contact selectedContact;
	
	/** The current form. */
	private UIForm form;
	
	/** Add new link. */
	private UICommand addNewCommand;
	
	/** Persist command. */
	private UICommand persistCommand;

	private GroupRepository groupRepository;
	
	private Long selectedGroupId;
	
	private Long[] selectedTagIds;

	private TagRepository tagRepository;
	
	
	/** For injection of collaborator. */
	public void setContactRepository(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}
	/** For injection of collaborator. */
	public void setGroupRepository(GroupRepository groupRepository) {
		this.groupRepository = groupRepository;
	}
	/** For injection of collaborator. */
	public void setTagRepository(TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}

	public void addNew() {
		form.setRendered(true);
		addNewCommand.setRendered(false);
		persistCommand.setValue("Add");
	}
	public void persist() {
		/* Setup the group into contact. */
		contact.setGroup(groupRepository.lookup(selectedGroupId));
		
		/* Setup the tags into contact. */
		List<Tag> tags = new ArrayList<Tag>(selectedTagIds.length);
		for (Long selectedTagId : selectedTagIds) {
			tags.add(tagRepository.lookup(selectedTagId));
		}
		contact.setTags(tags);
		
		/* Turn form off, turn link on. */
		form.setRendered(false);
		addNewCommand.setRendered(true);
		
		/* Add a status message. */
		if (contactRepository.persist(contact) == null) {
			addStatusMessage("Added " + contact);
		} else {
			addStatusMessage("Updated " + contact);
		}
	}
	public void remove() {
		contactRepository.remove(selectedContact);
		addStatusMessage("Removed " + selectedContact);	
	}
	public void read() {
		/* Prepare selected contact. */
		contact = selectedContact;
		
		/* Turn form on and the link off. */
		form.setRendered(true);
		addNewCommand.setRendered(false);

		/* Prepare selected group id. */
		selectedGroupId = contact.getGroup().getId();
		
		/* Prepare selected tag IDs. */
		List<Tag> tags = contact.getTags();
		List<Long> tagIds = new ArrayList<Long>(tags.size());
		for (Tag tag : tags) {
			tagIds.add(tag.getId());
		}
		this.selectedTagIds = tagIds.toArray(new Long[tags.size()]);
		
		addStatusMessage("Read " + contact);
		persistCommand.setValue("Update");
	}
	private void addStatusMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
				FacesMessage.SEVERITY_INFO, message, null));
	}

	
	public List<Contact> getContacts() {
		return contactRepository.list();
	}

	public List<SelectItem> getGroups() {
		List<Group> groups = groupRepository.list();
		List<SelectItem> list = new ArrayList<SelectItem>(groups.size()+1);
		list.add(new SelectItem(Long.valueOf(-1L), "select one"));
		for (Group group : groups) {
			SelectItem selectItem = new SelectItem(group.getId(), group.getName());
			list.add(selectItem);
		}
		return list;
	}
	
	public List<SelectItem> getAvailableTags() {
		List<Tag> tags = tagRepository.list();
		List<SelectItem> list = new ArrayList<SelectItem>(tags.size());
		for (Tag tag : tags) {
			SelectItem selectItem = new SelectItem(tag.getId(), tag.getName());
			list.add(selectItem);
		}
		return list;
	}
	
	public void cancel () {
		form.setRendered(false);
		addNewCommand.setRendered(true);		
	}

	/* ------------------- Just properties. ------------------------- */
	
	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public UIForm getForm() {
		return form;
	}

	public void setForm(UIForm form) {
		this.form = form;
	}

	public UICommand getAddNewCommand() {
		return addNewCommand;
	}

	public void setAddNewCommand(UICommand addNewCommand) {
		this.addNewCommand = addNewCommand;
	}

	public Contact getSelectedContact() {
		return selectedContact;
	}

	public void setSelectedContact(Contact selectedContact) {
		this.selectedContact = selectedContact;
	}	

	public UICommand getPersistCommand() {
		return persistCommand;
	}

	public void setPersistCommand(UICommand persistCommand) {
		this.persistCommand = persistCommand;
	}
	public Long getSelectedGroupId() {
		return selectedGroupId;
	}
	public void setSelectedGroupId(Long selectedGroupId) {
		this.selectedGroupId = selectedGroupId;
	}
	public Long[] getSelectedTagIds() {
		return selectedTagIds;
	}
	public void setSelectedTagIds(Long[] selectedTagIds) {
		this.selectedTagIds = selectedTagIds;
	}
	
}
