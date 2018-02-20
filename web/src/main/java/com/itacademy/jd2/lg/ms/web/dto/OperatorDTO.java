package com.itacademy.jd2.lg.ms.web.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OperatorDTO extends AbstractDTO {

	@NotNull
	@Size(min = 1, max = 40)
	private String title;

	private boolean deleted;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
