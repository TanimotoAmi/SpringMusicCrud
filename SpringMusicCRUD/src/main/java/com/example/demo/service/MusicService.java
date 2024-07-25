package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.Music;

public interface MusicService {
	//情報取得
	public Iterable<Music> findAll();
	public Optional<Music> selectById(Integer id);
	//DBにinsert
	public void Insert(Music music);
	//update
	public void Update(Music music);
	//delete
	public void Delete(Integer id);
}
