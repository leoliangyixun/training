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
	List<FileItem> items=null;//�����������б����ݡ�
	//���ؼ���-->ֵ
	Map<String,List<String>> params=null;//����������ͨ�������ݣ�֮������List<String>������Ҫ��Ϊ�˴�����г��ֵĸ��ӿؼ����磺��ѡ��
	Map<String,FileItem> files=null;//�����������ͨ�����ݡ��磺�ļ���ͼ����ȡ�
	public HttpServletRequest request=null;
	@SuppressWarnings("unchecked")
	public FileUploadUtil( HttpServletRequest request) //���췽����
	{
		this.request=request;
		DiskFileItemFactory factory=new DiskFileItemFactory();
		factory.setRepository(new File(request.getServletContext().getRealPath("/tempdir")));
		factory.setSizeThreshold(1024*1024);
		ServletFileUpload upload=new ServletFileUpload(factory);
		upload.setFileSizeMax(maxSize);
		try {
			items=upload.parseRequest(request);//��ȡ�����ύ�ı����ݡ�
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		params=new HashMap<String, List<String>>();
		files=new HashMap<String, FileItem>();
		Iterator<FileItem> it=items.iterator();
		while(it.hasNext())
		{
			FileItem fileItem=it.next();//�����ύ�����ݶ����װ��FileItem����
			if(fileItem.isFormField())//��ͨ�����ݵĴ��� ��
			{	
				String name=fileItem.getFieldName();//��ȡ���������ơ�
				/*
				 * ע�⣺�Դ������������ԣ�getString()���ص��ļ���������·������Opera����������·������
				 * ��˻�Ӧ�öԻ�õ��ļ������д���
				 * */
				String value=fileItem.getString();//��ȡ��ֵ��
				/*
				 * ����һ��
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
				 * ��������
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
				params.put(name, temp_param);//�����ͬ����key���Զ����ǡ�
				*/
			}
			else{
				//�����Ԫ�ش����ϴ��ļ�������
				String name=fileItem.getFieldName();//��ȡ���������ơ�
				String fileName=fileItem.getName();//getName() �������Եõ��ļ����������ơ�
				if(!fileName.equals(""))//if(!"".equals(fileName))//û���ϴ���Ƭ�Ŀؼ����ԡ�
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
	public Map<String,String> saveAll(String savePath) //�����ϴ����ļ���ָ���ļ��С��ؼ���<------>�ÿؼ������ݡ�
	{
		Map<String,String> fileNamesMap=null;
		if(files.size()>0)
		{
			fileNamesMap=new HashMap<String,String>();
			Set<String> keys=files.keySet();//��ȡ���ؼ�����Set���ϡ�
			Iterator<String> it=keys.iterator();
			InputStream ins=null;
			OutputStream ous=null;
			while(it.hasNext())
			{	
				String key=it.next();
				FileItem item=files.get(key);//key--->���ؼ����������ļ�����
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
					while((data=ins.read(buff))!=-1)//�ֿ鱣�档
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
	 * Map������ֻ��һ��key��
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
	public String[] getUploadFileNames(String savePath) //1,�����ϴ����ļ���ָ���ļ���;2,�����ϴ��ļ����������顣
	{
		List<String> fileNames=null;
		if(files.size()>0)
		{
			fileNames=new ArrayList<String>();//
			Set<String> keys=files.keySet();//��ȡ���ؼ�����Set���ϡ�
			Iterator<String> it=keys.iterator();
			InputStream ins=null;
			OutputStream ous=null;
			while(it.hasNext())
			{	
				FileItem item=files.get(it.next());//it.next()--->���ؼ����������ļ�����
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
					while((data=ins.read(buff))!=-1)//�ֿ鱣�档
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
	
	public List<String> getUploadFileLists(String savePath) //��ȡ�ϴ��ļ����ļ������ϡ�
	{
		List<String> fileNames=null;
		if(files.size()>0)
		{
			fileNames=new ArrayList<String>();//
			Set<String> keys=files.keySet();//��ȡ���ؼ�����Set���ϡ�
			Iterator<String> it=keys.iterator();
			InputStream ins=null;
			OutputStream ous=null;
			while(it.hasNext())
			{	
				FileItem item=files.get(it.next());//it.next()--->���ؼ����������ļ�����
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
					while((data=ins.read(buff))!=-1)//�ֿ鱣�档
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

