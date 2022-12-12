package com.fp.beans;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DropdownBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long count;
	private String kbName;
}
