var xmlhttp=null;
function createXMLHttpRquest()
{
	var objxmlhttp;
	if(window.ActiveXObject)
	{
		try{
				objxmlhttp=new ActiveXObject("Msxml2.XMLHTTP");
		   }
		catch(e){
					try{
							objxmlhttp=new ActiveXObject("Msxml2.XMLHTTP.3.0");
					   }
					catch(e){
								try{
							  	    	objxmlhttp=new ActiveXObject("Msxml2.XMLHTTP.4.0");
								   }
								catch(e){
											try{
											   		objxmlhttp=new ActiveXObject("Msxml2.XMLHTTP.5.0");
											   }
											catch(e){
														try{
														   		objxmlhttp=new ActiveXObject("Msxml2.XMLHTTP.6.0");
														   }
														catch(e){
																	try{
														   		        	objxmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
														               }
																	catch(e){objxmlhttp=null;}			
																}
												    }
										}
						    }
				}
	}
	else if(window.XMLHttpRequest)
	{
		try{
		    	objxmlhttp=new XMLHttpRequest();
		   }
		catch(e){objxmlhttp=null;}
	}
	return objxmlhttp;
}
