package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Music;
import com.example.demo.form.MusicForm;
import com.example.demo.service.MusicServiceImpl;

@Controller
public class MusicController {
	
	@Autowired
	MusicServiceImpl service;
	
	/* トップ */
	@GetMapping("index")
	public String topView() {
		return "top";
	}
	
	/* トップからの画面遷移 */
	@PostMapping(value = "/menu" , params="select")
	public String listView(Model model) {
		Iterable<Music> list = service.findAll();
		model.addAttribute("list",list);
		return "list";
	}
	@PostMapping(value = "/menu" , params="insert")
	public String InsertView() {
		return "Insert";
	}
	@PostMapping(value = "/menu" , params="update")
	public String UpdateView(Model model) {
		Iterable<Music> list = service.findAll();
		model.addAttribute("list",list);
		return "updatelist";
	}
	@PostMapping(value = "/menu" , params="delete")
	public String DeleteView(Model model) {
		Iterable<Music> list = service.findAll();
		model.addAttribute("list",list);
		return "delete";
	}
	
	/* 登録処理 */
	@PostMapping(value = "/insert")
	public String insertCompletionView(MusicForm f) {
		Music m = new Music(null,f.getSong_name(),f.getSinger());
		service.Insert(m);
		return "completion";
	}
	
	/* 更新処理 */
	//データを取得し、フォーム内に表示するのとこ
		@PostMapping(value = "/updateForm")
		public String updateFormView(Model model,MusicForm musicform,@RequestParam("Song_id")  Integer Song_id,Music music ,RedirectAttributes redirectAttributes) {
			//対象の情報取得
			Optional<Music> selectmusic = service.selectById(Song_id);
			//Formから詰めなおす
			Optional<MusicForm> MusicFormOpt = selectmusic.map(t -> makeMusicForm(t));
			//update画面に渡す
			musicform = MusicFormOpt.get();
			makeUpdateMusic(model,musicform);
			return "update";
		}
		private MusicForm makeMusicForm(Music t) {
			MusicForm music = new MusicForm();
			music.setSong_id(t.getSong_id());
			music.setSong_name(t.getSong_name());
			music.setSinger(t.getSinger());
			music.setSetNew(false);
			return music;
		}
	
		//更新用のModelを作成する
		private void makeUpdateMusic(Model model,MusicForm f) {
			model.addAttribute("id",f.getSong_id());
			f.setSetNew(false);
			model.addAttribute("Form",f);
		}
		//MusicForm から Music オブジェクトを生成する
		private Music makeUpdateMusic(MusicForm f) {
			return new Music(f.getSong_id(), f.getSong_name(), f.getSinger());
		}
	
		//idをキーにして更新のとこ
		@PostMapping(value = "/update")
		public String updateCompletionView(MusicForm f,Model model,RedirectAttributes redirectAttributes) {
			Music m = makeUpdateMusic(f);
			service.Update(m);
			return "completion";
		}
	
	/* 削除処理 */
		@PostMapping(value = "/delete")
		public String deleteCompletionView(Model model,@RequestParam("Song_id")  Integer Song_id,Music music) {
			service.Delete(music.getSong_id());
			return "completion";
		}

}
