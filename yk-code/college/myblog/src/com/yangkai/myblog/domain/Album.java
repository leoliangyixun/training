package com.yangkai.myblog.domain;

import java.util.Date;

public class Album 
{
	private Integer album_id;
	private String username;
	private String album_name;
	private String album_desc;
	private Date album_create_date;
	public Album() {
		
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getAlbumid() 
	{
		return album_id;
	}
	public void setAlbumid(Integer album_id) 
	{
		this.album_id = album_id;
	}
	public String getAlbumname() 
	{
		return album_name;
	}
	public void setAlbumname(String album_name) 
	{
		this.album_name = album_name;
	}
	public String getAlbumdesc() 
	{
		return album_desc;
	}
	public void setAlbumdesc(String album_desc) 
	{
		this.album_desc = album_desc;
	}
	public Date getAlbumcreatedate() 
	{
		return album_create_date;
	}
	public void setCreatedate(Date album_create_date) 
	{
		this.album_create_date = album_create_date;
	}

}
