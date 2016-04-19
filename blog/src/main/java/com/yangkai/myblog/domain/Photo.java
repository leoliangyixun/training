package com.yangkai.myblog.domain;

import java.util.Date;

public class Photo {
	private Integer photo_id=null;
	private Integer album_id=null;
	private String photo_name=null;
	private String photo_desc=null;
	private Date photo_upload_date=null;
	public Photo(){
		
	}
	public Integer getPhotoid() 
	{
		return photo_id;
	}
	public void setPhoId(Integer photo_id) 
	{
		this.photo_id = photo_id;
	}
	public Integer getAlbumid() 
	{
		return album_id;
	}
	public void setAlbumid(Integer album_id) 
	{
		this.album_id = album_id;
	}
	public String getPhotoname() 
	{
		return photo_name;
	}
	public void setPhotoname(String photo_name) 
	{
		this.photo_name = photo_name;
	}
	public String getPhotodesc() 
	{
		return photo_desc;
	}
	public void setPhotodesc(String photo_desc) 
	{
		this.photo_desc = photo_desc;
	}
	public Date getPhotouploaddate() 
	{
		return photo_upload_date;
	}
	public void setPhotouploaddate(Date photo_upload_date) 
	{
		this.photo_upload_date = photo_upload_date;
	}
}
