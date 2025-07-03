package com.isc.auth.mapper;

import com.isc.auth.dto.response.ApplicationResponseDTO;
import com.isc.auth.entitys.ApplicationEntity;

public class ApplicationMapper {
	 public static ApplicationResponseDTO toDto(ApplicationEntity entity) {
	        if (entity == null) return null;
	        ApplicationResponseDTO application = new ApplicationResponseDTO();
	        application.setId(entity.getId());
	        application.setName(entity.getName());
	        application.setImage(entity.getImage());
	        application.setCallback(entity.getCallback());
	        application.setDescription(entity.getDescription());
	        application.setActive(entity.getActive());
	        application.setCreationDate(entity.getCreationDate());
	        application.setLastModificationDate(entity.getLastModificationDate());
	        application.setGitlabRepositoryId(entity.getGitlabRepositoryId());
	        application.setCurrentVersion(entity.getCurrentVersion());
	        application.setVisible(entity.getVisible());
	        application.setProjectId(entity.getProjectId());
	        return application;
	    }
}
