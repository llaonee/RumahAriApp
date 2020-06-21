package com.bame.bameapp.Helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by imastudio on 2/9/16.
 */
public class SessionManager {
	private static final String KEY_TOKEN = "tokenLogin";
	private static final String KEY_LOGIN = "isLogin";
	SharedPreferences pref;
	SharedPreferences.Editor editor;

	int PRIVATE_MODE =0;    Context c;

	//0 agar cuma bsa dibaca hp itu sendiri
	String PREF_NAME="GojegClonePref";

	//konstruktor
	public SessionManager(Context c){
		this.c = c;
		pref = c.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = pref.edit();
	}
	//membuat session login
	public void createLoginSession(String token){
		editor.putString(KEY_TOKEN, token);
		editor.putBoolean(KEY_LOGIN, true);
		editor.commit();
		//commit digunakan untuk menyimpan perubahan
	}
	//mendapatkan token
	public String getToken(){
		return pref.getString(KEY_TOKEN, "");
	}
	//cek login
	public boolean isLoggedIn(){
		return pref.getBoolean(KEY_LOGIN, false);
	}
	//logout user
	public void logout(){
		editor.clear();
		editor.commit();
	}

	public void setUserId(int user_id){
		editor.putInt("userId", user_id);
		editor.commit();
	}
	public int getUserId(){
		return pref.getInt("userId", 0);
	}

	public void setUsername(String username){
		editor.putString("username", username);
		editor.commit();
	}
	public String getUsername(){
		return pref.getString("username", "");
	}


}
