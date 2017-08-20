package com.yangkai.myblog.tools;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUploadUtil {
	public static final long maxSize=50*1024*1024;
	List<FileItem> items=null;//用来处理所有表单数据。
	//表单控件名-->值
	Map<String,List<String>> params=null;//用来处理普通表单的数据，之所以用List<String>集合主要是为了处理表单中出现的复杂控件，如：复选框。
	Map<String,FileItem> files=null;//用来处理非普通表单数据。如：文件域，图像域等。
	public HttpServletRequest request=null;
	@SuppressWarnings("unchecked")
	public FileUploadUtil( HttpServletRequest request) //构造方法。
	{
		this.request=request;
		DiskFileItemFactory factory=new DiskFileItemFactory();
		factory.setRepository(new File(request.getServletContext().getRealPath("/tempdir")));
		factory.setSizeThreshold(1024*1024);
		ServletFileUpload upload=new ServletFileUpload(factory);
		upload.setFileSizeMax(maxSize);
		try {
			items=upload.parseRequest(request);//获取所有提交的表单数据。
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		params=new HashMap<String, List<String>>();
		files=new HashMap<String, FileItem>();
		Iterator<FileItem> it=items.iterator();
		while(it.hasNext())
		{
			FileItem fileItem=it.next();//所有提交的数据都会封装成FileItem对象
			if(fileItem.isFormField())//普通表单数据的处理 。
			{	
				String name=fileItem.getFieldName();//获取表单对象名称。
				/*
				 * 注意：对大多数浏览器而言，getString()返回的文件名不包含路径，而Opera浏览器会包含路径名，
				 * 因此还应该对获得的文件名进行处理。
				 * */
				String value=fileItem.getString();//获取表单值。
				/*
				 * 方法一：
				 */
				if(params.containsKey(name))
				{
					if(!value.equals(""))
					{
						params.get(name).add(value);
					}
				}
				else{
					if(!value.equals(""))
					{
						List<String> param=new ArrayList<String>();
						param.add(value);
						params.put(name, param);
					}
				}
				/*
				 * 方法二：
				 */
				/*
				List<String> temp_param=null;
				if(params.containsKey(name))
				{
					temp_param=params.get(name);
				}
				else{
					temp_param=new ArrayList<String>();
				}
				temp_param.add(value);
				params.put(name, temp_param);//如果有同名的key会自动覆盖。
				*/
			}
			else{
				//特殊表单元素处理（上传文件处理）。
				String name=fileItem.getFieldName();//获取表单对象名称。
				String fileName=fileItem.getName();//getName() 方法可以得到文件名完整名称。
				if(!fileName.equals(""))//if(!"".equals(fileName))//没有上传照片的控件忽略。
				{
				 	files.put(name, fileItem);
				}
			}
		}
	}
	public String getParameter(String name)
	{
		List<String> param=params.get(name);
		//String desc=temp_desc.toString();
		if(param!=null)
		{
			return param.get(0);	
		}
		else{
			return null;
		}
	}
	public String[] getParameterValues(String name)
	{
		List<String> param=params.get(name);
		if(param!=null)
		{
			return param.toArray(new String[]{});
		}
		//String[] ints=temp_ints.toArray(new String[]{});//String[] ints=temp_ints.toArray(new String[0]);
		else{
			return null;
		}
	}
	
	@SuppressWarnings("unused")
	public Map<String,String> saveAll(String savePath) //保存上传的文件到指定文件夹。控件名<------>该控件的内容。
	{
		Map<String,String> fileNamesMap=null;
		if(files.size()>0)
		{
			fileNamesMap=new HashMap<String,String>();
			Set<String> keys=files.keySet();//获取表单控件名的Set集合。
			Iterator<String> it=keys.iterator();
			InputStream ins=null;
			OutputStream ous=null;
			while(it.hasNext())
			{	
				String key=it.next();
				FileItem item=files.get(key);//key--->表单控件名而不是文件名。
				String fileName=new FileRenameUtil(new Date()).getFileName(this.request.getRemoteAddr())
						+"."+item.getName().split("\\.")[1];
				fileNamesMap.put(key,fileName);
				try {
					ins=item.getInputStream();
					//ous=new FileOutputStream(savePath+File.separator+fileName);
					ous=new FileOutputStream(new File(savePath+File.separator+fileName));
					//ous=new FileOutputStream(new File(savePath));
					int data=0;
					byte[] buff=new byte[512];
					while((data=ins.read(buff))!=-1)//分块保存。
					{
						ous.write(buff);
					}		
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					try {
						ins.close();
						ous.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return fileNamesMap;
	}
	
	@SuppressWarnings("unused")
	/*
	 * Map集合里只有一个key。
	 * */
	public Map<String,String> saveImage(String savePath) 
	{
		Map<String,String> fileNamesMap=null;
		if(files.size()>0)
		{
			fileNamesMap=new HashMap<String,String>();
			Set<String> keys=files.keySet();
			Iterator<String> it=keys.iterator();
			InputStream ins=null;
			OutputStream ous=null;
			if(it.hasNext())
			{	
				String key=it.next();
				FileItem item=files.get(key);
				String fileName="me."+item.getName().split("\\.")[1];
				fileNamesMap.put(key,fileName);
				try {
					ins=item.getInputStream();
					File imageDir=new File(savePath);
					if(!imageDir.exists()){
						imageDir.mkdirs();
					}
					ous=new FileOutputStream(new File(savePath+File.separator+fileName));
					int data=0;
					byte[] buff=new byte[512];
					while((data=ins.read(buff))!=-1)
					{
						ous.write(buff);
					}		
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					try {
						ins.close();
						ous.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return fileNamesMap;
	}
	
	/*
	public String[] getUploadFileNames(String savePath) //1,保存上传的文件到指定文件夹;2,返回上传文件的名称数组。
	{
		List<String> fileNames=null;
		if(files.size()>0)
		{
			fileNames=new ArrayList<String>();//
			Set<String> keys=files.keySet();//获取表单控件名的Set集合。
			Iterator<String> it=keys.iterator();
			InputStream ins=null;
			OutputStream ous=null;
			while(it.hasNext())
			{	
				FileItem item=files.get(it.next());//it.next()--->表单控件名而不是文件名。
				String fileName=new FileRenameUtil(new Date()).getFileName(this.request.getRemoteAddr())
						+"."+item.getName().split("\\.")[1];
				fileNames.add(fileName);
				try {
					ins=item.getInputStream();
					//ous=new FileOutputStream(savePath+File.separator+fileName);
					ous=new FileOutputStream(new File(savePath+File.separator+fileName));
					//ous=new FileOutputStream(new File(savePath));
					int data=0;
					byte[] buff=new byte[512];
					while((data=ins.read(buff))!=-1)//分块保存。
					{
						ous.write(buff);
					}		
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					try {
						ins.close();
						ous.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}	
		}
		if(fileNames!=null)
		{
			String[] names=fileNames.toArray(new String[]{});
			return names;
			//return fileNames;
		}
		else{
			return null;
		}
	}
	
	public List<String> getUploadFileLists(String savePath) //获取上传文件的文件名集合。
	{
		List<String> fileNames=null;
		if(files.size()>0)
		{
			fileNames=new ArrayList<String>();//
			Set<String> keys=files.keySet();//获取表单控件名的Set集合。
			Iterator<String> it=keys.iterator();
			InputStream ins=null;
			OutputStream ous=null;
			while(it.hasNext())
			{	
				FileItem item=files.get(it.next());//it.next()--->表单控件名而不是文件名。
				String fileName=new FileRenameUtil(new Date()).getFileName(this.request.getRemoteAddr())
						+"."+item.getName().split("\\.")[1];
				fileNames.add(fileName);
				try {
					ins=item.getInputStream();
					//ous=new FileOutputStream(savePath+File.separator+fileName);
					ous=new FileOutputStream(new File(savePath+File.separator+fileName));
					//ous=new FileOutputStream(new File(savePath));
					int data=0;
					byte[] buff=new byte[512];
					while((data=ins.read(buff))!=-1)//分块保存。
					{
						ous.write(buff);
					}		
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					try {
						ins.close();
						ous.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return fileNames;
	}
	*/

}

