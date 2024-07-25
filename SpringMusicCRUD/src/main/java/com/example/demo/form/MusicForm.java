package com.example.demo.form;

import lombok.Data;

@Data
public class MusicForm {
	private Integer song_id;
	private String song_name;
	private String singer;
	private boolean setNew;
}
