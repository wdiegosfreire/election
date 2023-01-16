package br.com.compassuol.common.entities;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseEntity {
	private Map<String, Object> map;
}
