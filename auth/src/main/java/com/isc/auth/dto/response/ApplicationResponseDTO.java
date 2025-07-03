package com.isc.auth.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationResponseDTO {
	private Integer id;
	private String name;
	private String image;
	private String callback;
	private String description;
	private Boolean active;
	private LocalDateTime creationDate;
	private LocalDateTime lastModificationDate;
	private Integer gitlabRepositoryId;
	private String currentVersion;
	private Boolean visible;
	private Integer projectId;
}
