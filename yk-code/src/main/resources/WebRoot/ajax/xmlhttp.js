var xmlhttp=null;
function createXMLHttpRequest()
{
     var objxmlhttp=null;
     var xmlhttpTypes=["Microsoft.XMLHTTP","Msxml2.XMLHTTP.3.0","Msxml2.XMLHTTP.4.0","Msxml2.XMLHTTP.5.0","Msxml2.XMLHTTP.6.0"];
     if(window.ActiveXObject)
     {
        for(var i=0;i<xmlhttpTypes.length;i++)
        {
        	try{
        			objxmlhttp=new ActiveXObject(xmlhttpTypes[i]);
        			break;
        	   }catch(e){continue;}
        }
     }
     else if(window.XMLHttpRequest)
     {
        objxmlhttp=new XMLHttpRequest();
     }
     return objxmlhttp;
}	