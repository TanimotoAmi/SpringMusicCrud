package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Music;
import com.example.demo.repository.MusicCrudRepository;

@Service
public class MusicServiceImpl implements MusicService {
	@Autowired
	MusicCrudRepository repository;
	
	//全件取得
	@Override
	public Iterable<Music> findAll(){
		return repository.findAll();
	}
	@Override
	public Optional<Music> selectById(Integer id){
		return repository.findById(id);
	}
	
	//登録
	@Override
	public void Insert(Music music) {
		repository.save(music);
	}
	
	//更新
	@Override
	public void Update(Music music) {
		repository.save(music);
	}
	
	//削除
	@Override
	public void Delete(Integer id) {
		repository.deleteById(id);
	}
}
